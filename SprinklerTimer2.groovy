/**
 *  Turn on Sprinklers Unless There's Rain - Sprinkler Timer 2
 *
 *  Copyright 2014 Matthew Nichols
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
 *
 * NOTE: This app is using experimental precipitation measurement from weather feature to determine if watering should occur.
 *
**/

definition(
    name: "Turn on Sprinklers Unless There's Rain",
    namespace: "d8adrvn/smart_sprinkler",
    author: "matt@nichols.name",
    description: "Schedule sprinklers run every n days unless rain is in the forecast.",
	category: "Green Living",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture@2x.png"
)

preferences {
	section("Water every..."){
		input "days", "number", title: "Days?", required: true
	}
	section("Water at..."){
		input "time", "time", title: "When?", required: true
	}
	section("Sprinkler switches..."){
		input "switches", "capability.switch", multiple: true
	}
	section("Zip code to check weather..."){
		input "zipcode", "text", title: "Zipcode?", required: false
	}
    section("Skip watering if more than... (default 0.5)"){
		input "wetThreshold", "number", title: "Inches?", required: false
	}

}

def installed() {
	log.info "Installed: $settings"
	
	subscribe(switches, "switch.on", sprinklersOn)
	schedule(time, "scheduleCheck")
	
	//sendPush("Next watering in ${days - daysSince()} days!")
}

def updated() {
	log.info "Updated: $settings"
	
	unsubscribe()
	unschedule()
	
	subscribe(switches, "switch.on", sprinklersOn)
	schedule(time, "scheduleCheck")
	
	//sendPush("Next watering in ${days - daysSince()} days!")
}

def sprinklersOn(evt) {
	if(!state.triggered && daysSince() > 0) {
		sendPush("Looks like the sprinklers are on. Pushing back next watering day.")
		state.daysSinceLastWatering = 0
	}
	state.triggered = false
}

def scheduleCheck() {
	state.daysSinceLastWatering = daysSince() + 1
    def inches = todaysPercip()
	log.info("Checking sprinkler schedule. ${daysSince()} days since laster watering. ${inches} inches of percip today. Threshold: ${wetThreshold?.toFloat() ?: 0.5}")
    
    if(inches >= (wetThreshold?.toFloat() ?: 0.5)) {
		sendPush("Looks like it was a wet day ($inches inches). Pushing back next watering day.")
        state.daysSinceLastWatering = 0
        switches.rainDelayed()
	} else if (daysSince() >= days) {
		sendPush("Watering now!")
        state.triggered = true
		switches.on()
        state.daysSinceLastWatering = 0
	}
    // Assuming that sprinklers will turn themselves off. Should add a delayed off?
}

def todaysPercip() {
	def result = 0.0
    if (zipcode) {
		def weather = getWeatherFeature("conditions", zipcode)
    	result = weather.current_observation.precip_today_in.toFloat()
        log.info("Checking percipitation for $zipcode: $result in")
    }
    result
}

def daysSince()
{
	if(!state.daysSinceLastWatering) state.daysSinceLastWatering = 0
    state.daysSinceLastWatering
}
