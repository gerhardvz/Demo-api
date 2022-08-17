package com.zovra.automation.automationapi.model.Garden;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity

public class Temperature {
    int device;
    @Column(name = "Temperatur", nullable = false)
    double temp; // in Celcius
    @Column(name = "Timestamp", nullable = false)
    LocalDateTime time;

}
