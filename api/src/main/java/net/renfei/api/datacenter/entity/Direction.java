package net.renfei.api.datacenter.entity;

public enum Direction {
    INPUT("INPUT"),
    OUTPUT("OUTPUT");
    private String type;

    Direction(String model) {
        this.type = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
