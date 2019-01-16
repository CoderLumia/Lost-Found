package com.lumia.lostfound.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDTO {

    private Long CommentId;

    private Long thingId;

    private UserDTO userDTO;

    private String content;

    private Timestamp gmtCreate;
}
