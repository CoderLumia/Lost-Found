package com.lumia.lostfound.service;

public interface AdminService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);
}
