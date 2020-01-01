package com.where.springboot.service;

import com.where.springboot.domain.posts.Posts;
import com.where.springboot.domain.posts.PostsRepository;
import com.where.springboot.web.dto.PostsListResponseDto;
import com.where.springboot.web.dto.PostsResponseDto;
import com.where.springboot.web.dto.PostsSaveRequestDto;
import com.where.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*
    RequiredArgsConstructor가 생성자로 bean객체를 받는 역할을 한다. private가 붙은 필드를 인자값으로 하는 생성자를 롬복이 대신 생성한다.
    생성자를 직접 안쓰고 롬복 어노테이션을 사용하면 해당 클래스의 의존성 관계가 변경되어도 생성자 코드를 수정하는 일이 발생하지 않는다. (롬복이 알아서 해주니까)
*/
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("해당 사용자가 없습니다. id = " + id)));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        postsRepository.delete(posts);
    }
}
