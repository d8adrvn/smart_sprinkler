## ESP8266 Smart Sprinkler**

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

ESP8266Smart_Sprinkler_Discovery
ESP8266Smart_Sprinkler_Scheduler

The first is a parent SmartApp that will discover ESP8266 Smart Sprinkler devices and add them to SmartThings.  The second is a Child SmartApp that allows schedules to be created for running the sprinklers.

#Device Types
Two device types have been written so far.  One for a 4 zone controller, and one for an 8 zone controller.

ESP8266_4_Zone_Irrigation_Controller
ESP8266_8_Zone_Irrigation_Controller



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


###Controller Hardware Options
	
There are a large variety of ESP8266 based options including prefabricated boards with relays that can be used for Sprinkler Controllers.  The two products I'm currently using are:

* [LinkNode R4] (http://linksprite.com/wiki/index.php5?title=LinkNode_R4:_Arduino-compatible_WiFi_relay_controller)

* [LinkNode R8] (http://linksprite.com/wiki/index.php5?title=LinkNode_R8:_Arduino-compatible_WiFi_relay_controller)


