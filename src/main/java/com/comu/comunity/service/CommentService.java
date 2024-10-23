package com.comu.comunity.service;

import com.comu.comunity.dto.CommentResponseDto;
import com.comu.comunity.dto.CommentRequestDto;
import com.comu.comunity.model.entity.Comment;
import com.comu.comunity.repository.BoardRepository;
import com.comu.comunity.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

//    public CommentService(CommentRepository commentRepository) {
//        this.commentRepository = commentRepository;
//    }



    public CommentResponseDto createComment(CommentRequestDto requestDto) {

        // RequestDto -> Entity
        Comment comment = new Comment(requestDto);

        // DB 저장
        Comment saveComment = commentRepository.save(comment);

        // Entity -> ResponseDto
        return new CommentResponseDto(saveComment);

    }

    public List<CommentResponseDto> getComment(Long boardId) {
        List<Comment> comments = commentRepository.findAllByBoardId(boardId);
        // DB 조회
        return commentRepository.findAll().stream().map(CommentResponseDto::new).toList();
    }

    @Transactional
    
    public Long updateComment(Long commentId, CommentRequestDto requestDto){
        //해당 댓글이 DB에 존재하는지 확인
        Comment comment = findComment(commentId);
        //댓글 내용 수정
        comment.update(requestDto);
        
        return commentId;

        
    }
    public Long deleteComment(Long commentId) {

        //해당 댓글이 DB에 존재하는지 확인
        Comment comment = findComment(commentId);
        // 댓글 삭제
        commentRepository.delete(comment);

        return commentId;

    }


    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
    }


}
