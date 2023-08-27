package com.spottag.app.controller.like;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.service.like.LikeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Like", description = "좋아요 기능")
public class TagLikeController extends ControllerSupport {

    private final LikeService likeService;

    @PostMapping("/tags/{tagId}/likes")
    public ResponseEntity<Void> updateLike(
            @Parameter(description = "tagId")
            @PathVariable Long tagId
    ) throws AccountException {
        likeService.likeCheck(getAccountId(), tagId);

        return ResponseEntity.ok().build();
    }
}
