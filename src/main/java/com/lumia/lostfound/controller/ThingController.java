package com.lumia.lostfound.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumia.lostfound.dto.ThingDTO;
import com.lumia.lostfound.dto.UserDTO;
import com.lumia.lostfound.entity.Thing;
import com.lumia.lostfound.exception.ErrorArgumentException;
import com.lumia.lostfound.service.ThingService;
import com.lumia.lostfound.service.UserService;
import com.lumia.lostfound.util.FileUtils;
import com.lumia.lostfound.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/thing/")
public class ThingController {

    @Autowired
    private ThingService thingService;
    @Autowired
    private UserService userService;

    @PostMapping("add")
    public ResultResponse add(@RequestParam("file") MultipartFile[] files, Thing thing){
        boolean checkParam = checkParam(thing);
        if (!checkParam){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        StringBuilder pathBuilder = new StringBuilder();
        if (files.length > 5){
            return ResultResponse.createByErrorMessage("图片上传不能超过五张");
        }
        for (int i=0; i<files.length; i++){
            if (files[i] != null){
                try {
                    pathBuilder.append(FileUtils.uploadFile(files[i]) + ",");
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResultResponse.createByErrorMessage("上传图片失败");
                }
            }
        }
        String imgs = pathBuilder.substring(0, pathBuilder.length() - 1);
        thing.setImgs(imgs);
        try {
            boolean add = thingService.add(thing);
            if (add){
                return ResultResponse.createBySuccessMessage("添加成功");
            }else{
                return ResultResponse.createByErrorMessage("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("添加失败");
        }
    }

    @PostMapping("update")
    public ResultResponse update(Thing thing){
        if (checkParam(thing) == false || thing.getThingId() == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try{
            boolean update = thingService.update(thing);
            if (update){
                return ResultResponse.createBySuccessMessage("更新成功");
            }else {
                return ResultResponse.createByErrorMessage("更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("更新失败");
        }
    }

    @GetMapping("delete")
    public ResultResponse delete(Long thingId){
        if (thingId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try{
            boolean delete = thingService.delete(thingId);
            if (delete){
                return ResultResponse.createBySuccessMessage("删除成功");
            }else{
                return ResultResponse.createByErrorMessage("删除失败");
            }
        }catch (ErrorArgumentException eae){
            return ResultResponse.createByErrorMessage(eae.getMessage());
        }catch (Exception e){
            return ResultResponse.createByErrorMessage("删除失败");
        }
    }

    @GetMapping("findOne")
    public ResultResponse findOne(Long thingId){
        if (thingId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            ThingDTO thingDTO = thingService.findOne(thingId);
            return ResultResponse.createBySuccessData("查询成功", thingDTO);
        }catch (ErrorArgumentException eae){
            return ResultResponse.createByErrorMessage(eae.getMessage());
        }catch (Exception e){
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }

    @GetMapping("findByUser")
    public ResultResponse findByUser(String userId){
        if (userId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            UserDTO userDTO = userService.findByOpenId(userId);
            if (userDTO == null){
                return ResultResponse.createByErrorMessage("未查询到用户");
            }
            List<ThingDTO> thingDTOList = thingService.findByUser(userId);
            return ResultResponse.createBySuccessData("查询成功", thingDTOList);
        }catch (Exception e){
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }

    /**
     * 分页查询
     * @param currentPage  当前页
     * @param pageSize   页面数
     * @param kind  类型
     * @param typeId  类别
     * @param status  状态
     * @return
     */
    @GetMapping("findPage")
    public ResultResponse findPage(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(value = "kind", required = false) Integer kind,
                                   @RequestParam(value = "typeId", required = false) Integer typeId,
                                   @RequestParam(value = "status", required = false) Integer status){
        Page page = new Page();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        try {
            Page<ThingDTO> thingDTOPage = thingService.findPage(page, kind, typeId, status);
            return ResultResponse.createBySuccessData("查询成功", thingDTOPage);
        }catch (Exception e){
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }

    /**
     * 参数校验
     * @param thing
     * @return
     */
    private boolean checkParam(Thing thing){
        if (thing == null){
            return false;
        }
        if (thing.getUserId() == null){
            return false;
        }
        if (thing.getTypeId() == null){
            return false;
        }
        if (thing.getKind() == null){
            return false;
        }
        if (thing.getName() == null ){
            return false;
        }
        return true;
    }
}
