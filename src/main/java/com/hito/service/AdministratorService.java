package com.hito.service;

import com.hito.vo.Administrator;

public interface AdministratorService {
    /**
     * 登录
     */
    String login(String username, String hash2, String verificationCode);

    /**
     * 根据token查询管理员
     */
    Administrator findAdministratorByToken(String token);

    /**
     * 根据username获取token
     */
    String getToken(String username);
}
