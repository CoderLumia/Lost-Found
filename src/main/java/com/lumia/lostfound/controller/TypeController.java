package com.lumia.lostfound.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumia.lostfound.entity.Type;
import com.lumia.lostfound.service.TypeService;
import com.lumia.lostfound.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/type/")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping("add")
    public ResultResponse add(String typeName){
        if (typeName == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        Type type = new Type();
        type.setTypeName(typeName);
        try{
            boolean add = typeService.add(type);
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
    public ResultResponse update(Type type){
        if (type == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try{
            boolean update = typeService.update(type);
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
    private ResultResponse delete(Integer typeId){
        if (typeId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            Type type = typeService.findById(typeId);
            if (type == null){
                return ResultResponse.createByErrorMessage("未找到要删除的对象");
            }else{
                boolean delete = typeService.delete(typeId);
                if (delete){
                    return ResultResponse.createBySuccessMessage("删除成功");
                }else {
                    return ResultResponse.createByErrorMessage("删除失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("删除失败");
        }
    }

    @GetMapping("findOne")
    public Type findOne(Integer typeId){
        if (typeId == null){
            return null;
        }
        try {
            Type type = typeService.findById(typeId);
            return type;
        }catch (Exception e){
            return null;
        }
    }
    @GetMapping("findPage")
    public ResultResponse findPage(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        Page page = new Page();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        try {
            Page<Type> pages = (Page<Type>) typeService.findAll(page);
            return ResultResponse.createBySuccessData("查询成功", pages);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }

    @GetMapping("findList")
    public ResultResponse findList(){
        try {
            List<Type> list = typeService.findList();
            return ResultResponse.createBySuccessData("查询成功", list);
        }catch (Exception e){
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }
}
