package com.spottag.app.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class TagInfoVo {
    private final Long tag_id;
    private final String tag_content;
    private final String longitude;
    private final String latitude;
    private final String account_id;
    private final String created_by;
    private final String updated_by;
    private final String deleted_by;
    private final OffsetDateTime created_at;
    private final OffsetDateTime updated_at;
    private final OffsetDateTime deleted_at;
    private final Long tag_music_id;
    private final OffsetDateTime music_created_at;
    private final String music_title;
    private final String artist_name;
    private final String preview_url;
    private final String track_id;
    private final String track_url;
    private final String l_image_url;
    private final String m_image_url;
    private final String s_image_url;
    private final Boolean has_music;
}
