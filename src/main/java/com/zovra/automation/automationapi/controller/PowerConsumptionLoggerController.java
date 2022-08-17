package com.zovra.automation.automationapi.controller;

import com.zovra.automation.automationapi.model.PowerConsumption.*;
import com.zovra.automation.automationapi.powerconsumption.dao.*;


import com.zovra.automation.automationapi.service.LastEntry_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/PowerConsumption")
public class PowerConsumptionLoggerController {

    @Autowired
    private Device_Repository devices;
    @Autowired
    private Log_Repository logsData;
    @Autowired
    private Energy_Repository energyRepository;
    @Autowired
    private Current_Repository currentData;
    @Autowired
    private Voltage_Repository voltageData;
    @Autowired
    private PowerFactor_Repository powerFactorRepository;
    @Autowired
    private Power_Repository powerRepository;
    @Autowired
    private EnergyImport_Repository energyImportRepository;
    @Autowired
    private EnergyTotal_Repository energyTotalRepository;
    @Autowired
    private LastEntry_Repository lastEntryRepository;
    @Autowired
    private LastEntry_Service lastEntryService;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH-mm-ss z");

    //Http Get method to get the student list in JSON format
    @GetMapping(path = "/", produces = "application/json")
    public HashMap<String, String> getList(@RequestHeader Map<String, String> headers) {
        var list =new HashMap<String,String>();
        var url =ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/")
                .path("devices")
                .toUriString();
        list.put("Devices",url);
         url =ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/")
                .path("logs")
                .toUriString();
        list.put("Logs",url);
        System.out.println(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        System.out.println(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString());
        System.out.println(headers.toString());
        for (var key:headers.keySet()) {
            System.out.println("Key: "+key + " ;Value: "+ headers.get(key));
        }
        System.out.println("---");
        return list;
    }
    //Http Post method to add the student in the student list

    @RequestMapping("/index")
    String index() {
        //mapped to hostname:port/home/index/
        return "Hello from index";
    }



    //    @GetMapping("/realtimes/{id}")
//    public List<RealTime> getRealTimeDataMap() {
//
//        return realTimedata.findAll();
//    }


    //=======================Logs Mapping======================================================
    @GetMapping(value = "/logs")
    public HashMap<String,Map> getLogData(@RequestParam Optional<Integer> id, @RequestParam Optional<Integer> deviceid) {
        List<Log> logs= new ArrayList<Log>();
        System.out.println("ID is present:" + id.isPresent() + "\nDeviceID is present:" + deviceid.isPresent());

        if (id.isPresent()) {
            logs.add(logsData.findLogById(id.get()));
            System.out.println("Printing Log with ID:" + id.get());

        } else if (deviceid.isPresent()) {
            Device device = devices.getById(deviceid.get());
            logs.addAll(logsData.findAllByDevice(device));
            System.out.println("Printing Log with device ID:" + deviceid.get());

        }
        else {
            System.out.println("Printing all Logs");
            logs.addAll(logsData.findAll());
        }
        var logListMap = new HashMap<String,Map>();
        for (var log:logs) {
            logListMap.put(Integer.toString(log.getId()),log.toHashMap(false));
        }
        var logMap = new HashMap<String,Map>();
        logMap.put("logs",logListMap);
        return logMap;
    }

//    @GetMapping(value = "/realtimes" )
//    public RealTime getRealTimeDatabyDeviceID(@RequestParam Integer deviceid) {
//
//        return realTimedata.getById(deviceid);
//    }

    @GetMapping(value = "/logs/timeframe")
    public List<Log> getLogsInTimeframe(@RequestParam Double from, @RequestParam Double to) {

        return logsData.findLogsByTimestampGreaterThanEqualAndTimestampLessThanEqual(from, to);
    }


    @GetMapping(value = "/logs/{id}")
    public HashMap<String,Map> getLogByID(@PathVariable("id") int id) {

        var log= logsData.findLogById(id);
        var logMap = new HashMap<String,Map>();
        logMap.put(Integer.toString(log.getId()),log.toHashMap(true));
return logMap;
    }

    @GetMapping(value = "/logs/{id}/current")
    public Map<String, List<Current>> getCurrentByLogID(@PathVariable("id") int id) {

        Map<String, List<Current>> map = new HashMap<String, List<Current>>();
        map.put("Current", currentData.findAllByLogId(id));
        return map;

    }

    @GetMapping(value = "/logs/{id}/voltage")
    public Map<String, List<Voltage>> getVoltageByLogID(@PathVariable("id") int id) {

        Map<String, List<Voltage>> map = new HashMap<String, List<Voltage>>();
        map.put("Voltage", voltageData.findAllByLogId(id));
        return map;

    }
    @GetMapping(value = "/logs/{id}/power")
    public Map<String, List<Power>> getPowerByLogID(@PathVariable("id") int id) {

        Map<String, List<Power>> map = new HashMap<String, List<Power>>();
        map.put("Power", powerRepository.findAllByLogId(id));
        return map;

    }
    @GetMapping(value = "/logs/{id}/pf")
    public Map<String, List<PowerFactor>> getPowerFactorByLogID(@PathVariable("id") int id) {

        Map<String, List<PowerFactor>> map = new HashMap<String, List<PowerFactor>>();
        map.put("PowerFactor", powerFactorRepository.findAllByLogId(id));
        return map;

    }


    //=======================Energy Mapping======================================================
    @GetMapping("/energy_totals")
    public List<Energy> getEnergyTotals() {
        return energyRepository.findAll();
    }

    //=======================Device Mapping======================================================

    @GetMapping("/devices")
    public HashMap<String,Map> getDevices() {
        var deviceList = devices.findAll();
        var deviceMap = new HashMap<String,Map>();
        var deviceListMap = new HashMap<String,HashMap<String,String>>();
        for (int i = 0; i < deviceList.size(); i++) {
            var device =new HashMap<String,String>();
            device.putAll(deviceList.get(i).toHashMap(false));
            var url =ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/")
                    .path(String.valueOf(deviceList.get(i).getId()))
                    .toUriString();
            device.put("href",url);


            deviceListMap.put(String.valueOf(deviceList.get(i).getId()),device);
        }
        deviceMap.put("devices",deviceListMap);

        return deviceMap;
    }

    //TODO::Add List of Log ID with url to jump to logs
    @GetMapping("/devices/{id}")
    public HashMap<String,HashMap<String,String>> getDeviceById(@PathVariable Integer id) {
                var optionalDevice = devices.findById(id);
        var deviceListMap = new HashMap<String,HashMap<String,String>>();
                if (optionalDevice.isEmpty()){
                    return  deviceListMap;
                }
                var device = optionalDevice.get();

            var deviceMap =new HashMap<String,String>();
            deviceMap.putAll(device.toHashMap(true));


            deviceListMap.put(String.valueOf(device.getId()),deviceMap);

        return deviceListMap;
    }

    @GetMapping("/devices/{id}/logs")
    public LinkedHashMap<String,String> getLogsByDeviceId(@PathVariable("id") int device_id) {
        var device = devices.getById(device_id);
        var logs = logsData.findAllByDevice(device);

        var output = new LinkedHashMap<String,String>();
        for (var log :logs) {
            var epochTime = Instant.ofEpochMilli(((Double)(log.getTimestamp() * 1000.0)).longValue());

            output.put(epochTime.atZone(TimeZone.getDefault().toZoneId()).format(dateTimeFormatter),ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/../../../logs/")
                    .path(String.valueOf(log.getId()))
                    .toUriString());
        }

        return output;
    }

    @GetMapping("/devices/{id}/logs/latest")
    public List getLatestLogsByDeviceId(@PathVariable("id") int device_id) {
        var device = devices.getById(device_id);
//        var log = logsData.getTopByDeviceOrderByTimestampDesc(device);
        var latest = lastEntryService.findLastEntryForDevice(device_id);

//        var output = new LinkedHashMap<String,String>();
//        var epochTime = Instant.ofEpochMilli(((Double)(log.getTimestamp() * 1000.0)).longValue());
//        output.put(epochTime.atZone(TimeZone.getDefault().toZoneId()).format(dateTimeFormatter),ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/../../../logs/")
//                .path(String.valueOf(log.getId()))
//                .toUriString());
        return latest;
    }


    @GetMapping("/devices/{id}/current")
    public Map<String, Map<String, List<Current>>> getCurrentbyDevice(@PathVariable("id") int id) {

        Device device = devices.getById(id);
        List<Log> realTimeListbyDevice = logsData.findAllByDevice(device);
        Map<String, Map<String, List<Current>>> outputMap = new HashMap<String, Map<String, List<Current>>>();
        Map<String, List<Current>> currentListMap = new HashMap<String, List<Current>>();

        for (Log log : realTimeListbyDevice) {
            List<Current> currentList = new ArrayList<Current>();
            currentList.addAll(currentData.findAllByLogId(log.getId()));
            currentListMap.put(Double.toString(log.getTimestamp()), currentList);
        }
        outputMap.put("current", currentListMap);
        return outputMap;
    }
    @GetMapping("/devices/{id}/voltage")
    public Map<String, Map<String, List<Voltage>>> getVoltagebyDevice(@PathVariable("id") int id) {

        Device device = devices.getById(id);
        List<Log> realTimeListbyDevice = logsData.findAllByDevice(device);
        Map<String, Map<String, List<Voltage>>> outputMap = new HashMap<String, Map<String, List<Voltage>>>();
        Map<String, List<Voltage>> currentListMap = new HashMap<String, List<Voltage>>();

        for (Log log : realTimeListbyDevice) {
            List<Voltage> voltageList = new ArrayList<Voltage>();
            voltageList.addAll(voltageData.findAllByLogId(log.getId()));
            currentListMap.put(Double.toString(log.getTimestamp()), voltageList);
        }
        outputMap.put("voltage", currentListMap);
        return outputMap;
    }

    //=======================Log Mapping======================================================
    @PostMapping(value = "/log", consumes = "application/json")
    public Map<String,Object> logAdd(@RequestBody Map<String, Object> payload) {

        Map<String, Object> output = new HashMap<String, Object>();
        output.put("powerlogger", new HashMap<String, Object>());

        if (payload.get("PowerLogger") != null) {

            Log rt = null;
            List<Current> curr = new ArrayList<>();
            List<Voltage> volt = new ArrayList<>();
            List<Power> pow = new ArrayList<>();
            List<PowerFactor> powerFactor1 = new ArrayList<>();
            try {
                Map<String, Object> pL = (Map<String, Object>) payload.get("PowerLogger");
                //Create New RealTime Data object
                Device device = devices.getDeviceBySerial((int) pL.get("Device_Serial"));
                if (device == null){
                    throw new Exception("Device does not exist.");
                }

                rt = new Log(device, ((Double) pL.get("Timestamp")), ((Double) pL.get("Frequency")), ((Double) pL.get("PF_Total")));
                System.out.printf("%f \n",(Double) pL.get("Timestamp"));

                //Create Current Object
                ArrayList<Map<String, Object>> current = (ArrayList<Map<String, Object>>) pL.get("Current");
                for (Map<String, Object> c : current) {
                    curr.add(new Current(rt, (int) c.get("Phase"), ((Double) c.get("I"))));

                }

                //Create Voltage Object
                ArrayList<Map<String, Object>> voltage = (ArrayList<Map<String, Object>>) pL.get("Voltage");
                for (Map<String, Object> v : voltage) {
                    volt.add(new Voltage(rt, (int) v.get("Phase"), ((Double) v.get("U_ll")), ((Double) v.get("U_ln"))));

                }

                //Create PowerFactor Object
                ArrayList<Map<String, Object>> powerFactor = (ArrayList<Map<String, Object>>) pL.get("PF");
                for (Map<String, Object> pf : powerFactor) {
                    powerFactor1.add(new PowerFactor(rt, (int) pf.get("Phase"), ((Double) pf.get("PF"))));

                }

                //Create Power Object
                ArrayList<Map<String, Object>> power = (ArrayList<Map<String, Object>>) pL.get("Power");
                for (Map<String, Object> p : power) {
                    pow.add(new Power(rt, (int) p.get("Phase"), ((Double) p.get("Active")), ((Double) p.get("Reactive")), ((Double) p.get("Apparent"))));

                }

                if (rt != null && curr != null && volt != null && powerFactor1 != null && pow != null) {
                    logsData.save(rt);
                    currentData.saveAll(curr);
                    voltageData.saveAll(volt);
                    powerFactorRepository.saveAll(powerFactor1);
                    powerRepository.saveAll(pow);

                } else {
                    throw new Exception("Data not valid.");
                }

                ((Map<String, Object>) output.get("powerlogger")).put("mesg", "Powerlog added successfully");
            } catch (Exception e) {
                System.out.println("Exception");
                e.printStackTrace();

                ((Map<String, Object>) output.get("powerlogger")).put("err", "Data not valid");
                ((Map<String, Object>) output.get("powerlogger")).put("mesg", e.getLocalizedMessage().replace("\"", "\'"));
            }


        } else {
            ((Map<String, Object>) output.get("powerlogger")).put("err", "Powerlog not given");

        }

        output.put("energy", new HashMap<String, Object>());
        if (payload.get("Energy") != null) {

            Energy energy = null;
            EnergyTotal energyTotal = null;
            EnergyImports energyImports = null;

            try {
                Map<String, Object> e = (Map<String, Object>) payload.get("Energy");

                Device device = devices.getDeviceBySerial((int) e.get("Device_Serial"));
                if (device == null){
                    throw new Exception("Device does not exist.");
                }

                energy = new Energy(device, ((Double) e.get("Timestamp")));



                ArrayList<Map<String, Object>> EnergyTotal = (ArrayList<Map<String, Object>>) e.get("Energy_Total");
                for (Map<String, Object> et : EnergyTotal) {
                    energyTotal = new EnergyTotal(energy, (boolean) et.get("Import"), ((Double) et.get("Active")), ((Double) et.get("Reactive")), ((Double) et.get("Apparent")));

                }

                ArrayList<Map<String, Object>> EnergyImports = (ArrayList<Map<String, Object>>) e.get("Energy_Import");
                for (Map<String, Object> ei : EnergyImports) {
                    energyImports = new EnergyImports(energy, (int) ei.get("Phase"), ((Double) ei.get("Active")), ((Double) ei.get("Reactive")), ((Double) ei.get("Apparent")));

                }

                if (energy != null && energyTotal != null && energyImports != null) {
                    energyRepository.save(energy);
                    energyTotalRepository.save(energyTotal);
                    energyImportRepository.save(energyImports);
                } else {
                    throw new Exception("Data not valid.");
                }


                ((Map<String, Object>) output.get("energy")).put("mesg", "Energy log added successfully");
            } catch (Exception e) {
                System.out.println("Exception");
                e.printStackTrace();

                ((Map<String, Object>) output.get("energy")).put("err", "Data not valid");
                ((Map<String, Object>) output.get("energy")).put("mesg", e.getLocalizedMessage().replace("\"", "\'"));
            }
        } else {

            ((Map<String, Object>) output.get("energy")).put("err", "Energy log not given");
        }

        return output;
    }

    @GetMapping("/stream/basicv2")
    public ResponseEntity<String> basicStreamV2(@RequestParam Integer device_id){
        Instant timeNow =  Instant.now();
        List<LastEntryView> lastEntryViewList = lastEntryRepository.findAllByDevice(device_id);
        if(lastEntryViewList.isEmpty()){
            String str = "{\"mesg\":\"No data available.\"}";
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(str);
        }
        String voltageStr="\"voltage\":{";
        String currentStr="\"current\":{";
        for (LastEntryView lev:lastEntryViewList) {
            voltageStr+="\"phase"+lev.getPhase()+"\":"+ lev.getU_ln()+",";
            currentStr+="\"phase"+lev.getPhase()+"\":"+ lev.getA_l()+",";
        }

        voltageStr = voltageStr.substring(0, voltageStr.lastIndexOf(','));
        voltageStr+="}";
        currentStr = currentStr.substring(0, currentStr.lastIndexOf(','));
        currentStr+="}";
        Date timestamp = new Date(((Double)(lastEntryViewList.get(0).getTimestamp()*Double.valueOf(1000))).longValue());
        DateFormat df = new SimpleDateFormat("dd-MM HH:mm:ss");
        String str= "{\"timestamp\":\""+ df.format(timestamp)+"\","+voltageStr+","+currentStr+"}\n";
        Instant end = Instant.now();
        System.out.println(str);
        System.out.printf("Duration %d", Duration.between(timeNow,end).toMillis());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(str);
    }

    @GetMapping("/stream/basic")
    public ResponseEntity<String> basicStream(@RequestParam Integer device_id){
        System.out.println(device_id);
        Instant timeNow =  Instant.now();
        Device device = devices.getById(device_id);
        Log realTimes = logsData.getTopByDeviceOrderByTimestampDesc(device);
        ArrayList<LogPhaseId> logPhaseIdList = new ArrayList<LogPhaseId>();
        logPhaseIdList.add(new LogPhaseId(realTimes,0));
        logPhaseIdList.add(new LogPhaseId(realTimes,1));
        logPhaseIdList.add(new LogPhaseId(realTimes,2));
        logPhaseIdList.add(new LogPhaseId(realTimes,3));
        List<Voltage> voltages = voltageData.findAllById(logPhaseIdList);
        List<Current> currents = currentData.findAllById(logPhaseIdList);
        Date timestamp = new Date(((Double)(realTimes.getTimestamp()*Double.valueOf(1000))).longValue());
//        String timeStamp = String.valueOf(((Double)(realTimes.getTimestamp()*Double.valueOf(1000))).longValue());

        String str = "{\"voltage\":{";

        for ( Voltage voltage: voltages){
            str+="\"phase"+voltage.getPhase()+"\":"+ voltage.getU_ln()+",";
        }
        str = str.substring(0, str.lastIndexOf(','));
        str+="},\"current\":{";
        for ( Current current: currents){
            str+="\"phase"+current.getPhase()+"\":"+ current.getI()+",";
        }
        str = str.substring(0, str.lastIndexOf(','));
        str+="}";
        //Current
        DateFormat df = new SimpleDateFormat("dd-MM HH:mm:ss");

        str+= ",\"timestamp\":\""+ df.format(timestamp)+"\"}\n";
//        String str = "{\"voltage\":{\"phase"+voltages.get(1).getPhase()+"\":"+ voltages.get(1).getU_ln()+",\"phase2\":"+ ThreadLocalRandom.current().nextInt(220, 251)+",\"phase3\":"+ ThreadLocalRandom.current().nextInt(220, 251)+"},\"current\":{\"phase1\":225,\"phase2\":230,\"phase3\":250},\"timestamp\":\""+ timeStamp+"\"}\n";
        Instant end = Instant.now();
        System.out.printf("Duration %d", Duration.between(timeNow,end).toMillis());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(str);
    }

    @GetMapping("/current")
    public List<Current> getCurrentDataMap() {
        System.out.println(currentData.findAll().toString());
        return currentData.findAll();
    }

    //    @GetMapping ("/upload")
//    public List<Current> getCurrentDataMap(){
//        System.out.println(currentData.findAll().toString());
//        return currentData.findAll();
//    }


}
