package com.example.springex.controller;

import com.example.springex.dto.UserRequest;
import com.example.springex.dto.UserResponse;
import com.example.springex.service.UserService;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    //회원가입 API(유저 등록)
    @PostMapping("")
    public ResponseEntity<User> signUp(@RequestBody UserRequest userRequest){
        User user = userRequest.insert(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    //모든 회원 정보를 가져 오는 API
    @GetMapping("/users")
    public ResponseEntity<List<User>> getALLUser(){
        List<User> userList = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    // 회원 정보를 가져오는 API
    // serverhost:8080/users//123
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        User user = userService.getUser(id);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setAge(user.getAge());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    //특정 ID의 유저 삭제
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
