package com.lumia.lostfound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 专业
 */
@Data
@TableName("major")
public class Major implements Serializable {
    @TableId(value = "major_id", type = IdType.AUTO)
    private Integer majorId;
    @TableField("major_name")
    private String majorName;
    @TableField("academy_id")
    private Integer academyId;
}
