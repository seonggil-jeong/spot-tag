package com.spottag.app.service.tag.dto;

import com.spottag.app.domain.vo.TagInfoVo;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor(force = true)
@Builder
public class TagInfoDto {
    private final Long tagId;
    private final String tagContent;
    private final String longitude;
    private final String latitude;
    private final String accountId;
    private final String createdBy;
    private final String updatedBy;
    private final String deletedBy;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;
    private final Long tagMusicId;
    private final LocalDateTime musicCreatedAt;
    private final String musicTitle;
    private final String artistName;
    private final String previewUrl;
    private final String trackId;
    private final String trackUrl;
    private final String lImageUrl;
    private final String mImageUrl;
    private final String sImageUrl;
    private final Boolean hasMusic;
    @Setter
    private Integer likeCount;

    public static TagInfoDto ofVo(final TagInfoVo vo) {
        return TagInfoDto.builder()
                .tagId(vo.getTag_id())
                .tagContent(vo.getTag_content())
                .longitude(vo.getLongitude())
                .latitude(vo.getLatitude())
                .accountId(vo.getAccount_id())
                .createdBy(vo.getCreated_by())
                .updatedBy(vo.getUpdated_by())
                .deletedBy(vo.getDeleted_by())
                .createdAt(vo.getCreated_at().toLocalDateTime())
                .updatedAt(toLocalDate(vo.getUpdated_at()))
                .deletedAt(toLocalDate(vo.getDeleted_at()))
                .tagMusicId(vo.getTag_music_id())
                .musicCreatedAt(toLocalDate(vo.getMusic_created_at()))
                .musicTitle(vo.getMusic_title())
                .artistName(vo.getArtist_name())
                .previewUrl(vo.getPreview_url())
                .trackId(vo.getTrack_id())
                .trackUrl(vo.getTrack_url())
                .lImageUrl(vo.getL_image_url())
                .mImageUrl(vo.getM_image_url())
                .sImageUrl(vo.getS_image_url())
                .hasMusic(vo.getHas_music())
                .build();
    }


    public static LocalDateTime toLocalDate(OffsetDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.toLocalDateTime();
        } else {
            return null;
        }

    }
}
