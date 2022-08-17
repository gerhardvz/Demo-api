package com.zovra.automation.automationapi.powerconsumption.dao;

import com.zovra.automation.automationapi.model.PowerConsumption.Current;
import com.zovra.automation.automationapi.model.PowerConsumption.LogPhaseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Current_Repository extends JpaRepository<Current, LogPhaseId> {


//    List<Current> findAllByRealtimeId(RealtimePhaseId realtimePhaseId);

   List<Current> findAllByLogPhaseId(LogPhaseId logPhaseId);

    @Query(value = "select * from PowerConsumptionLogger.Current c where c.log_id = :id", nativeQuery = true)
    List<Current> findAllByLogId(@Param("id") int realtimePhaseId);

}
