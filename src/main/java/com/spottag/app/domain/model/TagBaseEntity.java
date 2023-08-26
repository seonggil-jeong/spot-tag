package com.spottag.app.domain.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tag_base", indexes = {

})
public class TagBaseEntity {
    /**
     * Seq
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", updatable = false, nullable = false)
    private Long tagId;

    /**
     * 내용
     */
    @Column(name = "tag_content", nullable = false, length = 400)
    private String tagContent;

    /**
     * 생성자
     */
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    /**
     * 수정자
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 삭제자
     */
    @Column(name = "deleted_by")
    private String deletedBy;

    /**
     * 생성일
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    /**
     * 수정일
     */
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    /**
     * 삭제일
     */
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    @Builder
    public TagBaseEntity(String tagContent, String createdBy, String updatedBy, String deletedBy, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.tagContent = tagContent;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;

//        TODO ADD UserEntity
    }


    //    TODO FK by AccountEntity
}
