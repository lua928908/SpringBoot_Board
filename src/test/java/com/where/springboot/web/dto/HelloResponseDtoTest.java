package com.where.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        // given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}

/*
    assertThat이란
        1. assertj라는 테스트 검증 라이브러리의 검증메소드이다.
        2. 검증하고 싶은 대상을 메소드 인자로 받는다.
        3. 메소드 체이닝이 지원되어 isEqualTo와 같은 메소드를 이어서 사용할 수 있다.

    isEqualTo란
        1. assertj의 동등비교 메소드이다.
        2. assertThat에 있는 값과 isEqualTo의 값을 비교해 같을때만 성공이 된다.
*/
