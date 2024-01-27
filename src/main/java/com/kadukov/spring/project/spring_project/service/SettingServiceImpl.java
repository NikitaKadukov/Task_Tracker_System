package com.kadukov.spring.project.spring_project.service;

import com.kadukov.spring.project.spring_project.dao.SettingDAO;
import com.kadukov.spring.project.spring_project.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SettingServiceImpl implements SettingService{

    @Autowired
    SettingDAO settingDAO;

    @Transactional
    @Override
    public void saveSetting(Setting setting) {
        settingDAO.saveSetting(setting);
    }

    @Transactional
    @Override
    public Setting getSetting(String name){
        return settingDAO.getSetting(name);
    }

    @Transactional
    @Override
    public List<Setting> getSettings(){
        return settingDAO.getSettings();
    }
}
