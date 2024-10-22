package com.comu.comunity.service;

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

    // Todo
    //  토큰생성시, fromMemberId는 로그인한 사용자로 변경해야함
    // fromMember가 toMember 팔로우
    @Transactional
    public Friend follow(Long fromMemberId, Long toMemberId) {
        // 자기 자신 팔로우 안됨
        if (fromMemberId.equals(toMemberId)) {
            throw new RuntimeException("자기 자신을 follow 할 수 없습니다.");
        }
        // 이미 팔로우 중일때
        if (isFollowing(fromMemberId, toMemberId)) {
            throw new IllegalArgumentException("이미 follow 했습니다");
        }
        Member fromMember = memberRepository.findById(fromMemberId).orElseThrow(RuntimeException::new);
        Member toMember = memberRepository.findById(toMemberId).orElseThrow(RuntimeException::new);



        Friend friend = new Friend(fromMember, toMember);
        return friendRepository.save(friend);
    }

    public List<MemberResponseDto> getFollowings(Long fromMemberId) {
        // 해당 멤버가 존재하는지 확인
        memberRepository.findById(fromMemberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // fromMember의 팔로잉 목록을 조회한다.
        List<Friend> followings = friendRepository.findByFromMemberId(fromMemberId);
        // 팔로잉 정보가 필요할땐 get필드 추가
        return followings.stream()
                .map(friend -> new MemberResponseDto(friend.getToMember().getId(), friend.getToMember().getName()))
                .collect(Collectors.toList());
    }

    public List<MemberResponseDto> getFollowers(Long toMemberId) {
        memberRepository.findById(toMemberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<Friend> followers = friendRepository.findByToMemberId(toMemberId);
        // 팔로잉 정보가 필요할땐 get필드 추가
        return followers.stream()
                .map(friend -> new MemberResponseDto(friend.getFromMember().getId(), friend.getFromMember().getName()))
                .collect(Collectors.toList());
    }

    // 팔로우중인지 확인하기
    private boolean isFollowing(Long fromMemberId, Long toMemberId){
        return friendRepository.existsByFromMemberIdAndToMemberId(fromMemberId, toMemberId);
    }
}
