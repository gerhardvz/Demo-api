package com.zovra.automation.automationapi.model.PowerConsumption;

import javax.persistence.*;

@Entity
@Table
public final class Voltage {
    @EmbeddedId
    private LogPhaseId logPhaseId;


    @Column(name = "U_ll", nullable = false)
    private
    double U_ll;
    @Column(name = "U_ln", nullable = false)
    private
    double U_ln;


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

    public double getU_ll() {
        return U_ll;
    }

    public void setU_ll(double u_ll) {
        U_ll = u_ll;
    }

    public double getU_ln() {
        return U_ln;
    }

    public void setU_ln(double u_ln) {
        U_ln = u_ln;
    }


    public Voltage() {
    }

    public Voltage(LogPhaseId logPhaseId, double u_ll, double u_ln) {
        this.logPhaseId = logPhaseId;
        U_ll = u_ll;
        U_ln = u_ln;

    }

    public Voltage(Log logPhaseId, int phase, double u_ll, double u_ln) {
        this.logPhaseId = new LogPhaseId(logPhaseId, phase);
        U_ll = u_ll;
        U_ln = u_ln;

    }
}

