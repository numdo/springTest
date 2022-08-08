package com.hello.helloSpring.repository;

import com.hello.helloSpring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return  Optional.ofNullable(store.get(id)); //optional로 null을 방지
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    //list로 store에 있는 value를 반환
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();                     //람다식을 활용해서 루프로 돌린다. filter를 사용해서 찾음
    }
    public void clearStore(){
        store.clear();
    }
    //멤버가 저장되어있는 곳을 비우기 위해서 필요하다.

}

//동작을 위해서 test케이스를 작성해서 확인해본다.
