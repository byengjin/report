package com.pgm.board_pro.service;

import com.pgm.board_pro.dto.PageRequestDTO;
import com.pgm.board_pro.dto.PageResponseDTO;
import com.pgm.board_pro.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);
    void register(BoardDTO boardDTO);
    List<BoardDTO> getAll();

    BoardDTO getOne(int bno);

    void remove(int bno);

    void modify(BoardDTO boardDTO);
}
