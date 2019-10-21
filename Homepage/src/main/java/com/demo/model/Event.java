package com.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 행사 모델 클래스
 * @Memo: http://wonwoo.ml/index.php/post/520
 */
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private int eventId; // 행사 게시글 번호
    private String eventUserId; // 행사 게시글 작성자

    @Size(min = 1, max = 20, message = "행사 종류는 1~20자 이내로 작성해야 합니다.")
    private String eventType; // 행사 종류
    @Size(min = 1, max = 100, message = "행사 제목은 1~100자 이내로 작성해야 합니다.")
    private String eventTitle; // 행사 제목
    @Size(min = 1, max = 2500, message = "행사 내용은 1~2500자 이내로 작성해야 합니다.")
    private String eventContent; // 행사 내용

    private Date eventCreatedAt; // 행사 생성 일자
    private Date eventUpdatedAt; // 행사 수정 일자
    private int eventView; // 행사 조회수

    @Size(min = 1, max = 50, message = "행사 주최사는 1~50자 이내로 작성해야 합니다.")
    private String eventOrganizer; // 행사 주최사
    @Size(min = 1, max = 50, message = "행사 주최사 연락처는 1~50자 이내로 작성해야 합니다.")
    private String eventContact; // 행사 주최사 연락처

    @NotNull(message = "행사 시작 일자를 작성해야 합니다.")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") // MySQL(TIMESTAMP)
    private Date eventStartDate; // 행사 기간(시작 일자)
    @NotNull(message = "행사 종료 일자를 작성해야 합니다.")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") // MySQL(TIMESTAMP)
    private Date eventEndDate; // 행사 기간(종료 일자)

    @Size(min = 1, max = 50, message = "행사 장소는 1~50자 이내로 작성해야 합니다.")
    private String eventPlace; // 행사 장소

    @Min(value = 1, message = "행사 제한 인원은 최소 1명 이상으로 작성해야 합니다.")
    @Max(value = 1000, message = "행사 제한 인원은 최대 1000명 이내로 작성해야 합니다.")
    private int eventLimitNumber; // 행사 제한 인원
    private int eventApplyNumber; // 행사 신청 인원

    private boolean eventIsEnabled; // 행사 활성(마감) 여부(0: 활성X, 1: 활성O)
    private boolean eventIsDeleted; // 행사 삭제 여부(0: 삭제X, 1: 삭제O)

    public Event() { }

    public Event(int eventId, String eventUserId,
                 String eventType, String eventTitle, String eventContent,
                 Date eventCreatedAt, Date eventUpdatedAt, int eventView,
                 String eventOrganizer, String eventContact,
                 Date eventStartDate, Date eventEndDate, String eventPlace,
                 int eventLimitNumber, int eventApplyNumber,
                 boolean eventIsEnabled, boolean eventIsDeleted) {
        this.eventId = eventId;
        this.eventUserId = eventUserId;
        this.eventType = eventType;
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
        this.eventCreatedAt = eventCreatedAt;
        this.eventUpdatedAt = eventUpdatedAt;
        this.eventView = eventView;
        this.eventOrganizer = eventOrganizer;
        this.eventContact = eventContact;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventPlace = eventPlace;
        this.eventLimitNumber = eventLimitNumber;
        this.eventApplyNumber = eventApplyNumber;
        this.eventIsEnabled = eventIsEnabled;
        this.eventIsDeleted = eventIsDeleted;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventUserId() {
        return eventUserId;
    }

    public void setEventUserId(String eventUserId) {
        this.eventUserId = eventUserId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public Date getEventCreatedAt() {
        return eventCreatedAt;
    }

    public void setEventCreatedAt(Date eventCreatedAt) {
        this.eventCreatedAt = eventCreatedAt;
    }

    public Date getEventUpdatedAt() {
        return eventUpdatedAt;
    }

    public void setEventUpdatedAt(Date eventUpdatedAt) {
        this.eventUpdatedAt = eventUpdatedAt;
    }

    public int getEventView() {
        return eventView;
    }

    public void setEventView(int eventView) {
        this.eventView = eventView;
    }

    public String getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(String eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    public String getEventContact() {
        return eventContact;
    }

    public void setEventContact(String eventContact) {
        this.eventContact = eventContact;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public int getEventLimitNumber() {
        return eventLimitNumber;
    }

    public void setEventLimitNumber(int eventLimitNumber) {
        this.eventLimitNumber = eventLimitNumber;
    }

    public int getEventApplyNumber() {
        return eventApplyNumber;
    }

    public void setEventApplyNumber(int eventApplyNumber) {
        this.eventApplyNumber = eventApplyNumber;
    }

    public boolean getEventIsEnabled() {
        return eventIsEnabled;
    }

    public void setEventIsEnabled(boolean eventIsEnabled) {
        this.eventIsEnabled = eventIsEnabled;
    }

    public boolean getEventIsDeleted() {
        return eventIsDeleted;
    }

    public void setEventIsDeleted(boolean eventIsDeleted) {
        this.eventIsDeleted = eventIsDeleted;
    }
}