package com.lumia.lostfound.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumia.lostfound.entity.Type;

import java.util.List;

public interface TypeService {

    boolean add(Type type);

    boolean update(Type type);

    boolean delete(Integer typeId);

    Type findById(Integer typeId);

    IPage<Type> findAll(Page page);

    List<Type> findList();
}
