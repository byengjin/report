package com.pgm.board_pro.controller;

import com.pgm.board_pro.dto.PageRequestDTO;
import com.pgm.board_pro.dto.BoardDTO;
import com.pgm.board_pro.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    //@Autowired
    private final BoardService boardService;
    @GetMapping("/register")
    public void registerGet(){
    }

    @PostMapping("/register")
    public String regisiterPost(@Valid BoardDTO boardDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        log.info("regisiterPost()"+ boardDTO);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult);
            return "redirect:/board/register";
        }
        boardService.register(boardDTO);
        return "redirect:/board/list";
    }

    //@GetMapping("/list")
    public void list(Model model) {
        log.info("list");  //  board/list
        List<BoardDTO> boardList= boardService.getAll();
        model.addAttribute("boardList", boardList);
        //return "board/list";
    }

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("list");
        if (bindingResult.hasErrors()) {
            pageRequestDTO=PageRequestDTO.builder().build();
        }
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("responseDTO", boardService.getList(pageRequestDTO));
    }


    @PostMapping("/remove")
    public String remove(@RequestParam("bno") int bno) {
        log.info("remove");
        boardService.remove(bno);
        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("modify()"+ boardDTO);
        boardService.modify(boardDTO);
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        redirectAttributes.addFlashAttribute("pageRequestDTO",pageRequestDTO);
        return "redirect:/board/read";
    }

    @GetMapping({"/read","/modify"})
    public void read(@RequestParam("bno") int bno, PageRequestDTO pageRequestDTO, Model model) {
        log.info("read");
        BoardDTO boardDTO = boardService.getOne(bno);
        model.addAttribute("dto", boardDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
    }


}
