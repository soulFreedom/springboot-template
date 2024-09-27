package com.example.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author guohailong
 * @description: TODO
 * @date 2023/6/2620:17
 */

@Data
@ApiModel(value = "响应包装类")
public class Result {
    @ApiModelProperty(value = "返回码")
    private String retCode;
    @ApiModelProperty(value = "返回描述")
    private String retMsg;
    public static String abc = "ABC";

    public Result(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
}
