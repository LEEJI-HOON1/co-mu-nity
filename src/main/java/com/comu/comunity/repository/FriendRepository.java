package com.comu.comunity.repository;

import com.comu.comunity.model.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    // a가 b를 팔로우 하는지 확인 - 팔로잉 여부 확인
    boolean existsByFromMemberIdAndToMemberId(Long fromMemberId, Long toMemberId);

    // fromMemberId로 팔로잉 목록 조회
    List<Friend> findByFromMemberId(Long fromMemberId);

    // ToMemberId로 팔로워 목록 조회
    List<Friend> findByToMemberId(Long toMemberId);

    // 특정 팔로우 관계 찾기
    Optional<Friend> findByFromMemberIdAndToMemberId(Long fromMemberId, Long toMemberId);
}

