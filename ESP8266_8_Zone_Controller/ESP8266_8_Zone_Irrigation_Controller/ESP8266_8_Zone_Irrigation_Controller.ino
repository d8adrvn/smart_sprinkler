/**
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 * A Basic ESP8266 Device to Integrate with Samsung Smart Things
 * 
 *
 * Author: Jacob Schreiver 
 * Date: 2016-02-21
 *
 */

#include <ESP8266WiFi.h>
#include <DNSServer.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>
#include <WiFiManager.h> 
#include <ESP8266mDNS.h>
#include <ESP8266SSDP.h>
//#include <Event.h>
#include <Timer.h>


//const int ledZone1 = 13;    // Pin labeled GPIO13

//user configurable global variables to set before loading to Arduino
int relays = 4;  //set up before loading to Arduino (max = 8 with current code)
boolean isActiveHigh=false; //set to true if using "active high" relay, set to false if using "active low" relay
boolean isDebugEnabled=true;    // enable or disable debug in this example
boolean isPin4Pump =false;  //set to true if you add an additional relay to pin4 and use as pump or master valve.  

//set global variables
Timer t;
int trafficCop =0;  //tracks which station has the right of way (is on)
int stations=relays; //sets number of stations = number of relays. This is software configurable in device type
int relayOn = HIGH;
int relayOff = LOW;
// initialize irrigation zone variables; for readability, zone values store [1]-[8] and [0] is not used
long stationTime[] = {0,0,0,0,0,0,0,0,0};
int8_t stationTimer[] = {0,0,0,0,0,0,0,0,0}; 
int queue[]={0,0,0,0,0,0,0,0,0};  // off: 0, queued: 1, running: 2
int relay[9];

//initialize pump related variables. 
int pin4Relay = 16;  //pin16 reserved for optional additional relay to control a pump or master valve
boolean isConfigPump = false; // if true, relay8 is used as the pump/master valve switch.  Can be toggled by device tye v2.7 and later
const char* configPumpStatus = "off";
const char* pin4PumpStatus= "off";
boolean doUpdate = false;
boolean doPumpUpdate = false;
long pumpOffDelay = 0; // set number of minutes to delay turning pump or master valve off after last zone has finished running


ESP8266WebServer server(80);

int state = 1;

void handleRoot() {
  if (state == 1)
    server.send(200, "text/html", "<html><body>the device is on</body></html>");
  else
    server.send(200, "text/html", "<html><body>the device is off</body></html>");     
}

void handleOn() {
  //*****************ADD Custom Code Here**********************
  String runTime = server.arg("runTime");
  if (server.args() != 0 && server.arg(0) != "0" && server.arg(0) != "") { //&& strcmp(runTime,"null")!=0 {  //do not add to queue if zero time
    Serial.println("Print handleOn ");
    Serial.println(runTime);
    //int addStation=atoi(server.arg(1));
    //queue[addStation]=1;
    //stationTime[addStation]=atol(inValue[2])*60L*1000L;
  }
    //scheduleUpdate()
  //}
  
  //digitalWrite(ledZone1, HIGH);  
  state = 1;
  //****************End Turn on Device Code********************
  server.send(200, "text/html", "<html><body>on</body></html>");
  
}
void handleCommand() {
  //*****************ADD Custom Code Here**********************
  String message = server.arg("command");
  
  //if (server.args() != 0 && server.arg(0) != "0" && server.arg(0) != "") { //&& strcmp(runTime,"null")!=0 {  //do not add to queue if zero time
  ///  Serial.println("Print handleOn ");
  //  Serial.println(runTime);
    //int addStation=atoi(server.arg(1));
    //queue[addStation]=1;
    //stationTime[addStation]=atol(inValue[2])*60L*1000L;
  //}
    //scheduleUpdate()
  //}

  messageCallout(message);

  String updateStatus = "<html><body>";
  updateStatus.concat (makeUpdate("OK,"));
  updateStatus.concat ("</body></html>");
  
  //digitalWrite(ledZone1, HIGH);  
  state = 1;
  //****************End Turn on Device Code********************
  server.send(200, "text/html", updateStatus);
  
}

void handleOff() {
  //*****************ADD Custom Code Here**********************

  
 //digitalWrite(ledZone1, LOW);  
  state = 0;
  //****************End Turn off Device Code********************
  server.send(200, "text/html", "<html><body>off</body></html>");
}

void handleUpdate() {
  if (state == 1)
    server.send(200, "text/html", "<html><body>on</body></html>");
  else
    server.send(200, "text/html", "<html><body>off</body></html>");
}

void handleAllOn() {
  if (state == 1)
    server.send(200, "text/html", "<html><body>on</body></html>");
  else
    server.send(200, "text/html", "<html><body>off</body></html>");
}

void handleAllOff() {
  if (state == 1)
    server.send(200, "text/html", "<html><body>on</body></html>");
  else
    server.send(200, "text/html", "<html><body>off</body></html>");
}

void handleAdvance() {
  if (state == 1)
    server.send(200, "text/html", "<html><body>on</body></html>");
  else
    server.send(200, "text/html", "<html><body>off</body></html>");
}

void handlePump() {
  if (state == 1)
    server.send(200, "text/html", "<html><body>on</body></html>");
  else
    server.send(200, "text/html", "<html><body>off</body></html>");
}

void handleNotFound(){
  server.send(404, "text/html", "<html><body>Error! Page Not Found!</body></html>");
}




void setup(void){

//debug stuff
  if (isDebugEnabled) {
    // setup debug serial port
    Serial.begin(115200);         // setup serial with a baud rate of 9600
    Serial.println("setup..");  // print out 'setup..' on start
  }
  
    //WiFiManager
    //Local intialization. Once its business is done, there is no need to keep it around
  WiFiManager wifiManager;
    //reset saved settings
    //wifiManager.resetSettings();
    
    //set custom ip for portal
    //wifiManager.setAPConfig(IPAddress(10,0,1,1), IPAddress(10,0,1,1), IPAddress(255,255,255,0));

    //fetches ssid and pass from eeprom and tries to connect
    //if it does not connect it starts an access point with the specified name
    //here  "AutoConnectAP"
    //and goes into a blocking loop awaiting configuration
  wifiManager.autoConnect("AutoConnectAP");
    //or use this for auto generated name ESP + ChipID
    //wifiManager.autoConnect();

    
    //if you get here you have connected to the WiFi
  if (isDebugEnabled) {
    Serial.println("connected...yeey :)");
    Serial.println("local ip");
    Serial.println(WiFi.localIP());
  }
  
    
  //pinMode(ledZone1, OUTPUT);     // Initialize the ledZone1 pin as an output

  // ***irrigation setup
  // set SmartThings Shield LED
  //smartthing.shieldSetLED(0, 0, 1); // set to blue to start
  
  // setup timed actions 
  t.every(3*60L*1000L, queueManager);// double check queue to see if there is work to do
  t.every(10L*60L*1000L, timeToUpdate); //send update to smartThings hub every 10 min
 
  
  //set up relays to control irrigation valves
  if (!isActiveHigh) {
    relayOn=LOW;
    relayOff=HIGH;
  }

   relay[1]=12;
   relay[2]=13;
   relay[3]=14;
   relay[4]=16;
  
  int i=1;
  while (i<5) {
    //relay[i]= 13-i;
    pinMode(relay[i], OUTPUT); 
    digitalWrite(relay[i], relayOff);
    if (isDebugEnabled) {
      Serial.print("Turning off Relay ");
      Serial.println(relay[i]);
    }
    i++;
  }
 


  // set up optional hardware configured pump to pin4 on Arduino
  if (isPin4Pump) {
    pinMode(pin4Relay, OUTPUT);
    digitalWrite(pin4Relay, relayOff);
    if (isDebugEnabled) {
      Serial.println("Turning off Pump");
    }
  }
  
  server.on("/", handleRoot);
  server.on("/on", handleOn);
  server.on("/off", handleOff);
  server.on("/update", handleUpdate);
  server.on("/allOn", handleAllOn);
  server.on("/allOff", handleAllOff);
  server.on("/advance", handleAdvance);
  server.on("/pump", handlePump);
  server.on("/command", handleCommand);

  server.on("/esp8266.xml", HTTP_GET, [](){
    SSDP.schema(server.client());
  });
  
  server.onNotFound(handleNotFound);
  

  server.begin();

  SSDP.setSchemaURL("esp8266.xml");
  SSDP.setHTTPPort(80);
  SSDP.setName("ESP8266 Basic Switch");
  SSDP.setSerialNumber("0112358132134");
  SSDP.setURL("index.html");
  SSDP.setModelName("SmartThingsESP8266 Basic Switch");
  SSDP.setModelNumber("1");
  SSDP.setModelURL("https://github.com/SchreiverJ/SmartThingsESP8266/");
  SSDP.setManufacturer("Jacob Schreiver");
  SSDP.setManufacturerURL("https://github.com/SchreiverJ/SmartThingsESP8266/");
    
  SSDP.begin();
  
  //digitalWrite(ledZone1, LOW);
}

void loop(void){

  if (isDebugEnabled) {
    Serial.println("Starting loop");
  }
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
  //smartthing.run();
  
  
  server.handleClient();
  delay(1000);
  if (isDebugEnabled) {
    Serial.println("Ending loop");
  }
}

//process incoming messages from SmartThings hub
void messageCallout(String message) 
{
  if (isDebugEnabled) {
    Serial.print("Received message: '");
    Serial.print(message);
    Serial.println("' "); 
  }
  char* inValue[relays+2]; //array holds any values being delivered with message (1-8) and NULL; [0] is not used
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
//      stations = relays;
      if (stations == 7) {  
        stations = 8;
      }
    }
    if (strcmp(inValue[1],"1")==0) {
      //pumpOff();
    }
    if (strcmp(inValue[1],"2")==0) {
       //pumpOn();
    }
    if (strcmp(inValue[1],"3")==0) {
      isConfigPump = true;
      digitalWrite(relay[8], relayOff); 
      if (isDebugEnabled) {
        Serial.print("Turning off relay ");
        Serial.println(relay[8]);
      }
      configPumpStatus = "enabled";
//      stations = relays - 1;
      if (stations == 8) {  
        stations = 7;
      }
    }
    schedulePumpUpdate();
  }
  queueManager();
}

void scheduleUpdate() {
  if (isDebugEnabled) {
    Serial.println("Starting scheduleUpdate");
  }
  doUpdate = true; 
  if (isDebugEnabled) {
    Serial.println("Ending scheduleUpdate");
  }
}
void schedulePumpUpdate() {
  if (isDebugEnabled) {
    Serial.println("Starting schedulePumpUpdate");
  }  
  doPumpUpdate = true;
  if (isDebugEnabled) {
    Serial.println("Ending schedulePumpUpdate");
  }
}
//run through queue to check to see if there is work to do
void queueManager() 
{
  if (isDebugEnabled) {
    Serial.println("Starting queueManager");
  }
  
  int i=1;
  while (i<9) {
    if (trafficCop==0 && queue[i]==1) {
      //ready for next in line 
      trafficCop=i;
      toggleOn();
    }
    i++;
  }
  if (isDebugEnabled) {
    Serial.println("Ending queueManager");
  }
}

void toggleOn() {
  if (isDebugEnabled) {
    Serial.println("Starting toggleOn");
  }
  queue[trafficCop]=2;
  //smartthing.shieldSetLED(83, 1, 0); //Orange for relay one
  if (isConfigPump) {
    digitalWrite(relay[8], relayOn);
    if (isDebugEnabled) {
      Serial.print("Turn on Relay ");
      Serial.println(relay[8]);
    }
    configPumpStatus = "on";
  }
  if (isPin4Pump) {
    digitalWrite(pin4Relay, relayOn);
    if (isDebugEnabled) {
      Serial.println("Turning on Pump");
    }
    pin4PumpStatus = "on";
  }
  digitalWrite(relay[trafficCop], relayOn);
  if (isDebugEnabled) {
    Serial.print("Turning on Relay ");
    Serial.println(relay[trafficCop]);
  }

  t.stop(stationTimer[trafficCop]); // Kill any previously started timers.
  stationTimer[trafficCop] = t.after (stationTime[trafficCop],toggleOff);
  scheduleUpdate();
  schedulePumpUpdate();

  if (isDebugEnabled) {
    Serial.println("Ending toggleOn");
  }
}
void toggleOff() {
  if (isDebugEnabled) {
    Serial.println("Starting toggleOff");
  }
  digitalWrite(relay[trafficCop], relayOff);
  if (isDebugEnabled) {
    Serial.print("Turning off Relay ");
    Serial.println(relay[trafficCop]);
  }

  //smartthing.shieldSetLED(0, 0, 1);
  queue[trafficCop]=0; //remove relay from from queue
  if (maxvalue() ==0) {
    if (isConfigPump || isPin4Pump) {
      int8_t pumpOffEvent = t.after(pumpOffDelay*60L*1000L, pumpOff);  //turns off pump after delay
    }  
  }
  trafficCop=0; //ready to check queue or watch for new commmonds
  scheduleUpdate();
  schedulePumpUpdate();
  if (isDebugEnabled) {
    Serial.println("Ending toggleOff");
  }
}

//added allOff as requirement of SmartThings switch capability.  
void allOff() {
  if (isDebugEnabled) {
    Serial.println("Starting allOff");
  }
  int i=1;
  while(i<stations+1) {
    queue[i]=0;
    digitalWrite(relay[i], relayOff);
    if (isDebugEnabled) {
      Serial.print("Turning off Relay ");
      Serial.println(relay[i]);
    }

    i++;
  }
  if (isConfigPump || isPin4Pump) {
    int8_t pumpOffEvent = t.after(pumpOffDelay*60L*1000L, pumpOff);  //turns off pump after delay
  } 
  //smartthing.shieldSetLED(0, 0, 1);
  trafficCop=0;
  scheduleUpdate();
  schedulePumpUpdate();
  if (isDebugEnabled) {
    Serial.println("Ending allOff");
  }
}

void pumpOff() {
  if (isDebugEnabled) {
    Serial.println("Starting pumpOff");
  }
  if (isConfigPump) { 
      digitalWrite(relay[8], relayOff); 
      if (isDebugEnabled) {
        Serial.println("Turning off Pump");
      }

      configPumpStatus = "off";
  }
  if (isPin4Pump) {
      digitalWrite(pin4Relay, relayOff);
      if (isDebugEnabled) {
        Serial.println("Turning off Pump");
      }

      pin4PumpStatus="off";
  }
  schedulePumpUpdate();
  if (isDebugEnabled) {
    Serial.println("Ending pumpOff");
  }
}

void pumpOn() {
  if (isDebugEnabled) {
    Serial.println("Starting pumpOn");
  }
  if (isConfigPump) { 
      digitalWrite(relay[8], relayOn); 
      if (isDebugEnabled) {
        Serial.println("Turn on Pump");
      }

      configPumpStatus = "on";
  }
  if (isPin4Pump) {
      digitalWrite(pin4Relay, relayOn);
      if (isDebugEnabled) {
        Serial.println("Turn on Pump");
      }

      pin4PumpStatus = "on";
  }
  if (isDebugEnabled) {
    Serial.println("Ending pumpOn");
  }
}

void timeToUpdate() {
  if (isDebugEnabled) {
    Serial.println("Starting timeToUpdate");
  }
  scheduleUpdate();
  schedulePumpUpdate();
  if (isDebugEnabled) {
    Serial.println("Ending timeToUpdate");
  }
}

int maxvalue () {
  if (isDebugEnabled) {
    Serial.println("Starting maxvalue");
  }
  int mxm = 0;
  int i=1;
  for (i=1; i<stations+1; i++) {
    if (queue[i]>mxm) {
      mxm = queue[i];
    }
  }
  return mxm;
  if (isDebugEnabled) {
    Serial.println("Ending maxvalue");
  }
};
void sendUpdate(String statusUpdate) {
  if (isDebugEnabled) {
    Serial.println("Starting sendUpdate");
  }
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
  if (isDebugEnabled) {
    Serial.print("Sending Update Message ");
    Serial.println(statusUpdate);
  }
  //smartthing.send(statusUpdate);
  statusUpdate = "";
  if (isDebugEnabled) {
    Serial.println("Ending sendUpdate ");
  }
}

String makeUpdate(String statusUpdate) {
  if (isDebugEnabled) {
    Serial.println("Starting makeUpdate");
  }
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
  if (isDebugEnabled) {
    Serial.print("Created Update Message ");
    Serial.println(statusUpdate);
  }
    if (isDebugEnabled) {
    Serial.println("Ending makeUpdate ");
  }
  return statusUpdate;
}

void sendPumpUpdate() {
  if (isDebugEnabled) {
    Serial.println("Starting sendPumpUpdate");
  }
  if (isConfigPump && configPumpStatus == "enabled") {
    //smartthing.send("pumpAdded");
  }
  if (!isConfigPump && !isPin4Pump) {
    //smartthing.send("pumpRemoved");
  }
  if (configPumpStatus == "on" || pin4PumpStatus == "on") {
    //smartthing.send("onPump");
  }
  if (configPumpStatus =="off" && isConfigPump) {
    //smartthing.send("offPump");
  }
  if (pin4PumpStatus =="off" && isPin4Pump) {
    //smartthing.send("offPump");
  }
  if (isDebugEnabled) {
    Serial.println("Ending sendPumpUpdate");
  }
}
