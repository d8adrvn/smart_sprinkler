/**
 *  Sprinkler Timer
 *
 *  Author: matt@nichols.name
 */
definition(
    name: "Turn on Sprinklers Unless There's Rain",
    namespace: "name.nichols.matt.smartapps",
    author: "matt@nichols.name",
    description: "Schedule sprinklers to turn on unless rain is in the forcast. Maximum of 4 days per week can be scheduled.",
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
