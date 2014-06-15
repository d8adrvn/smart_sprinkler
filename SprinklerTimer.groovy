/**
 *  Watering Days - Sprinkler Scheduler With Smart Rain Gauge
 *
 *  Rain gauge keeps track of rain fall and then skips the next watering if the gauge reads above a "threshold".
 *  The "threshold" amount is entered as a preference
 *  Each time a scheduled watering is skipped, the "threshold" amount is removed from rain guage until rain gauge is empty.
 *  The program will skip scheduled waterings if the forecast for that day exceeds the "threshold" amount
 *  
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
    name: "Watering Days v2",
    namespace: "d8adrvn/smart_sprinkler",
    author: "matt@nichols.name",
    description: "Schedule your irrigation controller on specific days using a Smart Rain Gauge",
	category: "Green Living",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture@2x.png"
)

preferences {
	section {
        input (name: "days", 
    	type: "enum", 
    	title: "Which days of the week?", 
        required: false,
        multiple: true, 
        metadata: [values: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']])
        log.debug "days to run are: ${days}"
	}
	section("Select times to turn them on...") {
		input name: "waterTimeOne",  type: "time", required: true, title: "Turn them all on at..."
        input name: "waterTimeTwo",  type: "time", required: false, title: "and again at..."
        input name: "waterTimeThree",  type: "time", required: false, title: "and again at..."
	}
	section("Sprinkler switches...") {
		input "switches", "capability.switch", multiple: true
	}
	section("Zip code to check weather...") {
		input "zipcode", "string", title: "Zipcode?", required: false
	}
	section("Skip a watering if it has or will rain X inches yesterday or today... (default 0.25)"){
		input "wetThreshold", "string", title: "Inches?", required: false
	}
}

def installed() {
	log.debug "Installed: $settings"
    scheduling()
    schedule(waterTime, scheduleCheck)
}

def updated() {
	log.debug "Updated: $settings"
	unschedule()
    scheduling()

}

// Scheduling
def scheduling() {
    schedule(waterTimeOne, "waterTimeOneStart")
	if (waterTimeTwo) {
    	schedule(waterTimeTwo, "waterTimeTwoStart")
   	}
	if (waterTimeThree) {
    	schedule(waterTimeThree, "waterTimeThreeStart")
   	}
}
def waterTimeOneStart() {
	scheduleCheck()
}
def waterTimeTwoStart() {
	scheduleCheck()
}
def waterTimeThreeStart() {
	scheduleCheck()
}

// turn them on
def scheduleCheck() {
	log.debug("Checking schedule")
	 if (waterToday()) {
     	if (zipcode) {
			if (!wasWetYesterday() && !isWet() && !isStormy()) {
//	        	sendPush("Watering now!")
				log.trace "starting scheduled watering"
               	switches.on()
              	}
          	else {
				switches.rainDelayed()
               	log.trace "watering is rain delayed"
//	        	sendPush("Skipping watering today due to forecast.")
	 		}
        }
        else {  //no zip code entered so run without weather input
//			sendPush("Watering now!; No Weather data")
			log.trace "starting scheduled watering without weather data"
			switches.on()
    	}
    }
}

def waterToday() {
    def today = new Date().format("EEEE")  //bug alert: this uses UTC date not local date
	log.debug "today: ${today}, days: ${days}"
	if (days.contains(today)) {
		return true
	}
	log.trace "watering is not scheduled for today"
	return false
}

def wasWetYesterday() {
 	def yesterdaysWeather = getWeatherFeature("yesterday", zipcode)
    def yesterdaysPrecip=yesterdaysWeather.history.dailysummary.precipi.toArray()
   	def yesterdaysInches=yesterdaysPrecip[0].toFloat()
    log.info("Checking yesterdays percipitation for $zipcode: $yesterdaysInches in")
    
    if (yesterdaysInches > wetThreshold.toFloat()) {
        return true  //rainGuage is full
    }
 	else {
        return false // rainGuage is empty or below the line
    }
}

def isWet() {
	def todaysWeather = getWeatherFeature("conditions", zipcode)
   	def todaysInches = todaysWeather.current_observation.precip_today_in.toFloat()
	log.info("Checking percipitation for $zipcode: $todaysInches in")
	if (todaysInches > wetThreshold.toFloat()) {
  		return true  // rain gauge is full
	}
	else {
		return false
	}
}

def isStormy() {
     def forecastWeather = getWeatherFeature("forecast", zipcode)
     def forecastPrecip=forecastWeather.forecast.simpleforecast.forecastday.qpf_allday.in.toArray()
     def forecastInches=forecastPrecip[0].toFloat()
	log.info("Checking forecast percipitation for $zipcode: $forecastInches in")
	if (forecastInches > wetThreshold.toFloat()) {
    	return true  // rain guage is forecasted to be full
	}
	else {
		return false
	}
}

def initialize() {
	// TODO: subscribe to attributes, devices, locations, etc.
    //TODO: add rain guage tile to device type and subscribe to listen for "emptyRainGuage" custom command
}
