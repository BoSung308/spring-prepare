package com.sparta.schedulemanage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자


public class TempResponseDto {
    int Id;
    String managePerson;
    String pw;
    String task;
    String createDateTime;
    String updateDateTime;
}
