package com.example.goguma.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    @ApiModelProperty(example = "id1234")
    private String username;
    @ApiModelProperty(example = "dkjfasljadslfdcja;dfja")
    private String password;
    @ApiModelProperty(example = "고독한고구마")
    private String nickname;
    @ApiModelProperty(example = "서울시 성북구 성북동")
    private String address;
}
