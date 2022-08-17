package com.zovra.automation.automationapi.powerconsumption.dao;

import com.zovra.automation.automationapi.model.PowerConsumption.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Device_Repository extends JpaRepository<Device, Integer> {
    Device getDeviceBySerial(int serial);
    Device getDeviceById(int id);
}
