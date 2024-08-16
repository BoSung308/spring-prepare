
package com.sparta.schedulemanage.controller;

import com.sparta.schedulemanage.dto.DeleteRequestDto;
import com.sparta.schedulemanage.dto.DeleteResponseDto;
import com.sparta.schedulemanage.dto.TempResponseDto;
import com.sparta.schedulemanage.dto.RequestDto;
import com.sparta.schedulemanage.service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private final Service service;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Controller(Service service) {
        this.service = service;
    }


    @PostMapping("/create")
    public int createSchedule(@RequestBody RequestDto requestdto) {
        return service.createSchedule(requestdto);
    }

    // id사용 조회(선택한일정조회)
    @GetMapping("/inquiry/{id}")
    public TempResponseDto inquirySchedule(@PathVariable int id) {
        return service.inquirySchedule(id);
    }

    //    1)등록된 정보 목록 전부 조회
    @GetMapping("/allInfo")
    public List<TempResponseDto> getAllInfo(@RequestBody RequestDto requestDto) {
        return service.getAllInfo(requestDto);
    }

    //    2) 수정일 조회
    @GetMapping("/modified")
    public String getModifiedDate(@RequestParam int id) {
        return service.getModifiedDate(id);
    }

    //    3) 담당자 조회
    @GetMapping("/modified2")
    public String getManagePerson(@RequestParam int id) {
        return service.getManagePersonById(id);

    }

    //4) 수정일과 담당자의 조회
    @GetMapping("/modified3")
    public List<TempResponseDto> getByModifiedDateAndManagePerson(@RequestParam String updateDateTime, @RequestParam String managePerson) {
        return service.getByModifiedDateAndManagePerson(updateDateTime, managePerson);
    }

    @PutMapping("/update")
    public TempResponseDto updateSchedule(@RequestBody RequestDto requestDto) {
        return service.updateSchedule(requestDto);
    }

    @DeleteMapping("/delete")
    public DeleteResponseDto deletePw(@RequestBody DeleteRequestDto deleteRequestDto){
        return service.deletePw(deleteRequestDto);
    }
}


