package net.renfei.api.account.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 注册对象
 *
 * @author RenFei
 */
@Data
@ApiModel(value = "注册请求对象")
public class SignUpVO {
    /**
     * 手机、邮箱
     */
    @ApiModelProperty(value = "手机、邮箱")
    private String name;
    /**
     * 密码/验证码（如果使用手机、邮箱注册就是验证码）
     */
    @ApiModelProperty(value = "密码/验证码（如果使用手机、邮箱注册就是验证码）")
    private String passwd;
}
