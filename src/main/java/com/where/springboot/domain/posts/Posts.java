package com.where.springboot.domain.posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 롬복 어노테이션 - 클래스 내 모든 필드에 Getter 메소드를 자동 생성
@NoArgsConstructor // 롬복 어노테이션 - 기본 생성자 자동추가
@Entity // 테이블과 링크될 클래스임을 나타낸다, 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 매칭한다. 예) SalesManager -> sales_manager table
public class Posts { // 실제 DB테이블과 매칭될 클래스이다, 흔히 Entity클래스 라고도 부른다. JPA를 사용하면 DB데이터에 작업할 경우 실제쿼리를 날리기보다 Entity클래스의 수정을 통해 작업하게 된다.

    @Id // 해당 테이블의 PK(Primary Key)필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK생성 규칙을 나타낸다. 스프링부트 2.0에서는 Generation.Type.IDENTITY 옵션을 추가해야만 auto_increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타내며 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 된다. 기본값외에 추가로 필요한 옵션이 있는경우 명시적으로 선언한다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author; // @Column이 명시 되어있는 것과 같음

    @Builder // 해당 클래스에 빌더 패턴 클래스 생성,
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

/*
    Entity 클래스 에서는 Setter 메소드를 만들지 않아야 한다. 무작정 bean에 getter/setter를 생성하면 인스턴스값들이 언제 어디서 변해야하는지 코드상으로 명확하지 않고
    차후 기능변경시 매우 복잡해진다. 대신 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야 한다.

    최초 값을 DB에 넣을때에는 생성자를 통해 값을 채운후 삽입하고, 값을 바꿔야할 상황이 생기면 그에맞는 메소드를 만들어서 값을 변경해야 한다는것이다.
    즉, setter로 값을 바꾸는게 아니라 값을 바꿔야하는 이유를 알수있을 메소드를 만들고 메소드를 통해 값을 제어한다. 이 프로젝트 에서는 생성자 대신 @Builder를 통해 제공되는 빌더 클래스를 사용한다.
    예) Example.builder()
                .a(a)
                .b(b)
                .build();
*/