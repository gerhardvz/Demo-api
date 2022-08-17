package com.zovra.automation.automationapi.model.Inverter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Photovoltaic {
    @ManyToOne
    @JoinColumn(name = "log_id",nullable = false)
//    Log LogID;
    int pvNum;
    float pvCurrent;
    float pvVoltage;
}
