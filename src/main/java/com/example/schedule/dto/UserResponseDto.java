package com.example.schedule.dto;

import com.example.schedule.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String pw;
    private String email;

    public UserResponseDto(Users user){
        this.id = user.getId();
        this.name = user.getName();
        this.pw = user.getPw();
        this.email = user.getEmail();}
}
