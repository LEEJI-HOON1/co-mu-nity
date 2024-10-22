package com.comu.comunity.service;

import com.comu.comunity.dto.CommentResponseDto;
import com.comu.comunity.dto.CommentRequestDto;
import com.comu.comunity.model.entity.Comment;
import com.comu.comunity.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }



    public CommentResponseDto createComment(CommentRequestDto requestDto) {

        // RequestDto -> Entity
        Comment comment = new Comment(requestDto);

        // DB 저장
        Comment saveComment = commentRepository.save(comment);

        // Entity -> ResponseDto
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
        return commentResponseDto;

    }

    public List<CommentResponseDto> getComment() {
        // DB 조회
        return commentRepository.findAll().stream().map(CommentResponseDto::new).toList();
    }

    @Transactional
    
    public Long updateComment(Long Id, CommentRequestDto requestDto){
        //해당 댓글이 DB에 존재하는지 확인
        Comment comment = findComment(Id);
        //댓글 내용 수정
        comment.update(requestDto);
        
        return Id;
        
    }
    public Long deleteComment(Long Id) {

        //해당 댓글이 DB에 존재하는지 확인
        Comment comment = findComment(Id);
        // 댓글 삭제
        commentRepository.delete(comment);

        return Id;

    }


    private Comment findComment(Long Id) {
        return commentRepository.findById(Id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
    }


}
