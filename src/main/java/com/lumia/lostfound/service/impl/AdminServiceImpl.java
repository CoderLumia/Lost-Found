package com.lumia.lostfound.service.impl;

import com.lumia.lostfound.dao.AdminMapper;
import com.lumia.lostfound.entity.Admin;
import com.lumia.lostfound.exception.ErrorArgumentException;
import com.lumia.lostfound.service.AdminService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean login(String username, String password) {
        Admin admin = adminMapper.selectById(username);
        if (admin == null){
            throw new ErrorArgumentException("用户名错误");
        }
        String md5Password = DigestUtils.md5Hex(password);
        if (!StringUtils.equals(admin.getPassword(), md5Password)){
            throw new ErrorArgumentException("密码错误");
        }
        return true;
    }
}
