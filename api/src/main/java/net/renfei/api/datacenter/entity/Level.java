package net.renfei.api.datacenter.entity;

public enum Level {
    ACCESS("ACCESS"),
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR"),
    FATAL("FATAL"),
    EMAIL("EMAIL"),
    APPNOTI("APPNOTI"),
    SMS("SMS");
    private String type;

    Level(String model) {
        this.type = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
