package com.lumia.lostfound.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumia.lostfound.dto.UserDTO;
import com.lumia.lostfound.entity.User;
import com.lumia.lostfound.service.UserService;
import com.lumia.lostfound.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public ResultResponse add(User user){
       if (user == null || user.getOpenId() == null){
           return ResultResponse.createByErrorMessage("请传入指定参数");
       }
       try {
           boolean add = userService.add(user);
           if (add){
               return ResultResponse.createBySuccessMessage("添加成功");
           }else {
               return ResultResponse.createByErrorMessage("添加失败");
           }
       }catch (Exception e){
           e.printStackTrace();
           return ResultResponse.createByErrorMessage("添加失败");
       }
    }

    @PostMapping("update")
    public ResultResponse update(User user){
        if (user == null || user.getUserId() == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            boolean update = userService.update(user);
            if (update){
                return ResultResponse.createBySuccessMessage("更新成功");
            }else{
                return ResultResponse.createByErrorMessage("更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("更新失败");
        }
    }

    @GetMapping("findById")
    public ResultResponse findById(Long userId){
        if (userId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            UserDTO userDTO = userService.findById(userId);
            if (userDTO == null){
                return ResultResponse.createByErrorMessage("未查询到用户");
            }
            return ResultResponse.createBySuccessData("查询成功", userDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }

    @GetMapping("findByOpenId")
    public ResultResponse findByOpenId(String openId){
        if (openId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            UserDTO userDTO = userService.findByOpenId(openId);
            if (userDTO == null){
                return ResultResponse.createByErrorMessage("未查询到用户");
            }
            return ResultResponse.createBySuccessData("查询成功", userDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }

    /**
     * 根据状态分页查询
     * @param currentPage
     * @param pageSize
     * @param status
     * @return
     */
    @GetMapping("findPage")
    public ResultResponse findPage(@RequestParam(defaultValue = "1") Integer currentPage,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam(defaultValue = "0") Integer status){
        Page page = new Page();
        page.setSize(pageSize);
        page.setCurrent(currentPage);
        try{
            Page<UserDTO> pageList = userService.findPage(page, status);
            return ResultResponse.createBySuccessData("查询成功", pageList);
        }catch (Exception e){
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }
}
