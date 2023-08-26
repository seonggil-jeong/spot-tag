//package com.spottag.app.controller.like;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//public class LikeController {
//
//
//
//    @PostMapping(value = "/tags/{tagId}/likes")
//    public int updateLike(int bno, String memberId)throws Exception{
//
////        int likeCheck = likeService.likeCheck(bno, memberId);
////        if(likeCheck == 0) {
////            //좋아요 처음누름
////            likeService.insertLike(bno, memberId); //like테이블 삽입
////            likeService.updateLike(bno);	//게시판테이블 +1
////            likeService.updateLikeCheck(bno, memberId);//like테이블 구분자 1
////        }else if(likeCheck == 1) {
////            likeService.updateLikeCheckCancel(bno, memberId); //like테이블 구분자0
////            likeService.updateLikeCancel(bno); //게시판테이블 - 1
////            likeService.deleteLike(bno, memberId); //like테이블 삭제
////        }
////        return ;
//   //}
//}
