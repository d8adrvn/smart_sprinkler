/**
*  This is a start to porting the Arduino and SmartShield based
*  Irrigation Controllers to an ESP8266 based controller
*  Author:  Aaron Nienhuis (aaron.nienhuis@gmail.com)
*
*  Date:  2017-04-07
*  
*  
* 
*  Irrigation Scheduler SmartApp Smarter Lawn Contoller
*  Compatible with up to 24 Zones
*
*  ESP8266 port based on the extensive previous work of:
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
    name: "ESP8266 Irrigation Scheduler",
    namespace: "anienhuis",
    author: "aaron.nienhuis@gmail.com",
    description: "Schedule sprinklers to run unless there is rain.",
    version: "1.0.0",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture@2x.png"
)


//*****
preferences {
	page(name: "mainPage")
    page(name: "configurePDevice")
    page(name: "deletePDevice")
    page(name: "changeName")
    page(name: "discoveryPage", title: "Device Discovery", content: "discoveryPage", refreshTimeout:5)
    page(name: "addDevices", title: "Add Irrigation Controller", content: "addDevices")
    page(name: "manuallyAdd")
    page(name: "manuallyAddConfirm")
    page(name: "deviceDiscovery")
}

def mainPage() {
	dynamicPage(name: "mainPage", title: "Manage your Irrigation Controllers", nextPage: null, uninstall: true, install: true) {
        section("Configure"){
           href "deviceDiscovery", title:"Discover Devices", description:""
           href "manuallyAdd", title:"Manually Add Device", description:""
        }
        section("Installed Devices"){
            getChildDevices().sort({ a, b -> a["deviceNetworkId"] <=> b["deviceNetworkId"] }).each {
                href "configurePDevice", title:"$it.label", description:"", params: [did: it.deviceNetworkId]
            }
        }
    }
}

def configurePDevice(params){
   def currentDevice
   getChildDevices().each {
       if(it.deviceNetworkId == params.did){
           state.currentDeviceId = it.deviceNetworkId
           state.currentDisplayName = it.displayName
       }      
   }
   if (getChildDevice(state.currentDeviceId) != null) getChildDevice(state.currentDeviceId).configure()
   dynamicPage(name: "configurePDevice", title: "Configure Irrigation Controllers created with this app", nextPage: null) {
		section {
            app.updateSetting("${state.currentDeviceId}_label", getChildDevice(state.currentDeviceId).label)
            input "${state.currentDeviceId}_label", "text", title:"Device Name", description: "", required: false
            href "changeName", title:"Change Device Name", description: "Edit the name above and click here to change it", params: [did: state.currentDeviceId]
            href "schedulePage", title:"Configure Irrigation Schedule", description: "Click here to configure the irrigation schedule", params: [did: state.currentDeviceId]
        }
        section {
              href "deletePDevice", title:"Delete $state.currentDisplayName", description: "", params: [did: state.currentDeviceId]
        }
   }
}

def manuallyAdd(){
   dynamicPage(name: "manuallyAdd", title: "Manually add a Irrigation Controller", nextPage: "manuallyAddConfirm") {
		section {
			paragraph "This process will manually create a Irrigation Controller based on the entered IP address. The SmartApp needs to then communicate with the device to obtain additional information from it. Make sure the device is on and connected to your wifi network."
            input "deviceType", "enum", title:"Device Type", description: "", required: false, options: ["ESP8266 Irrigation Controller 4 Zones","ESP8266 Irrigation Controller 8 Zones"]
            input "ipAddress", "text", title:"IP Address", description: "", required: false 
		}
    }
}

def manuallyAddConfirm(){
   if ( ipAddress =~ /^(?:[0-9]{1,3}\.){3}[0-9]{1,3}$/) {
       log.debug "Creating ESP8266 Irrigation Controller with dni: ${convertIPtoHex(ipAddress)}:${convertPortToHex("80")}"
       addChildDevice("anienhuis", deviceType ? deviceType : "ESP8266 Irrigation Controller 4 Zones", "${convertIPtoHex(ipAddress)}:${convertPortToHex("80")}", location.hubs[0].id, [
           "label": (deviceType ? deviceType : "ESP8266 Irrigation Controller 4 Zones") + " (${ipAddress})",
           "data": [
           "ip": ipAddress,
           "port": "80" 
           ]
       ])
   
       app.updateSetting("ipAddress", "")
            
       dynamicPage(name: "manuallyAddConfirm", title: "Manually add a ESP8266 Irrigation Controller", nextPage: "mainPage") {
		   section {
			   paragraph "The device has been added. Press next to return to the main page."
	    	}
       }
    } else {
        dynamicPage(name: "manuallyAddConfirm", title: "Manually add a ESP8266 Irrigation Controller", nextPage: "mainPage") {
		    section {
			    paragraph "The entered ip address is not valid. Please try again."
		    }
        }
    }
}

def deletePDevice(params){
    try {
        unsubscribe()
        deleteChildDevice(state.currentDeviceId)
        dynamicPage(name: "deletePDevice", title: "Deletion Summary", nextPage: "mainPage") {
            section {
                paragraph "The device has been deleted. Press next to continue"
            } 
        }
    
	} catch (e) {
        dynamicPage(name: "deletePDevice", title: "Deletion Summary", nextPage: "mainPage") {
            section {
                paragraph "Error: ${(e as String).split(":")[1]}."
            } 
        }
    
    }
}

def changeName(params){
    def thisDevice = getChildDevice(state.currentDeviceId)
    thisDevice.label = settings["${state.currentDeviceId}_label"]

    dynamicPage(name: "changeName", title: "Change Name Summary", nextPage: "mainPage") {
	    section {
            paragraph "The device has been renamed. Press \"Next\" to continue"
        }
    }
}

def discoveryPage(){
   return deviceDiscovery()
}

def deviceDiscovery(params=[:])
{
	def devices = devicesDiscovered()
    
	int deviceRefreshCount = !state.deviceRefreshCount ? 0 : state.deviceRefreshCount as int
	state.deviceRefreshCount = deviceRefreshCount + 1
	def refreshInterval = 3
    
	def options = devices ?: []
	def numFound = options.size() ?: 0

	if ((numFound == 0 && state.deviceRefreshCount > 25) || params.reset == "true") {
    	log.trace "Cleaning old device memory"
    	state.devices = [:]
        state.deviceRefreshCount = 0
        app.updateSetting("selectedDevice", "")
    }

	ssdpSubscribe()

	//ESP8266 Irrigation Controller discovery request every 15 //25 seconds
	if((deviceRefreshCount % 5) == 0) {
		discoverDevices()
	}

	//setup.xml request every 3 seconds except on discoveries
	if(((deviceRefreshCount % 3) == 0) && ((deviceRefreshCount % 5) != 0)) {
		verifyDevices()
	}

	return dynamicPage(name:"deviceDiscovery", title:"Discovery Started!", nextPage:"addDevices", refreshInterval:refreshInterval, uninstall: true) {
		section("Please wait while we discover your ESP8266 Irrigation Controller devices. Discovery can take five minutes or more, so sit back and relax! Select your device below once discovered.") {
			input "selectedDevices", "enum", required:false, title:"Select Irrigation Controller (${numFound} found)", multiple:true, options:options
		}
        section("Options") {
			href "deviceDiscovery", title:"Reset list of discovered devices", description:"", params: ["reset": "true"]
		}
	}
}

Map devicesDiscovered() {
	def vdevices = getVerifiedDevices()
	def map = [:]
	vdevices.each {
		def value = "${it.value.name}"
		def key = "${it.value.mac}"
		map["${key}"] = value
	}
	map
}

def getVerifiedDevices() {
	getDevices().findAll{ it?.value?.verified == true }
}

private discoverDevices() {
	sendHubCommand(new physicalgraph.device.HubAction("lan discovery urn:schemas-upnp-org:device:Basic:1", physicalgraph.device.Protocol.LAN))
}

def configured() {
	
}

//def buttonConfigured(idx) {
//	return settings["lights_$idx"]
//}

def isConfigured(){
   if(getChildDevices().size() > 0) return true else return false
}

def isVirtualConfigured(did){ 
    def foundDevice = false
    getChildDevices().each {
       if(it.deviceNetworkId != null){
       if(it.deviceNetworkId.startsWith("${did}/")) foundDevice = true
       }
    }
    return foundDevice
}

private virtualCreated(number) {
    if (getChildDevice(getDeviceID(number))) {
        return true
    } else {
        return false
    }
}

private getDeviceID(number) {
    return "${state.currentDeviceId}/${app.id}/${number}"
}

def installed() {
	initialize()
}

def updated() {
	unsubscribe()
    unschedule()
	initialize()
}

def initialize() {
    ssdpSubscribe()
    runEvery5Minutes("ssdpDiscover")
}

void ssdpSubscribe() {
    subscribe(location, "ssdpTerm.urn:schemas-upnp-org:device:Basic:1", ssdpHandler)
}

void ssdpDiscover() {
    sendHubCommand(new physicalgraph.device.HubAction("lan discovery urn:schemas-upnp-org:device:Basic:1", physicalgraph.device.Protocol.LAN))
}

def ssdpHandler(evt) {
    def description = evt.description
    def hub = evt?.hubId
    def parsedEvent = parseLanMessage(description)
    //log.debug "ssdpHandler:description $description"
    
    parsedEvent << ["hub":hub]
    //log.debug "ssdpHandler:parsedEvent $parsedEvent"

    def devices = getDevices()
    
    String ssdpUSN = parsedEvent.ssdpUSN.toString()
    
    if (devices."${ssdpUSN}") {
        def d = devices."${ssdpUSN}"
        def child = getChildDevice(parsedEvent.mac)
        def childIP
        def childPort
        if (child) {
            childIP = child.getDeviceDataByName("ip")
            childPort = child.getDeviceDataByName("port").toString()
            log.debug "Device data: ($childIP:$childPort) - reporting data: (${convertHexToIP(parsedEvent.networkAddress)}:${convertHexToInt(parsedEvent.deviceAddress)})."
            if(childIP != convertHexToIP(parsedEvent.networkAddress) || childPort != convertHexToInt(parsedEvent.deviceAddress).toString()){
               log.debug "Device data (${child.getDeviceDataByName("ip")}) does not match what it is reporting(${convertHexToIP(parsedEvent.networkAddress)}). Attempting to update."
               child.sync(convertHexToIP(parsedEvent.networkAddress), convertHexToInt(parsedEvent.deviceAddress).toString())
            }
        }

        if (d.networkAddress != parsedEvent.networkAddress || d.deviceAddress != parsedEvent.deviceAddress) {
            d.networkAddress = parsedEvent.networkAddress
            d.deviceAddress = parsedEvent.deviceAddress
        }
    } else {
        devices << ["${ssdpUSN}": parsedEvent]
    }
}

void verifyDevices() {
    def devices = getDevices().findAll { it?.value?.verified != true }
    devices.each {
        def ip = convertHexToIP(it.value.networkAddress)
        def port = convertHexToInt(it.value.deviceAddress)
        String host = "${ip}:${port}"
        sendHubCommand(new physicalgraph.device.HubAction("""GET ${it.value.ssdpPath} HTTP/1.1\r\nHOST: $host\r\n\r\n""", physicalgraph.device.Protocol.LAN, host, [callback: deviceDescriptionHandler]))
    }
}

def getDevices() {
    state.devices = state.devices ?: [:]
}

void deviceDescriptionHandler(physicalgraph.device.HubResponse hubResponse) {
	log.trace "esp8266ic.xml response (application/xml)"
	def body = hubResponse.xml
    log.debug body?.device?.friendlyName?.text()
	if (body?.device?.modelName?.text().startsWith("ESP8266Irrigation")) {
		def devices = getDevices()
		def device = devices.find {it?.key?.contains(body?.device?.UDN?.text())}
		if (device) {
			device.value << [name:body?.device?.friendlyName?.text() + " (" + convertHexToIP(hubResponse.ip) + ")", serialNumber:body?.device?.serialNumber?.text(), verified: true]
		} else {
			log.error "/esp8266ic.xml returned a device that didn't exist"
		}
	}
}

def addDevices() {
    def devices = getDevices()
    def sectionText = ""

    selectedDevices.each { dni ->bridgeLinking
        def selectedDevice = devices.find { it.value.mac == dni }
        def d
        if (selectedDevice) {
            d = getChildDevices()?.find {
                it.deviceNetworkId == selectedDevice.value.mac
            }
        }

        if (!d) {
            log.debug selectedDevice
            log.debug "Creating Irrigation Controller with dni: ${selectedDevice.value.mac}"
            log.debug Integer.parseInt(selectedDevice.value.deviceAddress,16)
            addChildDevice("anienhuis", (selectedDevice?.value?.name?.startsWith("ESP8266 Irrigation") ? "ESP8266 Irrigation Controller 4 Zones" : "ESP8266 Irrigation Controller 8 Zones"), selectedDevice.value.mac, selectedDevice?.value.hub, [
                "label": selectedDevice?.value?.name ?: "ESP8266 Irrigation Controller 4 Zones",
                "data": [
                    "mac": selectedDevice.value.mac,
                    "ip": convertHexToIP(selectedDevice.value.networkAddress),
                    "port": "" + Integer.parseInt(selectedDevice.value.deviceAddress,16)
                ]
            ])
            sectionText = sectionText + "Successfully added ESP8266 Irrigation Controller with ip address ${convertHexToIP(selectedDevice.value.networkAddress)} \r\n"
        }
        
	} 
    log.debug sectionText
        return dynamicPage(name:"addDevices", title:"Devices Added", nextPage:"mainPage",  uninstall: true) {
        if(sectionText != ""){
		section("Add Irrigation Controller Results:") {
			paragraph sectionText
		}
        }else{
        section("No devices added") {
			paragraph "All selected devices have previously been added"
		}
        }
}
    }

def uninstalled() {
    unsubscribe()
    getChildDevices().each {
        deleteChildDevice(it.deviceNetworkId)
    }
}



private String convertHexToIP(hex) {
	[convertHexToInt(hex[0..1]),convertHexToInt(hex[2..3]),convertHexToInt(hex[4..5]),convertHexToInt(hex[6..7])].join(".")
}

private Integer convertHexToInt(hex) {
	Integer.parseInt(hex,16)
}

private String convertIPtoHex(ipAddress) { 
    String hex = ipAddress.tokenize( '.' ).collect {  String.format( '%02x', it.toInteger() ) }.join()
    return hex
}

private String convertPortToHex(port) {
	String hexport = port.toString().format( '%04x', port.toInteger() )
    return hexport
}
//****
preferences {
	page(name: "schedulePage", title: "Create An Irrigation Schedule", nextPage: "sprinklerPage", uninstall: true) {
        
        section("Preferences") {
        	label name: "title", title: "Name this irrigation schedule...", required: false, multiple: false, defaultValue: "Irrigation Scheduler"
        	input "isNotificationEnabled", "boolean", title: "Send Push Notification When Irrigation Starts", description: "Do You Want To Receive Push Notifications?", defaultValue: "true", required: false
        	input "isRainGuageNotificationEnabled", "boolean", title: "Send Push Notification With Rain Guage Report", description: "Do You Want To Receive Push Notifications?", defaultValue: "false", required: false
}
           
        section {
            input (
            name: "wateringDays",
            type: "enum",
            title: "Water on which days?",
            required: false,
            multiple: true, // This must be changed to false for development (known ST IDE bug)
            metadata: [values: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']])
        }

        section("Minimum interval between waterings...") {
            input "days", "number", title: "Days?", description: "minimum # days between watering", defaultValue: "1", required: false
        }

        section("Start watering at what times...") {
            input name: "waterTimeOne",  type: "time", required: true, title: "Turn them on at..."
            input name: "waterTimeTwo",  type: "time", required: false, title: "and again at..."
            input name: "waterTimeThree",  type: "time", required: false, title: "and again at..."
        }
    }

    page(name: "sprinklerPage", title: "Sprinkler Controller Setup", nextPage: "weatherPage", uninstall: true) {
        section("Select your sprinkler controller...") {
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

 	}
    
	page(name: "weatherPage", title: "Virtual Weather Station Setup", install: true) {
		
        section("Zip code or Weather Station ID to check weather...") {
            input "zipcode", "text", title: "Enter zipcode or or pws:stationid", required: false
        }
        
        section("Select which rain to add to your virtual rain guage...") {
        	input "isYesterdaysRainEnabled", "boolean", title: "Yesterday's Rain", description: "Include?", defaultValue: "true", required: false
        	input "isTodaysRainEnabled", "boolean", title: "Today's Rain", description: "Include?", defaultValue: "true", required: false
        	input "isForecastRainEnabled", "boolean", title: "Today's Forecasted Rain", description: "Include?", defaultValue: "false", required: false
        }
       
       	section("Skip watering if virutal rain guage totals more than... (default 0.5)") {
            input "wetThreshold", "decimal", title: "Inches?", defaultValue: "0.5", required: false
        }
        
        section("Run watering only if forecasted high temp (F) is greater than... (default 50)") {
            input "tempThreshold", "decimal", title: "Temp?", defaultValue: "50", required: false
        }
        
    }
}		

//def installed() {
//    scheduling()
//    state.daysSinceLastWatering = [0,0,0]
//}

//def updated() {
//    unschedule()
//    scheduling()
//    state.daysSinceLastWatering = [0,0,0]
//}

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
    log.info "Running Irrigation Schedule: ${app.label}"

    if (schedulerState == "onHold") {
        log.info("${app.label} sprinkler schedule on hold.")
        return
    } 
    
	if (schedulerState == "skip") { 
    	// delay this watering and reset device.effect to noEffect
        schedulerState = "delay" 
        for(s in switches) {
            if("noEffect" in s.supportedCommands.collect { it.name }) {
                s.noEffect()
                log.info ("${app.label} skipped one watering and will resume normal operations at next scheduled time")
            }
        }
 	}    
    
	if (schedulerState != "expedite") { 
    	// Change to delay if wet or too cold
        schedulerState = isWeatherDelay() ? "delay" : schedulerState
 	}

    if (schedulerState != "delay") {
        state.daysSinceLastWatering[state.currentTimerIx] = daysSince() + 1
    }
// 	  Next line is useful log statement for debugging why the smart app may not be triggering.
//    log.info("${app.label} scheduler state: ${schedulerState}. Days since last watering: ${daysSince()}. Is watering day? ${isWateringDay()}. Enought time? ${enoughTimeElapsed(schedulerState)} ")

    if ((isWateringDay() && enoughTimeElapsed(schedulerState) && schedulerState != "delay") || schedulerState == "expedite") {
        state.daysSinceLastWatering[state.currentTimerIx] = 0
        def wateringAttempts = 1
        water(wateringAttempts)
    }
}

def isWateringDay() {
    if(!wateringDays) return true

    def today = new Date().format("EEEE", location.timeZone)
    if (wateringDays.contains(today)) {
        return true
    }
    log.info "${app.label} watering is not scheduled for today"
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

def isWeatherDelay() { 
	log.info "${app.label} Is Checking The Weather"
    if (zipcode) {
        
        //add rain to virtual rain guage
        def rainGauge = 0
        def todaysInches
		def yesterdaysInches
        def forecastInches
        
        if (isYesterdaysRainEnabled.equals("true")) {        
            yesterdaysInches = wasWetYesterday()
            rainGauge = rainGauge + yesterdaysInches
        }

        if (isTodaysRainEnabled.equals("true")) {
            todaysInches=isWet()
            rainGauge = rainGauge + todaysInches
        }

        if (isForecastRainEnabled.equals("true")) {
            forecastInches = isStormy()
            rainGauge = rainGauge + forecastInches
        }
      
        if (isRainGuageNotificationEnabled.equals("true")) {
        		sendPush("Virtual rain gauge reads ${rainGauge.round(2)} inches.\nToday's Rain: ${todaysInches} inches, \nYesterday's Rain: ${yesterdaysInches} inches, \nForecast Rain: ${forecastInches} inches")  
        }
        log.info ("Virtual rain gauge reads ${rainGauge.round(2)} inches")
        
 //     check to see if virtual rainguage exceeds threshold
        if (rainGauge > (wetThreshold?.toFloat() ?: 0.5)) {
            if (isNotificationEnabled.equals("true")) {
                sendPush("Skipping watering today due to precipitation.")    
            }
            log.info "${app.label} skipping watering today due to precipitation."
            for(s in switches) {
                if("rainDelayed" in s.supportedCommands.collect { it.name }) {
                    s.rainDelayed()
                    log.info "Watering is rain delayed for $s"
                }
            }
            return true
        }
        
        def maxThermometer = isHot()
        if (maxThermometer < (tempThreshold?.toFloat() ?: 0)) {
        	if (isNotificationEnabled.equals("true")) {
                sendPush("Skipping watering: $maxThermometer forecast high temp is below threshold temp.")
            }
            log.info "${app.label} is skipping watering: temp is below threshold temp."
            return true
		}
     }
    return false
}

def safeToFloat(value) {
    if(value && value.isFloat()) return value.toFloat()
    return 0.0
}

def wasWetYesterday() {
    
    def yesterdaysWeather = getWeatherFeature("yesterday", zipcode)
    def yesterdaysPrecip = yesterdaysWeather?.history?.dailysummary?.precipi?.toArray() 
    def yesterdaysInches= yesterdaysPrecip ? safeToFloat(yesterdaysPrecip[0]) : 0
    log.info("Yesterday's percipitation for $zipcode: $yesterdaysInches in")
	return yesterdaysInches    
}

def isWet() {

    def todaysWeather = getWeatherFeature("conditions", zipcode)
    def todaysPrecip = (todaysWeather?.current_observation?.precip_today_in)
    def todaysInches = todaysPrecip ? safeToFloat(todaysPrecip) : 0
    log.info("Today's percipitation for ${zipcode}: ${todaysInches} in")
    return todaysInches
}

def isStormy() {

    def forecastWeather = getWeatherFeature("forecast", zipcode)
    def forecastPrecip=forecastWeather.forecast.simpleforecast.forecastday.qpf_allday.in?.toArray()
    def forecastInches = forecastPrecip ? safeToFloat(forecastPrecip[0]) : 0
    log.info("Forecast percipitation for $zipcode: $forecastInches in")
    return forecastInches
}

def isHot() {

    def forecastWeather = getWeatherFeature("forecast", zipcode)
    def todaysTemps=forecastWeather.forecast.simpleforecast.forecastday.high.fahrenheit?.toArray()
    def todaysHighTemp = todaysTemps ? safeToFloat(todaysTemps[0]) : 50
    log.info("Forecast high temperature for $zipcode: $todaysHighTemp F")
    return todaysHighTemp
}

//send watering times over to the device handler
def water(attempts) {
	log.info ("Starting Irrigation Schedule: ${app.label}")
    if (isNotificationEnabled.equals("true")) {
        	sendPush("${app.label} Is Starting Irrigation" ?: "null pointer on app name")
    }
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
    } 
    else {
        switches.on()
    }
	if (attempts <2) {
        // developers note: runIn() appears to only call void methods
        runIn(20, isWateringCheckOnce)
	}
	else {
        runIn(20, isWateringCheckTwice)
	}
}

def isWateringCheckOnce() { 
    def switchCurrentState = switches.currentSwitch    
	if (switchCurrentState != "on") {
		log.info "${app.label} is unable to turn on irrigation system.  Trying a second time"
		def wateringAttempts = 2
		// try to start watering again
		water(wateringAttempts)
	}
}		

def isWateringCheckTwice() { 
    def switchCurrentState = switches.currentSwitch    
	if (switchCurrentState != "on") {
		switches.warning()
        sendPush("${app.label} did not start after two attempts.  Check system")
        log.info "WARNING: ${app.label} failed to water. Check your system"
	}  
}		

def anyZoneTimes() {
    return zone1 || zone2 || zone3 || zone4 || zone5 || zone6 || zone7 || zone8 || zone9 || zone10 || zone11 || zone12 || zone13 || zone14 || zone15 || zone16 || zone17 || zone18 || zone19 || zone20 || zone21 || zone22 || zone23 || zone24
}