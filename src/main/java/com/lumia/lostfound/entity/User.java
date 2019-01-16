package com.lumia.lostfound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户表
 */
@Data
@TableName("wx_user")
public class User implements Serializable {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    @TableField("open_id")
    private String openId;
    @TableField("major_id")
    private Integer majorId;
    @TableField("name")
    private String name;
    @TableField("nickname")
    private String nickName;
    @TableField("gender")
    private Integer gender;
    @TableField("avata_url")
    private String avataUrl;
    @TableField("phone")
    private String phone;
    @TableField("status")
    private Integer status;
    @TableField("gmt_create")
    private Timestamp gmtCreate;
    @TableField("gmt_modified")
    private Timestamp gmtModified;

}
