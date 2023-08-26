package com.spottag.app.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "like_info")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_no", updatable = false, nullable = false, unique = true)
    private Long likeNo;

//    @ManyToOne
//    @JoinColumn(name = "TagSeq")
//    private // TODO: 2023/08/26   tag;
//
//    @ManyToOne
//    @JoinColumn(name = "UserId")
//    private  // TODO: 2023/08/26   userId;

    @Column(name = "like_date")
    private LocalDateTime likeDate;

    @Builder
    public LikeEntity(LocalDateTime likeDate) {
        this.likeDate = likeDate;
    }
}
