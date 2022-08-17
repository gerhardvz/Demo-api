package com.zovra.automation.automationapi.model.PowerConsumption;

import javax.persistence.*;

@Entity
@Table
public final class Power {

    @EmbeddedId
    private
    LogPhaseId logPhaseId;
    @Transient
    private
    int Phase;

    @Column(name = "Active", nullable = false)
    private
    Double Active;
    @Column(name = "Reactive", nullable = false)
    private
    Double Reactive;
    @Column(name = "Apparent", nullable = false)
    private
    Double Apparent;

    public int getLogPhaseId() {
        return logPhaseId.getLog_id().getId();
    }

    public void setLogPhaseId(LogPhaseId id) {
        this.logPhaseId = id;
    }

    public int getPhase() {
        return logPhaseId.getPhase();
    }

    public void setPhase(int phase) {
        logPhaseId.setPhase(phase);
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

    public Power() {
    }

    public Power(LogPhaseId logPhaseId, Double active, Double reactive, Double apparent) {
        this.logPhaseId = logPhaseId;
        Active = active;
        Reactive = reactive;
        Apparent = apparent;
    }

    public Power(Log logPhaseId, int phase, Double active, Double reactive, Double apparent) {
        this.logPhaseId = new LogPhaseId(logPhaseId, phase);
        Active = active;
        Reactive = reactive;
        Apparent = apparent;
    }
}
