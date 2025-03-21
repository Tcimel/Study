package com.example.memo.entity;

import com.example.memo.dto.MemoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자를 생성한다.
public class Memo {
    private Long id; //Long : 래퍼클래스이기 때무네 Null 사용가능
    private String title;
    private String contents;

    public void update(MemoRequestDto dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }

    public void updateTitle(MemoRequestDto dto){
        this.title = dto.getTitle();
    }
}
