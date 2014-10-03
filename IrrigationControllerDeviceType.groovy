/**
 *  Irrigation Controller
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
    input("nineTimer", "text", title: "Zone Nine", description: "Zone Nine Time", required: false)
    input("tenTimer", "text", title: "Zone Ten", description: "Zone Ten Time", required: false)
    input("elevenTimer", "text", title: "Zone Eleven", description: "Zone Eleven Time", required: false)
    input("twelveTimer", "text", title: "Zone Twelve", description: "Zone Twelve Time", required: false)
    input("thirteenTimer", "text", title: "Zone Thirteen", description: "Zone Thirteen Time", required: false)
    input("fourteenTimer", "text", title: "Zone Fourteen", description: "Zone Fourteen Time", required: false)
    input("fifteenTimer", "text", title: "Zone Fifteen", description: "Zone Fifteen Time", required: false)
    input("sixteenTimer", "text", title: "Zone Sixteen", description: "Zone Sixteen Time", required: false)
    input("seventeenTimer", "text", title: "Zone Seventeen", description: "Zone Seventeen Time", required: false)
    input("eightteenTimer", "text", title: "Zone Eighteen", description: "Zone Eighteen Time", required: false)
    input("nineteenTimer", "text", title: "Zone Nineteen", description: "Zone Nineteen Time", required: false)
    input("twentyTimer", "text", title: "Zone Twenty", description: "Zone Twenty Time", required: false)
    input("twentyoneTimer", "text", title: "Zone Twentyone", description: "Zone Twentyone Time", required: false)
    input("twentytwoTimer", "text", title: "Zone Twentytwo", description: "Zone Twentytwo Time", required: false)
    input("twentytheeTimer", "text", title: "Zone Twentythree", description: "Zone Twentythree Time", required: false)
    
}

metadata {
    definition (name: "Irrigation Controller v2.63", version: "2.63", author: "stan@dotson.info", namespace: "d8adrvn/smart_sprinkler") {
        
        fingerprint profileId: "0104", deviceId: "0138", inClusters: "0000"
        
        capability "Switch"
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
        command "rainDelayed"
        command "update" 
        command "enablePump"
        command "disablePump"
        command "onPump"
        command "offPump"
    }

    simulator {
        status "refresh_all_off" : "catchall: 0104 0000 01 01 0140 00 D919 00 00 0000 0A 00 0A6F6B2C6F6666312C6F6666322C6F6666332C6F6666342C6F6666352C6F6666362C6F6666372C6F6666382C"
        status "turn_all_on" : "catchall: 0104 0000 01 01 0140 00 D919 00 00 0000 0A 00 0A6F6B2C6F6E312C71322C71332C71342C71352C71362C71372C71382C"
    }



    
    tiles {
        standardTile("allZonesTile", "device.switch", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off", label: 'Start', action: "switch.on", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "starting"
            state "on", label: 'Running', action: "switch.off", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0", nextState: "stopping"
            state "starting", label: 'Starting...', action: "switch.on", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0"
            state "stopping", label: 'Stopping...', action: "switch.off", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0"
            state "rainDelayed", label: 'Rain Delay', action: "switch.off", icon: "st.Weather.weather10", backgroundColor: "#fff000", nextState: "off"
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
        }
        standardTile("zoneNineTile", "device.zoneNine", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off9", label: 'Nine', action: "RelayOn9", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending9"
            state "sending9", label: 'sending', action: "RelayOff9", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q9", label: 'Nine', action: "RelayOff9",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending9"
            state "on9", label: 'Nine', action: "RelayOff9",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending9"
            state "sendingOff9", label: 'sending', action: "RelayOff9", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }       
        standardTile("zoneTenTile", "device.zoneTen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off10", label: 'Ten', action: "RelayOn10", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending10"
            state "sending10", label: 'sending', action: "RelayOff19", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q10", label: 'Ten', action: "RelayOff10",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending10"
            state "on10", label: 'Ten', action: "RelayOff10",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending10"
            state "sendingOff10", label: 'sending', action: "RelayOff10", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        } 
        standardTile("zoneElevenTile", "device.zoneEleven", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off11", label: 'Eleven', action: "RelayOn11", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending11"
            state "sending11", label: 'sending', action: "RelayOff11", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q11", label: 'Eleven', action: "RelayOff11",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending11"
            state "on11", label: 'Eleven', action: "RelayOff11",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending11"
            state "sendingOff11", label: 'sending', action: "RelayOff11", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        } 
        standardTile("zoneTwelveTile", "device.zoneTwelve", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off12", label: 'Twelve', action: "RelayOn12", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending12"
            state "sending12", label: 'sending', action: "RelayOff12", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q12", label: 'Twelve', action: "RelayOff12",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending12"
            state "on12", label: 'Twelve', action: "RelayOff12",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending12"
            state "sendingOff12", label: 'sending', action: "RelayOff12", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        } 
        standardTile("zoneThirteenTile", "device.zoneThirteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off13", label: 'Thirteen', action: "RelayOn13", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending13"
            state "sending13", label: 'sending', action: "RelayOff13", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q13", label: 'Thirteen', action: "RelayOff13",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending13"
            state "on13", label: 'Thirteen', action: "RelayOff13",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending13"
            state "sendingOff13", label: 'sending', action: "RelayOff13", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        } 
        standardTile("zoneFourteenTile", "device.zoneFourteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off14", label: 'Fourteen', action: "RelayOn14", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending14"
            state "sending14", label: 'sending', action: "RelayOff14", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q14", label: 'Fourteen', action: "RelayOff14",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending14"
            state "on14", label: 'Fourteen', action: "RelayOff14",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending14"
            state "sendingOff14", label: 'sending', action: "RelayOff14", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }

        standardTile("zoneFifteenTile", "device.zoneFifteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off15", label: 'Fifteen', action: "RelayOn15", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending15"
            state "sending15", label: 'sending', action: "RelayOff15", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q15", label: 'Fifteen', action: "RelayOff15",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending15"
            state "on15", label: 'Fifteen', action: "RelayOf15",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending15"
            state "sendingOff15", label: 'sending', action: "RelayOff15", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        } 
        standardTile("zoneSixteenTile", "device.zoneSixteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off16", label: 'Sixteen', action: "RelayOn16", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending16"
            state "sending16", label: 'sending', action: "RelayOff16", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q16", label: 'Sixteen', action: "RelayOff16",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending16"
            state "on16", label: 'Sixteen', action: "RelayOff16",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending16"
            state "sendingOff16", label: 'sending', action: "RelayOff16", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        } 
        standardTile("zoneSeventeenTile", "device.zoneSeventeen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off17", label: 'Seventeen', action: "RelayOn17", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending17"
            state "sending17", label: 'sending', action: "RelayOff17", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q17", label: 'Seventeen', action: "RelayOff17",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending17"
            state "on17", label: 'Seventeen', action: "RelayOff17",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending17"
            state "sendingOff17", label: 'sending', action: "RelayOff17", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        } 
        standardTile("zoneEighteenTile", "device.zoneEighteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off18", label: 'Eighteen', action: "RelayOn18", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending18"
            state "sending18", label: 'sending', action: "RelayOff18", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q18", label: 'Eighteen', action: "RelayOff18",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending18"
            state "on18", label: 'Eighteen', action: "RelayOff18",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending18"
            state "sendingOff18", label: 'sending', action: "RelayOff18", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        } 
        standardTile("zoneNineteenTile", "device.zoneNineteen", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off19", label: 'Nineteen', action: "RelayOn19", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending19"
            state "sending19", label: 'sending', action: "RelayOff19", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q19", label: 'Nineteen', action: "RelayOff19",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending19"
            state "on19", label: 'Nineteen', action: "RelayOff19",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending19"
            state "sendingOff19", label: 'sending', action: "RelayOff19", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        } 
        standardTile("zoneTwentyTile", "device.zoneTwenty", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off20", label: 'Twenty', action: "RelayOn20", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending20"
            state "sending20", label: 'sending', action: "RelayOff20", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q20", label: 'Twenty', action: "RelayOff20",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending20"
            state "on20", label: 'Twenty', action: "RelayOff20",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending20"
            state "sendingOff20", label: 'sending', action: "RelayOff20", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        standardTile("zoneTwentyoneTile", "device.zoneTwentyone", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off21", label: 'Twentyone', action: "RelayOn21", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff",nextState: "sending21"
            state "sending21", label: 'sending', action: "RelayOff21", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q21", label: 'Twentyone', action: "RelayOff21",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending21"
            state "on21", label: 'Twentyone', action: "RelayOff21",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending21"
            state "sendingOff21", label: 'sending', action: "RelayOff21", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        standardTile("zoneTwentytwoTile", "device.zoneTwentytwo", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off22", label: 'Twentytwo', action: "RelayOn22", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending22"
            state "sending22", label: 'sending', action: "RelayOff22", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q22", label: 'Twentytwo', action: "RelayOff22",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending22"
            state "on22", label: 'Twentytwo', action: "RelayOff22",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending22"
            state "sendingOff22", label: 'sending', action: "RelayOff2", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        standardTile("zoneTwentythreeTile", "device.zoneTwentythree", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
            state "off23", label: 'Twentythree', action: "RelayOn23", icon: "st.Outdoor.outdoor12", backgroundColor: "#ffffff", nextState: "sending23"
            state "sending23", label: 'sending', action: "RelayOff23", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
            state "q23", label: 'Twentythree', action: "RelayOff23",icon: "st.Outdoor.outdoor12", backgroundColor: "#c0a353", nextState: "sending23"
            state "on23", label: 'Twentythree', action: "RelayOff23",icon: "st.Outdoor.outdoor12", backgroundColor: "#53a7c0", nextState: "sending23"
            state "sendingOff23", label: 'sending', action: "RelayOff23", icon: "st.Health & Wellness.health7", backgroundColor: "#cccccc"
        }
        // pump uses relay 24
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
        main "allZonesTile"
        details(["zoneOneTile","zoneTwoTile","zoneThreeTile","zoneFourTile","zoneFiveTile","zoneSixTile","zoneSevenTile","zoneEightTile", "zoneNineTile", "zoneTenTile", "zoneElevenTile", "zoneTwelveTile", "zoneThirteenTile", "zoneFourteenTile", "zoneFifteenTile", "zoneSixteenTile", "zoneSeventeenTile", "zoneEighteenTile", "zoneNineteenTile", "zoneTwentyTile", "zoneTwentyoneTile", "zoneTwentytwoTile", "zoneTwentythreeTile", "pumpTile","refreshTile"])
    }
}

// parse events into attributes to create events
def parse(String description) {
    log.debug "Parsing '${description}'"
    log.debug "Parsed: ${zigbee.parse(description)}"
  
    
    def value = zigbee.parse(description)?.text
    if (value != null && value != " " && value != '"' && value != "havePump" && value != "noPump") {
        String delims = ","
        String[] tokens = value.split(delims)
        for (int x=0; x<tokens.length; x++) {
            def displayed = tokens[x] && tokens[x] != "ping"  //evaluates whether to display message

            def name = tokens[x] in ["on1", "q1", "off1"] ? "zoneOne"
            : tokens[x] in ["on2", "q2", "off2"] ? "zoneTwo"
            : tokens[x] in ["on3", "q3", "off3"] ? "zoneThree"
            : tokens[x] in ["on4", "q4", "off4"] ? "zoneFour"
            : tokens[x] in ["on5", "q5", "off5"] ? "zoneFive"
            : tokens[x] in ["on6", "q6", "off6"] ? "zoneSix"
            : tokens[x] in ["on7", "q7", "off7"] ? "zoneSeven"
            : tokens[x] in ["on8", "q8", "off8"] ? "zoneEight"
            : tokens[x] in ["on9", "q9", "off9"] ? "zoneNine"
            : tokens[x] in ["on10", "q10", "off10"] ? "zoneTen"
            : tokens[x] in ["on11", "q11", "off11"] ? "zoneEleven"
            : tokens[x] in ["on12", "q12", "off12"] ? "zoneTwelve"
            : tokens[x] in ["on13", "q13", "off13"] ? "zoneThirteen"
            : tokens[x] in ["on14", "q14", "off14"] ? "zoneFourteen"
            : tokens[x] in ["on15", "q15", "off15"] ? "zoneFifteen"
            : tokens[x] in ["on16", "q16", "off16"] ? "zoneSixteen"
            : tokens[x] in ["on17", "q17", "off17"] ? "zoneSeventeen"
            : tokens[x] in ["on18", "q18", "off18"] ? "zoneEightteen"
            : tokens[x] in ["on19", "q19", "off19"] ? "zoneNineteen"
            : tokens[x] in ["on20", "q20", "off20"] ? "zoneTwenty"
            : tokens[x] in ["on21", "q21", "off21"] ? "zoneTwentyone"
            : tokens[x] in ["on22", "q22", "off22"] ? "zoneTwentytwo"
            : tokens[x] in ["on23", "q23", "off23"] ? "zoneTwentythree"
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
    if(device.currentValue("zoneNine") in ["on9","q9"]) return true;
    if(device.currentValue("zoneTen") in ["on10","q10"]) return true;
    if(device.currentValue("zoneEleven") in ["on11","q11"]) return true;
    if(device.currentValue("zoneTwelve") in ["on12","q12"]) return true;
    if(device.currentValue("zoneThirteen") in ["on13","q13"]) return true;
    if(device.currentValue("zoneFourteen") in ["on14","q14"]) return true;
    if(device.currentValue("zoneFifteen") in ["on15","q15"]) return true;
    if(device.currentValue("zoneSixteen") in ["on16","q16"]) return true;
    if(device.currentValue("zoneSeventeen") in ["on17","q17"]) return true;
    if(device.currentValue("zoneEightteen") in ["on18","q18"]) return true;
    if(device.currentValue("zoneNineteen") in ["on19","q19"]) return true;
    if(device.currentValue("zoneTwenty") in ["on20","q20"]) return true;
    if(device.currentValue("zoneTwentyone") in ["on21","q21"]) return true;
    if(device.currentValue("zoneTwentytwo") in ["on22","q22"]) return true;
    if(device.currentValue("zoneTwentythree") in ["on23","q23"]) return true;
    

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

def RelayOn4() {
    log.info "Executing 'on,4'"
    zigbee.smartShield(text: "on,4,${fourTimer}").format()
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

def RelayOn6() {
    log.info "Executing 'on,6'"
    zigbee.smartShield(text: "on,6,${sixTimer}").format()
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

def RelayOn10() {
    log.info "Executing 'on,10'"
    zigbee.smartShield(text: "on,10,${eightTimer}").format()
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


def on() {
    log.info "Executing 'allOn'"
    zigbee.smartShield(text: "allOn,${oneTimer ?: 0},${twoTimer ?: 0},${threeTimer ?: 0},${fourTimer ?: 0},${fiveTimer ?: 0},${sixTimer ?: 0},${sevenTimer ?: 0},${eightTimer ?: 0},${nineTimer ?: 0},${tenTimer ?: 0},$(elevenTimer ?: 0},$(twelveTimer ?: 0},${thirteenTimer ?: 0},${fourteenTimer ?: 0},${fifteenTimer ?: 0},${sixteenTimer ?: 0},${seventeenTimer ?: 0},${eightteenTimer ?: 0},${nineteenTimer ?: 0},${twentyTimer ?: 0},${twentyoneTimer ?: 0},${twentytwoTimer ?: 0},${twentythreeTimer ?: 0}").format()
}

def OnWithZoneTimes(value) {
    log.debug "Executing 'allOn' with zone times [$value]"
	def zoneTimes = [:]
    for(z in value.split(",")) {
    	def parts = z.split(":")
        zoneTimes[parts[0].toInteger()] = parts[1]
        log.info("Zone ${parts[0].toInteger()} on for ${parts[1]} minutes")
    }
    zigbee.smartShield(text: "allOn,${checkTime(zoneTimes[1]) ?: 0},${checkTime(zoneTimes[2]) ?: 0},${checkTime(zoneTimes[3]) ?: 0},${checkTime(zoneTimes[4]) ?: 0},${checkTime(zoneTimes[5]) ?: 0},${checkTime(zoneTimes[6]) ?: 0},${checkTime(zoneTimes[7]) ?: 0},${checkTime(zoneTimes[8]) ?: 0},${checkTime(zoneTimes[9]) ?: 0},${checkTime(zoneTimes[10]) ?: 0},${checkTime(zoneTimes[11]) ?: 0},${checkTime(zoneTimes[12]) ?: 0},${checkTime(zoneTimes[13]) ?: 0},${checkTime(zoneTimes[14]) ?: 0},${checkTime(zoneTimes[15]) ?: 0},${checkTime(zoneTimes[16]) ?: 0},${checkTime(zoneTimes[17]) ?: 0},${checkTime(zoneTimes[18]) ?: 0},${checkTime(zoneTimes[19]) ?: 0},${checkTime(zoneTimes[20]) ?: 0},${checkTime(zoneTimes[21]) ?: 0},${checkTime(zoneTimes[22]) ?: 0},${checkTime(zoneTimes[23]) ?: 0}").format()
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
    log.debug "rain delayed"
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

