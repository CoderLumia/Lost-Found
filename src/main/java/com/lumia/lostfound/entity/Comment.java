package com.lumia.lostfound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户评论
 */
@Data
@TableName("comment")
public class Comment implements Serializable {
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long CommentId;
    @TableField("thing_id")
    private Long thingId;
    @TableField("user_id")
    private String userId;
    @TableField("content")
    private String content;
    @TableField("gmt_create")
    private Timestamp gmtCreate;
}
