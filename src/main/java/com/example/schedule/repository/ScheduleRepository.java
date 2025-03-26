package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedules;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveScheudle(Schedules sc);
    List<ScheduleResponseDto> findAllSchedules(String date, String name, Long id);
    Schedules findScheduleById(Long id);
    int update(Long id, String content);
    void delete(Long id);
}
