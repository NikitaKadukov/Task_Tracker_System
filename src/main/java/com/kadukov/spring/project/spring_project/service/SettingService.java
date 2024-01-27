package com.kadukov.spring.project.spring_project.service;

import com.kadukov.spring.project.spring_project.entity.Setting;

import java.util.List;


public interface SettingService {
    public void saveSetting(Setting setting);

    public Setting getSetting(String name);

    public List<Setting> getSettings();

}
