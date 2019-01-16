package com.lumia.lostfound.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 种类
 */
@Data
@TableName("type")
public class Type implements Serializable {
    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;
    @TableField("type_name")
    private String typeName;
}
