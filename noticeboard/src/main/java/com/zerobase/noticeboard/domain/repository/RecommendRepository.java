package com.zerobase.noticeboard.domain.repository;

import com.zerobase.noticeboard.domain.model.Post;
import com.zerobase.noticeboard.domain.model.Recommend;
import com.zerobase.user.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {
}
