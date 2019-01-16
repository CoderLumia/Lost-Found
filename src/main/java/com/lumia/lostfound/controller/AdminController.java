package com.lumia.lostfound.controller;

import com.lumia.lostfound.exception.ErrorArgumentException;
import com.lumia.lostfound.service.AdminService;
import com.lumia.lostfound.util.ResultResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    public ResultResponse login(String username, String password){
        if (StringUtils.isBlank(username.trim()) || StringUtils.isBlank(password.trim())){
            return ResultResponse.createByErrorMessage("用户名或密码不能为空");
        }
        try {
            boolean login = adminService.login(username, password);
            if (login){
                return ResultResponse.createBySuccessMessage("登录成功");
            }else{
                return ResultResponse.createByErrorMessage("登陆失败");
            }
        }catch (ErrorArgumentException eae){
            return ResultResponse.createByErrorMessage(eae.getMessage());
        }
    }
}
