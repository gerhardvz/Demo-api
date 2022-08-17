package com.zovra.automation.automationapi.model.PowerConsumption;

import javax.persistence.*;


@Entity
@Table
public final class EnergyTotal {
    @EmbeddedId
    private
    EnergyTotalId id;

    @Column(name = "Active", nullable = false)
    private
    Double Active;
    @Column(name = "Reactive", nullable = false)
    private
    Double Reactive;
    @Column(name = "Apparent", nullable = false)
    private
    Double Apparent;

    public EnergyTotalId getId() {
        return id;
    }

    public void setId(EnergyTotalId id) {
        this.id = id;
    }

    public Double getActive() {
        return Active;
    }

    public void setActive(Double active) {
        Active = active;
    }

    public Double getReactive() {
        return Reactive;
    }

    public void setReactive(Double reactive) {
        Reactive = reactive;
    }

    public Double getApparent() {
        return Apparent;
    }

    public void setApparent(Double apparent) {
        Apparent = apparent;
    }

    public EnergyTotal(EnergyTotalId id, Double active, Double reactive, Double apparent) {
        this.id = id;
        this.Active = active;
        this.Reactive = reactive;
        this.Apparent = apparent;
    }

    public EnergyTotal(Energy energy, boolean imported, Double active, Double reactive, Double apparent) {
        this.id = new EnergyTotalId(energy, imported);
        this.Active = active;
        this.Reactive = reactive;
        this.Apparent = apparent;
    }

    public EnergyTotal() {
    }
}
