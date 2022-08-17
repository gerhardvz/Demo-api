package com.zovra.automation.automationapi.model.PowerConsumption;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public final class EnergyPhaseId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "Energy_id", nullable = false)
    private Energy energy_id;
    @Column(name = "Phase", nullable = false)
    private int Phase;

    public EnergyPhaseId(Energy energy_id, int phase) {
        this.energy_id = energy_id;
        this.Phase = phase;
    }

    public EnergyPhaseId(Energy energy_id) {
        this.energy_id = energy_id;

    }

    public EnergyPhaseId() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRealtime_id(), getPhase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EnergyPhaseId that = (EnergyPhaseId) obj;
        return Objects.equals(getRealtime_id(), that.getRealtime_id()) &&
                Objects.equals(getPhase(), that.getPhase());
    }

    public Energy getRealtime_id() {
        return energy_id;
    }

    public void setRealtime_id(Energy energy_id) {
        this.energy_id = energy_id;
    }

    public int getPhase() {
        return Phase;
    }

    public void setPhase(int phase) {
        this.Phase = phase;
    }
}

