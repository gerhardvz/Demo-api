package com.zovra.automation.automationapi.model.PowerConsumption;



import org.hibernate.annotations.Immutable;

import javax.persistence.*;


@Entity
@Immutable
@Table(name = "LastEntryView")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "findLastEntryForDevice",
                procedureName = "LatestLog",
                resultClasses = {LastEntryView.class},
                parameters = {
                        @StoredProcedureParameter(
                                name = "device_id",
                                type = int.class,
                                mode = ParameterMode.IN) }
        )
})
public final class LastEntryView {
    @EmbeddedId

    private LastEntityViewId id;
    @Column(name = "device_id", nullable = false)
    private Integer device;
    @Column(name = "timestamp", nullable = false)
    private Double timestamp;
//    @Column(name = "phase", nullable = false)
//    private Integer phase;
    @Column(name = "a_l", nullable = false)
    private Double a_l;
    @Column(name = "u_ln", nullable = false)
    private Double u_ln;
    @Column(name = "u_ll", nullable = false)
    private Double u_ll;

    public LastEntryView(LastEntityViewId id, Integer device, Double timestamp, Double a_l, Double u_ln, Double u_ll) {
        this.id = id;
        this.device = device;
        this.timestamp = timestamp;
        this.a_l = a_l;
        this.u_ln = u_ln;
        this.u_ll = u_ll;
    }

    public LastEntityViewId getId() {
        return id;
    }

    public LastEntryView() {
    }

    public Integer getPhase(){
        return id.getPhase();
    }

    public void setId(LastEntityViewId id) {
        this.id = id;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    public Double getA_l() {
        return a_l;
    }

    public void setA_l(Double a_l) {
        this.a_l = a_l;
    }

    public Double getU_ln() {
        return u_ln;
    }

    public void setU_ln(Double u_ln) {
        this.u_ln = u_ln;
    }

    public Double getU_ll() {
        return u_ll;
    }

    public void setU_ll(Double u_ll) {
        this.u_ll = u_ll;
    }

    @Override
    public String toString(){
        return "id: "+id.toString()  + ", Current: "+a_l.toString() + ", Voltage: "+u_ln.toString();

    }
}
