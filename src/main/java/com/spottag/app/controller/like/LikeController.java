package com.spottag.app.controller.like;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.service.like.LikeServiceImpl;
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
class LikeController extends ControllerSupport {

    private final LikeServiceImpl likeServiceImpl;

    @PostMapping("/tags/{tagId}/likes")
    public ResponseEntity<Void> updateLike(
            @Parameter(description = "tagId")
            @PathVariable Long tagId
    ) throws AccountException {
        likeServiceImpl.likeCheck(getAccountId(), tagId);

        return ResponseEntity.ok().build();
    }
}
