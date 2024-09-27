package com.example.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author guohailong
 * @description: TODO
 * @date 2023/6/2621:50
 */
@Data
@ApiModel(value = "请求包装类")
public class BusiData {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
}
