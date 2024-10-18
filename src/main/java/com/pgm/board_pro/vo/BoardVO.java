package com.pgm.board_pro.vo;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
    private int bno;
    private String title;
    private String content;
    private String writer;
    private Date postdate;
}
