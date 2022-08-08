package com.hello.helloSpring.repository;

import com.hello.helloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id); //optional null처리 기법중 하나 나중에 다시 설명해줌
    List<Member> findAll();
    Optional<Member> findByName(String name);
}
