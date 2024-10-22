package com.comu.comunity.repository;

import com.comu.comunity.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    default Board findBoardById(Long id) {
        return findById(id).orElseThrow(() -> new IllegalArgumentException("Board not found with id"+ id));
    }
}
