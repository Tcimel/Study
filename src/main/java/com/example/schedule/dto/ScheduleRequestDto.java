package com.example.schedule.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String title;

    @NotNull(message="내용을 입력해주세요")
    @NotBlank
    @Size(max = 200, message = "최대 200자까지 작성 가능합니다.")
    private String content;

    //user정보
    @NotNull(message="이름을 입력해주세요")
    @NotBlank
    private String name;

    @NotNull(message="비밀번호를 입력해주세요")
    @NotBlank
    private String pw;

    @Email
    private String email;
}
