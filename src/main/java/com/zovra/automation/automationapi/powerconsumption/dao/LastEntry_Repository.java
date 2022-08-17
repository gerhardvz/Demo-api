package com.zovra.automation.automationapi.powerconsumption.dao;

import com.zovra.automation.automationapi.model.PowerConsumption.LastEntityViewId;
import com.zovra.automation.automationapi.model.PowerConsumption.LastEntryView;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.StoredProcedureQuery;
import java.util.List;

public interface LastEntry_Repository extends JpaRepository<LastEntryView, LastEntityViewId> {

    List<LastEntryView> findAllByDevice(Integer device_id);

//     LastEntryView findLastEntryForDevice(int device_id) ;
}
