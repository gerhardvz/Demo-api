package com.zovra.automation.automationapi.powerconsumption.dao;

import com.zovra.automation.automationapi.model.PowerConsumption.Current;
import com.zovra.automation.automationapi.model.PowerConsumption.LogPhaseId;
import com.zovra.automation.automationapi.model.PowerConsumption.Power;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Power_Repository extends JpaRepository<Power, LogPhaseId> {

    @Query(value = "select * from PowerConsumptionLogger.Power p where p.log_id = :id", nativeQuery = true)
    List<Power> findAllByLogId(@Param("id") int realtimePhaseId);
}
