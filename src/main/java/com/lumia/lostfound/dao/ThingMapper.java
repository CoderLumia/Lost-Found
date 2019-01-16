package com.lumia.lostfound.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lumia.lostfound.entity.Thing;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingMapper extends BaseMapper<Thing> {
}
