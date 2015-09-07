
/**
* 
 ****************************************************************************************************************************
 * Irrigation Controller 16 Zones With Options for Master Valve and Pump  v1
 * Simple, elegant irrigation controller that takes advantage of the cloud and SmartThings ecosystem
 * Arduino MEGA with SmartThings Shield  and 16 relay modules
 * Works by receiving irrigation run times from the Cloud and then builds a queue to execute
 * Will automatically shut off if power goes out and/or the queue finishes execution
 * Updates the Cloud if a station is queued and as each station turns on or off. 
 * By using the timer library, the CPU remains ready to process any change requests to the queue
 * Allocates relay 16 to run a master valve or pump
 * 
 *
 ****************************************************************************************************************************
 * Libraries:
 * Timer library was created by Simon Monk as modified by JChristensen  https://github.com/JChristensen/Timer.  Note: if you
 * download the library from the source, you must rename the zip file to "Timer" before importing into the Arduino IDE.
 * 
 * An enhanced SmartThings Library was created by  Dan G Ogorchock & Daniel J Ogorchock and their version is required for this implementation.
 * Their enhanced library can found at:
 * https://github.com/DanielOgorchock/ST_Anything/tree/master/Arduino/libraries/SmartThings
 *
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
 * ArduinoMEGA Configuration:
 * Relays 1-16 are controlled by ArduinoMega pins 21-36, respectively
 * An optiona (17th) relay can be added and controlled by pin4 to give 16 zones + master pump valve
 * 24V AC power source to run irrigation valves connects to common (middle) contacts on the relay
 * Irrigation valve wires use the normally open (NO) contacts on relay
 * This code works with "Active HIGH" relays, such as SeeedUino v2 relays.  
 * To use with "Acitve LOW" relays, such as SainSmart, set isActiveHigh to false
 ****************************************************************************************************************************
/// ArduinoMEGA Pin Configuration (rough)
///               ______________
///               |              |
///               |         SW[] |
///               |[]RST         |
///               |         AREF |--
///               |          GND |--
///               |           13 |--
///               |           12 |--
///               |           11 |--
///             --| 3.3V      10 |----|  Run jumper from pin10 to pin3
/// Relay VCC   --| 5V         9 |--  |
/// Relay Ground--| GND        8 |--  |
///             --| GND          |    |
///             --| Vin        7 |--  |
///               |            6 |--  |
///             --| A0         5 |--  |
///             --| A1    ( )  4 |--  |  pin4 Optional Pump/Master Valve 
///             --| A2         3 |----|  pin3 THING_RX
///             --| A3         2 |--     pin2 THING_TX
///             --| A4         1 |--
///             --| A5        14 |--
///             --| A6        15 |--
///             --| A7        16 |--
///             --| A8        17 |--
///             --| A9        18 |--
///             --| A10       19 |--
///             --| A11       20 |--
///             --| A12       21 |--
///             --| A13          |
///             --| A14          |
///             --| A15          |
///               |_  (54->22)  _|
///                         
///               
///                    
///
//*****************************************************************************
/// @note typical relay with normally open (NO) and normally closed (NC) options
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
/// Typical finished wiring diagram for 16 zones
///
/// Warning: Connecting a Pump Start Relay is potentially dangerous 
/// Warning: Pumps run on 120V or 220V power and incorrect wiring could result in lethal shock or fire hazard!
/// Warning: Work is best done by licensed electrician following local codes.
///
/// Common terminals are daisy chained together.
/// Sprinkler load wires are connected to NO of respective relays
/// Ground from Sprinkler power supply is tied to the Common Ground Wire from Irrigation Wire Bundle
///
///
///         wire [ --from 24V AC Sprinkler Power Supply (-)
///         nut  [ --Common Ground wire from Irrigation Wire Bundle (-)
///              ---------------    
///              |           NC |   
///              |   1      CMN |===|---from 24V Sprinkler Power Supply (+)
///              |           NO |---]---to valve 1
///              ---------------    |
///              ---------------    |
///              |           NC |   |
///              |   2      CMN |---|
///              |           NO |---]---to valve 2
///              ---------------    |
////              ---------------   |
///              |           NC |   |
///              |   3      CMN |---|
///              |           NO |---]---to valve 3
///              ---------------    |
///              ---------------    |
///              |           NC |   |
///              |   4      CMN |---|
///              |           NO |---]---to valve 4
///              ---------------    |
///              ---------------    |
///              |           NC |   |
///              |   5      CMN |---|
///              |           NO |---]---to valve 5
///              ---------------    |
///              ---------------    |
///              |           NC |   |
///              |   6      CMN |---|
///              |           NO |---]---to valve 6
///              ---------------    |
///              ---------------    |
///              |           NC |   |
///              |   7      CMN |---|
///              |           NO |---]---to valve 7
///              ---------------    |
///                                 |
///    Keep wiring until you have wired all16 relays
///                                 |
///                                 |
///              ---------------    |
///              |           NC |   |
///              |   16     CMN |---|
///              |           NO | --]---to valve 16   
///              ---------------    
///                                 
///             //////////////////
///
///             You can do 2X8 relays or 1X16  relays 
///             Just keep them in order and be sure to connect the +5V to VCC and GND to GND on each relay unit
///             Also, its recommended to run the relays on auxillary power (use DC current to power relay)
///             The auxillary DC relay power is connected on JD-VCC or other indicated location
///             Depending on the relay board(s) you have, the relay auxillary power may be 5V, 12V or 24V DC.  This simplist solution
///             is to purchase and use 5V relays and power them directly from the Arduino.
///
///
/// ****************************************************************************************************************************
/// Option:  Software Enabled Pump or Master Valve Can Be Connected to Relay 16
///
/// Warning: Connecting a Pump Start Relay is potentiall dangerous 
/// Warning: Pumps run on 120V or 220V power and incorrect wiring could result in lethal shock or fire hazard!
/// Warning: Work is best done by licensed electrician following local codes.
///
/// After wiring the optional master valve or pump to zone 16, you must configure the device type to recognize the pump/master valve
/// In the device type, activate the pump tile which will deactivate irrigation zone 16 tile.
/// Common terminals are daisy chained together.
/// Sprinkler load wires are connected to NO of respective relays
/// Ground from Sprinkler power supply is tied to the Common Ground Wire from Irrigation Wire Bundle
/// Software switchable pump/master valve is wired to relay 16.   
///
///
///         wire {--from 24V Sprinkler Power Supply (-)
///          nut {--Common Ground wire from Irrigation Wire Bundle (-)
///     together {--Common from Pump Start Relay or Master Vavle 
///
///             //////////////////  |
///                                 |
///              ---------------    |  
///              |           NC |   | 
///              |   16     CMN |---|
///              |           NO |--- to Pump Start Relay or Master Vavle  (low voltage only!!!)
///              ---------------    
///              **In the device type, activate the pump tile which will automagically deactivate irrigation zone 16 tile.
///
/// ****************************************************************************************************************************
///
///             //////////////////  |
///                                 |
///      Optional: Add an extra     |
                   Relay for        |
///                Master Pump      |
///              ---------------    |
///     pin4  -- | IN1       NC |   |
///    +5V    -- | VCC      CMN | --| 
///     GND   -- | GND       NO | --- to Master Pump Relay (low voltage only!!!!)
///              ---------------
///
/// ****************************************************************************************************************************
*/

#include <SoftwareSerial.h>   
#include <SmartThings.h>  //be sure you are using the library from ST_ANYTHING which has support for the Arduino Mega
#include <Event.h>
#include <Timer.h>
#define PIN_THING_RX    10
#define PIN_THING_TX    2
#define PIN_THING_LED  13
SmartThingsCallout_t messageCallout;    // call out function forward decalaration
SmartThings smartthing(PIN_THING_RX, PIN_THING_TX, messageCallout);  // constructor

//user configurable global variables to set before loading to Arduino
int relays = 16;  //set up before loading to Arduino (max = 16 with current code and assumes last relay is a master valve/pump actuator)
boolean isActiveHigh=false; //set to true if using "active high" relay, set to false if using "active low" relay
boolean isDebugEnabled=false;    // enable or disable debug in this example  
boolean isPin4Pump =false;  //set to true if you add an additional relay to pin4 and use as pump or master valve.  

//set global variables
Timer t;
int trafficCop =0;  //tracks which station has the right of way (is on)
int stations=relays; //sets number of stations reserving 1 relay for the pump.
int relayOn = HIGH;
int relayOff = LOW;
// initialize irrigation zone variables; for readability, zone values store [1]-[23] and [0] is not used
long stationTime[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
int8_t stationTimer[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; 
int queue[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};  // off: 0, queued: 1, running: 2
int relay[17];  //for readability, zone values store [1]-[16] and [0] is not used

//initialize pump related variables. 
int pin4Relay = 4;  //pin4 reserved for optional additional relay to control a pump or master valve
boolean isConfigPump = false; // if true, relay16 is used as the pump/master valve switch.  Can be toggled by device tye v2.7 and later
char* configPumpStatus = "off";
char* pin4PumpStatus= "off";
boolean doUpdate = false;
boolean doPumpUpdate = false;

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
  t.every(3*60L*1000L, queueManager);// double check queue to see if there is work to do
  t.every(60L*60L*1000L, timeToUpdate); //send update to smartThings hub every 60 min
 
  
  //check which type of relay is being addressed
  if (!isActiveHigh) {
    relayOn=LOW;
    relayOff=HIGH;
  }
  
  //set up relays to control irrigation valves
  int i=1;
  while (i<(relays+1)) {
    relay[i]= 20+i;
    pinMode(relay[i], OUTPUT); 
    digitalWrite(relay[i], relayOff);
    i++;
  }
  
  // set up optional hardware configured pump to pin4 on Arduino
  if (isPin4Pump) {
    pinMode(pin4Relay, OUTPUT);
    digitalWrite(pin4Relay, relayOff);
  }
}

void loop() {
  
   //run timer 
  t.update();
  
  if (doUpdate) {
    sendUpdate("ok,");
    doUpdate = false;
  }
  if (doPumpUpdate) {
    sendPumpUpdate();
    doPumpUpdate = false;
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
  char* inValue[stations+2]; //array holds any values being delivered with message (1-16) and NULL; [0] is not used
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
  if (strcmp(inValue[0],"on")==0)  {   // add new station to queue
    if (strcmp(inValue[2],"0")!=0 && strcmp(inValue[2],"null")!=0) {  //do not add to queue if zero time
      int addStation=atoi(inValue[1]);
      queue[addStation]=1;
      stationTime[addStation]=atol(inValue[2])*60L*1000L;
    }
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
      if (i != trafficCop) {
        if (strcmp(inValue[i],"0")!=0 && strcmp(inValue[i],"null")!=0) {  //do not add to queue if zero time
          queue[i]=1;
        }
      }
      stationTime[i]=atoi(inValue[i])*60L*1000L;
      i++;
    }
    scheduleUpdate();
  }
  if (strcmp(inValue[0],"allOff")==0) {
    allOff();
  }
  if (strcmp(inValue[0],"advance")==0) {
    toggleOff();
  }
  if (strcmp(inValue[0],"pump")==0) {
    if (strcmp(inValue[1],"0")==0) {
      isConfigPump = false;
      stations = relays;
    }
    if (strcmp(inValue[1],"1")==0) {
      pumpOff();
    }
    if (strcmp(inValue[1],"2")==0) {
       pumpOn();
    }
    if (strcmp(inValue[1],"3")==0) {
      isConfigPump = true;
      digitalWrite(relay[16], relayOff); 
      configPumpStatus = "enabled";
      stations = relays - 1;
    }
    schedulePumpUpdate();
  }
  queueManager();
}

void scheduleUpdate() {
  doUpdate = true; 
}
void schedulePumpUpdate() {
  doPumpUpdate = true;
}
//run through queue to check to see if there is work to do
void queueManager() 
{
  int i=1;
  while (i<25) {
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
  if (isConfigPump) {
    digitalWrite(relay[16], relayOn);
    configPumpStatus = "on";
  }
  if (isPin4Pump) {
    digitalWrite(pin4Relay, relayOn);
    pin4PumpStatus = "on";
  }
  digitalWrite(relay[trafficCop], relayOn);
  t.stop(stationTimer[trafficCop]); // Kill any previously started timers.
  stationTimer[trafficCop] = t.after (stationTime[trafficCop],toggleOff);
  scheduleUpdate();
  schedulePumpUpdate();
}
void toggleOff() {
  digitalWrite(relay[trafficCop], relayOff);
  smartthing.shieldSetLED(0, 0, 1);
  queue[trafficCop]=0; //remove relay from from queue
  if (isConfigPump== true) {
    if (maxvalue() ==0) {
      digitalWrite(relay[16], relayOff);
      configPumpStatus = "off";
    }
  }
  if (isPin4Pump) {
    if (maxvalue() ==0) {
      digitalWrite(pin4Relay, relayOff);
      pin4PumpStatus = "off";
    }
  }  
  trafficCop=0; //ready to check queue or watch for new commmonds
  scheduleUpdate();
  schedulePumpUpdate();
}

//added allOff as requirement of SmartThings switch capability.  
void allOff() {
  int i=1;
  while(i<stations+1) {
    queue[i]=0;
    digitalWrite(relay[i], relayOff);
    i++;
  }
   if (isConfigPump) {
     digitalWrite(relay[16], relayOff);
     configPumpStatus="off";
  } 
  
  if (isPin4Pump) {
    digitalWrite(pin4Relay, relayOff);
    pin4PumpStatus="off";
  }
  smartthing.shieldSetLED(0, 0, 1);
  trafficCop=0;
  scheduleUpdate();
  schedulePumpUpdate();
}

void pumpOff() {
  if (isConfigPump) { 
      digitalWrite(relay[16], relayOff); 
      configPumpStatus = "off";
  }
  if (isPin4Pump) {
      digitalWrite(pin4Relay, relayOff);
      pin4PumpStatus="off";
  }
}

void pumpOn() {
  if (isConfigPump) { 
      digitalWrite(relay[16], relayOn); 
      configPumpStatus = "on";
  }
  if (isPin4Pump) {
      digitalWrite(pin4Relay, relayOn);
      pin4PumpStatus = "on";
  }
}

void timeToUpdate() {
  scheduleUpdate();
  schedulePumpUpdate();
}

int maxvalue () {
  int mxm = 0;
  int i=1;
  for (i=1; i<stations+1; i++) {
  if (queue[i]>mxm) {
  mxm = queue[i];
  }
}
return mxm;
};
void sendUpdate(String statusUpdate) {
  // builds a status update to send to SmartThings hub
  String action="";
  for (int i=1; i<(stations)+1; i++) {
    if (queue[i]==0) {
      action="o";  //off
    }
    if (queue[i]==1) {
      action="q";  //queued
    }
    if (queue[i]==2) {
      action="r"; //running
    }
    statusUpdate.concat (action + i + ",");
  }
  smartthing.send(statusUpdate);
  Serial.println(statusUpdate);
  statusUpdate = "";
}

void sendPumpUpdate() {
  if (isConfigPump && configPumpStatus == "enabled") {
    smartthing.send("pumpAdded");
  }
  if (!isConfigPump && !isPin4Pump) {
    smartthing.send("pumpRemoved");
  }
  if (configPumpStatus == "on" || pin4PumpStatus == "on") {
    smartthing.send("onPump");
  }
  if (configPumpStatus =="off" && isConfigPump) {
    smartthing.send("offPump");
  }
  if (pin4PumpStatus =="off" && isPin4Pump) {
    smartthing.send("offPump");
  }
}
