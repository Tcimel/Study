package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/memos") // prefix// Json형태 데이터
public class MemoController {
    //자료구조 활용 데이터 임시 저장
    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto dto){

        //식별자가 1씩 증가 하도록
        //비어있으면 아이디값 1, 아니면 Collections.max 최대값 찾기
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet())+1;

        //요청받은 데이터로 메모를 생성
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());

        //Inmemory(자바 맵 자료구조로 임시(프로그램 사용시에만 사용가능할때) DB에 Memo 메모
        memoList.put(memoId,memo);

        return new MemoResponseDto(memo);
    }

    @GetMapping("/{id}")
    public MemoResponseDto findMemoById(@PathVariable Long id){
        Memo memo = memoList.get(id);

        return new MemoResponseDto(memo);
    }

    @PutMapping("/{id}")
    public MemoResponseDto updateMemoById(@PathVariable Long id,@RequestBody MemoRequestDto dto){
        Memo memo = memoList.get(id);
        memo.update(dto);
        return new MemoResponseDto(memo);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id){
        memoList.remove(id);
    }
}
