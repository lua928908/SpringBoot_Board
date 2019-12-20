package com.where.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 테스트진행시 SpringRunner.class라는 스프링 실행자를 사용한다, 즉 스프링부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest // 스프링테스트 어노테이션중 Web에 집중할 수 있는 어노테이션 @Controller, @ControllerAdvice등을 쓸수있다. 단 @Service, @Component, @Repository는 쓸수없다.
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 Bean을 주입받는다 (Bean으로 등록되어야만 주입 가능)
    private MockMvc mvc; // 웹API를 테스트할때 사용되고 스프링 MVC테스트의 시작점이자 이 클래스를 통해 HTTP GET,POST등을 테스트 할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
                .andExpect(status().isOk()) // mvc.perform를 실행한 결과를 검증한다. status가 isOk, 200인지 검증
                .andExpect(content().string(hello)); // Controller가 "hello"를 리턴하기 때문에 이 값이 맞는지 검증한다.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}

/*
    param
        1. API테스트할때 사용될 요청 파라미터이다.
        2. 값은 String만 가능하기때문에 숫자/날짜 등의 데이터도 등록할때 문자열로 변경해야 된다.

    jsonPath
        1. JSON 응답값을 필드별로 검증할 수 있게하는 메소드이다.
        2. $를 기준으로 필드명을 명시한다.
*/