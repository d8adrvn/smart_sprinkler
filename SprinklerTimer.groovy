/**
 *  Sprinkler Timer
 *
 *  Author: matt@nichols.name
 */
definition(
    name: "Watering Days",
    namespace: "name.nichols.matt.smartapps",
    author: "matt@nichols.name",
    description: "Schedule sprinklers to turn on unless rain is in the forcast. Maximum of 4 days per week can be scheduled.",
	category: "Green Living",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture@2x.png"
)

preferences {
	section("Sunday"){
		input "sundayTime", "time", title: "When?", required: false
	}
	section("Monday"){
		input "mondayTime", "time", title: "When?", required: false
	}
	section("Tuesday"){
		input "tuesdayTime", "time", title: "When?", required: false
	}
	section("Wednesday"){
		input "wednesdayTime", "time", title: "When?", required: false
	}
	section("Thursday"){
		input "thursdayTime", "time", title: "When?", required: false
	}
	section("Friday"){
		input "fridayTime", "time", title: "When?", required: false
	}
	section("Saturday"){
		input "saturdayTime", "time", title: "When?", required: false
	}
	section("Sprinkler switches..."){
		input "switches", "capability.switch", multiple: true
	}
	section("Zip code to check weather..."){
		input "zipcode", "text", title: "Zipcode?", required: false
	}
}

def installed() {
	log.debug "Installed: $settings"
    updateSchedules()
}

def updated() {
	log.debug "Updated: $settings"
	unschedule()
    updateSchedules()
}

def updateSchedules() {
    setDaySchedule(sundayTime, 1)
    setDaySchedule(mondayTime, 2)
    setDaySchedule(tuesdayTime, 3)
    setDaySchedule(wednesdayTime, 4)
    setDaySchedule(thrusdayTime, 5)
    setDaySchedule(fridayTime, 6)
    setDaySchedule(saturdayTime, 7)
}

def setDaySchedule(t,day) {
	if (t) {
    	def splitTime = t.split('T')[1].split(':')
    	schedule("${splitTime[1]} ${splitTime[0]} * * ${day} ?", "scheduleCheck${day}")
    }
}

def scheduleCheck1() { scheduleCheck() }
def scheduleCheck2() { scheduleCheck() }
def scheduleCheck3() { scheduleCheck() }
def scheduleCheck4() { scheduleCheck() }
def scheduleCheck5() { scheduleCheck() }
def scheduleCheck6() { scheduleCheck() }
def scheduleCheck7() { scheduleCheck() }

def scheduleCheck() {
	if(zipcode) {
		def response = getWeatherFeature("forecast", zipcode)
		if (isStormy(response)) {
        	sendPush("Watering now!")
			switches.on()
		} else {
        	sendPush("Skipping watering today due to weather")
        }
    } else {
		sendPush("Watering now!")
		switches.on()
    }
    // Assuming that sprinklers will turn themselves off. Should add a delayed off?
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
