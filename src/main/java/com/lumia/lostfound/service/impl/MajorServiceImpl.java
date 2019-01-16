package com.lumia.lostfound.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lumia.lostfound.dao.AcademyMapper;
import com.lumia.lostfound.dao.MajorMapper;
import com.lumia.lostfound.dto.MajorDTO;
import com.lumia.lostfound.entity.Academy;
import com.lumia.lostfound.entity.Major;
import com.lumia.lostfound.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("majorService")
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private AcademyMapper academyMapper;

    @Override
    public boolean add(Major major) {
        if (major == null){
            return false;
        }
        int insert = majorMapper.insert(major);
        if (insert > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean update(Major major) {
        if (major == null || major.getMajorId() == null){
            return false;
        }
        int update = majorMapper.updateById(major);
        if (update > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(Integer majorId) {
        if (majorId == null){
            return false;
        }
        int delete = majorMapper.deleteById(majorId);
        if (delete > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Major findOne(Integer majorId) {
        if (majorId == null){
            return null;
        }
        return majorMapper.selectById(majorId);
    }

    @Override
    public List<Major> findByAcademyId(Integer academyId) {
        if (academyId == null){
            return null;
        }
        Wrapper<Major> wrapper = new QueryWrapper<>();
        ((QueryWrapper<Major>) wrapper).eq("academy_id", academyId);
        return majorMapper.selectList(wrapper);
    }

    @Override
    public MajorDTO findById(Integer majorId) {
        if (majorId == null){
            return null;
        }
        Major major = majorMapper.selectById(majorId);
        Integer academyId = major.getAcademyId();
        Academy academy = academyMapper.selectById(academyId);
        MajorDTO majorDTO = new MajorDTO();
        majorDTO.setMajorId(major.getMajorId());
        majorDTO.setMajorName(major.getMajorName());
        majorDTO.setAcademy(academy);
        return majorDTO;
    }
}
