package com.comu.comunity.repository;

import com.comu.comunity.model.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    // a가 b를 팔로우 하는지 확인 - 팔로잉 여부 확인
    boolean existsByFromMemberIdAndToMemberId(Long fromMemberId, Long toMemberId);

    // fromMemberId
    List<Friend> findByFromMemberId(Long fromMemberId);

    // ToMemberId의 Friend 
    List<Friend> findByToMemberId(Long toMemberId);
}

