/**
 *  Watering Days - Sprinkler Timer
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
**/

definition(
    name: "Watering Days",
    namespace: "d8adrvn/smart_sprinkler",
    author: "matt@nichols.name",
    description: "Schedule sprinklers to run on specified days of the week, unless rain is in the forecast.",
	category: "Green Living",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture@2x.png"
)

preferences {
	section("Sunday") {
		input "sun", "string", title: "Water?", description: "Yes | No", required: false
	}
	section("Monday") {
		input "mon", "string", title: "Water?", description: "Yes | No", required: false
	}
	section("Tuesday") {
		input "tue", "string", title: "Water?", description: "Yes | No", required: false
	}
	section("Wednesday") {
		input "wed", "string", title: "Water?", description: "Yes | No", required: false
	}
	section("Thursday") {
		input "thu", "string", title: "Water?", description: "Yes | No", required: false
	}
	section("Friday") {
		input "fri", "string", title: "Water?", description: "Yes | No", required: false
	}
	section("Saturday") {
		input "sat", "string", title: "Water?", description: "Yes | No", required: false
	}
	section("What time?") {
		input "waterTime", "time", title: "When?", required: true
	}
	section("Sprinkler switches...") {
		input "switches", "capability.switch", multiple: true
	}
	section("Zip code to check weather...") {
		input "zipcode", "string", title: "Zipcode?", required: false
	}
}

def installed() {
	log.debug "Installed: $settings"
    schedule(waterTime, scheduleCheck)
}

def updated() {
	log.debug "Updated: $settings"
	unschedule()
    schedule(waterTime, scheduleCheck)
}

def scheduleCheck() {
	log.debug("Checking schedule")
	 if (waterToday()) {
		if(zipcode) {
			def response = getWeatherFeature("forecast", zipcode)
			if (!isStormy(response)) {
	        	sendPush("Watering now!")
				switches.on()
			} else {
	        	sendPush("Skipping watering today due to forecast.")
	        }
	    } else {
			sendPush("Watering now!")
			switches.on()
    	}
    }
    // Assuming that sprinklers will turn themselves off. Should add a delayed off?
}

def waterToday() {
	def day = new Date().format("EEE", location.timeZone).toLowerCase()
    return (day == "sun" && waterOn(sun)) ||
	    (day == "mon" && waterOn(mon)) ||
    	(day == "tue" && waterOn(tue)) ||
    	(day == "wed" && waterOn(wed)) ||
    	(day == "thu" && waterOn(thu)) ||
    	(day == "fri" && waterOn(fri)) ||
    	(day == "sat" && waterOn(sat))
}

def waterOn(day) {
	day && day.toLowerCase() == "yes"
}

private isStormy(json) {
	def STORMY = ['rain', 'snow', 'showers', 'sprinkles', 'precipitation']

	def forecast = json?.forecast?.txt_forecast?.forecastday?.first()
	if (forecast) {
		def text = forecast?.fcttext?.toLowerCase()
		if (text) {
			def result = false
			for (int i = 0; i < STORMY.size() && !result; i++) {
				result = text.contains(STORMY[i])
			}
			return result
		} else {
			return false
		}
	} else {
		log.warn "Did not get a forecast: $json"
		return false
	}
}
