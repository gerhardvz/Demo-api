package com.zovra.automation.automationapi.model.PowerConsumption;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Entity
@Table

public final class Device {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "Brand", nullable = false)
    private String Brand;
    @Column(name = "Model_id", nullable = false)
    private String Model_id;
    @Column(name = "Serial", unique = true, nullable = false)
    private int serial;
    @Column(name = "Name", nullable = false)
    private String Name;
    @Column(name = "Active")
    private boolean Active;
    @Column(name = "RS485_Addr", nullable = false)
    private int RS485_addr;

    public int getId() {
        return id;
    }

    public Device(int id, String brand, String model_id, int serial, String name, int RS485_addr) {
        this.setId(id);
        setBrand(brand);
        setModel_id(model_id);
        setSerial(serial);
        setName(name);
        this.RS485_addr = RS485_addr;
    }

    public Device() {

    }

    public String getBrand() {
        return Brand;
    }

    public String getModel_id() {
        return Model_id;
    }

    public int getSerial() {
        return serial;
    }

    public String getName() {
        return Name;
    }

    public boolean isActive() {
        return Active;
    }

    public int getRS485_addr() {
        return RS485_addr;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public void setRS485_addr(int RS485_addr) {
        this.RS485_addr = RS485_addr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setModel_id(String model_id) {
        Model_id = model_id;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }


    public LinkedHashMap<String,String> toHashMap(boolean detailed){
        var deviceMap = new LinkedHashMap<String,String>();
        deviceMap.put("serial",String.valueOf(this.serial));
        deviceMap.put("name",this.Name);
        deviceMap.put("active",String.valueOf(this.isActive()));
        deviceMap.put("brand",this.Brand);
        deviceMap.put("model_id",this.Model_id);
        deviceMap.put("rs485_addr",String.valueOf(this.getRS485_addr()));
        if (detailed){
            deviceMap.put("logs", ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/logs")

                    .toUriString());
        }
        return deviceMap;
    }
}
