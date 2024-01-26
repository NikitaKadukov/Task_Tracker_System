package com.kadukov.spring.project.spring_project.dao;

import com.kadukov.spring.project.spring_project.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class SettingDAOImpl implements SettingDAO{

    @Autowired
    private EntityManager entityManager;
    @Override
    public void saveSetting(Setting setting) {
        entityManager.merge(setting);
    }

    public Setting getSetting(String name){
        return entityManager.find(Setting.class, name);
    }
}
