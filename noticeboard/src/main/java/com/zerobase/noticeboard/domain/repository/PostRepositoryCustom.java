package com.zerobase.noticeboard.domain.repository;

import com.zerobase.noticeboard.domain.model.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> searchByTitle(String title);
}
