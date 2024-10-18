package com.pgm.board_pro.mapper;

import com.pgm.board_pro.dto.PageRequestDTO;
import com.pgm.board_pro.vo.BoardVO;

import java.util.List;

public interface BoardMapper {
    List<BoardVO> selectList(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);
    String getTime();
    void insert(BoardVO boardVO);
    List<BoardVO> getList();
    BoardVO selectOne(int bno);
    void delete(int bno);
    void visitCountUpdate(int bno);
    void update(BoardVO boardVO);
}
