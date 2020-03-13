package net.renfei.api.account.entity;

/**
 * 用户状态
 *
 * @author RenFei
 */
public enum UserStatus {
    /**
     * 正常状态
     */
    NORMAL(1);
    private Integer status;

    UserStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }}
