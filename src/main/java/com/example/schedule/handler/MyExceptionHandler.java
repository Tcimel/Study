package com.example.schedule.handler;

import com.example.schedule.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class) //valid 검사 시 매개변수
//    public ResponseEntity<String> handlerRequestDto(MethodArgumentNotValidException e){
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //valid 검사 시 매개변수
    public ResponseEntity<Map<String, String>> handlerRequestDto(MethodArgumentNotValidException ex){ // Map<String, String> 여러 조건이 만족하지 않았을 경우, 키(어떤 필드가) 값(어떤 문제가 발생)
        Map<String, String> map = new HashMap<>();
        // e 가 많은 정보를 갖고 있을 수 있어서 for문으로 받아옴
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            map.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
