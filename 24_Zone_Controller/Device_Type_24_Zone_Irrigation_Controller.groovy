/**
 *  Irrigation Controller 24Zones with Master Valve and Pump Options 
 *  This SmartThings Device Type Code Works With Arduino Irrigation Controller for 24 zones v1.3 also available at this site
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
    input("oneTimer", "enum", title: "Zone One", description: "Zone One Time", required: false, defaultValue: 1)
    input("twoTimer", "enum", title: "Zone Two", description: "Zone Two Time", required: false, defaultValue: 1)
    input("threeTimer", "enum", title: "Zone Three", description: "Zone Three Time", required: false, defaultValue: 1)
    input("fourTimer", "enum", title: "Zone Four", description: "Zone Four Time", required: false, defaultValue: 1)
    input("fiveTimer", "enum", title: "Zone Five", description: "Zone Five Time", required: false, defaultValue: 1)
    input("sixTimer", "text", title: "Zone Six", description: "Zone Six Time", required: false, defaultValue: 1)
    input("sevenTimer", "text", title: "Zone Seven", description: "Zone Seven Time", required: false, defaultValue: 1)
    input("eightTimer", "text", title: "Zone Eight", description: "Zone Eight Time", required: false, defaultValue: "1")
    input("nineTimer", "text", title: "Zone Nine", description: "Zone Nine Time", required: false, defaultValue: "1")
    input("tenTimer", "text", title: "Zone Ten", description: "Zone Ten Time", required: false, defaultValue: "1")
    input("elevenTimer", "text", title: "Zone Eleven", description: "Zone Eleven Time", required: false, defaultValue: "1")
    input("twelveTimer", "text", title: "Zone Twelve", description: "Zone Twelve Time", required: false, defaultValue: "1")
    input("thirteenTimer", "text", title: "Zone Thirteen", description: "Zone Thirteen Time", required: false, defaultValue: "1")
    input("fourteenTimer", "text", title: "Zone Fourteen", description: "Zone Fourteen Time", required: false, defaultValue: "1")
    input("fifteenTimer", "text", title: "Zone Fifteen", description: "Zone Fifteen Time", required: false, defaultValue: "1")
    input("sixteenTimer", "text", title: "Zone Sixteen", description: "Zone Sixteen Time", required: false, defaultValue: "1")
    input("seventeenTimer", "text", title: "Zone Seventeen", description: "Zone Seventeen Time", required: false, defaultValue: "1")
    input("eightteenTimer", "text", title: "Zone Eighteen", description: "Zone Eighteen Time", required: false, defaultValue: "1")
    input("nineteenTimer", "text", title: "Zone Nineteen", description: "Zone Nineteen Time", required: false, defaultValue: "1")
    input("twentyTimer", "text", title: "Zone Twenty", description: "Zone Twenty Time", required: false, defaultValue: "1")
    input("twentyoneTimer", "text", title: "Zone Twentyone", description: "Zone Twentyone Time", required: false, defaultValue: "1")
    input("twentytwoTimer", "text", title: "Zone Twentytwo", description: "Zone Twentytwo Time", required: false, defaultValue: "1")
    input("twentythreeTimer", "text", title: "Zone Twentythree", description: "Zone Twentythree Time", required: false, defaultValue: "1")
    input("twentyfourTimer", "text", title: "Zone Twentyfour", description: "Zone Twentyfour Time", required: false, defaultValue: "1")    
    
}
metadata {
    definition (name: "Irrigation Controller 24 Zones with Optional Pump v3.01", version: "3.01", author: "stan@dotson.info", namespace: "d8adrvn/smart_sprinkler") 
    {
        capability "Switch"
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
        command "RelayOn9"
        command "RelayOn9For"
        command "RelayOff9"
        command "RelayOn10"
        command "RelayOn10For"
        command "RelayOff10"
        command "RelayOn11"
        command "RelayOn11For"
        command "RelayOff11"
        command "RelayOn12"
        command "RelayOn12For"
        command "RelayOff12"
        command "RelayOn13"
        command "RelayOn13For"
        command "RelayOff13"
        command "RelayOn14"
        command "RelayOn14For"
        command "RelayOff14"
        command "RelayOn15"
        command "RelayOn15For"
        command "RelayOff15"
        command "RelayOn16"
        command "RelayOn16For"
        command "RelayOff16"
        command "RelayOn17"
        command "RelayOn17For"
        command "RelayOff17"
        command "RelayOn18"
        command "RelayOn18For"
        command "RelayOff18"
        command "RelayOn19"
        command "RelayOn19For"
        command "RelayOff19"
        command "RelayOn20"
        command "RelayOn20For"
        command "RelayOff20"
        command "RelayOn21"
        command "RelayOn21For"
        command "RelayOff21"
        command "RelayOn22"
        command "RelayOn22For"
        command "RelayOff22"
        command "RelayOn23"
        command "RelayOn23For"
        command "RelayOff23"
        command "RelayOn24"
        command "RelayOn24For"
        command "RelayOff24"
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
        command "warning"
        attribute "effect", "string"
    }

    simulator {
   }



    
    tiles {
        standardTile("allZonesTile", "device.switch", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off", label: 'Start', action: "switch.on", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "starting"
            state "on", label: 'Running', action: "switch.off", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0", nextState: "stopping"
            state "starting", label: 'Starting...', action: "switch.off", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0"
            state "stopping", label: 'Stopping...', action: "switch.off", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0"
            state "rainDelayed", label: 'Rain Delay', action: "switch.off", icon: "st.Weather.weather10", backgroundColor: "#fff000", nextState: "off"
        	state "warning", label: 'Issue',  icon: "st.Health & Wellness.health7", backgroundColor: "#ff000f", nextState: "off"       
       }
        standardTile("zoneOneTile", "device.zoneOne", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o1", label: 'One', action: "RelayOn1", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff",nextState: "sending1"
            state "sending1", label: 'sending', action: "RelayOff1", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q1", label: 'One', action: "RelayOff1",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending1"
            state "r1", label: 'One', action: "RelayOff1",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending1"
        }
        standardTile("zoneTwoTile", "device.zoneTwo", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o2", label: 'Two', action: "RelayOn2", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending2"
            state "sending2", label: 'sending', action: "RelayOff2", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q2", label: 'Two', action: "RelayOff2",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending2"
            state "r2", label: 'Two', action: "RelayOff2",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending2"
        }
        standardTile("zoneThreeTile", "device.zoneThree", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o3", label: 'Three', action: "RelayOn3", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending3"
            state "sending3", label: 'sending', action: "RelayOff3", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q3", label: 'Three', action: "RelayOff3",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending3"
            state "r3", label: 'Three', action: "RelayOff3",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending3"
        }
        standardTile("zoneFourTile", "device.zoneFour", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o4", label: 'Four', action: "RelayOn4", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending4"
            state "sending4", label: 'sending', action: "RelayOff4", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q4", label: 'Four', action: "RelayOff4",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending4"
            state "r4", label: 'Four', action: "RelayOff4",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending4"
        }
        standardTile("zoneFiveTile", "device.zoneFive", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o5", label: 'Five', action: "RelayOn5", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending5"
            state "sending5", label: 'sending', action: "RelayOff5", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q5", label: 'Five', action: "RelayOff5",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending5"
            state "r5", label: 'Five', action: "RelayOff5",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending5"
        }
        standardTile("zoneSixTile", "device.zoneSix", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o6", label: 'Six', action: "RelayOn6", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending6"
            state "sending6", label: 'sending', action: "RelayOff6", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q6", label: 'Six', action: "RelayOff6",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending6"
            state "r6", label: 'Six', action: "RelayOff6",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending6"
        }
        standardTile("zoneSevenTile", "device.zoneSeven", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o7", label: 'Seven', action: "RelayOn7", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending7"
            state "sending7", label: 'sending', action: "RelayOff7", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q7", label: 'Seven', action: "RelayOff7",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending7"
            state "r7", label: 'Seven', action: "RelayOff7",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending7"
        }
        standardTile("zoneEightTile", "device.zoneEight", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o8", label: 'Eight', action: "RelayOn8", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending8"
            state "sending8", label: 'sending', action: "RelayOff8", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q8", label: 'Eight', action: "RelayOff8",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending8"
            state "r8", label: 'Eight', action: "RelayOff8",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending8"
        }
        standardTile("zoneNineTile", "device.zoneNine", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o9", label: 'Nine', action: "RelayOn9", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending9"
            state "sending9", label: 'sending', action: "RelayOff9", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q9", label: 'Nine', action: "RelayOff9",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending9"
            state "r9", label: 'Nine', action: "RelayOff9",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending9"
        }       
        standardTile("zoneTenTile", "device.zoneTen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o10", label: 'Ten', action: "RelayOn10", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending10"
            state "sending10", label: 'sending', action: "RelayOff10", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q10", label: 'Ten', action: "RelayOff10",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending10"
            state "r10", label: 'Ten', action: "RelayOff10",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending10"
        } 
        standardTile("zoneElevenTile", "device.zoneEleven", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o11", label: 'Eleven', action: "RelayOn11", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending11"
            state "sending11", label: 'sending', action: "RelayOff11", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q11", label: 'Eleven', action: "RelayOff11",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending11"
            state "r11", label: 'Eleven', action: "RelayOff11",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending11"
        } 
        standardTile("zoneTwelveTile", "device.zoneTwelve", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o12", label: 'Twelve', action: "RelayOn12", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending12"
            state "sending12", label: 'sending', action: "RelayOff12", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q12", label: 'Twelve', action: "RelayOff12",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending12"
            state "r12", label: 'Twelve', action: "RelayOff12",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending12"
        } 
        standardTile("zoneThirteenTile", "device.zoneThirteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o13", label: 'Thirteen', action: "RelayOn13", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending13"
            state "sending13", label: 'sending', action: "RelayOff13", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q13", label: 'Thirteen', action: "RelayOff13",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending13"
            state "r13", label: 'Thirteen', action: "RelayOff13",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending13"
        } 
        standardTile("zoneFourteenTile", "device.zoneFourteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o14", label: 'Fourteen', action: "RelayOn14", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending14"
            state "sending14", label: 'sending', action: "RelayOff14", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q14", label: 'Fourteen', action: "RelayOff14",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending14"
            state "r14", label: 'Fourteen', action: "RelayOff14",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending14"
        }

        standardTile("zoneFifteenTile", "device.zoneFifteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o15", label: 'Fifteen', action: "RelayOn15", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending15"
            state "sending15", label: 'sending', action: "RelayOff15", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q15", label: 'Fifteen', action: "RelayOff15",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending15"
            state "r15", label: 'Fifteen', action: "RelayOf15",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending15"
        } 
        standardTile("zoneSixteenTile", "device.zoneSixteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o16", label: 'Sixteen', action: "RelayOn16", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending16"
            state "sending16", label: 'sending', action: "RelayOff16", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q16", label: 'Sixteen', action: "RelayOff16",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending16"
            state "r16", label: 'Sixteen', action: "RelayOff16",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending16"
        } 
        standardTile("zoneSeventeenTile", "device.zoneSeventeen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o17", label: 'Seventeen', action: "RelayOn17", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending17"
            state "sending17", label: 'sending', action: "RelayOff17", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q17", label: 'Seventeen', action: "RelayOff17",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending17"
            state "r17", label: 'Seventeen', action: "RelayOff17",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending17"
        } 
        standardTile("zoneEighteenTile", "device.zoneEightteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o18", label: 'Eighteen', action: "RelayOn18", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending18"
            state "sending18", label: 'sending', action: "RelayOff18", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q18", label: 'Eighteen', action: "RelayOff18",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending18"
            state "r18", label: 'Eighteen', action: "RelayOff18",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending18"
        } 
        standardTile("zoneNineteenTile", "device.zoneNineteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o19", label: 'Nineteen', action: "RelayOn19", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending19"
            state "sending19", label: 'sending', action: "RelayOff19", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q19", label: 'Nineteen', action: "RelayOff19",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending19"
            state "r19", label: 'Nineteen', action: "RelayOff19",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending19"
        } 
        standardTile("zoneTwentyTile", "device.zoneTwenty", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o20", label: 'Twenty', action: "RelayOn20", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending20"
            state "sending20", label: 'sending', action: "RelayOff20", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q20", label: 'Twenty', action: "RelayOff20",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending20"
            state "r20", label: 'Twenty', action: "RelayOff20",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending20"
        }
        standardTile("zoneTwentyoneTile", "device.zoneTwentyone", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o21", label: 'Twentyone', action: "RelayOn21", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff",nextState: "sending21"
            state "sending21", label: 'sending', action: "RelayOff21", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q21", label: 'Twentyone', action: "RelayOff21",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending21"
            state "r21", label: 'Twentyone', action: "RelayOff21",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending21"
        }
        standardTile("zoneTwentytwoTile", "device.zoneTwentytwo", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o22", label: 'Twentytwo', action: "RelayOn22", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending22"
            state "sending22", label: 'sending', action: "RelayOff22", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q22", label: 'Twentytwo', action: "RelayOff22",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending22"
            state "r22", label: 'Twentytwo', action: "RelayOff22",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending22"
        }
        standardTile("zoneTwentythreeTile", "device.zoneTwentythree", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o23", label: 'Twentythree', action: "RelayOn23", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending23"
            state "sending23", label: 'sending', action: "RelayOff23", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q23", label: 'Twentythree', action: "RelayOff23",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending23"
            state "r23", label: 'Twentythree', action: "RelayOff23",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending23"
        }
        standardTile("zoneTwentyfourTile", "device.zoneTwentyfour", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "o24", label: 'Twentyfour', action: "RelayOn24", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending24"
            state "sending24", label: 'sending', action: "RelayOff24", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q24", label: 'Twentyfour', action: "RelayOff24",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending24"
            state "r24", label: 'Twentyfour', action: "RelayOff24",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending24"
            state "havePump", label: 'Twentyfour', action: "disablePump", icon: "st.custom.buttons.subtract-icon", backgroundColor: "#ffffff"
		}                
        standardTile("pumpTile", "device.pump", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "noPump", label: 'Pump', action: "enablePump", icon: "st.custom.buttons.subtract-icon", backgroundColor: "#ffffff",nextState: "enablingPump"
            state "offPump", label: 'Pump', action: "onPump", icon: "st.valves.water.closed", backgroundColor: "#ffffff", nextState: "sendingPump"
            state "enablingPump", label: 'sending', action: "disablePump", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "disablingPump", label: 'sending', action: "disablePump", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "onPump", label: 'Pump', action: "offPump",icon: "st.valves.water.open", backgroundColor: "#53a7c0", nextState: "sendingPump"
            state "sendingPump", label: 'sending', action: "offPump", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }    	
     	standardTile("scheduleEffect", "device.effect", width: 1, height: 1) {
            state("noEffect", label: "Normal", action: "skip", icon: "st.Office.office7", backgroundColor: "#ffffff")
            state("skip", label: "Skip 1X", action: "expedite", icon: "st.Office.office7", backgroundColor: "#c0a353")
            state("expedite", label: "Expedite", action: "onHold", icon: "st.Office.office7", backgroundColor: "#53a7c0")
            state("onHold", label: "Pause", action: "noEffect", icon: "st.Office.office7", backgroundColor: "#bc2323")
        }
        
        standardTile("refreshTile", "device.refresh", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true, decoration: "flat") {
            state "ok", label: "", action: "update", icon: "st.secondary.refresh", backgroundColor: "#ffffff"
        }
        main "allZonesTile"
        details(["zoneOneTile","zoneTwoTile","zoneThreeTile","zoneFourTile","zoneFiveTile","zoneSixTile","zoneSevenTile","zoneEightTile", "zoneNineTile", "zoneTenTile", "zoneElevenTile", "zoneTwelveTile", "zoneThirteenTile", "zoneFourteenTile", "zoneFifteenTile", "zoneSixteenTile", "zoneSeventeenTile", "zoneEighteenTile", "zoneNineteenTile", "zoneTwentyTile", "zoneTwentyoneTile", "zoneTwentytwoTile", "zoneTwentythreeTile", "zoneTwentyfourTile", "pumpTile", "scheduleEffect", "refreshTile"])
    }
}

// parse events into attributes to create events
def parse(String description) {
//    log.debug "Parsing '${description}'"
  
    def value = zigbee.parse(description)?.text
    if (value != null && !value.contains("ping") && value.trim().length() > 0 && value != "havePump" && value != "noPump" && value != "pumpRemoved") {
     	String delims = ","
		String[] tokens = value.split(delims)

        for (int x=0; x<tokens.length; x++) {
            def displayed = tokens[x] && tokens[x] != "ping"  //evaluates whether to display message

           def name = tokens[x] in ["r1", "q1", "o1"] ? "zoneOne"
            : tokens[x] in ["r2", "q2", "o2"] ? "zoneTwo"
            : tokens[x] in ["r3", "q3", "o3"] ? "zoneThree"
            : tokens[x] in ["r4", "q4", "o4"] ? "zoneFour"
            : tokens[x] in ["r5", "q5", "o5"] ? "zoneFive"
            : tokens[x] in ["r6", "q6", "o6"] ? "zoneSix"
            : tokens[x] in ["r7", "q7", "o7"] ? "zoneSeven"
            : tokens[x] in ["r8", "q8", "o8"] ? "zoneEight"
            : tokens[x] in ["r9", "q9", "o9"] ? "zoneNine"
            : tokens[x] in ["r10", "q10", "o10"] ? "zoneTen"
            : tokens[x] in ["r11", "q11", "o11"] ? "zoneEleven"
            : tokens[x] in ["r12", "q12", "o12"] ? "zoneTwelve"
            : tokens[x] in ["r13", "q13", "o13"] ? "zoneThirteen"
            : tokens[x] in ["r14", "q14", "o14"] ? "zoneFourteen"
            : tokens[x] in ["r15", "q15", "o15"] ? "zoneFifteen"
            : tokens[x] in ["r16", "q16", "o16"] ? "zoneSixteen"
            : tokens[x] in ["r17", "q17", "o17"] ? "zoneSeventeen"
            : tokens[x] in ["r18", "q18", "o18"] ? "zoneEightteen"
            : tokens[x] in ["r19", "q19", "o19"] ? "zoneNineteen"
            : tokens[x] in ["r20", "q20", "o20"] ? "zoneTwenty"
            : tokens[x] in ["r21", "q21", "o21"] ? "zoneTwentyone"
            : tokens[x] in ["r22", "q22", "o22"] ? "zoneTwentytwo"
            : tokens[x] in ["r23", "q23", "o23"] ? "zoneTwentythree"
            : tokens[x] in ["r24", "q24", "o24"] ? "zoneTwentyfour"
            : tokens[x] in ["onPump", "offPump"] ? "pump"
            : tokens[x] in ["ok"] ? "refresh" : null

            //manage and display events
            def currentVal = device.currentValue(name)
            def isDisplayed = true
            def isPhysical = true
            
            //manage which events are displayed in log
			if (tokens[x].contains("q")) {
				isDisplayed = false
                isPhysical = false
            }
            if (tokens[x].contains("o") && currentVal.contains("q")) {
				isDisplayed = false
            	isPhysical = false
            }
            
			//send an event if there is a state change
			if (currentVal != tokens[x]) {
				def result = createEvent(name: name, value: tokens[x], displayed: isDisplayed, isStateChange: true, isPhysical: isPhysical)
//            	log.debug "Parse returned ${result?.descriptionText}"
            	sendEvent(result)
            }
        }
    }
    if (value == "pumpAdded") {
    	//send an event if there is a state change
        if (device.currentValue("zoneTwentyfour") != "havePump" && device.currentValue("pump") != "offPump") {
    		sendEvent (name:"zoneTwentyfour", value:"havePump", displayed: true, isStateChange: true, isPhysical: true)
        	sendEvent (name:"pump", value:"offPump", displayed: true, isStateChange: true, isPhysical: true)
    	}
    }
    if (value == "pumpRemoved") {
    	//send event if there is a state change
        if (device.currentValue("pump") != "noPump") {
    		sendEvent (name:"pump", value:"noPump", displayed: true, isStateChange: true, isPhysical: true)
    	}
    }

    if(anyZoneOn()) {
        //manages the state of the overall system.  Overall state is "on" if any zone is on
        //set displayed to false; does not need to be logged in mobile app
        return createEvent(name: "switch", value: "on", descriptionText: "Irrigation Is On", displayed: false)
    } else if (device.currentValue("switch") != "rainDelayed") {
        return createEvent(name: "switch", value: "off", descriptionText: "Irrigation Is Off", displayed: false)
    }
}

def anyZoneOn() {
    if(device.currentValue("zoneOne") in ["r1","q1"]) return true;
    if(device.currentValue("zoneTwo") in ["r2","q2"]) return true;
    if(device.currentValue("zoneThree") in ["r3","q3"]) return true;
    if(device.currentValue("zoneFour") in ["r4","q4"]) return true;
    if(device.currentValue("zoneFive") in ["r5","q5"]) return true;
    if(device.currentValue("zoneSix") in ["r6","q6"]) return true;
    if(device.currentValue("zoneSeven") in ["r7","q7"]) return true;
    if(device.currentValue("zoneEight") in ["r8","q8"]) return true;
    if(device.currentValue("zoneNine") in ["r9","q9"]) return true;
    if(device.currentValue("zoneTen") in ["r10","q10"]) return true;
    if(device.currentValue("zoneEleven") in ["r11","q11"]) return true;
    if(device.currentValue("zoneTwelve") in ["r12","q12"]) return true;
    if(device.currentValue("zoneThirteen") in ["r13","q13"]) return true;
    if(device.currentValue("zoneFourteen") in ["r14","q14"]) return true;
    if(device.currentValue("zoneFifteen") in ["r15","q15"]) return true;
    if(device.currentValue("zoneSixteen") in ["r16","q16"]) return true;
    if(device.currentValue("zoneSeventeen") in ["r17","q17"]) return true;
    if(device.currentValue("zoneEightteen") in ["r18","q18"]) return true;
    if(device.currentValue("zoneNineteen") in ["r19","q19"]) return true;
    if(device.currentValue("zoneTwenty") in ["r20","q20"]) return true;
    if(device.currentValue("zoneTwentyone") in ["r21","q21"]) return true;
    if(device.currentValue("zoneTwentytwo") in ["r22","q22"]) return true;
    if(device.currentValue("zoneTwentythree") in ["r23","q23"]) return true;
    if(device.currentValue("zoneTwentyfour") in ["r24","q24"]) return true;

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

def RelayOn9() {
    log.info "Executing 'on,9'"
    zigbee.smartShield(text: "on,9,${eightTimer}").format()
}

def RelayOn9For(value) {
    value = checkTime(value)
    log.info "Executing 'on,9,$value'"
    zigbee.smartShield(text: "on,9,${value}").format()
}

def RelayOff9() {
    log.info "Executing 'off,9'"
    zigbee.smartShield(text: "off,9").format()
}

def RelayOn10() {
    log.info "Executing 'on,10'"
    zigbee.smartShield(text: "on,10,${eightTimer}").format()
}

def RelayOn10For(value) {
    value = checkTime(value)
    log.info "Executing 'on,10,$value'"
    zigbee.smartShield(text: "on,10,${value}").format()
}

def RelayOff10() {
    log.info "Executing 'off,10'"
    zigbee.smartShield(text: "off,10").format()
}

def RelayOn11() {
    log.info "Executing 'on,11'"
    zigbee.smartShield(text: "on,11,${oneTimer}").format()
}

def RelayOn11For(value) {
    value = checkTime(value)
    log.info "Executing 'on,11,$value'"
    zigbee.smartShield(text: "on,11,${value}").format()
}

def RelayOff11() {
    log.info "Executing 'off,11'"
    zigbee.smartShield(text: "off,11").format()
}

def RelayOn12() {
    log.info "Executing 'on,12'"
    zigbee.smartShield(text: "on,12,${twoTimer}").format()
}

def RelayOn12For(value) {
    value = checkTime(value)
    log.info "Executing 'on,12,$value'"
    zigbee.smartShield(text: "on,12,${value}").format()
}

def RelayOff12() {
    log.info "Executing 'off,12'"
    zigbee.smartShield(text: "off,12").format()
}

def RelayOn13() {
    log.info "Executing 'on,13'"
    zigbee.smartShield(text: "on,13,${threeTimer}").format()
}

def RelayOn13For(value) {
    value = checkTime(value)
    log.info "Executing 'on,13,$value'"
    zigbee.smartShield(text: "on,13,${value}").format()
}

def RelayOff13() {
    log.info "Executing 'off,13'"
    zigbee.smartShield(text: "off,13").format()
}

def RelayOn14() {
    log.info "Executing 'on,14'"
    zigbee.smartShield(text: "on,14,${fourTimer}").format()
}

def RelayOn14For(value) {
    value = checkTime(value)
    log.info "Executing 'on,14,$value'"
    zigbee.smartShield(text: "on,14,${value}").format()
}

def RelayOff14() {
    log.info "Executing 'off,14'"
    zigbee.smartShield(text: "off,14").format()
}

def RelayOn15() {
    log.info "Executing 'on,15'"
    zigbee.smartShield(text: "on,15,${fiveTimer}").format()
}

def RelayOn15For(value) {
    value = checkTime(value)
    log.info "Executing 'on,15,$value'"
    zigbee.smartShield(text: "on,15,${value}").format()
}

def RelayOff15() {
    log.info "Executing 'off,15'"
    zigbee.smartShield(text: "off,15").format()
}

def RelayOn16() {
    log.info "Executing 'on,16'"
    zigbee.smartShield(text: "on,16,${sixTimer}").format()
}

def RelayOn16For(value) {
    value = checkTime(value)
    log.info "Executing 'on,16,$value'"
    zigbee.smartShield(text: "on,16,${value}").format()
}

def RelayOff16() {
    log.info "Executing 'off,16'"
    zigbee.smartShield(text: "off,16").format()
}

def RelayOn17() {
    log.info "Executing 'on,17'"
    zigbee.smartShield(text: "on,17,${sevenTimer}").format()
}

def RelayOn17For(value) {
    value = checkTime(value)
    log.info "Executing 'on,17,$value'"
    zigbee.smartShield(text: "on,17,${value}").format()
}

def RelayOff17() {
    log.info "Executing 'off,17'"
    zigbee.smartShield(text: "off,17").format()
}

def RelayOn18() {
    log.info "Executing 'on,18'"
    zigbee.smartShield(text: "on,18,${eightTimer}").format()
}

def RelayOn18For(value) {
    value = checkTime(value)
    log.info "Executing 'on,18,$value'"
    zigbee.smartShield(text: "on,18,${value}").format()
}

def RelayOff18() {
    log.info "Executing 'off,18'"
    zigbee.smartShield(text: "off,18").format()
}

def RelayOn19() {
    log.info "Executing 'on,19'"
    zigbee.smartShield(text: "on,19,${eightTimer}").format()
}

def RelayOn19For(value) {
    value = checkTime(value)
    log.info "Executing 'on,19,$value'"
    zigbee.smartShield(text: "on,19,${value}").format()
}

def RelayOff19() {
    log.info "Executing 'off,19'"
    zigbee.smartShield(text: "off,19").format()
}

def RelayOn20() {
    log.info "Executing 'on,20'"
    zigbee.smartShield(text: "on,20,${eightTimer}").format()
}

def RelayOn20For(value) {
    value = checkTime(value)
    log.info "Executing 'on,20,$value'"
    zigbee.smartShield(text: "on,20,${value}").format()
}

def RelayOff20() {
    log.info "Executing 'off,20'"
    zigbee.smartShield(text: "off,20").format()
}

def RelayOn21() {
    log.info "Executing 'on,21'"
    zigbee.smartShield(text: "on,21,${oneTimer}").format()
}

def RelayOn21For(value) {
    value = checkTime(value)
    log.info "Executing 'on,21,$value'"
    zigbee.smartShield(text: "on,21,${value}").format()
}

def RelayOff21() {
    log.info "Executing 'off,21'"
    zigbee.smartShield(text: "off,21").format()
}

def RelayOn22() {
    log.info "Executing 'on,22'"
    zigbee.smartShield(text: "on,22,${twoTimer}").format()
}

def RelayOn22For(value) {
    value = checkTime(value)
    log.info "Executing 'on,22,$value'"
    zigbee.smartShield(text: "on,22,${value}").format()
}

def RelayOff22() {
    log.info "Executing 'off,22'"
    zigbee.smartShield(text: "off,22").format()
}

def RelayOn23() {
    log.info "Executing 'on,23'"
    zigbee.smartShield(text: "on,23,${threeTimer}").format()
}

def RelayOn23For(value) {
    value = checkTime(value)
    log.info "Executing 'on,23,$value'"
    zigbee.smartShield(text: "on,23,${value}").format()
}

def RelayOff23() {
    log.info "Executing 'off,23'"
    zigbee.smartShield(text: "off,23").format()
}

def RelayOn24() {
    log.info "Executing 'on,24'"
    zigbee.smartShield(text: "on,24,${threeTimer}").format()
}

def RelayOn24For(value) {
    value = checkTime(value)
    log.info "Executing 'on,24,$value'"
    zigbee.smartShield(text: "on,24,${value}").format()
}

def RelayOff24() {
    log.info "Executing 'off,24'"
    zigbee.smartShield(text: "off,24").format()
    
}

def on() {
//split this up into two seperate payloads to keep each payload under the 63 character limit.
    log.info "Executing allOn"
	[
    zigbee.smartShield(text: "allOn1,${oneTimer ?: 0},${twoTimer ?: 0},${threeTimer ?: 0},${fourTimer ?: 0},${fiveTimer ?: 0},${sixTimer ?: 0},${sevenTimer ?: 0},${eightTimer ?: 0},${nineTimer ?: 0},${tenTimer ?: 0},${elevenTimer ?: 0},${twelveTimer ?: 0},${thirteenTimer ?: 0},${fourteenTimer ?: 0},${fifteenTimer ?: 0},${sixteenTimer ?: 0}").format(),
    "delay 20000",
    zigbee.smartShield(text: "allOn2,${seventeenTimer ?: 0},${eightteenTimer ?: 0},${nineteenTimer ?: 0},${twentyTimer ?: 0},${twentyoneTimer ?: 0},${twentytwoTimer ?: 0},${twentythreeTimer ?: 0},${twentyfourTimer ?: 0}").format()
	]
}

def OnWithZoneTimes(value) {
    log.info "Executing 'allOn' with zone times [$value]"
    def evt = createEvent(name: "switch", value: "starting", displayed: true)
    sendEvent(evt)
    
	def zoneTimes = [:]
    for(z in value.split(",")) {
    	def parts = z.split(":")
        zoneTimes[parts[0].toInteger()] = parts[1]
        log.info("Zone ${parts[0].toInteger()} on for ${parts[1]} minutes")
    }
//this up into two seperate payloads to keep each payload under the 63 character limit.
    [
    zigbee.smartShield(text: "allOn1,${checkTime(zoneTimes[1]) ?: 0},${checkTime(zoneTimes[2]) ?: 0},${checkTime(zoneTimes[3]) ?: 0},${checkTime(zoneTimes[4]) ?: 0},${checkTime(zoneTimes[5]) ?: 0},${checkTime(zoneTimes[6]) ?: 0},${checkTime(zoneTimes[7]) ?: 0},${checkTime(zoneTimes[8]) ?: 0},${checkTime(zoneTimes[9]) ?: 0},${checkTime(zoneTimes[10]) ?: 0},${checkTime(zoneTimes[11]) ?: 0},${checkTime(zoneTimes[12]) ?: 0},${checkTime(zoneTimes[13]) ?: 0},${checkTime(zoneTimes[14]) ?: 0},${checkTime(zoneTimes[15]) ?: 0},${checkTime(zoneTimes[16]) ?: 0}").format(),
    "delay 20000",
    zigbee.smartShield(text: "allOn2,${checkTime(zoneTimes[17]) ?: 0},${checkTime(zoneTimes[18]) ?: 0},${checkTime(zoneTimes[19]) ?: 0},${checkTime(zoneTimes[20]) ?: 0},${checkTime(zoneTimes[21]) ?: 0},${checkTime(zoneTimes[22]) ?: 0},${checkTime(zoneTimes[23]) ?: 0},${checkTime(zoneTimes[24]) ?: 0}").format()
	]
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

def rainDelayed() {
    log.info "rain delayed"
    if(device.currentValue("switch") != "on") {
        sendEvent (name:"switch", value:"rainDelayed", displayed: true)
    }
}

def warning() {
    log.info "Warning: Programmed Irrigation Did Not Start"
    if(device.currentValue("switch") != "on") {
        sendEvent (name:"switch", value:"warning", displayed: true)
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



