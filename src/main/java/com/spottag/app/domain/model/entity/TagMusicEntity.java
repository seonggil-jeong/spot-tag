package com.spottag.app.domain.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tag_music", schema = "public", indexes = {

})
public class TagMusicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagMusicId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "tag_id")
    private TagBaseEntity tagId;


    public TagMusicEntity(TagBaseEntity tagBase) {
        this.createdAt = LocalDateTime.now();
        this.tagId = tagBase;
    }
}
