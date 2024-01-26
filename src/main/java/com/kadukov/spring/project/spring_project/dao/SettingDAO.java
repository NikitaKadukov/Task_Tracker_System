package com.kadukov.spring.project.spring_project.dao;

import com.kadukov.spring.project.spring_project.entity.Setting;
import com.kadukov.spring.project.spring_project.entity.User;

public interface SettingDAO {
    public void saveSetting(Setting setting);

    public Setting getSetting(String name);

}
