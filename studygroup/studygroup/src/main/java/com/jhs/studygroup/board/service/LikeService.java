package com.jhs.studygroup.board.service;

import com.jhs.studygroup.board.dto.LikeDTO;
import com.jhs.studygroup.board.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void plus(LikeDTO likeDTO) {
        likeRepository.plus(likeDTO);
    }

    public void minus(LikeDTO likeDTO) {
        likeRepository.minus(likeDTO);
    }

    public void boardLike(Long id) {
        likeRepository.boardLike(id);
    }

    public void boardDisLike(Long id) {
        likeRepository.boardDisLike(id);
    }
}
