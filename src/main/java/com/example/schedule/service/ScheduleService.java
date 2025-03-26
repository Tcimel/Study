package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);
    List<ScheduleResponseDto> findAllSchedules(String date, String name, Long id);
    ScheduleResponseDto findScheduleById(Long id);
    ScheduleResponseDto update(Long id, ScheduleRequestDto dto);
    ScheduleResponseDto delete(Long id, String pw);
}
