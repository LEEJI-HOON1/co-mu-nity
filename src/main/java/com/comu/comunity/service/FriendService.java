package com.comu.comunity.service;

import com.comu.comunity.dto.FriendResponseDto;
import com.comu.comunity.model.entity.Friend;
import com.comu.comunity.model.entity.Member;
import com.comu.comunity.repository.FriendRepository;
import com.comu.comunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    // Todo
    //  토큰생성시, fromMemberId는 로그인한 사용자로 변경해야함
    // fromMember가 toMember 팔로우
    public String follow(Long fromMemberId, Long toMemberId) {
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
        friendRepository.save(friend);
        return "Follow successful";
    }

    // 팔로우중인지 확인하기
    public boolean isFollowing(Long fromMemberId, Long toMemberId){
        return friendRepository.existsByFromMemberIdAndToMemberId(fromMemberId, toMemberId);
    }


}
