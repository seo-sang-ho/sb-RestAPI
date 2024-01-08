package com.ll.sbrestapi.global.rq;

import com.ll.sbrestapi.domain.member.member.entity.Member;
import com.ll.sbrestapi.domain.member.member.service.MemberService;
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

    public Member getMember(){
        if(member == null){
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            member = memberService.findByUsername(user.getUsername()).get();
        }

        return member;
    }
}
