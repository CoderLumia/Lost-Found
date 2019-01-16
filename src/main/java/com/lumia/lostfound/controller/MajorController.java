package com.lumia.lostfound.controller;

import com.lumia.lostfound.entity.Major;
import com.lumia.lostfound.service.MajorService;
import com.lumia.lostfound.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/major/")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @PostMapping("add")
    public ResultResponse add(Major major){
        if (major == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try{
            boolean add = majorService.add(major);
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
    public ResultResponse update(Major major){
        if (major == null || major.getAcademyId() == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try{
            boolean update = majorService.update(major);
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
    public ResultResponse delete(Integer majorId){
        if (majorId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            Major major = majorService.findOne(majorId);
            if (major == null){
                return ResultResponse.createByErrorMessage("未找到要删除的对象");
            }else {
                boolean delete = majorService.delete(majorId);
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

    @RequestMapping("findList")
    public ResultResponse findList(Integer academyId){
        if (academyId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try{
            List<Major> majorList = majorService.findByAcademyId(academyId);
            return ResultResponse.createBySuccessData("查询成功", majorList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }
}
