package com.comu.comunity.repository;

import com.comu.comunity.model.entity.Friend;
import com.comu.comunity.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    // a가 b를 팔로우 하는지 확인
    boolean existsByFromMemberIdAndToMemberId(Long fromMemberId, Long toMemberId);
}
