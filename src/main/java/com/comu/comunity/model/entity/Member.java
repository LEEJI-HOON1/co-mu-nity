package com.comu.comunity.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

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

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    /* 게시글과의 연관관계 설정 예시
    @OneToMany(mappedBy = "member", cascade = CascadeType.All, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();
     */

    /* 댓글과의 연관관계 설정 예시
    @OneToMany(mappedBy = "member", cascade = CascadeType.All, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
     */

    public void updateProfile(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }
}
