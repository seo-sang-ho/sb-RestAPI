package com.ll.sbrestapi.domain.member.member.service;

import com.ll.sbrestapi.domain.member.member.entity.Member;
import com.ll.sbrestapi.domain.member.member.repository.MemberRepository;
import com.ll.sbrestapi.global.rsData.RsData;
import com.ll.sbrestapi.global.util.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<Member> join(String username, String password, String email, String nickname) {
        Member member = Member.builder()
                .modifyDate(LocalDateTime.now())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        return RsData.of("200", "%s님 가입을 환영합니다.".formatted(username), member);
    }

    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }

    public long count() {
        return memberRepository.count();
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findByApiKey(String apiKey) {
        Claims claims = JwtUtil.decode(apiKey);

        Map<String, String> data = (Map<String, String>) claims.get("data");
        long id = Long.parseLong(data.get("id"));

        return findById(id);
    }

    public RsData<Member> checkUsernameAndPassword(String username, String password) {
        Optional<Member> memberOp = findByUsername(username);

        if(memberOp.isEmpty()){
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        if(!passwordEncoder.matches(password,memberOp.get().getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return RsData.of("200","로그인 성공",memberOp.get());
    }
}
