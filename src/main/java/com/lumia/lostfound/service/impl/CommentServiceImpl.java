package com.lumia.lostfound.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lumia.lostfound.dao.CommentMapper;
import com.lumia.lostfound.dto.CommentDTO;
import com.lumia.lostfound.dto.UserDTO;
import com.lumia.lostfound.entity.Comment;
import com.lumia.lostfound.exception.ErrorArgumentException;
import com.lumia.lostfound.service.CommentService;
import com.lumia.lostfound.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.activation.ActivationGroupDesc;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserService userService;

    @Override
    public boolean add(Comment comment) {
        if (comment == null){
            return false;
        }
        comment.setGmtCreate(new Timestamp(new Date().getTime()));
        int insert = commentMapper.insert(comment);
        if (insert > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(Comment comment) {
        if (comment == null || comment.getCommentId() == null){
            return false;
        }
        int update = commentMapper.updateById(comment);
        if (update > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(Long commentId) {
        if (commentId == null){
            return false;
        }
        int delete = commentMapper.deleteById(commentId);
        if (delete > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteByUser(Long commentId, String userId) {
        if (commentId == null || userId == null){
            return false;
        }
        Comment comment = commentMapper.selectById(commentId);
        if (comment != null){
            if (!StringUtils.equals(comment.getUserId(), userId)){
                throw new ErrorArgumentException("没有匹配评论用户,无法删除");
            }else{
                commentMapper.deleteById(commentId);
                return true;
            }
        }else{
            throw new ErrorArgumentException("没有找到评论");
        }
    }

    @Override
    public CommentDTO findById(Long commentId) {
        if (commentId == null){
            return null;
        }
        Comment comment = commentMapper.selectById(commentId);
        if (comment != null){
            return assembleComment(comment);
        }
        return null;
    }

    @Override
    public List<CommentDTO> findByThingId(Long thingId) {
        if (thingId == null){
            return null;
        }
        Wrapper<Comment> wrapper = new QueryWrapper<>();
        ((QueryWrapper<Comment>) wrapper).eq("thing_id", thingId);
        ((QueryWrapper<Comment>) wrapper).orderByDesc("gmt_create");
        List<Comment> commentList = commentMapper.selectList(wrapper);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList){
            commentDTOList.add(assembleComment(comment));
        }
        return commentDTOList;
    }

    /**
     * 组装数据传输对象
     * @param comment
     * @return
     */
    private CommentDTO assembleComment(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setThingId(comment.getThingId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setGmtCreate(comment.getGmtCreate());
        UserDTO userDTO = userService.findByOpenId(comment.getUserId());
        commentDTO.setUserDTO(userDTO);
        return commentDTO;
    }

}
