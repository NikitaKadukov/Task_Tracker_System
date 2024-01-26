package com.kadukov.spring.project.spring_project.entity;

import javax.persistence.*;

@Entity
@Table(name = "settings")
public class Setting {

    @Id
    @Column
    private String setting_name;
    @Column
    private String value_name;

    public Setting() {
    }

    public Setting(String setting_name, String value_name) {
        this.setting_name = setting_name;
        this.value_name = value_name;
    }

    public String getSetting_name() {
        return setting_name;
    }

    public void setSetting_name(String setting_name) {
        this.setting_name = setting_name;
    }

    public String getValue_name() {
        return value_name;
    }

    public void setValue_name(String value_name) {
        this.value_name = value_name;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "setting_name='" + setting_name + '\'' +
                ", value_name='" + value_name + '\'' +
                '}';
    }
}
