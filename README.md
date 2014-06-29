## **Hello, Smarter Lawn**

### **A project by Stan Dotson (stan@dotson.info) and Matthew Nichols (matt@nichols.name)**

## Background

Why would you build a lawn sprinkler system controller when you can just buy one?  But you can’t just buy one... at least not the sprinkler system we were thinking about!!  Let’s start by first making the controller connected.  And of course, we will need iPhone controls.  Then we need to make it smart.  Smart like it knows when it has rained, is raining or will rain!   And then what if we could add cognition so that it actually learns how to water your lawn!  Not that would be really smart!!!

## High Level Project Steps

1. Order the hardware
2. Add SmartThings hub to your home network, download app to your iPhone
3. Obtain a Maker/Developer account for SmartThings (graph.api.smartthings.com)
4. Assemble the Arduino controller, ThingShield and 8 Channel Relay.  Load Arduino code to the Arduino
5. Add Arduino to your SmartThings hub using your iPhone app
6. Go to graph.api.smartthings.com
  1. On My Device Types, create a new device type and paste in the device type code.  Save & Publish
  2. On My SmartApps, create a new Smart App and paste in the smart app code. Save & Publish
7.  Go to My Devices, select the Arduino and edit the Device Type and select the Irrigation Controller device type (6.i.)
8.  Test out system 
9.  Wire the Arduino to your irrigation system
10. You now have a smarter lawn!


## The Hardware

All items from this project were easily obtained from Amazon and mostly available via Amazon Prime

### An Arduino with SmartThing Shield

An **Arduino Uno* was used as the controller and was stacked with the SmartThings ThingShield.  Note, set the DIP switch on the ThingShield to D2/D3, if not already

<img src="https://cloud.githubusercontent.com/assets/5625006/3353572/56b840aa-fa85-11e3-980c-6c8cd8c8b156.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353577/8256ee78-fa85-11e3-93c8-866ef2ca9967.jpg" width="200px"  />

### 8-Channel Relay

To control the sprinkler valves, we used a **Sain Smart 8 channel relay**.


<img src="https://cloud.githubusercontent.com/assets/5625006/3353578/86de7d94-fa85-11e3-86b9-b3b08601987f.jpg" width="200px"  />

### Wiring

For the wiring, we ordered a **20 cm dupont cable male to female** (Phantom YoYo 40P dupont cable 200mm male to female or similar from Amazon).  We liked this cable since the wires are organized as a ribbon cable which keeps the project neat.  We also used individual male to male jumper cables to wire the “COMMON” contacts together in parallel (see below).  We found these at Amazon such as **Male to Male Solderless Flexible Breadboard Jumper Cable Wires 65Pcs for Arduino by Sunkee**.

<img src="https://cloud.githubusercontent.com/assets/5625006/3353583/b426908e-fa85-11e3-81de-3c7b55a1db92.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353586/c2ceb562-fa85-11e3-9a2a-df5ed5e429cb.jpg" width="200px"  />

### Power Supplies
For a power supply to power up the relay and run the irrigation valves, we re-used the 24V power supply from our existing controller.   We could have also used either a **Rain Bird UT1 Sprinkler System Timer Electric Transformer Plug** or the **Orbit Sprinkler System Power Source Transformer 57040**, both of which are available from Amazon Prime.  We  also needed a 9V power supply to run the Arduino+ThingShield+Relay.  The **Wall Adapter Power Supply - 9V DC 650mA Sold by Karnotech**  worked and is available on Amazon.

### Project Housing
Finally, for the project housing, we just ripped out the guts of our existing controller and used the box to house our final project. Another option is the **Arington EB0708** electronic equipment enclosure.


## Wiring the Project
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

### Wiring The Controller To The Irrigation System
The final wiring of the project to your irrigation system is straight forward.  Irrigation sytems use a standard irrigation wire bundle which has multiple colored wires (one per valve + extras) and a white wire as a common ground.  We connected “ground” wire from the 24V transformer to the common ground (white) wire in the irrigation wire bundle.

To connect the wires running to each valve, we used the Normally Open positions on the relay.  Each colored wire for each valve was connected to one of the NO positions on a relay.  One valve per relay.   Up to 8 are possible with this hardware, however, you do not need to use all 8.  We then connected the “hot” wire from the transformer to connect to the COMMON position (middle contact) on one of the relays.  This provides power to all realys since they are daisy chained together (see Arduino wiring above)  


<img src="https://cloud.githubusercontent.com/assets/5625006/3353611/6b78fc9a-fa86-11e3-9557-424b4a2bf896.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353626/d163cc06-fa86-11e3-8aaf-c4a65fc64b5e.jpg" width="200px"  />

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

Once the software is installed, you can connect the Arduino Uno to your computer, create a new sketch, paste the code from github into the Arduino IDE and then upload to the Arduino.

Pairing instructions for the Arduino to the SmartThings hub can be found at SmartThings.com and are copied here:

“To join the shield to your SmartThings hub, go to “Add SmartThings” mode in the
SmartThings app by hitting the “+” icon in the desired location, and then press the Switch button on the shield. You should see the shield appear in the app.

To unpair the shield, press and hold the Switch button for 6 seconds and release. The shield will now be unpaired from your SmartThings Hub.”


### Device Type Code 

**(IrrigationControllerDeviceType.groovy):**



The device type code allows you to control the Arduino via the SmartThings physical graph.  The Irrigation controller device type code has the following features:

The Main Tile is an all on or all off tile and links to the “switch” capability to turn on or off all 8 zones of the sprinkler by pressing one button.  By using the “switch” capability, you to use any of the SmartThings apps that includes a switch capability to run the sprinkler system!  There is also an “switch off” capability that turns off all zones. The Main Tile can also receive a message when the system is on Rain Delay mode.

Each sprinkler zone additionally has its own tile.  When the zone is off, the tile is white.  If the zone is in the queue and waiting its turn, the tile is gold.  When the zone is running, the tile is blue.

The “Refresh” tile can be pressed to refresh the status of all the zones.  There are a number of situations that the tiles become out of synch.  If you press too many individual tiles to fast, the communication may not be captured by the Arduino.  If you try to add or delete a zone from the queue while “refresh” is running (it takes 8 sec to refresh), the communication may not be captured by the Arduino.  And there are times the SmartThings hub just doesn’t seem to capture the message from the Arduino or at least is slow to respond.  If in doubt, press the “Refresh”.   As an additional "escape" feature, any tile that seems to be out of synch can be pressed again to trigger the zone to turn off.  

The “Preferences” tile allows you to enter the run times for each station.  Just enter the times in minutes.  If you are using less than 8 zones, just enter zero minutes for zones not in use.  These time Preferences are used when you run the sprinkler manually from within the Irrigation Controller Device Type app.  If you are using the SmartApp Irrigation Scheduler App to automatically run the system and have entered times via that app, the Scheduler supplied times are used.

<img src="https://cloud.githubusercontent.com/assets/5625006/3353741/2cdbb0ea-fa8b-11e3-9e13-81c3b004f902.jpg" width="200px"  />

### Irrigation Controller Smart App 

**(IrrigationSchedulerApp.groovy)**

This is where we put the brains in the system.

This app provides flexibility to schedule by days and/or by interval.  If you use both days and interval, you can take advantage of interval watering and avoid certain days, such as mowing days or days banned by your local ordinances.  The app also allows up to three watering times per day.

During set up mode, the app will also ask for zone watering times.  Times entered in the SmartApp will over-ride times entered in the device-type preferences when using the SmartApp to schedule. **NOTE: if any zone times are configured in the app, only those zones will be run by the timer.**

The App will also ask for a zipcode.  We use the zipcode to look up your hyperlocal weather forecast to the your irrigation controller.  In essence, we take a snapshot of the rainfall on your lawn for roughly a 48 hour window, using the precip that fell the day before, the precip that fell that day and the forecasted precip for the remainder of the day.  Very cool.

After totaling up the precip, the App checks the threshold that you set as a preference.  If the precip exceeds the threshold, the App skips that watering.  Automagic!

<img src="https://cloud.githubusercontent.com/assets/5625006/3353742/3480941e-fa8b-11e3-935b-c485a2c88461.jpg" width="200px"  />

<img src="https://cloud.githubusercontent.com/assets/5625006/3353744/361a6372-fa8b-11e3-8fe5-1f1081f49fc2.jpg" width="200px"  />


### Irrigation Virtual Scheduler Switch 

**(IrrigationVirtualSchedulerSwitchDevice.groovy)**

This is an optional utility that allows you to put your system on extended hold or in other states. To install:

* Open the SmartThings IDE
* Create a new device type and paste the code in
* Save and then Publish (to me)
* Create a new device and select the device type you just created
* The device should now show up on your smartphone SmartThings app
* You must select it in the Irrigation Scheduler app

Example uses for this accessory app include putting the system on hold for the winter, during yard construction projects, after applying weed killer to the yard, and so on.  

## Bonus Feature:

We modified our Device Type command set to be compatible with the Virtual Switch capability by Jonathan (jwsf on github or badgermanus on SmartThings).  This allows you to virtualize just one or each of the 8 zones giving enhanced ecosystem capabilities.  Virtualizing gives each zone its own “switch” capability that can be recognized within SmartThings ecosystem. Then you can start using all the SmartApps that link switches to other devices.  

For example, you can use the "Turn on when there is motion" SmartApp to connect an irrigation zone to a motion detector and trigger the sprinkler every time deer or intruder enters that zone!

## FAQs

* After installing the Device Type or the Smart App, be sure to both Save AND Publish (for me).  If you do not publish, they will not be able to communicate with the hub.  If you make changes to either code, Publish early and often to avoid IDE issues.

* After powering off the Arduino or switching from USB power to 9V power, you may notice that the SmartShield LED goes dark.  The SmartShield LED will relight at the first activity and then function normally after that.  The Arduino LEDs should both be on at all times when power is supplied to the hardware

*What if you want to run your lawn irrigation on a different schedule than your drip irrigation?  No problems.  Just install the Irrigation Scheduler App for your lawn (rename it something like Lawn Irrigation Scheduler) and install the app a second time for the drip irrigation (rename it something like Drip Irrigation Scheduler).  
