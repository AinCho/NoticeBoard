package com.zerobase.noticeboard.controller;

import com.zerobase.noticeboard.service.RecommendService;
import com.zerobase.noticeboard.user.RecommendRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend/")
public class RecommendController {
    private final RecommendService recommendService;

    @PostMapping
    public void insert(@RequestBody @Validated RecommendRequestDTO recommendRequestDTO) throws Exception {
        recommendService.insert(recommendRequestDTO);
    }

    @DeleteMapping
    public void delete(@RequestBody @Validated RecommendRequestDTO recommendRequestDTO) {
        recommendService.delete(recommendRequestDTO);
    }
}
