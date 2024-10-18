package com.pgm.board_pro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import com.pgm.board_pro.dto.PageRequestDTO;
import com.pgm.board_pro.dto.PageResponseDTO;
import com.pgm.board_pro.dto.BoardDTO;
import com.pgm.board_pro.mapper.BoardMapper;
import com.pgm.board_pro.vo.BoardVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {
        List<BoardVO> voList= boardMapper.selectList(pageRequestDTO);
        List<BoardDTO> dtoList=voList.stream()
                .map(vo->modelMapper.map(vo, BoardDTO.class))
                .collect(Collectors.toUnmodifiableList());
        int total= boardMapper.getCount(pageRequestDTO);
        PageResponseDTO<BoardDTO> pageResponseDTO=PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @Override
    public void register(BoardDTO boardDTO) {
        log.info("service register"+ boardDTO);
        BoardVO boardVO =modelMapper.map(boardDTO, BoardVO.class);
        log.info(boardVO);
        boardMapper.insert(boardVO);
    }

    @Override
    public List<BoardDTO> getAll() {
        log.info("service getAll");
        //return todoMapper.getList();
        List<BoardDTO> dtoList= boardMapper.getList().stream()
                .map(vo->modelMapper.map(vo, BoardDTO.class))
                .collect(Collectors.toUnmodifiableList());
        return dtoList;
    }

    @Override
    public BoardDTO getOne(int bno) {
        return null;
    }


    @Override
    public void remove(int bno) {
        log.info("service remove");
        boardMapper.delete(bno);
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        BoardVO boardVO =modelMapper.map(boardDTO, BoardVO.class);
        boardMapper.update(boardVO);
    }
    public BoardVO visitCountUpdate(int bno){
        boardMapper.visitCountUpdate(bno);
        return boardMapper.selectOne(bno);
    }
}
