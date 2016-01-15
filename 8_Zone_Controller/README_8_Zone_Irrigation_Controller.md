#Project Description For Our 8 Zone Irrigation Controller with Options For Master Valve and Pump


## High Level Project Steps

1. Order the hardware
2. Add SmartThings hub to your home network, download app to your iPhone
3. Obtain a Maker/Developer account for SmartThings (graph.api.smartthings.com)
4. Assemble the Arduino controller, ThingShield and 8 Channel Relay.  
5. Download the Arduino developer environment and import the irrigation controller sketch as well as the required libraries for SmartThings and the Timer library.  
6. Add Arduino to your SmartThings hub using your iPhone app
7. Go to graph.api.smartthings.com
  1. On My Device Types, create a new device type and paste in the device type code.  Save & Publish
  2. On My SmartApps, create a new Smart App and paste in the smart app code. Save & Publish
8.  Go to My Devices, select the Arduino and edit the Device Type and select the Irrigation Controller device type (7.i.)
9.  Test out system 
10.  Wire the Arduino to your irrigation system

You now have a smarter lawn!


## The Hardware

Most items from this project were easily obtained from Amazon and mostly available via Amazon Prime.   The SmartThings ThingShield was obtained directly from SmartThings.

### An Arduino with SmartThing Shield

An **Arduino Uno* was used as the controller and was stacked with the SmartThings ThingShield.  *Note, set the DIP switch to D2/D3 if not already*.

<img src="https://cloud.githubusercontent.com/assets/5625006/3353572/56b840aa-fa85-11e3-980c-6c8cd8c8b156.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353577/8256ee78-fa85-11e3-93c8-866ef2ca9967.jpg" width="200px"  />

### 8-Channel Relay

To control the sprinkler valves, we used a **SainSmart 8 channel relay**.


<img src="https://cloud.githubusercontent.com/assets/5625006/3353578/86de7d94-fa85-11e3-86b9-b3b08601987f.jpg" width="200px"  />

### Wiring

For the wiring, we ordered a **20 cm dupont cable male to female** (Phantom YoYo 40P dupont cable 200mm male to female or similar from Amazon).  We liked this cable since the wires are organized as a ribbon cable which keeps the project neat.  We also used individual male to male jumper cables to wire the “COMMON” contacts together in parallel (see below).  We found these at Amazon such as **Male to Male Solderless Flexible Breadboard Jumper Cable Wires 65Pcs for Arduino by Sunkee**.

<img src="https://cloud.githubusercontent.com/assets/5625006/3353583/b426908e-fa85-11e3-81de-3c7b55a1db92.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353586/c2ceb562-fa85-11e3-9a2a-df5ed5e429cb.jpg" width="200px"  />

### Power Supplies
For a power supply to power up the relay and run the irrigation valves, we re-used the 24V power supply from our existing controller.   We could have also used either a **Rain Bird UT1 Sprinkler System Timer Electric Transformer Plug** or the **Orbit Sprinkler System Power Source Transformer 57040**, both of which are available from Amazon Prime.  We  also needed a 9V power supply to run the Arduino+ThingShield+Relay.  This can be purchased at Amazon as well by searching for "Arduino Power Supply".  Right now, I am using the Super Power Supply® AC / DC Adapter Charger Cord Plug - 9V 650mA compatible with Arduino Freeduino Duemilanove Uno Mega Hobby Electronics, which was available by Amazon Prime.   CAUTION: There are 9V power supplies availble on Amazon that do not work for the Arduino (they are made for musical instrament controllers) and some that perform very poorly on Arduino.  Be sure to read the reviews!  

### Project Housing
Finally, for the project housing, we just ripped out the guts of our existing controller and used the box to house our final project. Another option is the **Arington EB0708** electronic equipment enclosure. Or try the 9"x9"x3" OUTDOOR CABLETEK ENCLOSURE PLASTIC GRAY CASE UTILITY CABLE BOX CTE-S.  


## Wiring the Project

* Wiring diagrams are included in the header of the Arduino sketch.


### Wiring The Arduino Controller
1. Stack the SmartThing ThingShield on top of the Arduino Uno.  
2. Connect a ground wire from the GND pin on ThingShield to the GND pin on the SainSmart 8 Channel relay.  
3. Connect another jumper wire from the +5V on the ThingShield to the VCC pin on the Relay.  
4. Make sure the jumper on the relay board bridges VCC to JD-VCC.  
5. Use 8 wires from your ribbon cable to connect pins 12-5 on ThingShield to pins 1 to 8 on the relay board.   

<img src="https://cloud.githubusercontent.com/assets/5625006/3385379/81cfd7d6-fc69-11e3-8f89-9cb25b10bb7e.jpg" width="200px"  />


<img src="https://cloud.githubusercontent.com/assets/5625006/3353588/e52e942e-fa85-11e3-9513-89809a182c9c.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353589/e9610928-fa85-11e3-949f-5f8b6d63ccda.jpg" width="200px"  />
 
6. Finally, using seven short jumper cable (male to male) we daisy chained the COMMON contact positions together across all 8 relays.

<img src="https://cloud.githubusercontent.com/assets/5625006/3353593/f3f9b8f8-fa85-11e3-877c-f05b27e22214.jpg" width="200px"  />

Note, the ThingShield pins are not labeled.  So you can either identify the pins using labels on the Arduino or refer to a diagram.  Here is a diagram for Arduino Uno V2 from Flikr:   https://www.flickr.com/photos/28521811@N04/8520970405/
7. Connect the Arduino to USB power or to a 9V power supply using the appropriate ports.  
8. When not using the USB power supply, place a piece of black electrical tape over the USB port to prevent accidental shorting


### Wiring The Controller To The Irrigation System
The final wiring of the project to your irrigation system is straight forward.  Irrigation sytems use a standard irrigation wire bundle which has multiple colored wires (one per valve + extras) and a white wire as a common ground. They typically run on 24V AC power.  

* Be sure power is disconnected before attempting to wire. Avoid being shocked or creating a fire hazard!  


We connected “ground” wire from the 24V AC transformer to the common ground (white) wire in the irrigation wire bundle.

To connect the wires running to each valve, we used the Normally Open positions on the relay.  Each colored wire for each valve was connected to one of the NO positions on a relay.  One valve per relay.   Up to 8 are possible with this hardware, however, you do not need to use all 8.  We then connected the “hot” wire from the transformer to connect to the COMMON position (middle contact) on one of the relays.  This provides power to all realys since they are daisy chained together (see Arduino wiring above)  


<img src="https://cloud.githubusercontent.com/assets/5625006/3353611/6b78fc9a-fa86-11e3-9557-424b4a2bf896.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353626/d163cc06-fa86-11e3-8aaf-c4a65fc64b5e.jpg" width="200px"  />

###OPTIONAL: Connect additional relay to activate a master valve or pump

If your irrigation system requires a pump to be turned on or a master valve to be opened, then there are two options for you.  A) you can wire the master valve or connect the pumps relay connector to the 8th relay on the 8-relay board.  B) you can purchase a single relay (find on Amazone) and add it to your controller.  

To add an additional single relay:
1. Connect the ground (GND) of the additional relay to one of the additional GND terminals on the Arduino.  There is one right next to the GND pin used to wire the 8-channel relay.
2. You will need to splice your 5V jumper (create a Y connection using a wire nut or slice connector) so that you can feed 5V both to your 8-channel relay and your single relay.  There is only one 5V pin on the Arduino
3. Go ahead and daisy chain the common port on the relay with the common port on the 8th relay on the 8-channel relay
4. In the Arduino sketch, set isPin13Pump = true to activate optional single Master Valve Pump Start Relay

Wiring the master valve or the pump relay:

Warning: Connecting a Pump Start Relay is potentially dangerous 
Warning: Pumps run on 120V or 220V power and incorrect wiring could result in lethal shock or fire hazard!
Warning: Work is best done by licensed electrician following local codes.

1. You should only be wiring the Pump Start Relay which uses 24V current.  Do not connect the 120V/240V wires!
2. Wire the ground wire to the  ground wire bundle that contains the ground wire from the 24V irrigation transformer/power supply and the common ground that runs to the irrigation valves.
3. Attach the load wire from the Pump Start Relay to Relay 8 or to the single relay that you added to the system, depending if you set up A) or B) above.
4. In scenario A), where you use relay 8 to control your pump, you need to switch the use of the relay in the device type by tapping the pump tile.  See above to activate scenario B) by modifying the Arduino sketch. 

## The Software

The code for this project is in a few files which are posted on github:

https://github.com/d8adrvn/smart_sprinkler.git

### Arduino Code 

(ArduinoSmartShieldIrrigationController.ino):

In its most basic form, our controller manages a queue and turns on/off switches. That’s it. Nothing else. All other features are in the cloud!

Managing the queue only needs a few basic functions:

1. Add or remove one or all irrigation zones to the queue
2. Ensure only one zone is on at a time (need a traffic cop)
3. Report out the status of the queue

The irrigation controller also has two basic protections

1. Before turning on a zone, it requires knowing when to turn off, even if communication with the cloud is lost.
2. If power goes out, the controller reverts to off


The code for the Arduino has the following features:

Creates a queue to turn on or off a specific zone or all zones

* The queue holds the state for each zone: 0=off, 1=waiting, 2=on
* A traffic cop feature ensures only one zone is running at once
* Works with up to 8 zones, although you can use less
* Allows zone times to be communicated as a string, which is parsed into an array
* Once a zone is turned on, the Arduino knows when to turn it off (no additional communication needed)
* Utilizes a timer function to run the zones.  This keeps the CPU free to manage communication between Arduino and SmartThings while a zone is running
* When wired correctly, the system turns off when power goes off and stays off until new commands are sent from SmartThings

To load the code onto the Arduino, you will need the Arduino developer environment:

http://arduino.cc/en/main/software

Once the software is installed, the first thing to do is obtain the required libraries.  

* Timer library was created by Simon Monk as modified by JChristensen  https://github.com/JChristensen/Timer
The KNOWN LIBRARY BUG (timer.cpp) - identified by mattnichols 5/20/14 - has no known affect on the code.  Also, the Timer library release downloads with the name: "Timer-master-2".  Before loading into the Arduino IDE, change the name to: "Timer"
* SmartThings library available from http://cl.ly/ZMHh)
* SoftwareSerial library was default library provided with Arduino IDE
 
Once you have the zip files downloaded and you have changed the name for the Timer zip file, you can import them within the Arduino IDE. Go to the Sketch:Import Library;Add Library drop down menu. Once you have added the libraries, they will show up under Sketch:Add Library:Contributed as "Timer" and "SmartThings".  Be sure the Timer library is installed as "Timer"

You can connect the Arduino Uno to your computer via an USB cable, create a new sketch, paste the code from github into the Arduino IDE and then transfer to tehe Arduino.

Be sure the Arduino IDE is set to an ArduinoUno.  Go to the menu: Tools>Board and select Arduino Uno

Pairing instructions for the Arduino to the SmartThings hub can be found at SmartThings.com and are copied here:

“To join the shield to your SmartThings hub, go to “Add SmartThings” mode in the
SmartThings app by hitting the “+” icon in the desired location, and then press the Switch button on the shield. You should see the shield appear in the app.

To unpair the shield, press and hold the Switch button for 6 seconds and release. The shield will now be unpaired from your SmartThings Hub.”

Once you have finished transfering the code to the Arduino, you can remove the USB and power the Arduino using a 9V transformer.

Its more than a good idea to put a piece of electrical tape over the USB port to prevent accidental grounding of the port!

### Device Handler Code 

**(IrrigationControllerDeviceType.groovy):**



The device type code allows you to control the Arduino via the SmartThings physical graph.  The Irrigation controller device type code has the following features:

The Main Tile is an all on or all off tile and links to the “switch” capability to turn on or off all 8 zones of the sprinkler by pressing one button.  By using the “switch” capability, you to use any of the SmartThings apps that includes a switch capability to run the sprinkler system!  There is also an “switch off” capability that turns off all zones. The Main Tile can also receive a message when the system is on Rain Delay mode.

Once you enter the Device Handler (Device Type), you will see the following screen:

<img src="https://cloud.githubusercontent.com/assets/5625006/12346436/d3ea402c-bb19-11e5-87f0-770bbd9923c1.jpg" width="200px"  />


Each sprinkler zone additionally has its own tile.  When the zone is off, the tile is white.  If the zone is in the queue and waiting its turn, the tile is gold.  When the zone is running, the tile is blue.

The “Refresh” tile can be pressed to refresh the status of all the zones.  There are a number of situations that the tiles become out of synch.  If you press too many individual tiles to fast, the communication may not be captured by the Arduino.  If you try to add or delete a zone from the queue while “refresh” is running (it takes 8 sec to refresh), the communication may not be captured by the Arduino.  And there are times the SmartThings hub just doesn’t seem to capture the message from the Arduino or at least is slow to respond.  If in doubt, press the “Refresh”.   As an additional "rescue" feature, any tile that seems to be out of synch can be pressed again to trigger the zone to turn off.  

Scheduler Override Tile.  This tile interacts with the Schedular App (see below) to provide additional functionality.  By pressing the tile you can rotate through the features
* Normal - allows Schedular App to run as scheduled
* Skip 1X - skips one scheduled watering routine
* Expedite - over-rides the weather forecast and allows the Schedule to run, even if there is rain
* Pause - indefinitely pauses the Scheduler App.  

Example uses for this Scheduler Overide tile include putting the system on hold for the winter, during yard construction projects, after applying weed killer to the yard, and so on.

The “Preferences” page allows you to enter the run times for each station.  To get to the preferences page, press the 3 vertical dots in the top right corner of the Device Handler:

<img src="https://cloud.githubusercontent.com/assets/5625006/12346438/d5876e8c-bb19-11e5-907d-3fbba667f8c0.jpg" width="200px"  />


Once in the Preferences page, you can enter a set of times for each zone that you trigger manually (by pressing the zone tile in the Device Handler).  To enter you manual zone times, just enter the times in minutes.  If you are using less than 8 zones, just enter zero minutes for zones not in use.  These time Preferences are used when you run the sprinkler manually from within the Irrigation Controller Device Type app.  If you are using the SmartApp Irrigation Scheduler App to automatically run the system and have entered times via that app, the Scheduler supplied times are used.


### Irrigation Schedular Smart App 

**(IrrigationSchedulerApp.groovy)**

This is where we put the brains in the system.

This app provides flexibility to schedule by days and/or by interval.  If you use both days and interval, you can take advantage of interval watering and avoid certain days, such as mowing days or days banned by your local ordinances.  The app also allows up to three watering times per day.

During set up mode, the app will also ask for zone watering times.  Times entered in the SmartApp will over-ride times entered in the device-type preferences when using the SmartApp to schedule. **NOTE: if any zone times are configured in the app, only those zones will be run by the timer.**

The App will also ask for a zipcode.  We use the zipcode to look up your hyperlocal weather forecast to the your irrigation controller.  In essence, we take a snapshot of the rainfall on your lawn for roughly a 48 hour window, using the precip that fell the day before, the precip that fell that day and the forecasted precip for the remainder of the day.  Very cool.

After totaling up the precip, the App checks the threshold that you set as a preference.  If the precip exceeds the threshold, the App skips that watering.  Automagic!

<img src="https://cloud.githubusercontent.com/assets/5625006/3353742/3480941e-fa8b-11e3-935b-c485a2c88461.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353744/361a6372-fa8b-11e3-8fe5-1f1081f49fc2.jpg" width="200px"  />


You can install multipe Scheduler apps.  For example one to run your lawn sprinklers every day and another to run your drip sprinklers a couple times of week.  Or whatever unique situation exists with your system.
  

## Take Your Controller to the Next Level:

We modified our Device Type command set to be compatible with the Virtual Switch capability by Jonathan (jwsf on github or badgermanus on SmartThings).  This allows you to virtualize each of the 8 zones giving enhanced ecosystem capabilities.  Virtualizing gives each zone its own “switch” capability that can be recognized within SmartThings ecosystem. Then you can start linking irrigatoin zones to all the SmartApps that link switches to other devices.  

For example, you can use the "Turn on when there is motion" SmartApp to connect an irrigation zone to a motion detector and trigger the sprinkler every time deer or intruder enters that zone!

As another example, you can use the Aeon Labs MiniMote to wirelessly turn on and then off individual zones.  This is great if you do not have your iPhone or if you have a repair person working on your system.  The repair person just needs the MiniMote to switch the zones.  No running back and forth to the garage to advance the system!!!!!!!

Here is how to virtualize your switches:

1. Log into graph.api.smartthings.com
2. Go to "My Device Types"
3. Create a new SmartDevice, filling out the required info to create the device
4. Paste in the VirtualSwitch.groovy code from jwsf:  https://github.com/d8adrvn/device-type.arduino-8-way-relay/blob/master/VirtualSwitch.groovy
5. Save and Publish (for me)
6. Go to My SmartApps and create a new SmartApp
7. Paste in the code for the VirtualSwitchParent.groovy that has been modified to recoginize our Irrigation Controller.  The modified code can be found at:   https://github.com/d8adrvn/device-type.arduino-8-way-relay/blob/master/VirtualSwitchParent.groovy
8. Save and Publish (for me)
9. Go to My Devices and create a new Device.
10. Give it a 'name' and a 'label' such as "Virtual Irrigation Zone #1"
11. Under 'type', select "virtual switch", which is the device type you created in #4 above
12. For 'version', select "published"
13. Now repeat this 7 more times for each of your zones!

The above is a bit tedious.  As soon as SmartThings supports child devices, I imagine we will update our code and can do away with the need to create virtual switches.  

Now for the cool part.  You can integrate the sprinkler system with other SmartThings devices.  See below for some examples:

To control your system with the Aeon MiniMote:
1. Add the MiniMote to the sytem using Aeon's instructions "Becoming a Secondary/Inclusion Controller to Another Z-Wave Controller or Gateway in an Existing Z-Wave Network
2. When it asks you to configure 'button 1': add Virtual Irrigation Zone 1  as the switch under 'Pushed' and Virtual Irrigation Zone 2 as the switch under 'Held'
3. Repeat for buttons 2,3,4 using zones 3-8

Now when you 'push' or 'hold' a MiniMote button, the corresponding zone will activate.  If you push again, it will turn off.   If you do not turn off the sytem, it will run for as long as the zone ran, the last time it was activated from the device type or the smart app.   Tip: you can tell push and hold apart.  When you push, a solid blue led comes on.  When you have 'held' the button, the blue led starts flashing.  

To control your system with the Amazone Echo, just import your SmartThings devices into Echo.  The Sprinkler system will show up as an on/off switch which will trigger the sprinkler to run through each station using the manual zone times (the ones set in the preferences).  If you set up virtual switches for each zone, then you can use the Echo to turn on a specific zone.  Just say Alex, turn on my Sprinkler system!

## FAQs

* After installing the Device Type or the Smart App, be sure to both Save AND Publish (for me).  If you do not publish, they will not be able to communicate with the hub.  If you make changes to either code, Publish early and often to avoid IDE issues.

* After powering off the Arduino or switching from USB power to 9V power, you may notice that the SmartShield LED goes dark.  The SmartShield LED will relight at the first activity and then function normally after that.  The Arduino LEDs should both be on at all times when power is supplied to the hardware

* What if you want to run your lawn irrigation on a different schedule than your drip irrigation?  No problems.  Just install the Irrigation Scheduler App for your lawn (rename it something like Lawn Irrigation Scheduler) and install the app a second time for the drip irrigation (rename it something like Drip Irrigation Scheduler).  

* The Arduino runs on 9V power.  The sprinkler valves run on 24V power which is connected and daisy chained through the relays.  Be sure to keep them straight.  

* The Arduino 9V power supply (transformer) can be purchased at Amazon by searching for "Arduino Power Supply".  CAUTION: There are 9V power supplies availble on Amazon that do not work for the Arduino (they are made for musical instrament controllers) and some that perform very poorly on Arduino.  Be sure to read the reviews!  


