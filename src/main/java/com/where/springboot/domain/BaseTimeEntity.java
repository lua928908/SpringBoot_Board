package com.where.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 createDate, modifiedDate 필드들을 칼럼으로 인식하도록 한다. 즉, 부모클래스의 필드를 자기클래스의 칼럼처럼 인식하게 만든다.
@EntityListeners(AuditingEntityListener.class) // 해당클래스(BaseTimeEntity)에 Auditing기능을 포함시킨다.
public abstract class BaseTimeEntity { // BaseTimeEntity 클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createDate, modifiedDate를 자동으로 관리하는 역할을 한다.
    @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장된다.
    private LocalDateTime createDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경(수정)할 때 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;
}
