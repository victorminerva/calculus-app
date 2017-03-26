package com.minervavi.app.workcalcapp.databinding.domain;

/**
 * Created by victo on 19/03/2017.
 */

public enum ParcelaEnum {

    UNICA("Única"), PRIMEIRA("1ª"), SEGUNDA("2ª");

    private String value;

    ParcelaEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
