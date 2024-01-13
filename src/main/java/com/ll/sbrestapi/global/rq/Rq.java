package com.ll.sbrestapi.global.rq;

import com.ll.sbrestapi.domain.member.member.entity.Member;
import com.ll.sbrestapi.domain.member.member.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final MemberService memberService;

    private Member member;
    private final EntityManager entityManager;

    public Member getMember(){
        if(member == null){
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long memberId = Long.parseLong(user.getUsername());

            member = entityManager.getReference(Member.class,memberId); // 프록시 모드 발동
            // member.getId(); -> 쿼리 발생 x
            // member.getUsername(); -> select * from member where id = ?;
            // Member member = new Member(memberId); 랑 같다.
            // member.getUsername(); // null
            // 45강 강의 내용
            // 무식하게 다 회원의 정보를 다 가져오는 것이 아닌 필요할때만 가져온다.
        }

        return member;
    }

    public String getHeader(String name) {
        return request.getHeader(name);
    }
}
