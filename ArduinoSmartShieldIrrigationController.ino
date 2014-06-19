
/**
 ****************************************************************************************************************************
 * Irrigation Controller v2.4
 * Simple, elegant irrigation controller that takes advantage of the cloud and SmartThings ecosystem
 * Arduino UNO with SmartThings Shield  and an 8 Relay Module
 * Works by receiving irrigation run times from the Cloud and then builds a queue to execute
 * Will automatically shut off if power goes out and/or the queue finishes execution
 * Updates the Cloud if a station is queued and as each station turns on or off. 
 * By using the timer library, the CPU remains ready to process any change requests to the queue
 *
 ****************************************************************************************************************************
 * Libraries:
 * Timer library was created by Simon Monk as modified by JChristensen  https://github.com/JChristensen/Timer
 *   KNOWN LIBRARY BUG (timer.cpp) - identified by mattnichols 5/20/14 - has no known affect on the code below
 * SmartThings library available from https://www.dropbox.com/s/8hon320qmuio8fz/Shield%20Library.zip
 * SoftwareSerial library was default library provided with Arduino IDE
 *
 ****************************************************************************************************************************
 *  Copyright 2014 Stan Dotson (stan@dotson.info) and Matthew Nichols (matt@nichols.name)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 ****************************************************************************************************************************
 * Configuration:
 * Relays 1-8 are controlled by Arduino pins 12-6, respectively
 * 24V power source connects to common (middle) contacts on the relay
 * Irrigation valve wires use the normally open (NO) contacts on relay
 * This code works with "Active HIGH" relays, such as SeeedUino v2 relays.  
 * To use with "Acitve LOW" relays, such as SainSmart, set isActiveHigh to false
 ****************************************************************************************************************************
/// @note Arduino Pin Configuration
///               ______________
///               |              |
///               |         SW[] |
///               |[]RST         |
///               |         AREF |--
///               |          GND |--
///               |           13 |--X LED
///               |           12 |--Relay Signal Zone 1
///               |           11 |--Relay Signal Zone 2
///             --| 3.3V      10 |--Relay Signal Zone 3
/// Relay VCC   --| 5V         9 |--Relay Signal Zone 4
/// Relay Ground--| GND        8 |--Relay Signal Zone 5
///             --| GND          |
///             --| Vin        7 |--Relay Signal Zone 6
///               |            6 |--Relay Signal Zone 7
///             --| A0         5 |--Relay Signal Zone 8
///             --| A1    ( )  4 |--
///             --| A2         3 |--X THING_RX
///             --| A3  ____   2 |--X THING_TX
///             --| A4 |    |  1 |--
///             --| A5 |    |  0 |--
///               |____|    |____|
///                    |____|
///
//*****************************************************************************
/// @note typical relay configuration and hook up to valves
///              ---------------
///              |              |
///              |              |___
///              |     ( )      |   |  'NC' normally closed (not used)
///              |              |   *
///              |              |  /
///              |     ( )      | /   'comn' common (connect 24V power from transformer to here and daisy chain to remaining 7 relays)
///              |              |
///              |              |   *
///              |     ( )      |___|  'NO' normally open (connect sprinkler valve wire here)
///              |              |
///              ---------------
///
///              note: the ground from 24V transformer is connected to common ground running to sprinkler valves
/// ****************************************************************************************************************************
*/

#include <SoftwareSerial.h>   
#include <SmartThings.h>
#include <Event.h>
#include <Timer.h>
#define PIN_THING_RX    3
#define PIN_THING_TX    2

SmartThingsCallout_t messageCallout;    // call out function forward decalaration
SmartThings smartthing(PIN_THING_RX, PIN_THING_TX, messageCallout);  // constructor


//set global variables

Timer t;
int trafficCop =0;  //tracks which station has the right of way (is on)
boolean isDebugEnabled=true;    // enable or disable debug in this example
int stations=8; //number of stations or relays
boolean isActiveHigh=false; //set to true if using "active high" relay, set to false if using "active low" relay
int relayOn = HIGH;
int relayOff = LOW;
// initialize arrays; for readability, zone values store [1]-[8] and [0] is not used
long stationTime[] = {0,0,0,0,0,0,0,0,0};
int8_t stationTimer[] = {0,0,0,0,0,0,0,0,0}; 
int queue[]={0,0,0,0,0,0,0,0,0};  // off: 0, queued: 1, running: 2
int relay[9];  
boolean doUpdate = false;

void setup()
{
  //debug stuff
  if (isDebugEnabled) {
    // setup debug serial port
    Serial.begin(9600);         // setup serial with a baud rate of 9600
    Serial.println("setup..");  // print out 'setup..' on start
  }
  // set SmartThings Shield LED
  smartthing.shieldSetLED(0, 0, 1); // set to blue to start
  
  // setup timed actions 
  t.every(10L*60L*1000L, timeToUpdate); //send update to smartThings hub every 10 min
  t.every(10L*60L*1000L, queueManager);// check queue to see if there is work to do
  
  //set up relays to control irrigation valves
  if (!isActiveHigh) {
    relayOn=LOW;
    relayOff=HIGH;
  }
  int i=1;
  while (i<9) {
    relay[i]= 13-i;
    pinMode(relay[i], OUTPUT); 
    digitalWrite(relay[i], relayOff);
    i++;
  }
}

void loop() {
  
   //run timer 
  t.update();
  
  if (doUpdate) {
    sendUpdate("ok,");
    doUpdate = false;
  }
 
  //run smartthing logic
  smartthing.run();
  
}

//process incoming messages from SmartThings hub
void messageCallout(String message) 
{
  if (isDebugEnabled) {
    Serial.print("Received message: '");
    Serial.print(message);
    Serial.println("' "); 
  }
  char* inValue[stations+2]; //array holds any values being delivered with message (1-8) and NULL; [0] is not used
  char delimiters[] = ",";
  char charMessage[100];
  strncpy(charMessage, message.c_str(), sizeof(charMessage));
  charMessage[sizeof(charMessage) - 1] = '\0';
  inValue[0] = strtok(charMessage,delimiters);  //remove first substring as messageType
  int i=1;
  while(inValue [i-1] != NULL) {
    inValue [i] = strtok(NULL, delimiters); //remove remaining substrings as incoming values
    i++;
  }
  if (strcmp(inValue[0],"on")==0 && strcmp(inValue[2],"0")==1) {   // add new station to queue
    int addStation=atoi(inValue[1]);
    queue[addStation]=1;
    stationTime[addStation]=atol(inValue[2])*60L*1000L;
    scheduleUpdate();
  }
  if (strcmp(inValue[0],"off")==0) {
    int removeStation=atoi(inValue[1]);     
    if (removeStation==trafficCop)  {
      toggleOff();
    }
    //else remove from queue and update queue status to SmartThings
    queue[removeStation]=0;
    scheduleUpdate();
  }
  if (strcmp(inValue[0],"update")==0) {
    scheduleUpdate();
  }
  if (strcmp(inValue[0],"allOn")==0) {
    int i=1;
    while (i<stations+1) {
      if (i != trafficCop && (strcmp(inValue[i],"0")==1)) {
        queue[i]=1;
      }
      stationTime[i]=atoi(inValue[i])*60L*1000L;
      i++;
    }
    scheduleUpdate();
  }
  if (strcmp(inValue[0],"allOff")==0) {
    allOff();
  }
  queueManager();
}

void scheduleUpdate() {
  doUpdate = true; 
}
//run through queue to check to see if there is work to do
void queueManager() 
{
  int i=1;
  while (i<9) {
    if (trafficCop==0 && queue[i]==1) {
      //ready for next in line 
      trafficCop=i;
      toggleOn();
    }
    i++;
  }
}
void toggleOn() {
  queue[trafficCop]=2;
  smartthing.shieldSetLED(83, 1, 0); //Orange for relay one
  digitalWrite(relay[trafficCop], relayOn);
  t.stop(stationTimer[trafficCop]); // Kill any previously started timers.
  stationTimer[trafficCop] = t.after (stationTime[trafficCop],toggleOff);
  scheduleUpdate();
}
void toggleOff() {
  digitalWrite(relay[trafficCop], relayOff);
  smartthing.shieldSetLED(0, 0, 1);
  queue[trafficCop]=0; //remove relay from from queue
  
  trafficCop=0; //ready to check queue or watch for new commmonds
  scheduleUpdate();
}

//added allOff as requirement of SmartThings switch capability.  
void allOff() {
  int i=1;
  while(i<stations+1) {
    queue[i]=0;
    digitalWrite(relay[i], relayOff);
    i++;
  }
  smartthing.shieldSetLED(0, 0, 1);
  trafficCop=0;
  scheduleUpdate();
}

void timeToUpdate() {
  scheduleUpdate();
}

void sendUpdate(String statusUpdate) {
  // builds a status update to send to SmartThings hub
  String action="";
  for (int i=1; i<stations+1; i++) {
    
    if (queue[i]==0) {
      action="off";
    }
    if (queue[i]==1) {
      action="q";
    }
    if (queue[i]==2) {
      action="on";
    }
    statusUpdate.concat (action + i + ",");
  }
  smartthing.send(statusUpdate);
  statusUpdate = "";
}
