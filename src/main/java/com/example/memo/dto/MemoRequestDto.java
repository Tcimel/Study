package com.example.memo.dto;

import lombok.Getter;

@Getter
//요청 데이터를 처리하는 객체 생성
public class MemoRequestDto {
    private String title;
    private String contents;
}
