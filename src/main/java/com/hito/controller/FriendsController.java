package com.hito.controller;

import com.hito.mapper.FriendshipsMapper;
import com.hito.pojo.Result;
import com.hito.service.FriendService;
import com.hito.vo.Friendships;
import com.hito.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {
    @Autowired
    private FriendshipsMapper friendsMapper;

    @Autowired
    private FriendService friendService;

    /**
     * 添加好友请求
     */
    @RequestMapping("addRequest")
    public Result addRequest(Integer friendId, Integer userId) {
        List<Friendships> friendships = friendsMapper.get(friendId, userId);
        if (null != friendships && friendships.size() > 0) {
            Friendships friendship = friendships.get(0);
            if (friendship.equals("accepted")) {
                return Result.error("你已经添加好友，请直接到聊天页面聊天");
            }
            if (friendship.getStatus().equals("requested")) {
                return Result.error("你已经添加好友，等待同意");
            }
        }
        Friendships friends = new Friendships();
        friends.setFriendId(friendId);
        friends.setUserId(userId);
        friends.setInitiator(userId);
        friends.setCreatedAt(new Date());
        friends.setStatus("requested");
        friendsMapper.insert(friends);
        Friendships friends1 = new Friendships();
        friends1.setFriendId(userId);
        friends1.setUserId(friendId);
        friends1.setInitiator(userId);
        friends1.setCreatedAt(new Date());
        friends1.setStatus("requested");
        friendsMapper.insert(friends1);
        return Result.success();
    }

    /**
     * 判断当前两用户是否为好友
     */
    @RequestMapping("get")
    public Result get(Integer friendId, Integer userId) {
        int friendships = friendsMapper.getByStatus(friendId, userId, "accepted");
        if (friendships > 0) {
            return Result.success("true");
        }
        return Result.error("当前非好友");
    }

    /**
     * 同意添加好友请求
     */
    @RequestMapping("ensure")
    public Result ensure(Integer friendId, Integer userId, String status) {
        // 确保关系是对称的
        friendsMapper.updateFriend(userId, friendId, status);
        // 修改原来的状态
        friendsMapper.updateFriend(friendId, userId, status);
        return Result.success();
    }

    /**
     * 获取好友列表
     */
    @RequestMapping("list")
    public Result getFriendList(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Integer id = user.getId();
        List<Friendships> friendships = friendsMapper.selectAllByUserId(id);
        return Result.success(friendships);
    }

    /**
     * 获取好友对应的用户对象形式的列表
     */
    @GetMapping("/getfriendlist")
    public Result getFriendListInUserFormat(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Integer id = user.getId();
        List<User> friendList = friendService.getFriendList(id);
        return Result.success(friendList);
    }

    /**
     * 删除好友
     */
    @DeleteMapping("/deletefriend")
    public Result deleteFriend(@RequestParam(name = "id1") Integer id1,
                               @RequestParam(name = "id2") Integer id2) {
        friendsMapper.delete(id1, id2);
        return Result.success();
    }
}
