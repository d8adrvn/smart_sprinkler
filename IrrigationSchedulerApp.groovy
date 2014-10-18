/**
*  
* 
*  Irrigation Scheduler SmartApp Smarter Lawn Controller
*  Compatible with up to 24 Zones
*
*  Author: Stan Dotson (stan@dotson.info) and Matthew Nichols (matt@nichols.name)
*  Date: 2014-06-16
*
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
*
*
**/

definition(
    name: "Irrigation Scheduler v2.92",
    namespace: "d8adrvn/smart_sprinkler",
    author: "matt@nichols.name and stan@dotson.info",
    description: "Schedule sprinklers to run unless there is rain.",
    category: "Green Living",
    version: "2.92",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture@2x.png"
)

preferences {
	page(name: "schedulePage", title: "Schedule", nextPage: "sprinklerPage", uninstall: true) {
      	section("App configruation...") {
        	label title: "Choose an title for App", required: true, defaultValue: "Irrigation Scheduler"
        }
        section {
            input (
            name: "wateringDays",
            type: "enum",
            title: "Water which days?",
            required: false,
            multiple: true, // This must be changed to false for development (known ST IDE bug)
            metadata: [values: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']])
        }

        section("Water every...") {
            input "days", "number", title: "Days?", description: "minimum # days between watering", required: false
        }

        section("Water when...") {
            input name: "waterTimeOne",  type: "time", required: true, title: "Turn them on at..."
            input name: "waterTimeTwo",  type: "time", required: false, title: "and again at..."
            input name: "waterTimeThree",  type: "time", required: false, title: "and again at..."
        }
    }

    page(name: "sprinklerPage", title: "Sprinkler Controller Setup", install: true) {
        section("Sprinkler switches...") {
            input "switches", "capability.switch", multiple: false
        }
        section("Zone Times...") {
            input "zone1", "string", title: "Zone 1 Time", description: "minutes", multiple: false, required: false
            input "zone2", "string", title: "Zone 2 Time", description: "minutes", multiple: false, required: false
            input "zone3", "string", title: "Zone 3 Time", description: "minutes", multiple: false, required: false
            input "zone4", "string", title: "Zone 4 Time", description: "minutes", multiple: false, required: false
            input "zone5", "string", title: "Zone 5 Time", description: "minutes", multiple: false, required: false
            input "zone6", "string", title: "Zone 6 Time", description: "minutes", multiple: false, required: false
            input "zone7", "string", title: "Zone 7 Time", description: "minutes", multiple: false, required: false
            input "zone8", "string", title: "Zone 8 Time", description: "minutes", multiple: false, required: false
            input "zone9", "string", title: "Zone 9 Time", description: "minutes", multiple: false, required: false
            input "zone10", "string", title: "Zone 10 Time", description: "minutes", multiple: false, required: false
            input "zone11", "string", title: "Zone 11 Time", description: "minutes", multiple: false, required: false
            input "zone12", "string", title: "Zone 12 Time", description: "minutes", multiple: false, required: false
            input "zone13", "string", title: "Zone 13 Time", description: "minutes", multiple: false, required: false
            input "zone14", "string", title: "Zone 14 Time", description: "minutes", multiple: false, required: false
            input "zone15", "string", title: "Zone 15 Time", description: "minutes", multiple: false, required: false
            input "zone16", "string", title: "Zone 16 Time", description: "minutes", multiple: false, required: false
            input "zone17", "string", title: "Zone 17 Time", description: "minutes", multiple: false, required: false
            input "zone18", "string", title: "Zone 18 Time", description: "minutes", multiple: false, required: false
            input "zone19", "string", title: "Zone 19 Time", description: "minutes", multiple: false, required: false
            input "zone20", "string", title: "Zone 20 Time", description: "minutes", multiple: false, required: false
            input "zone21", "string", title: "Zone 21 Time", description: "minutes", multiple: false, required: false
            input "zone22", "string", title: "Zone 22 Time", description: "minutes", multiple: false, required: false
            input "zone23", "string", title: "Zone 23 Time", description: "minutes", multiple: false, required: false
            input "zone24", "string", title: "Zone 24 Time", description: "minutes", multiple: false, required: false
        }

 //	}
    
//	page (name: "virtualRainGuage", title: "Virtual Rain Guage Setup", install: true) {
		
        section("Zip code to check weather...") {
            input "zipcode", "text", title: "Zipcode?", required: false
        }
        
        section("Select which rain to add to guage...") {
        	input "isYesterdaysRainEnabled", "boolean", title: "Yesterday's Rain", description: "Include?", defaultValue: "true", required: false
        	input "isTodaysRainEnabled", "boolean", title: "Today's Rain", description: "Include?", defaultValue: "true", required: false
        	input "isForecastRainEnabled", "boolean", title: "Today's Forecasted Rain", description: "Include?", defaultValue: "false", required: false
        }
       
       section("Skip watering if more than... (default 0.5)") {
            input "wetThreshold", "decimal", title: "Inches?", required: false
        }
        
    }
}		

def installed() {
    scheduling()
    state.daysSinceLastWatering = [0,0,0]
}

def updated() {
    unschedule()
    scheduling()
    state.daysSinceLastWatering = [0,0,0]
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
    state.currentTimerIx = 0
    scheduleCheck()
}
def waterTimeTwoStart() {
    state.currentTimerIx = 1
    scheduleCheck()
}
def waterTimeThreeStart() {
    state.currentTimerIx = 2
    scheduleCheck()
}

def scheduleCheck() {

    def schedulerState = switches?.latestValue("effect")?.toString() ?:"[noEffect]"
        

    if (schedulerState == "onHold") {
        log.info("Sprinkler schedule on hold.")
        return
    } 
    
	if (schedulerState == "skip") { 
    	// delay this watering and reset device.effect to noEffect
        schedulerState = "delay" 
        for(s in switches) {
            if("noEffect" in s.supportedCommands.collect { it.name }) {
                s.noEffect()
                log.info ("sent noEffect() to ${s}")
            }
        }
 	}    
    
	if (schedulerState != "expedite") { 
    	// Change to rain delay if wet
    	schedulerState = isRainDelay() ? "delay" : schedulerState
 	}

    if (schedulerState != "delay") {
        state.daysSinceLastWatering[state.currentTimerIx] = daysSince() + 1
    }

    log.info("Scheduler state: $schedulerState. Days since last watering: ${daysSince()}. Is watering day? ${isWateringDay()}. Enought time? ${enoughTimeElapsed(schedulerState)} ")

    if ((isWateringDay() && enoughTimeElapsed(schedulerState) && schedulerState != "delay") || schedulerState == "expedite") {
        sendPush("$switches[0] Is Watering Now!" ?: "null pointer on app name")
        state.daysSinceLastWatering[state.currentTimerIx] = 0
        water()
        // Assuming that sprinklers will turn themselves off. 
    }
}

def isWateringDay() {
    if(!wateringDays) return true

    def today = new Date().format("EEEE", location.timeZone)
    if (wateringDays.contains(today)) {
        return true
    }
    log.info "watering is not scheduled for today"
    return false
}

def enoughTimeElapsed(schedulerState) {
    if(!days) return true
    return (daysSince() >= days)
}

def daysSince() {
    if(!state.daysSinceLastWatering) state.daysSinceLastWatering = [0,0,0]
    state.daysSinceLastWatering[state.currentTimerIx] ?: 0
}

def isRainDelay() { 

	def rainGauge = 0
    
    if (isYesterdaysRainEnabled.equals("true")) {        
    	rainGauge = rainGauge + wasWetYesterday()
   	}
    
    if (isTodaysRainEnabled.equals("true")) {
    	rainGauge = rainGauge + isWet()
    }
    
    if (isForecastRainEnabled.equals("true")) {
    	rainGauge = rainGauge + isStormy()
  	}
    
    log.info ("Virtual rain gauge reads $rainGauge in")
    if (rainGauge > (wetThreshold?.toFloat() ?: 0.5)) {
        sendPush("Skipping watering today due to precipitation.")
        for(s in switches) {
            if("rainDelayed" in s.supportedCommands.collect { it.name }) {
                s.rainDelayed()
                log.trace "Watering is rain delayed for $s"
            }
        }
        return true
    }
    return false
}

def safeToFloat(value) {
    if(value && value.isFloat()) return value.toFloat()
    return 0.0
}

def wasWetYesterday() {
    if (!zipcode) return false

    def yesterdaysWeather = getWeatherFeature("yesterday", zipcode)
    def yesterdaysPrecip=yesterdaysWeather.history.dailysummary.precipi.toArray()
    def yesterdaysInches=safeToFloat(yesterdaysPrecip[0])
    log.info("Checking yesterday's percipitation for $zipcode: $yesterdaysInches in")
	return yesterdaysInches
}


def isWet() {
    if (!zipcode) return false

    def todaysWeather = getWeatherFeature("conditions", zipcode)
    def todaysInches = safeToFloat(todaysWeather.current_observation.precip_today_in)
    log.info("Checking today's percipitation for $zipcode: $todaysInches in")
    return todaysInches
}

def isStormy() {
    if (!zipcode) return false

    def forecastWeather = getWeatherFeature("forecast", zipcode)
    def forecastPrecip=forecastWeather.forecast.simpleforecast.forecastday.qpf_allday.in.toArray()
    def forecastInches=forecastPrecip[0]
    log.info("Checking forecast percipitation for $zipcode: $forecastInches in")
    return forecastInches
}

def water() {
    state.triggered = true
    if(anyZoneTimes()) {
        def zoneTimes = []
        for(int z = 1; z <= 24; z++) {
            def zoneTime = settings["zone${z}"]
            if(zoneTime) {
            zoneTimes += "${z}:${zoneTime}"
            log.info("Zone ${z} on for ${zoneTime} minutes")
        }
    }
        switches.OnWithZoneTimes(zoneTimes.join(","))
    } else {
        log.debug("Turning all zones on")
        switches.on()
    }
}

def anyZoneTimes() {
    return zone1 || zone2 || zone3 || zone4 || zone5 || zone6 || zone7 || zone8 || zone9 || zone10 || zone11 || zone12 || zone13 || zone14 || zone15 || zone16 || zone17 || zone18 || zone19 || zone20 || zone21 || zone22 || zone23 || zone24
}
