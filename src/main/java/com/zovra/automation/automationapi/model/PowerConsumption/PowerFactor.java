package com.zovra.automation.automationapi.model.PowerConsumption;

import javax.persistence.*;

@Entity
@Table
public final class PowerFactor {
    @EmbeddedId
    private
    LogPhaseId logPhaseId;
    @Transient
    private
    int Phase;
    @Column(name = "PF", nullable = false)
    private
    Double PF;

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

    public Double getPF() {
        return PF;
    }

    public void setPF(Double PF) {
        this.PF = PF;
    }

    public PowerFactor() {
    }

    public PowerFactor(LogPhaseId logPhaseId, Double PF) {
        this.logPhaseId = logPhaseId;
        this.PF = PF;
    }

    public PowerFactor(Log logPhaseId, int phase, Double PF) {
        this.logPhaseId = new LogPhaseId(logPhaseId, phase);
        this.PF = PF;
    }
}
