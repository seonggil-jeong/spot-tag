package com.spottag.app.service.spotify.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import com.wrapper.spotify.model_objects.specification.Track;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class SpotifyMusicTrackDto {
    private final String musicTitle;
    private final String artistName;
    private final String trackId;
    private final String trackUrl;
    private final String previewUrl;
    private final String lImageUrl;
    private final String mImageUrl;
    private final String sImageUrl;


    public static SpotifyMusicTrackDto ofSpotifyTrack(Track track) {
        return SpotifyMusicTrackDto.builder()
                .musicTitle(track.getName())
                .artistName(track.getArtists()[0].getName())
                .trackId(track.getId())
                .trackUrl(track.getUri())
                .previewUrl(track.getPreviewUrl())
                .lImageUrl(track.getAlbum().getImages()[0].getUrl())
                .mImageUrl(track.getAlbum().getImages()[1].getUrl())
                .sImageUrl(track.getAlbum().getImages()[2].getUrl())
                .build();
    }
}
