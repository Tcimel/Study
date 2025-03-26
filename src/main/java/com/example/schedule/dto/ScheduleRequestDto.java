package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String content;

    //user정보
//    @NotNull(message="이름을 입력해주세요")
    private String name;
//    @NotNull(message="비밀번호를 입력해주세요")
    private String pw;
//    @NotNull(message="이메일을 입력해주세요")
    private String email;
}
