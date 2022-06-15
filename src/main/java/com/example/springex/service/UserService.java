package com.example.springex.service;

import com.example.springex.dto.UserRequest;
import com.example.springex.entitiy.User;
import com.example.springex.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    // 보안이 취약하다 -> 오히려 보안이 더 좋을 수 있음
    // '다른 사람'이랑 공유하기 어렵다
    // 서버들간에 데이터를 동기화를 해야한다 -> 동기화 비용(시간/memory/cpu 사용), 시간, 복잡해짐
    // 서버를 껐다 키면 데이터가 날아감
    // 데이터를 직접 관리해야함 - 데이터 정합성, 트랜잭션, 조회
    @Autowired
    private UserMapper userMapper;

    //회원 가입
    public User insert(UserRequest userRequest) {
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setName(userRequest.getName());
        userMapper.insertUser(user);

        return user;
    }

    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    public User getUser(long id) {
        //특정 유저의 정보
        return userMapper.findUserById(id);
    }
    public List<User> getUserName(String name) {
        //특정 유저의 정보
        return userMapper.findUserByName(name);
    }

    public User update(long id, UserRequest userRequest) {
        // 해당 id를 가진 유저가 없으면 null을 바로 반환
        // 해당 id를 가진 유저가 있으면 유저 수정 후 반환
        User user = getUser(id);
        if (user == null) {
            return null;
        } else {
            user.setAge(userRequest.getAge());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setName(userRequest.getName());
            userMapper.updateUser(id, user);
            return user;
        }
    }

    public void delete(long id) {
        // soft delete
        userMapper.deactivateUserById(id);
    }

    public User getLoanData(long id) {
        return userMapper.findUserById(id);
    }

}

