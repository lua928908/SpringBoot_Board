package com.where.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// RestController는 JSON을 반환하는 컨트롤러를 만들어준다. (과거 @ResponseBody를 각메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다고 생각하면 된다.)
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
