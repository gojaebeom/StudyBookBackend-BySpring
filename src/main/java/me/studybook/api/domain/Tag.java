package me.studybook.api.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tags")
@NoArgsConstructor
@Getter
@ToString
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 12)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
