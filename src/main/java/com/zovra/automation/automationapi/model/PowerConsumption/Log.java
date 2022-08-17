package com.zovra.automation.automationapi.model.PowerConsumption;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
@Table
public final class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private
    int id;

    @ManyToOne
    @JoinColumn(name = "Device_id",nullable = false)
    private
    Device device;

    @Column(name = "Timestamp", nullable = false)
    private
    Double timestamp;
    @Column(name = "Frequency", nullable = false)
    private
    Double frequency;
    //    @Column(name="Efficiency", nullable = false)
//    private
//    Double Efficiency;
    @Column(name = "P_Active_Total", nullable = true)
    private
    Double p_Active_Total;
    @Column(name = "P_Reactive_Total", nullable = true)
    private
    Double p_Reactive_Total;
    @Column(name = "P_Apparent_Total", nullable = true)
    private
    Double p_Apparent_Total;
    @Column(name = "PF_Total", nullable = false)
    private
    Double pf_Total;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Log() {
    }

    public Log(Device device, Double timestamp, Double frequency, Double p_Active_Total, Double p_Reactive_Total, Double p_Apparent_Total, Double PF_Total) {
//        this.id = id;
        this.device = device;
        this.timestamp = timestamp;
        this.frequency = frequency;

        setP_Active_Total(p_Active_Total);
        setP_Reactive_Total(p_Reactive_Total);
        setP_Apparent_Total(p_Apparent_Total);
        this.setPF_Total(PF_Total);
    }

    public Log(Device device, Double timestamp, Double frequency, Double PF_Total) {
        this.device = device;
        this.timestamp = timestamp;
        this.frequency = frequency;
//        Efficiency = efficiency;
        this.setPF_Total(PF_Total);
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

//    public Double getEfficiency() {
//        return Efficiency;
//    }

//    public void setEfficiency(Double efficiency) {
//        Efficiency = efficiency;
//    }

    public Double getP_Active_Total() {
        return p_Active_Total;
    }

    public void setP_Active_Total(Double p_Active_Total) {
        this.p_Active_Total = p_Active_Total;
    }

    public Double getP_Reactive_Total() {
        return p_Reactive_Total;
    }

    public void setP_Reactive_Total(Double p_Reactive_Total) {
        this.p_Reactive_Total = p_Reactive_Total;
    }

    public Double getP_Apparent_Total() {
        return p_Apparent_Total;
    }

    public void setP_Apparent_Total(Double p_Apparent_Total) {
        this.p_Apparent_Total = p_Apparent_Total;
    }

    public Double getPF_Total() {
        return pf_Total;
    }

    public void setPF_Total(Double pf_Total) {
        this.pf_Total = pf_Total;
    }

    public Instant getEpochTime(){

        return Instant.ofEpochMilli(((Double)(this.timestamp * 1000.0)).longValue());
    }

    public LinkedHashMap<String,String> toHashMap(boolean detailed){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH-mm-ss z");
        var logMap = new LinkedHashMap<String, String>();


        logMap.put("timestamp",getEpochTime().atZone(TimeZone.getDefault().toZoneId()).format(dateTimeFormatter));


        if(!detailed){
            var url = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/../devices/")
                    .path(Integer.toString(this.device.getId()))
                    .toUriString();

            logMap.put("device",url);//Print path to device
            logMap.put("href",ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/")
                    .path(Integer.toString(this.getId()))
                    .toUriString());
            return logMap;
        }
        var url = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/../../devices/")
                .path(Integer.toString(this.device.getId()))
                .toUriString();

        logMap.put("device",url);//Print path to device
        logMap.put("frequency",String.valueOf(this.frequency));
        logMap.put("p_Active_Total",String.valueOf(this.p_Active_Total));
        logMap.put("p_Reactive_Total",String.valueOf(this.p_Reactive_Total));
        logMap.put("p_Apparent_Total",String.valueOf(this.p_Apparent_Total));
        logMap.put("pf_Total",String.valueOf(this.pf_Total));
        logMap.put("current",ServletUriComponentsBuilder.fromCurrentRequest()

                .path("/current")
                .toUriString());
        logMap.put("voltages",ServletUriComponentsBuilder.fromCurrentRequest()

                .path("/voltage")
                .toUriString());
        logMap.put("energy",ServletUriComponentsBuilder.fromCurrentRequest()

                .path("/energy")
                .toUriString());
        logMap.put("power",ServletUriComponentsBuilder.fromCurrentRequest()

                .path("/power")
                .toUriString());
        logMap.put("power_Factor",ServletUriComponentsBuilder.fromCurrentRequest()

                .path("/pf")
                .toUriString());
        logMap.put("href",ServletUriComponentsBuilder.fromCurrentRequest()

                .toUriString());

        return logMap;
    }
    @Override
    public String toString(){
        return new String("");
    }
}
