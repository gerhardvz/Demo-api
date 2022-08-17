package com.zovra.automation.automationapi.model.PowerConsumption;

import javax.persistence.*;


@Entity
@Table
public final class Energy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private
    int id;
    @ManyToOne
    @JoinColumn(name = "Device_id", nullable = false)
    private
    Device device_id;
    @Column(name = "Timestamp", nullable = false)
    private
    Double timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Device getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Device device_id) {
        this.device_id = device_id;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    public Energy(Device device_id, Double timestamp) {

        this.device_id = device_id;
        this.timestamp = timestamp;
    }

    public Energy() {
    }
}
