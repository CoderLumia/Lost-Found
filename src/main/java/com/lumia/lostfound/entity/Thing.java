package com.lumia.lostfound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 物品类
 */
@Data
@TableName("thing")
public class Thing implements Serializable {
    @TableId(value = "thing_id", type = IdType.AUTO)
    private Long thingId;
    @TableField("user_id")
    private String userId;
    @TableField("kind")
    private Integer kind;
    @TableField("type_id")
    private Integer typeId;
    @TableField("name")
    private String name;
    @TableField("place")
    private String place;
    @TableField("status")
    private Integer status;
    @TableField("detail")
    private String detail;
    @TableField("imgs")
    private String imgs;
    @TableField("gmt_create")
    private Timestamp gmtCreate;
    @TableField("gmt_modified")
    private Timestamp gmtModified;
}
