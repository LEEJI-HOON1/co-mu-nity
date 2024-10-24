package com.comu.comunity.service;

import com.comu.comunity.dto.FriendResponseDto;
import com.comu.comunity.dto.MemberFollowResponse;
import com.comu.comunity.dto.MemberResponseDto;
import com.comu.comunity.model.entity.Friend;
import com.comu.comunity.model.entity.Member;
import com.comu.comunity.repository.FriendRepository;
import com.comu.comunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public FriendResponseDto follow(Member loginedMember, Long toMemberId) {
        Long loginedMemberId = loginedMember.getId();
        // 자기 자신 팔로우 안됨
        if (loginedMemberId.equals(toMemberId)) {
            throw new RuntimeException("자기 자신을 follow 할 수 없습니다.");
        }
        // 이미 팔로우 중일때
        if (isFollowing(loginedMemberId, toMemberId)) {
            throw new IllegalArgumentException("이미 follow 했습니다");
        }
//        Member fromMember = memberRepository.findById(loginedMemberId)
//                .orElseThrow(RuntimeException::new);
        // 팔로우 대상 회원 조회
        Member toMember = memberRepository.findById(toMemberId)
                .orElseThrow(RuntimeException::new);

        Friend friend = new Friend(loginedMember, toMember);
        loginedMember.addFollowing(friend);
        toMember.addFollower(friend);

        friendRepository.save(friend);

        // follower 및 following 수치 업데이트
        // Drity Checking에 의해 tx 종료 시 자동으로 업데이트 됨
//        fromMember.setFollowing(fromMember.getFollowing() + 1);
//        toMember.setFollowing(toMember.getFollower() + 1);

        return new FriendResponseDto(friend.getId(), loginedMemberId, toMemberId);
    }

    public FriendResponseDto unfollow(Member loginedMember, Long toMemberId) {

        Long loginedMemberId = loginedMember.getId();

        if (loginedMemberId.equals(toMemberId)) {
            throw new RuntimeException("자기 자신을 unfollow 할 수 없습니다");
        }
        // 팔로우 관계 조회
        Friend friend = friendRepository.findByFromMemberIdAndToMemberId(loginedMemberId, toMemberId)
                .orElseThrow(() -> new IllegalArgumentException("팔로우가 되어 있지 않습니다."));

        friendRepository.delete(friend);
        return new FriendResponseDto(friend.getId(), loginedMemberId, toMemberId);
    }

    public List<MemberFollowResponse> getFollowings(Long fromMemberId) {
        // 해당 멤버가 존재하는지 확인
        memberRepository.findById(fromMemberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<Friend> followings = friendRepository.findByFromMemberId(fromMemberId);
        return followings.stream()
                .map(MemberFollowResponse::new)
                .collect(Collectors.toList());
    }

    public List<MemberFollowResponse> getFollowers(Long toMemberId) {
        memberRepository.findById(toMemberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<Friend> followers = friendRepository.findByToMemberId(toMemberId);
        return followers.stream()
                .map(friend -> new MemberFollowResponse(friend.getFromMember().getId(), friend.getFromMember().getName()))
                .collect(Collectors.toList());
    }

    private boolean isFollowing(Long fromMemberId, Long toMemberId){
        return friendRepository.existsByFromMemberIdAndToMemberId(fromMemberId, toMemberId);
    }
}
