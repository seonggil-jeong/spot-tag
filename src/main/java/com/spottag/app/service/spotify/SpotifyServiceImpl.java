package com.spottag.app.service.spotify;

import com.spottag.app.service.spotify.dto.SpotifyMusicDto;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyServiceImpl {
    @Value("${api.spotify.client-id}")
    private String clientId;

    @Value("${api.spotify.client-secret}")
    private String clientSecret;

    /**
     * find Track List By Name
     *
     * @param trackName searchName
     * @param offset    offset
     * @param limit     limit
     * @return
     * @throws Exception
     */
    public List<SpotifyMusicDto> searchTrackByTrackName(final String trackName, Integer offset, Integer limit) throws Exception {
        Paging<Track> result = getSpotifyApi().searchTracks(trackName)
                .offset(offset)
                .limit(limit)
                .includeExternal("audio").build()
                .execute();

        log.debug("Track Count : " + result.getItems().length);
        return Arrays.stream(result.getItems()).map(SpotifyMusicDto::ofSpotifyTrack)
                .collect(Collectors.toList());
    }


    /**
     * Search Track(Music) By Id
     * @param trackId
     * @return
     * @throws Exception
     */
    public Optional<SpotifyMusicDto> searchTrackByTrackId(final String trackId) throws Exception {

        return Optional.of(SpotifyMusicDto.ofSpotifyTrack(getSpotifyApi().getTrack(trackId)
                .build()
                .execute()));
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
