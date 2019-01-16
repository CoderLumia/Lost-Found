package com.lumia.lostfound.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumia.lostfound.dao.TypeMapper;
import com.lumia.lostfound.entity.Type;
import com.lumia.lostfound.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("typeService")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public boolean add(Type type) {
        if (type == null){
            return false;
        }
        int result = typeMapper.insert(type);
        if (result > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(Type type) {
        if (type == null || type.getTypeId() == null){
            return false;
        }
        int result = typeMapper.updateById(type);
        if (result > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(Integer typeId) {
        if (typeId == null){
            return false;
        }
        int result = typeMapper.deleteById(typeId);
        if (result > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Type findById(Integer typeId) {
        if (typeId == null){
            return null;
        }
        return typeMapper.selectById(typeId);
    }

    @Override
    public IPage<Type> findAll(Page page) {
        IPage iPage = typeMapper.selectPage(page, null);
        return iPage;
    }

    @Override
    public List<Type> findList() {
        return typeMapper.selectList(null);
    }
}
