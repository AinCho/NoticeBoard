package com.zerobase.noticeboard.domain.repository;

import com.zerobase.noticeboard.domain.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositoryCustom {
    List<Post> searchByTitle(String title);
}
