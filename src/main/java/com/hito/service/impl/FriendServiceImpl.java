package com.hito.service.impl;

import com.hito.mapper.FriendshipsMapper;
import com.hito.mapper.UserMapper;
import com.hito.service.FriendService;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendshipsMapper friendshipsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getFriendList(Integer id) {
        List<Integer> friendIdList=friendshipsMapper.selectIdById(id);
        List<User> friendList=new ArrayList<>();
        User friend=new User();
        for (int i = 0; i < friendIdList.size(); i++) {
            friend=userMapper.selectById(friendIdList.get(i));
            if(friend!=null){
                friendList.add(friend);
            }
        }
        return friendList;
    }
}
