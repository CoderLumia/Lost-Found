package com.lumia.lostfound.service;


import com.lumia.lostfound.entity.Academy;

import java.util.List;

public interface AcademyService {

    /**
     * 新增
     * @param academy
     * @return
     */
    boolean add(Academy academy);

    /**
     * 修改
     * @param academy
     * @return
     */
    boolean update(Academy academy);

    /**
     * 删除
     * @param academyId
     * @return
     */
    boolean delete(Integer academyId);

    /**
     * 查询所有学院
     */
    List<Academy> findAll();

    /**
     * 根据id查询对象
     * @return
     */
    Academy findById(Integer academyId);

}
