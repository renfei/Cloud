package net.renfei.api.message.entity;

public enum PushMessageType {
    MESSAGE("MESSAGE"),
    NOTICE("NOTICE");
    private String type;

    PushMessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
