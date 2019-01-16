package com.lumia.lostfound.service.impl;

import com.lumia.lostfound.dao.AcademyMapper;
import com.lumia.lostfound.entity.Academy;
import com.lumia.lostfound.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("academyService")
public class AcademyServiceImpl implements AcademyService {

    @Autowired
    private AcademyMapper academyMapper;
    @Override
    public List<Academy> findAll() {
        return academyMapper.selectList(null);
    }


    @Override
    public boolean add(Academy academy) {
        if (academy == null){
            return false;
        }
        int result = academyMapper.insert(academy);
        if (result > 0){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean update(Academy academy) {
        if (academy == null || academy.getAcademyId() == null){
            return false;
        }
        int result = academyMapper.updateById(academy);
        if (result > 0){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean delete(Integer academyId) {
        if (academyId == null){
            return false;
        }
        int result = academyMapper.deleteById(academyId);
        if (result > 0){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Academy findById(Integer academyId) {
        if (academyId == null){
            return null;
        }
        Academy academy = academyMapper.selectById(academyId);
        return academy;
    }
}
