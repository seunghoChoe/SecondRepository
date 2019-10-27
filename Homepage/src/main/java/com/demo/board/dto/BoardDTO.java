package com.demo.board.dto;

import javax.validation.constraints.Size;
import java.util.Date;

public class BoardDTO {

    private int boardIdx;
    private int boardHit;
    private Date boardCreateDate;
    private Date boardUpdateDate;
    private int boardLike;

    @Size(min = 1, max = 100, message = "게시글 제목은 1~100자 이내로 작성해야 합니다.")
    private String boardTitle;

    @Size(min = 1, max = 1000, message = "게시글 내용은 1~1000자 이내로 작성해야 합니다.")
    private String boardContent;
    private String boardAuthor;

    public int getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(int boardIdx) {
        this.boardIdx = boardIdx;
    }

    public int getBoardHit() {
        return boardHit;
    }

    public void setBoardHit(int boardHit) {
        this.boardHit = boardHit;
    }

    public Date getBoardCreateDate() {
        return boardCreateDate;
    }

    public void setBoardCreateDate(Date boardCreateDate) {
        this.boardCreateDate = boardCreateDate;
    }

    public Date getBoardUpdateDate() {
        return boardUpdateDate;
    }

    public void setBoardUpdateDate(Date boardUpdateDate) {
        this.boardUpdateDate = boardUpdateDate;
    }

    public int getBoardLike() {
        return boardLike;
    }

    public void setBoardLike(int boardLike) {
        this.boardLike = boardLike;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardAuthor() {
        return boardAuthor;
    }

    public void setBoardAuthor(String boardAuthor) {
        this.boardAuthor = boardAuthor;
    }
}