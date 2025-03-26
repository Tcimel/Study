package com.example.schedule.dto;

import com.example.schedule.entity.Schedules;
import com.example.schedule.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private Date create_date; //생성일자, 업데이트 일자 세부 분류
    private LocalDateTime update_date; //생성일은 최초 1회만 저장, 그이후 수정시에는 업데이트 일자만 갱신

    public ScheduleResponseDto(Schedules sc){
        this.id = sc.getId();
        this.title = sc.getTitle();
        this.content = sc.getContent();
        this.userId = sc.getUserId();
        this.create_date = sc.getCreate_date();
        this.update_date = sc.getUpdate_date();
    }
}
