package com.example.springex.controller;

import com.example.springex.dto.UserIsActiveResponse;
import com.example.springex.dto.UserRequest;
import com.example.springex.dto.UserResponse;
import com.example.springex.dto.UserResponseWithoutName;
import com.example.springex.entitiy.Book;
import com.example.springex.entitiy.Loan;
import com.example.springex.service.BookService;
import com.example.springex.service.UserService;
import com.example.springex.entitiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
// Argument Resolver
@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    //회원가입 API(유저 등록)
    public UserResponse signUp(@RequestBody UserRequest userRequest){
        User user = userService.insert(userRequest);
        UserResponse userResponse = convert(user);

        return userResponse;
    }


    //회원 정보 수정
    @PutMapping("/users/{id}")
    public UserResponse putUser(@PathVariable long id, @RequestBody UserRequest userRequest){
        // UserRequest 객체를 유저로부터 받아서 userService에 넘겨줘서 해당 유저를 변경
        User user = userService.update(id, userRequest);
        if(user == null){
            return null;
        }
        // 중복 코드가 있으면 안 좋은 점
        // 코드가 길어져서 가독성이 떨어진다
        // 나중에 수정이 필요할 때 여러군데 전부 찾아서 수정해야한다
        // 나중에 똑같은 코드가 너무 많아지면 분명히 어느 한 곳은 뺴먹고 수정한다
        UserResponse userResponse = convert(user);

        return userResponse;
    }

    // 회원 정보를 가져오는 API
    // serverhost:8080/users//123
    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable long id){
        User user = userService.getUser(id);
        if(user==null){
            //return ResponseEntity.notFound().build();
            return null;
        }
        UserResponse userResponse = convert(user);
        return userResponse;
    }

    //모든 회원 정보를 가져 오는 API
    @GetMapping("/users")
    // Generic
    public List<UserResponseWithoutName> getALLUsers(){
        List<User> userList = userService.getAllUsers();
        //List<UserResponseWithoutName> userResponses = new ArrayList<>();
        // for(User user : userList){
        //UserResponseWithoutName response = new UserResponseWithoutName(userList.get(i));
        //    UserResponseWithoutName response  = UserResponseWithoutName.convert(user);
        //    userResponses.add(response);
        //}
        List<UserResponseWithoutName> userResponses2 = userList.stream()
                .map(UserResponseWithoutName::new)
                .collect(Collectors.toList());
        return userResponses2;
    }

    // 유저 이름으로 유저 리스트 찾기
    @GetMapping("/users/search2/{name}")
    public List<UserResponse> getUserName(@PathVariable String name){
        List<User> userList = userService.getUserName(name);
        if(userList==null){
            //return ResponseEntity.notFound().build();
            return null;
        }
        List<UserResponse> userResponses = new ArrayList<>();

        // 3개의 ddd가 잘 들어 왔는데 for문을 돌리면서 convert된 부분이 리턴이 되지 않는다.
        // userList
        // -> 하나하나 userResponse로 바꿔서
        // -> userResponses에 추가한다
        // -> userResponses를 응답한다
        for(User user: userList){
            UserResponse response = convert(user);
            userResponses.add(response);
        }

        return userResponses;
    }

    //특정 ID의 유저 삭제
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") long id){
        userService.delete(id);
    }

    @GetMapping("/users/{id}/loans")
    public void loanData(@PathVariable long id){
        User user = userService.getLoanData(id);

        UserResponse userResponse = new UserResponse();
        userResponse.setLoanData(user.getLoanData());
    }
    // 응답
    @GetMapping("/users/{id}/isActive")
    public UserIsActiveResponse isActive(@PathVariable long id){
        User user = userService.getUser(id);

        UserIsActiveResponse userResponse = new UserIsActiveResponse(user.isActivate());
        return userResponse;
    }

    //  user 이름으로 빌린 책 검색
    @GetMapping("/users/search/{name}")
    public ResponseEntity<List<Book>> SearchBookByName(@PathVariable String name){
        List<Book> bookList = bookService.searchBook(name);
        if (name == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookList);
    }

    private UserResponse convert(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setAge(user.getAge());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setActive(user.isActivate());

        return userResponse;
    }


}
