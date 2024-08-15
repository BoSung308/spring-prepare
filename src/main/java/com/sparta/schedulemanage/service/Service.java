package com.sparta.schedulemanage.service;


import com.sparta.schedulemanage.dto.RequestDto;
import com.sparta.schedulemanage.dto.TempResponseDto;
import com.sparta.schedulemanage.entity.Entity;
import com.sparta.schedulemanage.repository.Repository;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public TempResponseDto updateSchedule(RequestDto requestDto) {
        Entity entity = repository. findByPw(requestDto.getId(), requestDto.getPw());

        entity.update(requestDto.getTask(), requestDto.getManagePerson());

        //수정일 갱신 (format에 대한 이해가 높지가 않아서 format 코드는 gpt도움받음..)
        String updateDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // DB 업데이트
        repository.updateSchedule(requestDto.getId(), requestDto.getTask(), requestDto.getManagePerson(), updateDateTime, requestDto.getPw());
        // 수정된 정보 ResponseDto로 바꾸고 반환
        TempResponseDto tempResponseDto = new TempResponseDto();
        tempResponseDto.setId(entity.getId());
        tempResponseDto.setTask(requestDto.getTask());
        tempResponseDto.setManagePerson(requestDto.getManagePerson());
        tempResponseDto.setCreateDateTime(requestDto.getCreateDateTime());
        tempResponseDto.setUpdateDateTime(updateDateTime);

        return tempResponseDto;
    }
}


