package net.renfei.api.message.entity;

public enum PushTargetType {
    DEVICE("DEVICE"),
    ALIAS("ALIAS"),
    ACCOUNT("ACCOUNT"),
    TAG("TAG"),
    ALL("ALL");
    private String type;

    PushTargetType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
