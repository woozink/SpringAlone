package com.example.springex.controller;

import com.example.springex.dto.UserRequest;
import com.example.springex.dto.UserResponse;
import com.example.springex.service.UserService;
import com.example.springex.entitiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    //회원가입 API(유저 등록)
    @PostMapping("/users")
    public ResponseEntity<User> signUp(@RequestBody UserRequest userRequest){
        User user = userService.insert(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    //모든 회원 정보를 가져 오는 API
    @GetMapping("/users")
    public ResponseEntity<List<User>> getALLUser(){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    //회원 정보 수정
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> putUser(@PathVariable long id, @RequestBody UserRequest userRequest){
        // UserRequest 객체를 유저로부터 받아서 userService에 넘겨줘서 해당 유저를 변경
        User user = userService.update(id, userRequest);
        UserResponse userResponse = new UserResponse();

        userResponse.setAge(user.getAge());
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getName());

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    // 회원 정보를 가져오는 API
    // serverhost:8080/users//123
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        User user = userService.getUser(id);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        UserResponse userResponse = convert(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    //특정 ID의 유저 삭제
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    private UserResponse convert(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setAge(user.getAge());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }
}
