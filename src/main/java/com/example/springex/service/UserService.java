package com.example.springex.service;

import com.example.springex.dto.UserRequest;
import entitiy.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();
    private long newUserId = 1L;

    public User insert(UserRequest userRequest) {
        //회원 가입
        //값을 받아와서 users에 저장
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setName(userRequest.getName());
        user.setId(newUserId);
        newUserId++;
        users.put(user.getId(), user);

        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUser(long id) {
        //특정 유저의 정보
        return users.get(id);
    }

    //9
    public User update(long id, UserRequest userRequest) {
        // 1 - user1
        // 2 - user2
        // 5 - user5
        // 9 - user9
        // 1,2,5,9
        User user = users.get(id);
        if (user == null) {
            return null;
        } else {
            user.setAge(userRequest.getAge());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setName(userRequest.getName());
            return user;
        }
    }

    public void delete(long id) {
        users.remove(id);
    }
}

