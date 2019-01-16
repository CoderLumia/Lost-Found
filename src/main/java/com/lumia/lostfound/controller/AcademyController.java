package com.lumia.lostfound.controller;

import com.lumia.lostfound.entity.Academy;
import com.lumia.lostfound.service.AcademyService;
import com.lumia.lostfound.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/academy/")
public class AcademyController {

    @Autowired
    private AcademyService academyService;


    @PostMapping("add")
    public ResultResponse add(String academyName){
        if (academyName == null || academyName.trim() == ""){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        Academy academy = new Academy();
        academy.setAcademyName(academyName);
        try {
            boolean add = academyService.add(academy);
            if (add){
                return ResultResponse.createBySuccessMessage("新增成功");
            }else {
                return ResultResponse.createByErrorMessage("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("新增失败");
        }
    }

    @PostMapping("update")
    public ResultResponse update(Academy academy){
        if (academy == null || academy.getAcademyId() == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try{
            boolean update = academyService.update(academy);
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

    @GetMapping("delete")
    public ResultResponse delete(Integer academyId){
        if (academyId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            Academy academy = academyService.findById(academyId);
            if (academy == null){
                return ResultResponse.createByErrorMessage("未找到要删除的对象");
            }else {
                boolean delete = academyService.delete(academyId);
                if (delete){
                    return ResultResponse.createBySuccessMessage("删除成功");
                }else{
                    return ResultResponse.createByErrorMessage("删除失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("删除失败");
        }
    }

    @RequestMapping("findAll")
    public ResultResponse findAll(){
        try {
            List<Academy> academyList = academyService.findAll();
            return ResultResponse.createBySuccessData("查询成功", academyList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }
}
