package com.sparta.schedulemanage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RequestDto {
    // 할일, 담당자명, 비밀번호, 작성일, 수정일,
    int Id;
    String managePerson;
    String pw;
    String task;
    String createDateTime;
    String updateDateTime;
}


/*
* 순서 : cotnroller - dto -service -repository - entity - Service - Controller

1.  controller : http 요청을 처리하고 받은 요청 데이터를 dto 객체로 변환
 사용자로부터 적절한 서비스 메서드 호출하고 반환
2. dto : controller에서 받은 데이터를 dto로 변환해서 Service로 전달
3. service : dto를 기반으로 비즈니스 로직처리, 필요한 경우 Repositorty를 호출해서
 데이터 조회, 저장, 수정, 삭제
4. repository : 데이터베이스와 상호작용함. 필요한 데이터 조회, 삽입, 수정, 삭제(CRUD)
5. entity : Repository에서 db 데이터 가져오거나 저장할때 사용.
Service 계층에서 Entity를 dto오로 변환 or dto를 entity로 변환해서 데이터 처리
 */
