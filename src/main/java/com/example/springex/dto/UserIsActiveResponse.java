package com.example.springex.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
public class UserIsActiveResponse {
    private final boolean isActive;

    public UserIsActiveResponse(boolean isActive) {
        this.isActive = isActive;
    }

}
