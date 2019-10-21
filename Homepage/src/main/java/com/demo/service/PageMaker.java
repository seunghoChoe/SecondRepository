package com.demo.service;

import com.demo.model.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 페이징 서비스 클래스
 */
public class PageMaker {
    private static final Logger logger = LoggerFactory.getLogger(PageMaker.class);

    private Criteria criteria;

    private int totalCount; // 전체 개수
    private int startPage; // 시작 페이지 번호
    private int endPage; // 끝 페이지 번호

    private boolean previous; // 이전 페이지 버튼의 생성 여부
    private boolean next; // 다음 페이지 버튼의 생성 여부
    private int displayPageNumber = 5; // 출력할 페이지 개수

    /**
     * 페이지 버튼을 생성하기 위한 계산식 메소드
     */
    private void calculateData() {
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNumber) * displayPageNumber);

        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNumber()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        startPage = (endPage - displayPageNumber) + 1;
        if (startPage <= 0) {
            startPage = 1;
        }

        previous = startPage == 1 ? false : true;
        next = endPage * criteria.getPerPageNumber() >= totalCount ? false : true;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.calculateData(); // 전체 개수를 설정할 때 calculateData() 메소드를 호출하여 계산한다.
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrevious() {
        return previous;
    }

    public void setPrevious(boolean previous) {
        this.previous = previous;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getDisplayPageNumber() {
        return displayPageNumber;
    }

    public void setDisplayPageNumber(int displayPageNumber) {
        this.displayPageNumber = displayPageNumber;
    }
}
