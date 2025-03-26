package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.entity.Users;

public interface UsersRepository {
    UserResponseDto saveUser(Users user);
    Users findUserById(Long userId);
}
