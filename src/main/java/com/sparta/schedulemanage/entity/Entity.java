package com.sparta.schedulemanage.entity;

import com.sparta.schedulemanage.dto.RequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Entity {
    private int Id;
    private String managePerson;
    private String pw;
    private String task;
    private String createDateTime;
    private String updateDateTime;

    public Entity(RequestDto requestDto) {
        this.managePerson = requestDto.getManagePerson();
        this.pw = requestDto.getPw();
        this.task = requestDto.getTask();
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createDateTime = simpleDateFormat.format(nowDate);
        this.updateDateTime = simpleDateFormat.format(nowDate);
    }

    public void update(String task, String managePerson) {
        this.task = task;
        this.managePerson = managePerson;
        this.updateDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
