package com.where.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}


/*
    머스테치 스타터(플러그인) 덕분에 앞의 경로와 뒤의 파일확장자는 자동으로 지정된다, 앞의 경로 src/main/resources/templates와
    뒤의 경로 .mustache가 자동으로 붙어 src/main/resources/templates/index.mustache와 같이 전환 되어
    View Resolver가 처리하게 된다.
*/