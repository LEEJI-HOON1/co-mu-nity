package com.comu.comunity.repository;

import com.comu.comunity.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일로 회원 찾기
    Optional<Member> findByEmail(String email);

}
