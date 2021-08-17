package me.studybook.api.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, length = 50, nullable = false)
    private String username;

    @Column(name = "nickname", unique = true, length = 20, nullable = false)
    private String nickname;

    @Column(name ="profile", length = 150)
    private String profile;

    @Column(name ="info", length = 150)
    private String info;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public User(Long id, String username, String nickname, String profile, String info) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.profile = profile;
        this.info = info;
    }
}
