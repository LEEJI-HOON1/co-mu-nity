package com.comu.comunity.service;

import com.comu.comunity.dto.CommentResponseDto;
import com.comu.comunity.dto.CommentRequestDto;
import com.comu.comunity.model.entity.Board;
import com.comu.comunity.model.entity.Comment;
import com.comu.comunity.repository.BoardRepository;
import com.comu.comunity.model.entity.Member;
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



    public CommentResponseDto createComment(Long boardId, CommentRequestDto requestDto, Member loginedMember) {

        Long loginedMemberId = loginedMember.getId();
        String loginedMemberName= loginedMember.getName();

        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        Comment comment = new Comment(requestDto);

        comment.setMemberId(loginedMemberId);
        comment.setMemberName(loginedMemberName);
        comment.setBoard(board);
        // DB 저장
        Comment saveComment = commentRepository.save(comment);

        // Entity -> ResponseDto
        return new CommentResponseDto(saveComment);

    }

    public List<CommentResponseDto> getComment(Long boardId) {
        Board board = boardRepository.findBoardById(boardId);
        return board.getComments().stream()
                .map(CommentResponseDto::new)
                .toList();
    }

    @Transactional

    public Long updateComment(Long commentId, CommentRequestDto requestDto, Member loginedMember){

        Long loginedMemberId = loginedMember.getId();
        String loginedMemberName = loginedMember.getName();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("해당 댓글은 존재하지 않습니다."));

        if(!comment.getMemberId().equals(loginedMemberId)){
            throw new RuntimeException("권한이 없습니다.");
        }

        comment.setContents(requestDto.getContents());
        comment.setMemberName(loginedMemberName); // 필요한 경우 업데이트
        commentRepository.save(comment);

        return commentId;


    }
    public Long deleteComment(Long commentId, Member loginedMember) {

        Long loginedMemberId = loginedMember.getId();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));

        if (!comment.getMemberId().equals(loginedMemberId)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        //댓글 삭제
        commentRepository.delete(comment);

        return commentId;

    }


    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
    }


}
