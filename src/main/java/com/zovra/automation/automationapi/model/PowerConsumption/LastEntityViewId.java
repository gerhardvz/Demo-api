package com.zovra.automation.automationapi.model.PowerConsumption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public final class LastEntityViewId implements Serializable {

    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "phase", nullable = false)
    private int phase;

    public LastEntityViewId(Integer id, int phase) {
        this.id = id;
        this.phase = phase;
    }



    public LastEntityViewId() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LastEntityViewId that = (LastEntityViewId) obj;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getPhase(), that.getPhase());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }
}

