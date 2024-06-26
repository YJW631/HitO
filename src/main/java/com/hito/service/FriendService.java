package com.hito.service;

import com.hito.vo.User;

import java.util.List;

public interface FriendService {
    List<User> getFriendList(Integer id);
}
