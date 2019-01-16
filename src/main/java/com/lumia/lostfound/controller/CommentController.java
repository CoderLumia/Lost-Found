package com.lumia.lostfound.controller;

import com.lumia.lostfound.dto.CommentDTO;
import com.lumia.lostfound.entity.Comment;
import com.lumia.lostfound.exception.ErrorArgumentException;
import com.lumia.lostfound.service.CommentService;
import com.lumia.lostfound.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("add")
    public ResultResponse add(Comment comment){
        if (comment.getThingId() == null || comment.getUserId() == null || comment.getContent() == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            boolean add = commentService.add(comment);
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

    @GetMapping("delete")
    public ResultResponse delete(Long commentId){
        if (commentId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            CommentDTO commentDTO = commentService.findById(commentId);
            if (commentDTO == null){
                return ResultResponse.createByErrorMessage("未找到要删除的对象");
            }
            boolean delete = commentService.delete(commentId);
            if (delete){
                return ResultResponse.createBySuccessMessage("删除成功");
            }else{
                return ResultResponse.createByErrorMessage("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("删除失败");
        }
    }

    @RequestMapping("deleteByUser")
    public ResultResponse deleteByUser(Long commentId, String userId){
        if (commentId == null || userId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            boolean delete = commentService.deleteByUser(commentId, userId);
            if (delete){
                return ResultResponse.createBySuccessMessage("删除成功");
            }else {
                return ResultResponse.createByErrorMessage("删除失败");
            }
        }catch (ErrorArgumentException eae){
            return ResultResponse.createByErrorMessage(eae.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.createByErrorMessage("删除失败");
        }
    }

    @RequestMapping("findOne")
    public ResultResponse findOne(Long commentId){
        if (commentId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try{
            CommentDTO commentDTO = commentService.findById(commentId);
            if (commentDTO == null){
                return ResultResponse.createByErrorMessage("未找到对象");
            }else{
                return ResultResponse.createBySuccessData("查询成功", commentDTO);
            }
        }catch (Exception e){
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }

    @RequestMapping("findByThingId")
    public ResultResponse findByThingId(Long thingId){
        if (thingId == null){
            return ResultResponse.createByErrorMessage("请传入指定参数");
        }
        try {
            List<CommentDTO> commentDTOList = commentService.findByThingId(thingId);
            return ResultResponse.createBySuccessData("查询成功", commentDTOList);
        }catch (Exception e){
            return ResultResponse.createByErrorMessage("查询失败");
        }
    }
}
