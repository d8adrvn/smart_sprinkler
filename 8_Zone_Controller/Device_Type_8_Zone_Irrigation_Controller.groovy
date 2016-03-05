/**
 *  Irrigation Controller 8 Zones
 *  This SmartThings Device Type Code Works With Arduino Irrigation Controller also available at this site
 *  
 *
 *	Creates connected irrigation controller
 *  Author: Stan Dotson (stan@dotson.info) and Matthew Nichols (matt@nichols.name)
 *  Date: 2014-06-14
 *  Copyright 2014 Stan Dotson and Matthew Nichols
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 */
 
 // for the UI
preferences {
    input("oneTimer", "text", title: "Zone One", description: "Zone One Time", required: false)
    input("twoTimer", "text", title: "Zone Two", description: "Zone Two Time", required: false)
    input("threeTimer", "text", title: "Zone Three", description: "Zone Three Time", required: false)
    input("fourTimer", "text", title: "Zone Four", description: "Zone Four Time", required: false)
    input("fiveTimer", "text", title: "Zone Five", description: "Zone Five Time", required: false)
    input("sixTimer", "text", title: "Zone Six", description: "Zone Six Time", required: false)
    input("sevenTimer", "text", title: "Zone Seven", description: "Zone Seven Time", required: false)
    input("eightTimer", "text", title: "Zone Eight", description: "Zone Eight Time", required: false)
}

metadata {
    definition (name: "Irrigation Controller 8 Zones v2.67", version: "2.67", author: "stan@dotson.info", namespace: "d8adrvn/smart_sprinkler") {
        
        capability "Switch"
        capability "Momentary"
 //       capability "Refresh"
        command "OnWithZoneTimes"
        command "RelayOn1"
        command "RelayOn1For"
        command "RelayOff1"
        command "RelayOn2"
        command "RelayOn2For"
        command "RelayOff2"
        command "RelayOn3"
        command "RelayOn3For"
        command "RelayOff3"
        command "RelayOn4"
        command "RelayOn4For"
        command "RelayOff4"
        command "RelayOn5"
        command "RelayOn5For"
        command "RelayOff5"
        command "RelayOn6"
        command "RelayOn6For"
        command "RelayOff6"
        command "RelayOn7"
        command "RelayOn7For"
        command "RelayOff7"
        command "RelayOn8"
        command "RelayOn8For"
        command "RelayOff8"
        command "rainDelayed"
        command "update" 
        command "enablePump"
        command "disablePump"
        command "onPump"
        command "offPump"
        command "noEffect"
        command "skip"
        command "expedite"
        command "onHold"
        attribute "effect", "string"
    }

    simulator {
        status "refresh_all_off" : "catchall: 0104 0000 01 01 0140 00 D919 00 00 0000 0A 00 0A6F6B2C6F6666312C6F6666322C6F6666332C6F6666342C6F6666352C6F6666362C6F6666372C6F6666382C"
        status "turn_all_on" : "catchall: 0104 0000 01 01 0140 00 D919 00 00 0000 0A 00 0A6F6B2C6F6E312C71322C71332C71342C71352C71362C71372C71382C"
    }
    
    tiles {
        standardTile("allZonesTile", "device.switch", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off", label: 'Start', action: "switch.on", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "starting"
            state "on", label: 'Running', action: "switch.off", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0", nextState: "stopping"
            state "starting", label: 'Starting...', action: "switch.off", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0"
            state "stopping", label: 'Stopping...', action: "switch.off", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0"
            state "rainDelayed", label: 'Rain Delay', action: "switch.off", icon: "st.Weather.weather10", backgroundColor: "#fff000", nextState: "off"
            state "autoStarting", label: 'Program starting...', action: "switch.off", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0"        
        }
        standardTile("zoneOneTile", "device.zoneOne", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off1", label: 'One', action: "RelayOn1", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff",nextState: "sending1"
            state "sending1", label: 'sending', action: "RelayOff1", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q1", label: 'One', action: "RelayOff1",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending1"
            state "on1", label: 'One', action: "RelayOff1",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending1"
            state "sendingOff1", label: 'sending', action: "RelayOff1", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        standardTile("zoneTwoTile", "device.zoneTwo", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off2", label: 'Two', action: "RelayOn2", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending2"
            state "sending2", label: 'sending', action: "RelayOff2", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q2", label: 'Two', action: "RelayOff2",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending2"
            state "on2", label: 'Two', action: "RelayOff2",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending2"
            state "sendingOff2", label: 'sending', action: "RelayOff2", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        standardTile("zoneThreeTile", "device.zoneThree", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off3", label: 'Three', action: "RelayOn3", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending3"
            state "sending3", label: 'sending', action: "RelayOff3", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q3", label: 'Three', action: "RelayOff3",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending3"
            state "on3", label: 'Three', action: "RelayOff3",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending3"
            state "sendingOff3", label: 'sending', action: "RelayOff3", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        standardTile("zoneFourTile", "device.zoneFour", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off4", label: 'Four', action: "RelayOn4", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending4"
            state "sending4", label: 'sending', action: "RelayOff4", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q4", label: 'Four', action: "RelayOff4",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending4"
            state "on4", label: 'Four', action: "RelayOff4",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending4"
            state "sendingOff4", label: 'sending', action: "RelayOff4", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        standardTile("zoneFiveTile", "device.zoneFive", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off5", label: 'Five', action: "RelayOn5", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending5"
            state "sending5", label: 'sending', action: "RelayOff5", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q5", label: 'Five', action: "RelayOff5",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending5"
            state "on5", label: 'Five', action: "RelayOff5",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending5"
            state "sendingOff5", label: 'sending', action: "RelayOff5", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        standardTile("zoneSixTile", "device.zoneSix", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off6", label: 'Six', action: "RelayOn6", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending6"
            state "sending6", label: 'sending', action: "RelayOff6", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q6", label: 'Six', action: "RelayOff6",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending6"
            state "on6", label: 'Six', action: "RelayOff6",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending6"
            state "sendingOff6", label: 'sending', action: "RelayOff6", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        standardTile("zoneSevenTile", "device.zoneSeven", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off7", label: 'Seven', action: "RelayOn7", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending7"
            state "sending7", label: 'sending', action: "RelayOff7", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q7", label: 'Seven', action: "RelayOff7",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending7"
            state "on7", label: 'Seven', action: "RelayOff7",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending7"
            state "sendingOff7", label: 'sending', action: "RelayOff7", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        standardTile("zoneEightTile", "device.zoneEight", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off8", label: 'Eight', action: "RelayOn8", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending8"
            state "sending8", label: 'sending', action: "RelayOff8", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q8", label: 'Eight', action: "RelayOff8",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending8"
            state "on8", label: 'Eight', action: "RelayOff8",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending8"
            state "sendingOff8", label: 'sending', action: "RelayOff8", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "havePump", label: 'Eight', action: "disablePump", icon: "st.custom.buttons.subtract-icon", backgroundColor: "#ffffff"

        }
        standardTile("pumpTile", "device.pump", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "noPump", label: 'Pump', action: "enablePump", icon: "st.custom.buttons.subtract-icon", backgroundColor: "#ffffff",nextState: "enablingPump"
         	state "offPump", label: 'Pump', action: "onPump", icon: "st.valves.water.closed", backgroundColor: "#ffffff", nextState: "sendingPump"
           	state "enablingPump", label: 'sending', action: "disablePump", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "disablingPump", label: 'sending', action: "disablePump", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "onPump", label: 'Pump', action: "offPump",icon: "st.valves.water.open", backgroundColor: "#53a7c0", nextState: "sendingPump"
            state "sendingPump", label: 'sending', action: "offPump", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        	
        standardTile("refreshTile", "device.refresh", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true, decoration: "flat") {
            state "ok", label: "", action: "update", icon: "st.secondary.refresh", backgroundColor: "#ffffff"
        }
        standardTile("scheduleEffect", "device.effect", width: 1, height: 1) {
            state("noEffect", label: "Normal", action: "skip", icon: "st.Office.office7", backgroundColor: "#ffffff")
            state("skip", label: "Skip 1X", action: "expedite", icon: "st.Office.office7", backgroundColor: "#c0a353")
            state("expedite", label: "Expedite", action: "onHold", icon: "st.Office.office7", backgroundColor: "#53a7c0")
            state("onHold", label: "Pause", action: "noEffect", icon: "st.Office.office7", backgroundColor: "#bc2323")
        }
        
        main "allZonesTile"
        details(["zoneOneTile","zoneTwoTile","zoneThreeTile","zoneFourTile","zoneFiveTile","zoneSixTile","zoneSevenTile","zoneEightTile", "pumpTile","scheduleEffect","refreshTile"])
    }
}

// parse events into attributes to create events
def parse(String description) {
//    log.debug "Parsing '${description}'"
//    log.debug "Parsed: ${zigbee.parse(description)}"
  
    
    def value = zigbee.parse(description)?.text
    if (value != null && value != " " && value != '"' && value != "ping" ) {
	 log.debug "Parsed: ${value}"
    }
    if (value != null && value != " " && value != '"' && value != "ping" && value != "havePump" && value != "noPump") {
        String delims = ","
        String[] tokens = value.split(delims)
        for (int x=0; x<tokens.length; x++) {
            def displayed = tokens[x]  //evaluates whether to display message

            def name = tokens[x] in ["on1", "q1", "off1"] ? "zoneOne"
            : tokens[x] in ["on2", "q2", "off2"] ? "zoneTwo"
            : tokens[x] in ["on3", "q3", "off3"] ? "zoneThree"
            : tokens[x] in ["on4", "q4", "off4"] ? "zoneFour"
            : tokens[x] in ["on5", "q5", "off5"] ? "zoneFive"
            : tokens[x] in ["on6", "q6", "off6"] ? "zoneSix"
            : tokens[x] in ["on7", "q7", "off7"] ? "zoneSeven"
            : tokens[x] in ["on8", "q8", "off8"] ? "zoneEight"
            : tokens[x] in ["onPump", "offPump"] ? "pump"
            : tokens[x] in ["ok"] ? "refresh" : null

            def currentVal = device.currentValue(name)

            def stateChange = true
            // It seems like this should work. When a state change is made due to a nextState parameter, the value is not changed.
            // if(currentVal) stateChange = currentVal != tokens[x]

            def result = createEvent(name: name, value: tokens[x], displayed: true, isStateChange: true, isPhysical: true)
            log.debug "Parse returned ${result?.descriptionText}"
            sendEvent(result)
        }
    }
    if (value == "pumpAdded") {
    	sendEvent (name:"zoneEight", value:"havePump", displayed: true, isStateChange: true, isPhysical: true)
        sendEvent (name:"pump", value:"offPump", displayed: true, isStateChange: true, isPhysical: true)
    }
    if (value == "pumpRemoved") {
    	sendEvent (name:"pump", value:"noPump", displayed: true, isStateChange: true, isPhysical: true)
    }

    if(anyZoneOn()) {
        return createEvent(name: "switch", value: "on", displayed: true)
    } else if (device.currentValue("switch") != "rainDelayed") {
        return createEvent(name: "switch", value: "off", displayed: true)
    }
}

def anyZoneOn() {
    if(device.currentValue("zoneOne") in ["on1","q1"]) return true;
    if(device.currentValue("zoneTwo") in ["on2","q2"]) return true;
    if(device.currentValue("zoneThree") in ["on3","q3"]) return true;
    if(device.currentValue("zoneFour") in ["on4","q4"]) return true;
    if(device.currentValue("zoneFive") in ["on5","q5"]) return true;
    if(device.currentValue("zoneSix") in ["on6","q6"]) return true;
    if(device.currentValue("zoneSeven") in ["on7","q7"]) return true;
    if(device.currentValue("zoneEight") in ["on8","q8"]) return true;

    false;
}

// handle commands
def RelayOn1() {
    log.info "Executing 'on,1'"
    zigbee.smartShield(text: "on,1,${oneTimer}").format()
}

def RelayOn1For(value) {
    value = checkTime(value)
    log.info "Executing 'on,1,$value'"
    zigbee.smartShield(text: "on,1,${value}").format()
}

def RelayOff1() {
    log.info "Executing 'off,1'"
    zigbee.smartShield(text: "off,1").format()
}

def RelayOn2() {
    log.info "Executing 'on,2'"
    zigbee.smartShield(text: "on,2,${twoTimer}").format()
}

def RelayOn2For(value) {
    value = checkTime(value)
    log.info "Executing 'on,2,$value'"
    zigbee.smartShield(text: "on,2,${value}").format()
}

def RelayOff2() {
    log.info "Executing 'off,2'"
    zigbee.smartShield(text: "off,2").format()
}

def RelayOn3() {
    log.info "Executing 'on,3'"
    zigbee.smartShield(text: "on,3,${threeTimer}").format()
}

def RelayOn3For(value) {
    value = checkTime(value)
    log.info "Executing 'on,3,$value'"
    zigbee.smartShield(text: "on,3,${value}").format()
}

def RelayOff3() {
    log.info "Executing 'off,3'"
    zigbee.smartShield(text: "off,3").format()
}

def RelayOn4() {
    log.info "Executing 'on,4'"
    zigbee.smartShield(text: "on,4,${fourTimer}").format()
}

def RelayOn4For(value) {
    value = checkTime(value)
    log.info "Executing 'on,4,$value'"
    zigbee.smartShield(text: "on,4,${value}").format()
}

def RelayOff4() {
    log.info "Executing 'off,4'"
    zigbee.smartShield(text: "off,4").format()
}

def RelayOn5() {
    log.info "Executing 'on,5'"
    zigbee.smartShield(text: "on,5,${fiveTimer}").format()
}

def RelayOn5For(value) {
    value = checkTime(value)
    log.info "Executing 'on,5,$value'"
    zigbee.smartShield(text: "on,5,${value}").format()
}

def RelayOff5() {
    log.info "Executing 'off,5'"
    zigbee.smartShield(text: "off,5").format()
}

def RelayOn6() {
    log.info "Executing 'on,6'"
    zigbee.smartShield(text: "on,6,${sixTimer}").format()
}

def RelayOn6For(value) {
    value = checkTime(value)
    log.info "Executing 'on,6,$value'"
    zigbee.smartShield(text: "on,6,${value}").format()
}

def RelayOff6() {
    log.info "Executing 'off,6'"
    zigbee.smartShield(text: "off,6").format()
}

def RelayOn7() {
    log.info "Executing 'on,7'"
    zigbee.smartShield(text: "on,7,${sevenTimer}").format()
}

def RelayOn7For(value) {
    value = checkTime(value)
    log.info "Executing 'on,7,$value'"
    zigbee.smartShield(text: "on,7,${value}").format()
}

def RelayOff7() {
    log.info "Executing 'off,7'"
    zigbee.smartShield(text: "off,7").format()
}

def RelayOn8() {
    log.info "Executing 'on,8'"
    zigbee.smartShield(text: "on,8,${eightTimer}").format()
}

def RelayOn8For(value) {
    value = checkTime(value)
    log.info "Executing 'on,8,$value'"
    zigbee.smartShield(text: "on,8,${value}").format()
}

def RelayOff8() {
    log.info "Executing 'off,8'"
    zigbee.smartShield(text: "off,8").format()
}

def on() {
    log.info "Executing 'allOn'"
    zigbee.smartShield(text: "allOn,${oneTimer ?: 0},${twoTimer ?: 0},${threeTimer ?: 0},${fourTimer ?: 0},${fiveTimer ?: 0},${sixTimer ?: 0},${sevenTimer ?: 0},${eightTimer ?: 0}").format()
}

def OnWithZoneTimes(value) {
    log.info "Executing 'allOn' with zone times [$value]"
    def evt = createEvent(name: "switch", value: "autoStarting", displayed: true)
    log.info("Sending: $evt")
    sendEvent(evt)
    
	def zoneTimes = [:]
    for(z in value.split(",")) {
    	def parts = z.split(":")
        zoneTimes[parts[0].toInteger()] = parts[1]
        log.info("Zone ${parts[0].toInteger()} on for ${parts[1]} minutes")
    }
    zigbee.smartShield(text: "allOn,${checkTime(zoneTimes[1]) ?: 0},${checkTime(zoneTimes[2]) ?: 0},${checkTime(zoneTimes[3]) ?: 0},${checkTime(zoneTimes[4]) ?: 0},${checkTime(zoneTimes[5]) ?: 0},${checkTime(zoneTimes[6]) ?: 0},${checkTime(zoneTimes[7]) ?: 0},${checkTime(zoneTimes[8]) ?: 0}").format()
}

def off() {
    log.info "Executing 'allOff'"
    zigbee.smartShield(text: "allOff").format()
}

def checkTime(t) {
	def time = (t ?: 0).toInteger()
    time > 60 ? 60 : time
}

def update() {
    log.info "Execting refresh"
    zigbee.smartShield(text: "update").format()
}
//def refresh() {
//    log.debug "Executing polling"
//    update()
//}

def rainDelayed() {
    log.info "rain delayed"
    if(device.currentValue("switch") != "on") {
        sendEvent (name:"switch", value:"rainDelayed", displayed: true)
    }
}

def enablePump() {
    log.info "Pump Enabled"
    zigbee.smartShield(text: "pump,3").format()  //pump is queued and ready to turn on when zone is activated
}
def disablePump() {
    log.info "Pump Disabled"
    zigbee.smartShield(text: "pump,0").format()  //remove pump from system, reactivate Zone8
}
def onPump() {
    log.info "Pump On"
    zigbee.smartShield(text: "pump,2").format()
    }

def offPump() {
	log.info "Pump Enabled"
    zigbee.smartShield(text: "pump,1").format()  //pump returned to queue state to turn on when zone turns on
        }
def push() {
    log.info "advance to next zone"
    zigbee.smartShield(text: "advance").format()  //turn off currently running zone and advance to next
    }

// commands that over-ride the SmartApp

// skip one scheduled watering
def	skip() {
    def evt = createEvent(name: "effect", value: "skip", displayed: true)
    log.info("Sending: $evt")
    sendEvent(evt)
}
// over-ride rain delay and water even if it rains
def	expedite() {
    def evt = createEvent(name: "effect", value: "expedite", displayed: true)
    log.info("Sending: $evt")
    sendEvent(evt)
}

// schedule operates normally
def	noEffect() {
    def evt = createEvent(name: "effect", value: "noEffect", displayed: true)
    log.info("Sending: $evt")
    sendEvent(evt)
}

// turn schedule off indefinitely
def	onHold() {
    def evt = createEvent(name: "effect", value: "onHold", displayed: true)
    log.info("Sending: $evt")
    sendEvent(evt)
}


