package com.zovra.automation.automationapi.powerconsumption.dao;

import com.zovra.automation.automationapi.model.PowerConsumption.LogPhaseId;
import com.zovra.automation.automationapi.model.PowerConsumption.Voltage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Voltage_Repository extends JpaRepository<Voltage, LogPhaseId> {

 @Query(value = "select * from PowerConsumptionLogger.Voltage v where v.log_id = :id", nativeQuery = true)
 List<Voltage> findAllByLogId(@Param("id") int id);

}
