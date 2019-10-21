package com.demo.repository;

import com.demo.model.Event;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 행사 레포지토리 클래스
 */
@Repository
public class EventRepository {
    private static final Logger logger = LoggerFactory.getLogger(EventRepository.class);
    private static String namespace = "mappers.EventMapper";

    @Autowired
    private SqlSessionTemplate sql;

    // INSERT 작업                                                                                                      INSERT 작업

    /**
     * @INSERT: 행사 게시글 등록
     */
    public int insertEvent(Event event) {
        return sql.insert(namespace + ".insertEvent", event);
    }

    // SELECT 작업                                                                                                      SELECT 작업

    /**
     * @SELECT: 행사 개수 조회
     */
    public int countAllEvent(Event event) {
        return sql.selectOne(namespace + ".countAllEvent", event);
    }

    /**
     * @SELECT: 행사 목록 조회 (페이징 X)
     */
    public List<Event> selectEventList(Event event) {
        return sql.selectList(namespace + ".selectEventList", event);
    }

    /**
     * @SELECT: 행사 상세 조회
     */
    public Event selectEvent(Event event) {
        return sql.selectOne(namespace + ".selectEvent", event);
    }

    /**
     * @SELECT: 행사 작성자 검사
     */
    public int countEventUserId(int eventId, String loggedUserId) {
        Map<String, Object> map = new HashMap<>();
        map.put("eventId", eventId);
        map.put("loggedUserId", loggedUserId);
        return sql.selectOne(namespace + ".countEventUserId", map);
    }

    // UPDATE 작업                                                                                                      UPDATE 작업

    /**
     * @UPDATE: 행사 수정
     */
    public int updateEvent(Event event) {
        return sql.update(namespace + ".updateEvent", event);
    }

    /**
     * @UPDATE: 행사 마감 수정
     */
    public int updateEventIsEnabled(Event event) {
        return sql.update(namespace + ".updateEventIsEnabled", event);
    }

    /**
     * @UPDATE: 행사 삭제 수정
     */
    public int updateEventIsDeleted(Event event) {
        return sql.update(namespace + ".updateEventIsDeleted", event);
    }

    /**
     * @UPDATE: 행사 조회수 수정
     */
    public int updateEventView(int eventId) {
        return sql.update(namespace + ".updateEventView", eventId);
    }

    /**
     * @UPDATE: 행사 신청자수 수정
     */
    public int updateEventApplyNumber(int eventId) {
        return sql.update(namespace + ".updateEventApplyNumber", eventId);
    }

    // DELETE 작업                                                                                                      DELETE 작업

}