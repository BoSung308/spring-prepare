package com.sparta.schedulemanage.repository;


import com.sparta.schedulemanage.ScheduleManageApplication;
import com.sparta.schedulemanage.dto.RequestDto;
import com.sparta.schedulemanage.dto.ResponseDto;
import com.sparta.schedulemanage.dto.TempResponseDto;
import com.sparta.schedulemanage.entity.Entity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@org.springframework.stereotype.Repository

public class Repository {
    private final JdbcTemplate jdbcTemplate;

    public Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createSchedule(RequestDto requestDto) {

        String sql = "INSERT INTO major (task, managePerson, pw, createDateTime, updateDateTime) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, requestDto.getTask(), requestDto.getManagePerson(), requestDto.getPw(),
                requestDto.getCreateDateTime(), requestDto.getUpdateDateTime());
    }

    public TempResponseDto inquirySchedule(int id) {
        String sql = "SELECT * FROM major WHERE Id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            TempResponseDto schedule = new TempResponseDto();

            schedule.setManagePerson(rs.getString("managePerson"));
            schedule.setPw(rs.getString("pw"));
            schedule.setTask(rs.getString("task"));
            schedule.setCreateDateTime(rs.getString("createDateTime"));
            schedule.setUpdateDateTime(rs.getString("updateDateTime"));

            return schedule;
        });
    }

    public List<TempResponseDto> getAllInfo(Entity entity) {
        String sql = "SELECT * FROM major";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            TempResponseDto tempResponseDto = new TempResponseDto();


            tempResponseDto.setTask(rs.getString("task"));
            tempResponseDto.setPw(rs.getString("pw"));
            tempResponseDto.setManagePerson(rs.getString("managePerson"));
            tempResponseDto.setCreateDateTime(rs.getString("createDateTime"));
            tempResponseDto.setUpdateDateTime(rs.getString("updateDateTime"));

            return tempResponseDto;
        });
    }

    public TempResponseDto findByUpdateDateTime(String updateDateTime) {
        String sql = "SELECT * FROM major WHERE updateDateTime=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{updateDateTime}, (rs, rowNum) -> {


            TempResponseDto schedule = new TempResponseDto();
            schedule.setManagePerson(rs.getString("managePerson"));
            schedule.setPw(rs.getString("pw"));
            schedule.setTask(rs.getString("task"));
            schedule.setCreateDateTime(rs.getString("createDateTime"));
            schedule.setUpdateDateTime(rs.getString("updateDateTime"));

            return schedule;
        });
    }

    public TempResponseDto getByModifiedDate(String updateDateTime) {
        String sql = "SELECT * FROM major WHERE updateDateTime=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{updateDateTime}, (rs, rowNum) -> {


            TempResponseDto schedule = new TempResponseDto();
            schedule.setManagePerson(rs.getString("managePerson"));
            schedule.setPw(rs.getString("pw"));
            schedule.setTask(rs.getString("task"));
            schedule.setCreateDateTime(rs.getString("createDateTime"));
            schedule.setUpdateDateTime(rs.getString("updateDateTime"));

            return schedule;

        });
    }

    public List<TempResponseDto> findByModifiedDateAndManagePersonOrderByupdateTime(String updateDateTime,
                                                                                    String managePerson) {
        String sql = "SELECT * FROM major WHERE updateDateTime = ? AND managePerson = ? ORDER BY updateDateTime DESC";

        return jdbcTemplate.query(sql, new Object[]{updateDateTime, managePerson}, (rs, rowNum) -> {

            TempResponseDto responseDto = new TempResponseDto();

            responseDto.setTask(rs.getString("task"));
            responseDto.setPw(rs.getString("pw"));
            responseDto.setManagePerson(rs.getString("managePerson"));
            responseDto.setCreateDateTime(rs.getString("createDateTime"));
            responseDto.setUpdateDateTime(rs.getString("updateDateTime"));

            return responseDto;

        });
    }
}




