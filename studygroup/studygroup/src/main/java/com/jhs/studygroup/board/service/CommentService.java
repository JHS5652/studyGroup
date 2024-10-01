package com.jhs.studygroup.board.service;

import com.jhs.studygroup.board.dto.CommentDTO;
import com.jhs.studygroup.board.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void commentSave(CommentDTO commentDTO) {
        commentRepository.commentSave(commentDTO);
    }

    public void commentDelete(Long id) {
        commentRepository.commentDelete(id);
    }
}
