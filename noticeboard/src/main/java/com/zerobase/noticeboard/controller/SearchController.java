package com.zerobase.noticeboard.controller;

import com.zerobase.noticeboard.domain.post.PostDto;
import com.zerobase.noticeboard.service.PostSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search/post")
@RequiredArgsConstructor
public class SearchController {
    private final PostSearchService postSearchService;

    @GetMapping
    public ResponseEntity<List<PostDto>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(
                postSearchService.searchByTitle(title).stream()
                        .map(PostDto::from).collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<PostDto> searchById(@RequestParam Long postId) {
        return ResponseEntity.ok(PostDto.from(postSearchService.getByPostId(postId)));
    }

    @GetMapping
    public ResponseEntity<PostDto> searchByCreatedDate(@RequestParam LocalDateTime createdAt) {
        return ResponseEntity.ok(PostDto.from(postSearchService.searchByCreatedDate(createdAt)));
    }

    @GetMapping
    public ResponseEntity<PostDto> searchByCommentCount(@RequestParam int commentCount) {
        return ResponseEntity.ok(PostDto.from(postSearchService.searchByCommentCount(commentCount)));
    }

    @GetMapping
    public ResponseEntity<PostDto> searchByRecommendCount(@RequestParam int recommendCount) {
        return ResponseEntity.ok(PostDto.from(postSearchService.searchByRecommendCount(recommendCount)));
    }
}
