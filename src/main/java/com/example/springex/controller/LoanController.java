package com.example.springex.controller;


import com.example.springex.dto.LoanRequest;
import com.example.springex.dto.LoanResponse;
import com.example.springex.entitiy.Book;
import com.example.springex.entitiy.Loan;
import com.example.springex.service.LoanService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("")
public class LoanController {
    @Autowired
    private LoanService loanService;

    // 도서 대출 - O
    // 도서 반납 - x///
    // 도서 대출기한 연장 - O
    // 특정 유저의 도서 대출 기록 조회 -0

    //특정 유저가 특정 도서 대출(대출일, 반납예정일)
    @PostMapping("/loans")
    public ResponseEntity<LoanResponse> borrowBook(@RequestBody LoanRequest loanRequest) {
        // 서버에서는 어떤 값이 필요할까?
        // bookId, userId
        // 대출날짜는 필요없다
        Loan loan = loanService.borrowBooks(loanRequest);

        if (loan == null) {
            return ResponseEntity.badRequest().build();
        }

        LoanResponse loanResponse = new LoanResponse();

        loanResponse.setReturnDateTime(loan.getReturnDateTime());
        loanResponse.setReturned(loan.isReturned());
        loanResponse.setLoanDateTime(loan.getLoanDateTime());
        loanResponse.setExpireDateTime(loan.getExpireDateTime());
        loanResponse.setBookId(loan.getBookId());
        loanResponse.setUserId(loan.getUserId());
        loanResponse.setId(loan.getId());

        return ResponseEntity.ok().body(loanResponse);
    }

    // 도서 전체 조회
    @GetMapping("/loans")
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loanList = loanService.getAllLoans();
        return ResponseEntity.status(HttpStatus.OK).body(loanList);
    }

    //특정 대출 기록 조회
    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanResponse> getLoan(@PathVariable long id) {
        Loan loan = loanService.getLoan(id);
        if (loan == null) {
            return ResponseEntity.notFound().build();
        }
        LoanResponse loanResponse = convert(loan);

        return ResponseEntity.status(HttpStatus.OK).body(loanResponse);
    }

    @PutMapping("/Loans/return")
    //특정 유저가 특정 도서 반납
    public ResponseEntity<Void> returned(@RequestBody LoanRequest loanRequest) {
        loanService.returned(loanRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/loans/{id}/extend")
    //특정 유저가 특정 도서반납 일자 연장
    public ResponseEntity<Void> extended(@PathVariable long id) {
        loanService.extend(id);

        return ResponseEntity.ok().build();
    }


    private LoanResponse convert(Loan loan) {
        LoanResponse loanResponse = new LoanResponse();
        loanResponse.setLoanDateTime(loan.getReturnDateTime());
        loanResponse.setReturned(loan.isReturned());
        loanResponse.setExpireDateTime(loan.getExpireDateTime());
        loanResponse.setBookId(loan.getBookId());

        return loanResponse;
    }
}
