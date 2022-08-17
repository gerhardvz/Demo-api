package com.zovra.automation.automationapi.powerconsumption.dao;

import com.zovra.automation.automationapi.model.PowerConsumption.LogPhaseId;
import com.zovra.automation.automationapi.model.PowerConsumption.Power;
import com.zovra.automation.automationapi.model.PowerConsumption.PowerFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PowerFactor_Repository extends JpaRepository<PowerFactor, LogPhaseId> {

    @Query(value = "select * from PowerConsumptionLogger.PowerFactor pf where pf.log_id = :id", nativeQuery = true)
    List<PowerFactor> findAllByLogId(@Param("id") int realtimePhaseId);
}
