## **ESP8266 Smart Sprinkler**

### **A project by Aaron Nienhuis (aaron.nienhuis@gmail.com)**

## Introduction

I've started a port of the Smart Sprinkler project to use the ESP8266 microcontroller.  The ESP8266 family offers a range of chipset options and are extremely affordable (Total cost of my controller is < $15).  Unlike the Arduino based SmartThings Shield which communicated with the SmartThing hub via ZigBee, the ESP8266 is WIFI enabled and communicates with the hub over the local LAN.

You can control your lawn irrigation directly from your smartphone via the SmartThings app.  Also, you can set up as many schedules as you like to precisely control your lawn irrigation.  Hyperlocal weather forecasts make sure you save water when it rains!!!


## Background

Stan Dotson (stan@dotson.info) and Matthew Nichols (matt@nichols.name) started the Smart Sprinkler project several years ago using Arduino and the SmartThings Shield.  When Samsung discontinued the SmartThings Shield, the parts to build the project became difficult to find.  The original Smart Sprinkler project is located at:
* [Smart Sprinkler](https://github.com/d8adrvn/smart_sprinkler)


## Description

This project contains the ESP8266 code and the groovy code for the SmartThings SmartApps and Device Types.  

#SmartApps

There are two SmartApps that need to be installed:

ESP8266Smart_Sprinkler_Discovery.groovy
ESP8266Smart_Sprinkler_Scheduler.groovy

The first is a parent SmartApp that will discover ESP8266 Smart Sprinkler devices and add them to SmartThings.  The second is a Child SmartApp that allows schedules to be created for running the sprinklers.

#Device Types
Two device types have been written so far.  One for a 4 zone controller, and one for an 8 zone controller.  Both can be installed and the Discovery SmartApp will determine which to install based on the device discovered.

Smart_Sprinkler_4_Zone_Irrigation_Controller.groovy
Smart_Sprinkler_8_Zone_Irrigation_Controller.groovy


#ESP8266 Firmware
The source for the firmware was developed in the Arduino IDE. The source as well as a compiled binary is provided.

ESP8266_4_Zone_Irrigation_Controller.ino
ESP8266_4_Zone_Irrigation_Controller.ino.generic.bin

ESP8266_8_Zone_Irrigation_Controller.ino
ESP8266_8_Zone_Irrigation_Controller.ino.generic.bin



###Project Features
* Automated discovery of controllers via SmartApp
* Over-the-air (OTA) updates of controller firmware
* Build your own irrigation controller for SmartThings
* Flexibility to manage 1-8 irrigation zones
* Directly control from your iPhone
* Create one or more schedules to automatically run the irrigation system
* Easily over-ride the schedule
* Option to control a master relay or pump
* A virtual rain guage that uses local weather stations to measure recent and forecasted rain and skip irrigation when rain exceeds a threshhold
* A virtual temperature guage gives you flexibility to set minimum temperature thresholds to initiate an irrigation
* Voice controls via the SmartThings integration with Alexa (Amazon Echo)

## High Level Instructions

Install the two SmartApps and the two Device Handlers using the SmartThings IDE.

Flash the firmware onto an ESP8266 microcontroller using ESPEasy or the Arduino IDE.

Once flashed, the ESP8266 controller will start a WIFI Access Point named SmartSprinkler.  Connect to the AP using a WIFI device (phone, laptop, etc.)  If the device is not automatically redirected, use a web browser to connect to http://192.168.4.1.  This web page will show all detected WIFI SSIDs.  Select the SSID that is on the same local network as the SmartThings Hub and enter the password for that SSID.

The controller will then connect to that WIFI AP using DHCP.

Using the SmartThings Phone App, start the SmartApp called Smart Sprinkler (Connect).  Use this SmartApp to discover all Smart Sprinkler controllers on the network and create Irrigation schedules for the controller(s).

Additional instructions on scheduling, wiring the controller to your sprinkler valves, etc. can be found on the original Smart Sprinkler project.

*[Smart Sprinkler](https://github.com/d8adrvn/smart_sprinkler)

#Important Note!!!  To turn on individual Sprinkler Zones using the SmartThings App rather than a schedule, individual zone times must be entered in the preferences for the controller.


##Controller Hardware Options
	
There are a large variety of ESP8266 based options including prefabricated boards with relays that can be used for Sprinkler Controllers.  The two products I'm currently using are the LinkNode R4 and LinkNode R8.  Unfortunately, the R8 was not well designed and only 7 of the 8 relays can be used.  The links below provide detailed product specs and links to 3D print enclosures.

*[LinkNode R4](http://linksprite.com/wiki/index.php5?title=LinkNode_R4:_Arduino-compatible_WiFi_relay_controller)

![LinkNode R4](http://linksprite.com/wiki/images/thumb/4/42/211201004-with_pillar-001.jpg/640px-211201004-with_pillar-001.jpg)

*[LinkNode R8](http://linksprite.com/wiki/index.php5?title=LinkNode_R8:_Arduino-compatible_WiFi_relay_controller)

![LinkNode R8](http://linksprite.com/wiki/images/thumb/a/ae/LinkNode_R8-5.jpg/640px-LinkNode_R8-5.jpg)

