package com.zovra.automation.automationapi.service;

import com.zovra.automation.automationapi.powerconsumption.dao.LastEntry_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class LastEntry_Service {

    @Autowired
    private LastEntry_Repository lastEntryRepository;

    @Autowired
    @PersistenceContext
    private EntityManager em;

    public List findLastEntryForDevice(int device_id){
        return em.createNamedStoredProcedureQuery("findLastEntryForDevice").setParameter("device_id",device_id).getResultList();
    }
}
