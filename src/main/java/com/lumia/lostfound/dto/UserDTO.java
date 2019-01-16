package com.lumia.lostfound.dto;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class UserDTO {

    private Long userId;

    private String openId;

    private MajorDTO majorDTO;

    private String name;

    private String nickName;

    private Integer gender;

    private String avataUrl;

    private String phone;

    private Integer status;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;
}
