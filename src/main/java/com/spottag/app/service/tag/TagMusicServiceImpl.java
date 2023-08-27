package com.spottag.app.service.tag;

import com.spottag.app.domain.model.entity.TagBaseEntity;
import com.spottag.app.domain.model.entity.TagMusicEntity;
import com.spottag.app.domain.repository.TagBaseRepository;
import com.spottag.app.domain.repository.TagMusicRepository;
import com.spottag.app.service.spotify.SpotifyServiceImpl;
import com.spottag.app.service.spotify.dto.SpotifyMusicDto;
import com.spottag.app.service.tag.dto.TagMusicDto;
import com.wrapper.spotify.exceptions.detailed.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagMusicServiceImpl {
    private final TagMusicRepository tagMusicRepository;
    private final TagBaseRepository tagBaseRepository;
    private final SpotifyServiceImpl spotifyService;


    /**
     * Update TagMusic
     *
     * @param tagId   tagBase Id
     * @param trackId Spotify TrackId
     * @return
     * @throws NoSuchElementException 일치하는 노래 정보를 찾을 수 없음
     * @throws NoSuchElementException 일치하는 TagBase 정보를 찾을 수 없음
     */
    @Transactional(rollbackOn = Exception.class)
    public TagMusicDto updateTagMusic(final String accountId, final Long tagId, final String trackId) throws Exception {
        final SpotifyMusicDto spotifyMusicDto = spotifyService.searchTrackByTrackId(trackId).orElseThrow(()
                -> new NoSuchElementException("cannot found track with Id : " + trackId));

        final TagBaseEntity tagBase = tagBaseRepository.findByTagIdAndDeletedAtIsNull(tagId).orElseThrow(()
                -> new NoSuchElementException("cannot found TagBase with id : " + tagId));

        if (!tagBase.getAccountId().getAccountId().equals(accountId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot access Response");

        } else if (tagBase.getTagMusic() == null) {
            throw new BadRequestException("Tag Doesn't hase Tag Music Info tag Id : " + tagId);

        } else {
            return TagMusicDto.ofEntity(tagMusicRepository.save(tagBase.getTagMusic().updateTagMusic(spotifyMusicDto.getMusicTitle(),
                    spotifyMusicDto.getArtistName(),
                    spotifyMusicDto.getTrackId(),
                    spotifyMusicDto.getTrackUrl(),
                    spotifyMusicDto.getPreviewUrl(),
                    spotifyMusicDto.getLImageUrl(),
                    spotifyMusicDto.getMImageUrl(),
                    spotifyMusicDto.getSImageUrl())));
        }
    }

    /**
     * Create Tag Music with TagId, trackId
     *
     * @param tagId   tagId
     * @param trackId trackId
     * @return
     * @throws BadRequestException    이미 Music 정보를 가지고 있음
     * @throws NoSuchElementException 해당 trackId 로 노래 정보를 찾을 수 없음
     */
    @Transactional(rollbackOn = Exception.class)
    public TagMusicDto createTagMusic(final String accountId, final Long tagId, final String trackId) throws Exception {
        final SpotifyMusicDto spotifyMusicDto = spotifyService.searchTrackByTrackId(trackId).orElseThrow(()
                -> new NoSuchElementException("cannot found track with Id : " + trackId));

        return this.createTagMusic(accountId, tagId, spotifyMusicDto);
    }

    /**
     * Create Tag Music with Dto, tagId
     *
     * @param tagId
     * @param spotifyTrack
     * @return
     * @throws BadRequestException 이미 Music 정보를 가지고 있음
     */
    @Transactional(rollbackOn = Exception.class)
    public TagMusicDto createTagMusic(final String accountId, final Long tagId, final SpotifyMusicDto spotifyTrack) throws BadRequestException {

        TagBaseEntity tagBase = tagBaseRepository.findById(tagId).orElseThrow(()
                -> new NoSuchElementException("cannot found TagBase with id : " + tagId));

        if (!tagBase.getAccountId().getAccountId().equals(accountId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot access Response");

        } else if (tagBase.getTagMusic() != null) {
            throw new BadRequestException("Tag Already Has Music");

        } else {
            return TagMusicDto.ofEntity(tagMusicRepository.save(TagMusicEntity.builder()
                    .tagId(tagBase)
                    .musicTitle(spotifyTrack.getMusicTitle())
                    .artistName(spotifyTrack.getArtistName())
                    .trackId(spotifyTrack.getTrackId())
                    .trackUrl(spotifyTrack.getTrackUrl())
                    .previewUrl(spotifyTrack.getPreviewUrl())
                    .lImageUrl(spotifyTrack.getLImageUrl())
                    .mImageUrl(spotifyTrack.getMImageUrl())
                    .sImageUrl(spotifyTrack.getSImageUrl())
                    .build()));
        }
    }

    /**
     * Delete TagMusic Info
     *
     * @param accountId account Id
     * @param tagId     tagBase Id
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteTagMusic(final String accountId, final Long tagId) throws Exception {

        TagBaseEntity tagBase = tagBaseRepository.findByTagIdAndDeletedAtIsNull(tagId).orElseThrow(()
                -> new NoSuchElementException("cannot found TagBase with Id : " + tagId));

        if (!tagBase.getAccountId().getAccountId().equals(accountId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot access Response");
        } else {
            tagMusicRepository.deleteById(tagBase.getTagMusic().getTagMusicId());
        }
    }
}
