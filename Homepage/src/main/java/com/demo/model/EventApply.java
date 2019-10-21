package com.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 행사 신청자 모델 클래스
 */
public class EventApply implements Serializable {
    private static final long serialVersionUID = 1L;

    private int eventApplyId; // 행사 신청 번호
    private int eventApplyEventId; // 행사 게시글 번호
    private String eventApplyUserId; // 행사 신청자
    private Date eventApplyCreatedAt; // 행사 신청 일자

    public EventApply() { }

    public EventApply(int eventApplyId, int eventApplyEventId, String eventApplyUserId, Date eventApplyCreatedAt) {
        this.eventApplyId = eventApplyId;
        this.eventApplyEventId = eventApplyEventId;
        this.eventApplyUserId = eventApplyUserId;
        this.eventApplyCreatedAt = eventApplyCreatedAt;
    }

    public int getEventApplyId() {
        return eventApplyId;
    }

    public void setEventApplyId(int eventApplyId) {
        this.eventApplyId = eventApplyId;
    }

    public int getEventApplyEventId() {
        return eventApplyEventId;
    }

    public void setEventApplyEventId(int eventApplyEventId) {
        this.eventApplyEventId = eventApplyEventId;
    }

    public String getEventApplyUserId() {
        return eventApplyUserId;
    }

    public void setEventApplyUserId(String eventApplyUserId) {
        this.eventApplyUserId = eventApplyUserId;
    }

    public Date getEventApplyCreatedAt() {
        return eventApplyCreatedAt;
    }

    public void setEventApplyCreatedAt(Date eventApplyCreatedAt) {
        this.eventApplyCreatedAt = eventApplyCreatedAt;
    }
}