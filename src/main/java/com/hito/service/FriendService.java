package com.hito.service;

import com.hito.vo.User;

import java.util.List;

public interface FriendService {
    /**
     * 查询id对应用户的所用好友的用户信息
     */
    List<User> getFriendList(Integer id);
}
