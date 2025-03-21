package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/memos") // prefix// Json형태 데이터
public class MemoController {
    //자료구조 활용 데이터 임시 저장
    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto){

        //식별자가 1씩 증가 하도록
        //비어있으면 아이디값 1, 아니면 Collections.max 최대값 찾기
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet())+1;

        //요청받은 데이터로 메모를 생성
        Memo memo = new Memo(memoId, dto.getTitle(), dto.getContents());

        //Inmemory(자바 맵 자료구조로 임시(프로그램 사용시에만 사용가능할때) DB에 Memo 메모
        memoList.put(memoId,memo);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id){
        Memo memo = memoList.get(id);

        if(memo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MemoResponseDto(memo),HttpStatus.OK);
    }

    @GetMapping
    public List<MemoResponseDto> findAllMemo(){
        List<MemoResponseDto> responseList = new ArrayList<>();
        // 리스트는 인터페이스여서 항상 초기화 해줘야 함

        // HashMap -> List로
        for(Memo memo : memoList.values()){
            MemoResponseDto responseDto = new MemoResponseDto(memo);
            responseList.add(responseDto);
        }

//        responseList = memoList.values().stream().map(MemoResponseDto::new).toList();

        return responseList;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemoById(@PathVariable Long id,@RequestBody MemoRequestDto dto){
        Memo memo = memoList.get(id);
        if(memo==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(dto.getTitle() == null || dto.getContents() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        memo.update(dto);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(@PathVariable Long id, @RequestBody MemoRequestDto dto){
        Memo memo = memoList.get(id);

        //null case
        if(memo==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(dto.getTitle() == null || dto.getContents() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memo.updateTitle(dto);

        return new ResponseEntity<>(new MemoResponseDto(memo),HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id){
        //id가 있는지
        if(memoList.containsKey(id)){
            memoList.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
