package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.entity.Schedules;
import com.example.schedule.entity.Users;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final UsersRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository screpository, UsersRepository userRepository) {
        this.scheduleRepository = screpository;
        this.userRepository = userRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        //유저 데이터 생성
        Users user = new Users(dto.getName(),dto.getPw(),dto.getEmail());
        UserResponseDto saveduser = userRepository.saveUser(user);

        // 객체 생성 후 DB 저장
        Schedules sc = new Schedules(dto.getTitle(),dto.getContent(),saveduser.getId());

        //create_date는 DB에서 자동기록, update_date만 서비스에서 관리
        //sc.setUpdate_date(user.getCreate_date());
        sc.setUpdate_date(LocalDateTime.now());
        return scheduleRepository.saveScheudle(sc);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(String date, String name, Long id) {
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules(date,name,id);
        return allSchedules;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedules sc = scheduleRepository.findScheduleById(id);

        return new ScheduleResponseDto(sc);
    }

    @Override
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto) {
        Schedules sc = scheduleRepository.findScheduleById(id);
        Users user = userRepository.findUserById(sc.getUserId());
        if(!user.getPw().equals(dto.getPw())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }else{
            scheduleRepository.update(id,dto.getName(),dto.getContent());
        }

        return new ScheduleResponseDto(sc);
    }

    @Override
    public void delete(Long id, String pw) {
        Schedules sc = scheduleRepository.findScheduleById(id);
        Users user = userRepository.findUserById(sc.getUserId());
        if(pw == null || !user.getPw().equals(pw)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }else{
            scheduleRepository.delete(id);
        }
    }
}
