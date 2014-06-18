/**
*  Irrigation Virtual Scheduler Switch
*  Used with Timer App, allows user to manually modify the timer app's schedule. This is optional.
*
*  Author: Stan Dotson (stan@dotson.info) and Matthew Nichols (matt@nichols.name)
*  Date: 2014-06-16
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

metadata {
    // Automatically generated. Make future change here.
    definition (name: "Irrigation Virtual Scheduler Switch", namespace: "d8adrvn/smart_sprinkler", author: "matt@nichols.name", version: "2.5") {
        capability "actuator"
        command "noEffect"
        command "delay"
        command "expedite"
        command "onHold"
        command "rainDelayed"
        attribute "effect", "string"
        }

        tiles {
            standardTile("scheduleEffect", "device.effect", width: 2, height: 2) {
            state("noEffect", label: "--", action: "delay", icon: "st.Health & Wellness.health7", backgroundColor: "#ffffff")
            state("delay", label: "delay", action: "expedite", icon: "st.Health & Wellness.health7", backgroundColor: "#c0a353")
            state("expedite", label: "expedite", action: "onHold", icon: "st.Health & Wellness.health7", backgroundColor: "#53a7c0")
            state("onHold", label: "on hold", action: "noEffect", icon: "st.Health & Wellness.health7", backgroundColor: "#bc2323")
            state("rainDelayed", label: "Rain Delay", action: "noEffect", icon: "st.Weather.weather10", backgroundColor: "#fff000")
        }

        main(["scheduleEffect"])
        details(["scheduleEffect"])
    }
}

def	delay() {
    def evt = createEvent(name: "effect", value: "delay", displayed: true)
    log.debug("Sending: $evt")
    sendEvent(evt)
}

def	rainDelayed() {
    def evt = createEvent(name: "effect", value: "rainDelayed", displayed: true)
    log.debug("Sending: $evt")
    sendEvent(evt)
}

def	expedite() {
    def evt = createEvent(name: "effect", value: "expedite", displayed: true)
    log.debug("Sending: $evt")
    sendEvent(evt)
}

def	noEffect() {
    def evt = createEvent(name: "effect", value: "noEffect", displayed: true)
    log.debug("Sending: $evt")
    sendEvent(evt)
}

def	onHold() {
    def evt = createEvent(name: "effect", value: "onHold", displayed: true)
    log.debug("Sending: $evt")
    sendEvent(evt)
}

// Parse incoming device messages to generate events
def parse(String description) {
    return null
}