package net.renfei.api.message.entity;

/**
 * @author RenFei
 */
public enum  PushAndroidOpenType {
    APPLICATION("APPLICATION"),
    ACTIVITY("ACTIVITY"),
    URL("URL"),
    NONE("NONE");
    private String type;

    PushAndroidOpenType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
