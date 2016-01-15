Note:  The pictures are for a 24 Zone System.  I will update with a 16 zone system in the near future.



## **Hello, Smarter Lawn**
## **Building A 16 Zone Smarter Lawn Irrigation Controller**


### **A project by Stan Dotson (stan@dotson.info) and Matthew Nichols (matt@nichols.name)**

## Background

Why would you build a lawn sprinkler system controller when you can just buy one?  But you can’t just buy one... at least not the sprinkler system we were thinking about!!  Let’s start by first making the controller connected.  And of course, we will need iPhone controls.  Then we need to make it smart.  Smart like it knows when it has rained, is raining or will rain!   And then what if we could add cognition so that it actually learns how to water your lawn!  Not that would be really smart!!!

## High Level Project Steps

1. Order the hardware
2. Add SmartThings hub to your home network, download app to your iPhone
3. Obtain a Maker/Developer account for SmartThings (graph.api.smartthings.com)
4. Connect Pins/Wires Between the Arduino controller, ThingShield and Relay Boards.  
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

All items from this project were easily obtained from Amazon and mostly available via Amazon Prime

### An Arduino with SmartThing Shield

An **Arduino MEGA* was used as the controller and was stacked with the SmartThings ThingShield.  *Note, set the DIP switch to D2/D3 if not already*.

<img src="https://cloud.githubusercontent.com/assets/5625006/4689513/665f710a-56be-11e4-9c37-ec0ade60d44b.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353577/8256ee78-fa85-11e3-93c8-866ef2ca9967.jpg" width="200px"  />

### Relays

To control the sprinkler valves, we used a **16 Channel Relay Board**.  You can also use 2x 8 Channel Relay Boards,  you will just have to wire them up in serial.  Special Note: the relays for sale online can be +5V, +12V, +24V.  The SIMPLEST thing to do was to buy +5V relays so that you can power directly from the Arduino.  Since only one (up to three if you add a pump and master valve) relays are on at any given time, the Arduino is able to power them.   

<img src="https://cloud.githubusercontent.com/assets/5625006/4689508/664a04fa-56be-11e4-9de9-37bd043f8fc1.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353578/86de7d94-fa85-11e3-86b9-b3b08601987f.jpg" width="200px"  />

### Wiring

For the wiring, we ordered both **20 cm dupont cable male to female** and **20 cm dupont cable female to female** (RioRand 3 x 40P 20cm Dupont Wire Jumper Cable 2.54 1P-1P Male-Male/Female-Female/Female-Male or similar from Amazon).  We liked this cable since the wires are organized as a ribbon cable which keeps the project neat.  We also used individual male to male jumper cables to wire the “COMMON” contacts together in parallel (see below).  We found these at Amazon such as **Male to Male Solderless Flexible Breadboard Jumper Cable Wires 65Pcs for Arduino by Sunkee**.

<img src="https://cloud.githubusercontent.com/assets/5625006/3353583/b426908e-fa85-11e3-81de-3c7b55a1db92.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353586/c2ceb562-fa85-11e3-9a2a-df5ed5e429cb.jpg" width="200px"  />

### Power Supplies

#### To run the sprinkler valves:
The project requires at least two power supplies.  One power supply is to run the sprinkler valves which are typically 24V AC.   The **Rain Bird UT1 Sprinkler System Timer Electric Transformer Plug** is a good choice from Amazon Prime.  

#### To run the Arduino, ThingShield and Relays:

*If your relays are 5V:* You will need a 9V power supply to run the Arduino+ThingShield+Relays.   The 5V relays can be driven from the Arduino 5v pin (you use a jumper to connect the 5V pin and the VCC pin on the relay board).  This works because at most 3 relays are on at once (one for the valve and the other for the optional pump and optional master valve).   This can be purchased at Amazon as well by searching for "Arduino Power Supply".  Right now, I am using the Super Power Supply® AC / DC Adapter Charger Cord Plug - 9V 650mA compatible with Arduino Freeduino Duemilanove Uno Mega Hobby Electronics, which was available by Amazon Prime.   CAUTION: There are 9V power supplies availble on Amazon that do not work for the Arduino (they are made for musical instrament controllers) and some that perform very poorly on Arduino.  Be sure to read the reviews!  


### Project Housing
Finally, for the project housing, the 9"x9"x3" OUTDOOR CABLETEK ENCLOSURE PLASTIC GRAY CASE UTILITY CABLE BOX CTE-S from Amazon was a nice size that housed all the relay boards, as well as the Arduino+ThingShield. 

<img src="https://cloud.githubusercontent.com/assets/5625006/4667478/5eee633e-555d-11e4-88fd-ced3e1fa7f52.jpg" width="200px"  />


## Wiring the Project

* Wiring diagrams are included in the header of the Arduino sketch.

Here is an overview of the project all wired up and placed in the suggested housing:


<img src="https://cloud.githubusercontent.com/assets/5625006/4691623/44894b36-5728-11e4-9674-c059561ec71a.jpg" width="200px"  />



### Wiring The Arduino Controller
1. Stack the SmartThing ThingShield on top of the Arduino MEGA.  
2. Connect a ground wire from the GND pin on ThingShield to the GND pin on the 16 Channel relay.  
3. Connect another jumper wire from the +5V on the ThingShield to the VCC pin on the 16 Channel Relay. 
4. Use 16 wires from your ribbon cable to connect pins 21-36 on Arduino MEGA to pins 1 to 16 on the relay 16 channel board (or other scenarios depending on what combination of relay boards that you have purchased).

ThingShield Connections: 5V power to Relay Board and a jumper connecting pin10 to pin3 needed for communications:

<img src="https://cloud.githubusercontent.com/assets/5625006/4689511/66510b88-56be-11e4-9bff-c4c9c9064543.jpg" width="200px"  />

Jumper connections originating from the MEGA that will connect to the relay boards:

<img src="https://cloud.githubusercontent.com/assets/5625006/4689512/665d067c-56be-11e4-9348-c184c0246678.jpg" width="200px"  />

Jumper connections from the MEGA terminating on the 16 channel relay:

<img src="https://cloud.githubusercontent.com/assets/5625006/4689510/664f5b62-56be-11e4-8b00-f5e0f60c44dc.jpg" width="200px"  />

### Wiring The Relay Boards

In addition to making the connections between the Arduino MEGA and the relay pins (above), you also need to power the relay boards and connect the "Common" terminals between all 16 of the relays.  

####Powering the Relays:
The relays are potentiated by connections with the ARDUINO MEGA pins 21-44.  However, the actual relay switching is seperately powered by either a 5V, 12V or 24V DC power, depending on which relay you purchase. If using a +5V relays, run jumpers from the +5V and GND pins on the Arduino to the VCC and GND pins on the Relay Board, respectively.  If applicable move the jumper on the relay board that bridges VCC to JD-VCC.

####Connecting a Common terminals across the relays.
Using short male to male jumper flexible wires or pre-formed solid male-to-male jumper wire or combination, we daisy chained the COMMON contact positions together across all 16 relays.

Example of daisy chaining the COMMON terminals of the relays using flexible jumper wire:

<img src="https://cloud.githubusercontent.com/assets/5625006/3353593/f3f9b8f8-fa85-11e3-877c-f05b27e22214.jpg" width="200px"  />

Example of daisy chaining the COMMON terminals of the relays using pre-formed solid wire:

<img src="https://cloud.githubusercontent.com/assets/5625006/4689509/664cba06-56be-11e4-834b-1b57f8e895f2.jpg" width="200px"  />


Note, the ThingShield pins are not labeled.  So you can either identify the pins using labels on the Arduino or from the Arduino pic above
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
2. Wire the ground wire from the Pump Start Relay to the  ground wire bundle that contains the ground wire from the 24V irrigation transformer/power supply and the common ground that runs to the irrigation valves.
3. Attach the load wire from the Pump Start Relay to Relay 16 or to the single relay that you added to the system, depending if you set up A) or B) above.
4. In scenario A), where you use relay 16 to control your pump, you need to switch the use of the relay in the device type software by tapping the pump tile.  See above to activate scenario B) by modifying the Arduino sketch. 

## The Software

The code for this project is in a few files which are posted on github:

**https://github.com/d8adrvn/smart_sprinkler**

### Arduino Code 

**(Arduino_16_Zone_Irrigation_Controller.ino)**

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
* Works with up to 16 zones (relays), although you can use less
* Allows zone times to be communicated as a string, which is parsed into an array
* Once a zone is turned on, the Arduino knows when to turn it off (no additional communication needed)
* Utilizes a timer function to run the zones.  This keeps the CPU free to manage communication between Arduino and SmartThings while a zone is running
* When wired correctly, the system turns off when power goes off and stays off until new commands are sent from SmartThings

To load the code onto the Arduino, you will need the Arduino developer environment:

http://arduino.cc/en/main/software or follow your board manufacturer's suggestion.

Once the software is installed, the first thing to do is obtain the required libraries.  

* Timer library was created by Simon Monk as modified by JChristensen  https://github.com/JChristensen/Timer
The KNOWN LIBRARY BUG (timer.cpp) - identified by mattnichols 5/20/14 - has no known affect on the code.  Also, the Timer library release zip file downloads to your computer as "Timer-master-2".  Before loading into the Arduino IDE, change the name to "Timer"
*  An enhanced SmartThings Library was created by  Dan G Ogorchock & Daniel J Ogorchock and their version is required for this implementation.  Their enhanced library can found at: https://github.com/DanielOgorchock/ST_Anything/tree/master/Arduino/libraries/SmartThings
* SoftwareSerial library was default library provided with Arduino IDE
 
Once you have the zip files downloaded and you have changed the name for the Timer zip file, you can import them within the Arduino IDE. Go to the Sketch:Import Library;Add Library drop down menu. Once you have added the libraries, they will show up under Sketch:Add Library:Contributed as "Timer" and "SmartThings".  Be sure the Timer library is installed as "Timer"

Hint: Also make sure that under the <Tools<Boards menu, you have select Arduino MEGA 2560 as the board.  Otherwise you will receive a compile error and/or the code will not finish transferring to the Arduino MEGA

You can connect the Arduino MEGA to your computer via an USB cable, create a new sketch, paste the code from github into the Arduino IDE and then transfer to the Arduino.

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

Once you enter the Device Handler (Device Type), you will see the following screen (note this is for the 8 zone, the 16 zone will look the same but with more zones):

<img src="https://cloud.githubusercontent.com/assets/5625006/12346436/d3ea402c-bb19-11e5-87f0-770bbd9923c1.jpg" width="200px"  />


Each sprinkler zone additionally has its own tile.  When the zone is off, the tile is white.  If the zone is in the queue and waiting its turn, the tile is gold.  When the zone is running, the tile is blue.

The “Refresh” tile can be pressed to refresh the status of all the zones.  There are a number of situations that the tiles become out of synch.  If you press too many individual tiles to fast, the communication may not be captured by the Arduino.  If you try to add or delete a zone from the queue while “refresh” is running (it takes 8 sec to refresh), the communication may not be captured by the Arduino.  And there are times the SmartThings hub just doesn’t seem to capture the message from the Arduino or at least is slow to respond.  If in doubt, press the “Refresh”.   As an additional "rescue" feature, any tile that seems to be out of synch can be pressed again to trigger the zone to turn off.  

Scheduler Override Tile.  This tile interacts with the Schedular App (see below) to provide additional functionality.  By pressing the tile you can rotate through the features
* Normal - allows Schedular App to run as scheduled
* Skip 1X - skips one scheduled watering routine
* Expedite - over-rides the weather forecast and allows the Schedule to run, even if there is rain
* Pause - indefinitely pauses the Scheduler App.  

Example uses for this Scheduler Overide tile include putting the system on hold for the winter, during yard construction projects, after applying weed killer to the yard, and so on.

<img src="https://cloud.githubusercontent.com/assets/5625006/12346445/df465bd6-bb19-11e5-83d7-66223d6a96cc.jpg" width="200px"  />


The “Preferences” page allows you to enter the run times for each station.  To get to the preferences page, press the 3 vertical dots in the top right corner of the Device Handler:

<img src="https://cloud.githubusercontent.com/assets/5625006/12346438/d5876e8c-bb19-11e5-907d-3fbba667f8c0.jpg" width="200px"  />


Once in the Preferences page, you can enter a set of times for each zone that you trigger manually (by pressing the zone tile in the Device Handler).  To enter you manual zone times, just enter the times in minutes.  If you are using less than 8 zones, just enter zero minutes for zones not in use.  These time Preferences are used when you run the sprinkler manually from within the Irrigation Controller Device Type app.  If you are using the SmartApp Irrigation Scheduler App to automatically run the system and have entered times via that app, the Scheduler supplied times are used.

<img src="https://cloud.githubusercontent.com/assets/5625006/12346439/d70889c6-bb19-11e5-8896-72a52dba53c1.jpg" width="200px"  />


### Irrigation Controller Smart App 

**(IrrigationSchedulerApp.groovy)**

This is where we put the brains in the system.

This app provides flexibility to schedule by days and/or by interval.  If you use both days and interval, you can take advantage of interval watering and avoid certain days, such as mowing days or days banned by your local ordinances.  The app also allows up to three watering times per day.

During set up mode, the app will also ask for zone watering times.  Times entered in the SmartApp will over-ride times entered in the device-type preferences when using the SmartApp to schedule. **NOTE: if any zone times are configured in the app, only those zones will be run by the timer.**

The App will also ask for a zipcode.  We use the zipcode to look up your hyperlocal weather forecast to the your irrigation controller.  In essence, we take a snapshot of the rainfall on your lawn for roughly a 48 hour window, using the precip that fell the day before, the precip that fell that day and the forecasted precip for the remainder of the day.  Very cool.

After totaling up the precip, the App checks the threshold that you set as a preference.  If the precip exceeds the threshold, the App skips that watering.  Automagic!

<img src="https://cloud.githubusercontent.com/assets/5625006/3353742/3480941e-fa8b-11e3-935b-c485a2c88461.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353744/361a6372-fa8b-11e3-8fe5-1f1081f49fc2.jpg" width="200px"  />


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

Now for the cool part.  To control your system with the Aeon MiniMote:
1. Add the MiniMote to the sytem using Aeon's instructions "Becoming a Secondary/Inclusion Controller to Another Z-Wave Controller or Gateway in an Existing Z-Wave Network
2. When it asks you to configure 'button 1': add Virtual Irrigation Zone 1  as the switch under 'Pushed' and Virtual Irrigation Zone 2 as the switch under 'Held'
3. Repeat for buttons 2,3,4 using zones 3-8

Now when you 'push' or 'hold' a MiniMote button, the corresponding zone will activate.  If you push again, it will turn off.   If you do not turn off the sytem, it will run for as long as the zone ran, the last time it was activated from the device type or the smart app.   Tip: you can tell push and hold apart.  When you push, a solid blue led comes on.  When you have 'held' the button, the blue led starts flashing.  

## FAQs

* After installing the Device Type or the Smart App, be sure to both Save AND Publish (for me).  If you do not publish, they will not be able to communicate with the hub.  If you make changes to either code, Publish early and often to avoid IDE issues.

* After powering off the Arduino or switching from USB power to 9V power, you may notice that the SmartShield LED goes dark.  The SmartShield LED will relight at the first activity and then function normally after that.  The Arduino LEDs should both be on at all times when power is supplied to the hardware

* What if you want to run your lawn irrigation on a different schedule than your drip irrigation?  No problems.  Just install the Irrigation Scheduler App for your lawn (rename it something like Lawn Irrigation Scheduler) and install the app a second time for the drip irrigation (rename it something like Drip Irrigation Scheduler).  

* The Arduino runs on 9V DC power.  Your relays may require on 5V DC, which can be supplied by the Arduino 5V pin or your relays may require an additional 12V DC or 24V DC power supply, depending on which relay boards you purchase.  The sprinkler valves run on 24V AC power which is connected and daisy chained through the relays.  Be sure to keep them straight.  

* The Arduino 9V power supply (transformer) can be purchased at Amazon by searching for "Arduino Power Supply".  CAUTION: There are 9V power supplies availble on Amazon that do not work for the Arduino (they are made for musical instrament controllers) and some that perform very poorly on Arduino.  Be sure to read the reviews!  

*  There is nothing that requires you to have all 16 relays.  If you choose to use less than 16 relays AND plan to use the optional software activated master pump relay, just be sure that pin36 from the Arduino Mega is attached to the relay associated with the Master Pump Relay.
