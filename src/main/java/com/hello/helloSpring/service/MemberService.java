package com.hello.helloSpring.service;

import com.hello.helloSpring.domain.Member;
import com.hello.helloSpring.repository.MemberRepository;


import java.util.List;
import java.util.Optional;


public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    //회원 가입
    public Long join(Member member){
        //같은 이름이 있는 중복회원이 안된다.
        validateDuplicateMember(member);    //optional을 바로 꺼내기 부담되니까 바로 ifpresent를 사용 근데 그걸 메소드로 이용
        memberRepository.save(member);
        return member.getId();
        //id 를 반환하게 해줌
    }
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
