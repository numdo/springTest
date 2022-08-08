package com.hello.helloSpring.repository;

import com.hello.helloSpring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    //여기서 clear를 불러준다.

    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setName("spring");
        //member를 세팅하고 이름을 설정한다

        //when
        repository.save(member);
        //repository에 저장을 한다.

        //then
        Member result = repository.findById(member.getId()).get();  //검증을 해본다. findById를 통해서 검증 optional에서 꺼낼때는 get사용하는데 그리 좋은 방법은 아니다.
        assertThat(result).isEqualTo(member);       //syso로 출력해서 확인은 가능하지만 계속해서 사용할수 없으니까 assertThat을 사용해서 둘이 똑같은지 확인한다.
        //출력되는건 없지만 체크가 안뜬다.
        //assertion의 assertThat을 활용해서 isEqualTo를 사용해서 member가 result랑 같은지를 확인한다.
    }

    @Test
    public void findByName(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        //shift f 사용으로 리네임
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
        //동작을 확인하기 위해서 member변수 만들고 이름을 만들어주고 repository에 save를 사용해서 저장한뒤에
        //변수를 2개 만들어서 결과값이 하나랑 같게끔만 만들고 save와 똑같이 assertThat을 사용해서 맞는지 확인한다.
    }

    //순서가 보장인 안되서 findByName이 오류가 날 수 있다. 그래서 같은 멤버 이름은 member 변수들이 저장이 되버린다.
    //그래서 다른게 호출이 된다. 그래서 afterEach가 았다.
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
