package com.zovra.automation.automationapi.powerconsumption.dao;

import com.zovra.automation.automationapi.model.PowerConsumption.Device;
import com.zovra.automation.automationapi.model.PowerConsumption.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface Log_Repository extends JpaRepository<Log, Integer> {

    Log findLogById(int id);

    List<Log> findAllByDevice(Device device);

    Log getLogByDeviceAndTimestamp(Device device, int timestamp);

    List<Log> findLogsByTimestampGreaterThanEqualAndTimestampLessThanEqual(Double from, Double to);

//    List<Log> getTopByDeviceOrderByTimestampTimestampDesc(Device device);
    Log getTopByDeviceOrderByTimestampDesc(Device device);
}
