package com.spottag.app.controller.like;


import com.spottag.app.service.like.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/tags/{tagId}/likes")
    public ResponseEntity<Void> updateLike(@RequestParam Long userId, @RequestParam Long tagId) {
        likeService.likecheck(userId, tagId);
        return ResponseEntity.ok().build();
    }
}
