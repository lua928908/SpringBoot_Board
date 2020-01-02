package com.where.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // 소셜 로그인으로 반횐되는 값 중 email을 통해 이미 생성(가입)된 사용자 인지 처음 가입하는지 판단하기 위한 메서드
}
