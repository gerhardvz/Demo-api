package com.zovra.automation.automationapi.powerconsumption.dao;

import com.zovra.automation.automationapi.model.PowerConsumption.EnergyImports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyImport_Repository extends JpaRepository<EnergyImports, Integer> {
}
