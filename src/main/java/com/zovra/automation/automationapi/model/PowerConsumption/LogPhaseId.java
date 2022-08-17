package com.zovra.automation.automationapi.model.PowerConsumption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public final class LogPhaseId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "log_id", nullable = false)
    private Log log_id;
    @Column(name = "Phase", nullable = false)
    private int Phase;

    public LogPhaseId(Log log_id, int phase) {
        this.log_id = log_id;
        this.Phase = phase;
    }

    public LogPhaseId(Log log_id) {
        this.log_id = log_id;

    }

    public LogPhaseId() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLog_id(), getPhase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LogPhaseId that = (LogPhaseId) obj;
        return Objects.equals(getLog_id(), that.getLog_id()) &&
                Objects.equals(getPhase(), that.getPhase());
    }

    public Log getLog_id() {
        return log_id;
    }

    public void setLog_id(Log realtime_id) {
        this.log_id = realtime_id;
    }

    public int getPhase() {
        return Phase;
    }

    public void setPhase(int phase) {
        this.Phase = phase;
    }
}

