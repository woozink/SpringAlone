package com.example.springex.service;

import com.example.springex.dto.UserRequest;
import entitiy.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static entitiy.User.user;

@Service
public class UserService {
    private Map<Long, User> users = new HashMap<>();
    private long newUserId = 1L;

    public User insert(UserRequest userRequest){
        //회원 가입
        //값을 받아와서 users에 저장
        user = new User();
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

        for(int i = 0; i < users.size(); i++){
            User user = users.get(i);
            if(id == user.getId()){
                return user;
            }
        }

        return null;
    }
    public User update(long id, UserRequest userRequest){
        for(int i = 0; i < users.size(); i++){
            User user = users.get(i);
            if (id == user.getId()) {
                user.setAge(userRequest.getAge());
                user.setEmail(userRequest.getEmail());
                user.setPassword(userRequest.getPassword());
                user.setName(userRequest.getName());
                return user;
            }
        }
        return null;
    }

    public void delete(long id){
        for(int i = 0; i < users.size(); i++){
            User user = users.get(i);
            if(id == user.getId()){
                users.remove(i);
            }
        }
    }
}
