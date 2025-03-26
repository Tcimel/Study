package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Schedules {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    //private Users user;
    private Date create_date; //생성일자, 업데이트 일자 세부 분류
    private LocalDateTime update_date; //생성일은 최초 1회만 저장, 그이후 수정시에는 업데이트 일자만 갱신

    public Schedules(String title, String content, Long userId){
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

}
