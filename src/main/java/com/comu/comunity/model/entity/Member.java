package com.comu.comunity.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Setter
@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "follower")
    private int follower;

    @Column(name = "following")
    private int following;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public void updateProfile(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    // 팔로우 (내가 추가한 사람들)
    @OneToMany(mappedBy = "fromMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friend> followings = new ArrayList<>();

    // 팔로워(나를 추가한 사람들)
    @OneToMany(mappedBy = "toMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friend> followers = new ArrayList<>();


    public void addFollowing(Friend friend) {
        this.followings.add(friend);
        friend.setFromMember(this);
    }

    public void addFollower(Friend friend) {
        this.followers.add(friend);
        friend.setToMember(this);
    }

    public void removeFollowing(Friend friend) {
        this.followings.remove(friend);
        friend.setFromMember(null);
    }

    public void removeFollower(Friend friend) {
        this.followers.remove(friend);
        friend.setToMember(null);
    }
}
