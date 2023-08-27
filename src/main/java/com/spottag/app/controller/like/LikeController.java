package com.spottag.app.controller.like;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController extends ControllerSupport {

    private final LikeService likeService;

    @PostMapping("/tags/{tagId}/likes")
    public ResponseEntity<Void> updateLike(@RequestParam String userId, @RequestParam Long tagId) {
        likeService.likecheck(userId, tagId);
        return ResponseEntity.ok().build();
    }
}
