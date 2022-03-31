package com.example.springex.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
public class LoanResponse {
    private long id;
    private long userId;
    private long bookId;
    // 대출일
    private LocalDateTime loanDateTime;
    // 만료일
    private LocalDateTime expireDateTime;
    // 반납일
    private LocalDateTime returnDateTime=null;
    private boolean isReturned = false;
}
