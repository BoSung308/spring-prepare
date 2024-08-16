package com.sparta.schedulemanage.repository;


import com.sparta.schedulemanage.dto.RequestDto;
import com.sparta.schedulemanage.dto.TempResponseDto;
import com.sparta.schedulemanage.entity.Entity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Repository

public class Repository {
    private final JdbcTemplate jdbcTemplate;
    private List<Entity> findSchedule = new ArrayList<>();
    // id와 비밀번호로 일정을 찾으려고 List타입을만듦

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

    public String getModifiedDate(int id) {
        String sql = "SELECT updateDateTime FROM major WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
    }

    public String getManagePersonById(int id) {
        String sql = "SELECT managePerson FROM major WHERE id=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
    }

    public List<TempResponseDto> findByModifiedDateAndManagePersonOrderByupdateTime(String updateDateTime,
                                                                                    String managePerson) {

        if(updateDateTime == null || updateDateTime.isEmpty() || managePerson ==null || managePerson.isEmpty()) {
            throw new IllegalArgumentException("올바르지 않은 값입니다.");
        }
        String sql = "SELECT * FROM major WHERE updateDateTime = ? AND managePerson = ? ORDER BY updateDateTime DESC";

        return jdbcTemplate.query(sql, new Object[]{updateDateTime, managePerson}, (rs, rowNum) -> {
            TempResponseDto dto1 = new TempResponseDto();

            dto1.setTask(rs.getString("task"));
            dto1.setPw(rs.getString("pw"));
            dto1.setManagePerson(rs.getString("managePerson"));
            dto1.setCreateDateTime(rs.getString("createDateTime"));
            dto1.setUpdateDateTime(rs.getString("updateDateTime"));
            return dto1;
            // 입력값이 없을때,, 예외나 if 사용
        });
    }

    public Entity findByPw(int id, String pw) {
        String sql = "SELECT * FROM major WHERE id = ? AND pw = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id, pw}, (rs, rowNum) -> {

            Entity entity = new Entity();
            entity.setId(rs.getInt("id"));
            entity.setTask(rs.getString("task"));
            entity.setManagePerson(rs.getString("managePerson"));
            entity.setPw(rs.getString("pw"));
            entity.setCreateDateTime(rs.getString("createDateTime"));
            entity.setUpdateDateTime(rs.getString("updateDateTime"));
            return entity;

        });
    }

    public int updateSchedule(int id, String task, String managePerson, String updateDateTime, String pw) {
        String sql = "UPDATE major SET task = ?, managePerson = ?, updateDateTime = ? WHERE id = ? AND pw = ?";
        return jdbcTemplate.update(sql, task, managePerson, updateDateTime, id, pw);
    }

    public int deleteByPw(int id, String pw) {
        String sql = "DELETE FROM major WHERE id = ? AND pw = ?";
        return jdbcTemplate.update(sql, id, pw); //
    }
}







