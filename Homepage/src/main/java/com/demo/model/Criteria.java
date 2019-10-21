package com.demo.model;

/**
 * @Description: 페이징 모델 클래스
 */
public class Criteria {
    private int page; // 현재 페이지 번호
    private int perPageNumber; // 페이지당 출력할 게시믈의 개수

    /**
     * 기본 생성자: 목록에 처음 접근할 경우, 첫 번째 페이지를 출력해야 하므로 page = 1 로 지정한다.
     */
    public Criteria() {
        this.page = 1;
        this.perPageNumber = 15; // 페이지당 출력될 게시물의 개수 지정
    }

    public int getPageStart() {
        return (this.page - 1) * perPageNumber;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page <= 0) {
            this.page = 1; // page 값이 음수가 될 경우, 1 을 재할당한다. (1 페이지가 되도록)
        } else {
            this.page = page;
        }
    }

    public int getPerPageNumber() {
        return perPageNumber;
    }

    public void setPerPageNumber(int pageCount) {
        int count = this.perPageNumber; // perPageNumber = 15

        if (pageCount != count) {
            this.perPageNumber = count;
        } else {
            this.perPageNumber = pageCount;
        }
    }
}