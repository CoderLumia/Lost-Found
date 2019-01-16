package com.lumia.lostfound.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumia.lostfound.dto.UserDTO;
import com.lumia.lostfound.entity.User;

public interface UserService {

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 查询用户
     * @param userId
     * @return
     */
    UserDTO findById(Long userId);

    /**
     * 根据openId查询
     * @param openId
     * @return
     */
    UserDTO findByOpenId(String openId);

    /**
     * 根据状态查询用户
     * @param page
     * @param status
     * @return
     */
    Page<UserDTO> findPage(Page page, Integer status);
}
