package com.where.springboot.domain.posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>{
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

/*
    보통 MyBatis 등에서 부르는 Dao 라고 불리는 DB Layer접근자이다.
    JPA에선 Repository라고 부르며 인터페이스로 생선한다. 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입>을 상속하면
    기본적인 CRUD 메소드가 자동으로 생선된다. @Repository 어노테이션도 필요없다.
    주의할 점은 Entity 클래스와 Entity Repository는 함께 위치해야한다 (같은 패키지에 있어야 한다)
    나중에 프로젝트 규모가 커져 도메인별로 프로젝트를 분리해야 한다면 이때 Entity클래스와 기본Repository는 함께 움직여야 하기때문에 도메인 패키지에서 관리하는 것이다.
*/