package com.esdo.bepilot.Model.Enum;

public enum MissionStatus {
    RUNNING,
    COMPLETE,
    NOT_RUN;

    public static MissionStatus getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
