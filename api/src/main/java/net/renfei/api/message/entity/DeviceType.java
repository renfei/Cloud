package net.renfei.api.message.entity;

public enum DeviceType {
    ANDROID("ANDROID"),
    iOS("iOS"),
    ALL("ALL");
    private String type;

    DeviceType(String model) {
        this.type = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
