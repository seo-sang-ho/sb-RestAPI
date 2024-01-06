package com.ll.sbrestapi.domain.member.article.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.sbrestapi.domain.member.member.entity.Member;
import com.ll.sbrestapi.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@SuperBuilder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
public class Article extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    private Member author;
    private String title;
    private String body;
}
