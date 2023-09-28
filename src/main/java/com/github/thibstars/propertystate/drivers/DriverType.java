package com.github.thibstars.propertystate.drivers;

import java.util.Arrays;

/**
 * @author Thibault Helsmoortel
 */
public enum DriverType {

    CHROME("chrome"), EDGE("edge");

    private final String code;

    DriverType(String code) {
        this.code = code;
    }

    public static DriverType parse(String code) {
        return Arrays.stream(values())
                .filter(value -> value.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }

    public String getCode() {
        return code;
    }
}
