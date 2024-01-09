package com.ll.sbrestapi.domain.member.member.entity;

import com.ll.sbrestapi.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
public class Member extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String nickname;
    @Column(unique = true)
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private String apiKey; // apiKey 는 최초에 한번만 들어간다.

    public String getName() {
        return nickname;
    }
}
