package com.where.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    SpringBootApplication로 인해 자동 설정, Bean 일기,쓰기가 가능해진다.
    SpringBootApplication 어노테이션이 있는 위치부터 설정을 읽어가기 때문에 항상 프로젝트 최상단에 있어야 한다.
*/
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        /*
            run을 통해 내장WAS를 실행시켜 톰캣없이 구동이 가능해지고 jar 파일로 실행하면 된다.
            jar파일 = java 패키징 파일을 의미, 스프링부트 에서는 외장WAS대신 내장 WAS를 사용하길 권장한다.
            언제 어디서나 같은 환경에서 스프링 부트를 배포할 숫 있기 때문이다.
        */
        SpringApplication.run(Application.class, args);
    }
}
