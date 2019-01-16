package com.lumia.lostfound.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumia.lostfound.dao.UserMapper;
import com.lumia.lostfound.dto.MajorDTO;
import com.lumia.lostfound.dto.UserDTO;
import com.lumia.lostfound.entity.User;
import com.lumia.lostfound.service.MajorService;
import com.lumia.lostfound.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MajorService majorService;

    @Override
    public boolean add(User user) {
        if (user == null){
            return false;
        }
        if (user.getGender() == null){
            user.setGender(0); //设置性别默认为未知
        }
        user.setStatus(0);  //设置用户状态为正常
        user.setGmtCreate(new Timestamp(new Date().getTime()));
        user.setGmtModified(new Timestamp(new Date().getTime()));
        int insert = userMapper.insert(user);
        if (insert > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        if (user == null || user.getUserId() == null){
            return false;
        }
        user.setGmtModified(new Timestamp(new Date().getTime()));
        int update = userMapper.updateById(user);
        if (update > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public UserDTO findById(Long userId) {
        if (userId == null){
            return null;
        }
        User user = userMapper.selectById(userId);
        if (user == null){
            return null;
        }
        return assembleUser(user);
    }

    @Override
    public UserDTO findByOpenId(String openId) {
        if (openId == null){
            return null;
        }
        Wrapper<User> wrapper = new QueryWrapper<>();
        ((QueryWrapper<User>) wrapper).eq("open_id", openId);
        User user = userMapper.selectOne(wrapper);
        if (user == null){
            return null;
        }
        return assembleUser(user);
    }

    @Override
    public Page<UserDTO> findPage(Page page, Integer status) {
        if (page == null || status == null){
            return null;
        }
        Wrapper<User> wrapper = new QueryWrapper<>();
        ((QueryWrapper<User>) wrapper).eq("status", status);
        ((QueryWrapper<User>) wrapper).orderByDesc("gmt_create");
        IPage<User> iPage = userMapper.selectPage(page, wrapper);
        return assemblePage((Page<User>) iPage);
    }

    /**
     * 组装分页对象
     * @param page
     * @return
     */
    private Page<UserDTO> assemblePage(Page<User> page){
        Page<UserDTO> userDTOPage = new Page<>();
        userDTOPage.setTotal(page.getTotal());
        userDTOPage.setSize(page.getSize());
        userDTOPage.setCurrent(page.getCurrent());
        userDTOPage.setPages(page.getPages());
        List<User> records = page.getRecords();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : records){
            userDTOList.add(assembleUser(user));
        }
        userDTOPage.setRecords(userDTOList);
        return userDTOPage;
    }

    /**
     * 组装返回User对象
     * @param user
     * @return
     */
    private UserDTO assembleUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setOpenId(user.getOpenId());
        if (user.getMajorId() != null){
            MajorDTO majorDTO = majorService.findById(user.getMajorId());
            userDTO.setMajorDTO(majorDTO);
        }
        userDTO.setName(user.getName());
        userDTO.setNickName(user.getNickName());
        userDTO.setGender(user.getGender());
        userDTO.setAvataUrl(user.getAvataUrl());
        userDTO.setPhone(user.getPhone());
        userDTO.setStatus(user.getStatus());
        userDTO.setGmtCreate(user.getGmtCreate());
        userDTO.setGmtModified(user.getGmtModified());
        return userDTO;
    }
}
