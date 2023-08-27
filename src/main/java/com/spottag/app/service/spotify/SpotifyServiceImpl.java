package com.spottag.app.service.spotify;

import com.neovisionaries.i18n.CountryCode;
import com.spottag.app.service.spotify.dto.SpotifyMusicTrackDto;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyServiceImpl {
    @Value("${api.spotify.client-id}")
    private String clientId;

    @Value("${api.spotify.client-secret}")
    private String clientSecret;

    public List<SpotifyMusicTrackDto> searchTrackByTrackName(final String trackName, Integer offset, Integer limit) throws Exception {
        Paging<Track> result = getSpotifyApi().searchTracks(trackName)
                .offset(offset)
                .limit(limit)
                .includeExternal("audio").build()
                .execute();

        log.debug("Track Count : " + result.getItems().length);
        return Arrays.stream(result.getItems()).map(SpotifyMusicTrackDto::ofSpotifyTrack)
                .collect(Collectors.toList());
    }


    public List<Track> searchTrackByTrackId(final String trackId) throws Exception {
        final Paging<Track> result = getSpotifyApi().searchTracks(trackId)
                .build().execute();


        return Arrays.asList(result.getItems());
    }

    private SpotifyApi getSpotifyApi() throws Exception {

        SpotifyApi spotifyApiBasic = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApiBasic
                .clientCredentials()
                .build();
        final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

        spotifyApiBasic.setAccessToken(clientCredentials.getAccessToken());

        log.debug("Expires : " + clientCredentials.getExpiresIn());


        return spotifyApiBasic;

    }
}
