package com.demo.service;

import com.demo.model.Event;
import com.demo.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 행사 서비스 클래스 (개발 완료 시, 인터페이스 클래스를 생성하도록 한다.)
 */
@Service
public class EventServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private EventRepository eventRepository;

    /**
     * @INSERT: 행사 등록
     */
    public int insertEvent(Event event) {
        return eventRepository.insertEvent(event);
    }

    /**
     * @SELECT: 행사 개수 조회
     */
    public int countAllEvent(Event event) {
        return eventRepository.countAllEvent(event);
    }

    /**
     * @SELECT: 행사 목록 조회 (페이징 X)
     */
    public List<Event> selectEventList(boolean eventIsEnabled, boolean eventIsDeleted) {
        Event event = new Event(); // 향후 생성자 추가로 단순화하기
        event.setEventIsEnabled(eventIsEnabled);
        event.setEventIsDeleted(eventIsDeleted);
        return eventRepository.selectEventList(event);
    }

    /**
     * @SELECT: 행사 상세 조회
     */
    public Event selectEvent(int eventId, boolean eventIsEnabled, boolean eventIsDeleted) {
        Event event = new Event(); // 향후 생성자 추가로 단순화하기
        event.setEventId(eventId);
        event.setEventIsEnabled(eventIsEnabled);
        event.setEventIsDeleted(eventIsDeleted);
        return eventRepository.selectEvent(event);
    }

    /**
     * @SELECT: 행사 작성자 검사
     */
    public boolean countEventUserId(int eventId, String loggedUserId) {
        // 동일할 경우, DB 에서 1을 반환한다. 이 값을 1과 비교하여 boolean 값을 반환한다.
        return eventRepository.countEventUserId(eventId, loggedUserId) == 1;
    }

    /**
     * @UPDATE: 행사 수정
     */
    public int updateEvent(Event event) {
        return eventRepository.updateEvent(event);
    }

    /**
     * @UPDATE: 행사 마감 수정
     */
    public int updateEventIsEnabled(Event event) {
        return eventRepository.updateEventIsEnabled(event);
    }

    /**
     * @UPDATE: 행사 삭제 수정
     */
    public int updateEventIsDeleted(int eventId, String loggedUserId, boolean eventIsDeleted) {
        Event event = new Event(); // 향후 생성자 추가로 단순화하기
        event.setEventId(eventId);
        event.setEventUserId(loggedUserId);
        event.setEventIsDeleted(eventIsDeleted);
        return eventRepository.updateEventIsDeleted(event);
    }

    /**
     * @UPDATE: 행사 조회수 수정
     */
    public int updateEventView(int eventId) {
        return eventRepository.updateEventView(eventId);
    }

    /**
     * @UPDATE: 행사 신청자수 수정
     */
    public int updateEventApplyNumber(int eventId) {
        return eventRepository.updateEventApplyNumber(eventId);
    }
}