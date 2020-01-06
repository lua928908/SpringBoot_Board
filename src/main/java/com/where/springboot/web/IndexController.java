package com.where.springboot.web;

import com.where.springboot.config.auth.LoginUser;
import com.where.springboot.config.auth.dto.SessionUser;
import com.where.springboot.service.PostsService;
import com.where.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){ // Model은 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다, 어느 컨트롤러 에서든 @LoginUser를 통해 세션 정보를 가져올 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null){ // 세션에 저장된 값이 있을때만 model에 userName으로 등록
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}


/*
    머스테치 스타터(플러그인) 덕분에 앞의 경로와 뒤의 파일확장자는 자동으로 지정된다, 앞의 경로 src/main/resources/templates와
    뒤의 경로 .mustache가 자동으로 붙어 src/main/resources/templates/index.mustache와 같이 전환 되어
    View Resolver가 처리하게 된다.
*/