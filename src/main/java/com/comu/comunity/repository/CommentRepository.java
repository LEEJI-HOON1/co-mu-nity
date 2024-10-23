package com.comu.comunity.repository;

import com.comu.comunity.model.entity.Board;
import com.comu.comunity.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoardId(Long boardId);

}
