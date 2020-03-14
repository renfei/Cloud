package net.renfei.api.message.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 邮件
 *
 * @author RenFei
 */
@Data
@ApiModel(value = "邮件")
public class Email {
    /**
     * 接收邮件的邮箱地址。
     */
    @ApiModelProperty(value = "接收邮件的邮箱地址。")
    private String to;
    /**
     * 邮件主题。
     */
    @ApiModelProperty(value = "邮件主题。")
    private String subject;
    /**
     * 邮件内容。
     */
    @ApiModelProperty(value = "邮件内容。")
    private String content;
}
