//package com.zovra.automation.automationapi.model.Inverter;
//
//
//import javax.persistence.*;
//
//public class Log {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private
//    int id;
//
//    @ManyToOne
//    @JoinColumn(name = "Device_id",nullable = false)
//    private
//    Device device;
//
//    @Column(name = "Timestamp", nullable = false)
//    private
//    Double timestamp;
//
//    public Log(Device device, Double timestamp) {
////        this.id = id;
//        this.device = device;
//        this.timestamp = timestamp;
//
//    }
//
//
//}
