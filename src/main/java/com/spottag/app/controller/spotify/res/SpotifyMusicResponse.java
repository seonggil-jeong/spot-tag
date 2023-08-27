package com.spottag.app.controller.spotify.res;

import com.spottag.app.service.spotify.dto.SpotifyMusicDto;
import com.wrapper.spotify.model_objects.specification.Track;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class SpotifyMusicResponse {
    @Schema(description = "노래 제목")
    private final String musicTitle;

    @Schema(description = "가수 이름")
    private final String artistName;

    @Schema(description = "track Id")
    private final String trackId;

    @Schema(description = "클릭 시 Spotify Page로 이동")
    private final String trackUrl;

    @Schema(description = "30초 미리 듣기")
    private final String previewUrl;

    @Schema(description = "L Size Image")
    private final String lImageUrl;

    @Schema(description = "M Size Image")
    private final String mImageUrl;

    @Schema(description = "S Size Image")
    private final String sImageUrl;

    public static SpotifyMusicResponse ofDto(SpotifyMusicDto dto) {
        return SpotifyMusicResponse.builder()
                .musicTitle(dto.getMusicTitle())
                .artistName(dto.getArtistName())
                .trackId(dto.getTrackId())
                .trackUrl(dto.getTrackUrl())
                .previewUrl(dto.getPreviewUrl())
                .lImageUrl(dto.getLImageUrl())
                .mImageUrl(dto.getMImageUrl())
                .sImageUrl(dto.getSImageUrl())
                .build();
    }
}
