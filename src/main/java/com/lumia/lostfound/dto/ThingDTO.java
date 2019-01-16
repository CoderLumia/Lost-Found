package com.lumia.lostfound.dto;


import com.lumia.lostfound.entity.Type;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 物品传输对象
 */

@Data
public class ThingDTO {

    private Long thingId;

    private UserDTO userDTO;

    private Integer kind;

    private Type type;

    private String name;

    private String place;

    private Integer status;

    private String detail;

    private String imgs;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;
}
