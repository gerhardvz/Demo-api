package com.zovra.automation.automationapi.powerconsumption.dao;

import com.zovra.automation.automationapi.model.PowerConsumption.Energy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Energy_Repository extends JpaRepository<Energy, Integer> {
}
