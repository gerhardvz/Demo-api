package com.zovra.automation.automationapi.model.PowerConsumption;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table
public final class Current {
    @EmbeddedId
    private
    LogPhaseId logPhaseId;

    @Column(name = "I", nullable = false)
    private
    Double I;

    public Current(LogPhaseId id, Double i) {
        this.logPhaseId = id;

        I = i;
    }

    public Current(Log id, int phase, Double i) {
        this.logPhaseId = new LogPhaseId(id, phase);

        I = i;
    }


    public Current() {
    }

//    public RealtimePhaseId getRealtimeId() {
//        return realtimeId;
//    }

    public int getLogPhaseId() {
        return logPhaseId.getLog_id().getId();
    }

    public void setLogPhaseId(LogPhaseId realtimeId) {
        this.logPhaseId = realtimeId;
    }



    public Double getI() {
        return I;
    }

    public void setI(Double i) {
        I = i;
    }

    public int getPhase() {
        return logPhaseId.getPhase();
    }

    public void setPhase(int phase) {
        logPhaseId.setPhase(phase);
    }

    public String toString() {
        System.out.println(I);
        return String.format(Locale.ENGLISH, "{\"Phase\": %d,\"I\":%f}", logPhaseId.getPhase(), I);
    }
}
