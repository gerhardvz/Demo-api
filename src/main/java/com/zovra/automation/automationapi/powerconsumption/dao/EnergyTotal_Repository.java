package com.zovra.automation.automationapi.powerconsumption.dao;

import com.zovra.automation.automationapi.model.PowerConsumption.EnergyTotal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyTotal_Repository extends JpaRepository<EnergyTotal, Integer> {
}
