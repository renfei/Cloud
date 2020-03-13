package net.renfei.api.account.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登陆对象
 *
 * @author RenFei
 */
@Data
@ApiModel(value = "登录请求对象")
public class SignInVO {
    private String name;
    /**
     * 密码/验证码（如果使用手机登录就是验证码）
     */
    @ApiModelProperty(value = "密码/验证码（如果使用手机登录就是验证码）")
    private String passwd;
    /**
     * 双因子验证，如果开启了两步验证就需要
     */
    @ApiModelProperty(value = "双因子验证，如果开启了两步验证就需要")
    private Integer totp;
}
