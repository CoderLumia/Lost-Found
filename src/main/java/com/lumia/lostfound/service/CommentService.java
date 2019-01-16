package com.lumia.lostfound.service;

import com.lumia.lostfound.dto.CommentDTO;
import com.lumia.lostfound.entity.Comment;

import java.util.List;


/**
 * 评论
 */
public interface CommentService {

    boolean add(Comment comment);

    boolean update(Comment comment);

    boolean delete(Long commentId);

    boolean deleteByUser(Long commentId, String userId);

    CommentDTO findById(Long commentId);

    List<CommentDTO> findByThingId(Long thingId);

}
