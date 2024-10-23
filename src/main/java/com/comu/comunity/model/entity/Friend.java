package com.comu.comunity.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "friend")
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    // 팔로우 요청 하는 사람 fromMember
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member")
    private Member fromMember;

    // 팔로우 요청 받는 사람 toMember
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member")
    private Member toMember;

    @Column
    private String status;

    public Friend(Member fromMember, Member toMember) {
        this.fromMember = fromMember;
        this.toMember = toMember;
    }
}
