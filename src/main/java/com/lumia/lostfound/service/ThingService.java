package com.lumia.lostfound.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumia.lostfound.dto.ThingDTO;
import com.lumia.lostfound.entity.Thing;

import java.util.List;

public interface ThingService {

    /**
     * 新增
     * @param thing
     * @return
     */
    boolean add(Thing thing );

    /**
     * 更新
     * @param thing
     * @return
     */
    boolean update(Thing thing);

    /**
     * 删除
     * @param thingId
     * @return
     */
    boolean delete(Long thingId);

    ThingDTO findOne(Long thingId);

    /**
     * 根据用户id进行查询
     * @param userId
     * @return
     */
    List<ThingDTO> findByUser(String userId);

    /**
     * 条件查询所有物品信息
     * @param page  分页对象
     * @param kind   物品类型
     * @param typeId  物品种类
     * @param status  物品状态
     * @return
     */
    Page<ThingDTO> findPage(Page page, Integer kind, Integer typeId, Integer status);


}
