package com.zovra.automation.automationapi.model.PowerConsumption;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public final class EnergyTotalId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "Energy_id", nullable = false)
    private Energy energy_id;
    @Column(name = "Imported", nullable = false)
    private boolean imported;

    public EnergyTotalId(Energy energy_id, boolean imported) {
        this.energy_id = energy_id;
        this.imported = imported;
    }

    public EnergyTotalId(Energy energy_id) {
        this.energy_id = energy_id;

    }

    public EnergyTotalId() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRealtime_id(), getImported());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EnergyTotalId that = (EnergyTotalId) obj;
        return Objects.equals(getRealtime_id(), that.getRealtime_id()) &&
                Objects.equals(getImported(), that.getImported());
    }

    public Energy getRealtime_id() {
        return energy_id;
    }

    public void setRealtime_id(Energy energy_id) {
        this.energy_id = energy_id;
    }

    public boolean getImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }
}

