package com.lumia.lostfound.dto;

import com.lumia.lostfound.entity.Academy;
import lombok.Data;

@Data
public class MajorDTO {

    private Integer majorId;

    private String majorName;

    private Academy academy;
}
