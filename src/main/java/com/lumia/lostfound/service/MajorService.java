package com.lumia.lostfound.service;


import com.lumia.lostfound.dto.MajorDTO;
import com.lumia.lostfound.entity.Major;

import java.util.List;

public interface MajorService {

    boolean add(Major major);

    boolean update(Major major);

    boolean delete(Integer majorId);

    Major findOne(Integer majorId);
    /**
     * 根据学院id查询专业列表
     * @param academyId
     * @return
     */
    List<Major> findByAcademyId(Integer academyId);

    /**
     * 根据专业id查询对象
     * @param majorId
     * @return
     */
    MajorDTO findById(Integer majorId);
}
