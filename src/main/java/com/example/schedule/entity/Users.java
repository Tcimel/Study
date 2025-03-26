package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Users {
    private Long id;
    private String name;
    private String pw;
    private String email;

    public Users(String name, String pw, String email){
        this.name = name;
        this.pw = pw;
        this.email = email; // 중복 검사 필요
    }
}
