package com.sparta.schedulemanage.service;


import com.sparta.schedulemanage.dto.RequestDto;
import com.sparta.schedulemanage.dto.ResponseDto;
import com.sparta.schedulemanage.dto.TempResponseDto;
import com.sparta.schedulemanage.entity.Entity;
import com.sparta.schedulemanage.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public int createSchedule(RequestDto requestDto) {
        return repository.createSchedule(requestDto);
    }

    public TempResponseDto inquirySchedule(int id) {
        return repository.inquirySchedule(id);
    }

    public List<TempResponseDto> getAllInfo(RequestDto requestDto) {
        Entity entity = new Entity(requestDto);
        // Repository를 통해서 데이터 가져오기
        return repository.getAllInfo(entity);
    }

    public TempResponseDto getByModifiedDate(String updateDatetime) {
        return repository.getByModifiedDate(updateDatetime);
    }

    public TempResponseDto getByModifiedManagerPerson(String managePerson) {
        // Repository 데이터를 조회하고 dto로 변환
        return repository.findByUpdateDateTime(managePerson);
    }

    public List<TempResponseDto> getByModifiedDateAndManagePerson(String updateDateTime, String managePerson) {
        // Repository 데이터를 조회하고 dto로 변환
        return repository.findByModifiedDateAndManagePersonOrderByupdateTime(updateDateTime, managePerson);
    }
}


