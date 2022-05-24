package com.example.springex.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
public class BookIsActiveResponse {
    private final boolean isActive;

    public BookIsActiveResponse(boolean isActive) {
        this.isActive = isActive;
    }

}