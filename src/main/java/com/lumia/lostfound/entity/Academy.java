package com.lumia.lostfound.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 学院
 */
@Data
@TableName("academy")
public class Academy implements Serializable {
    @TableId(value = "academy_id", type = IdType.AUTO)
    private Integer academyId;
    @TableField("academy_name")
    private String academyName;
}
