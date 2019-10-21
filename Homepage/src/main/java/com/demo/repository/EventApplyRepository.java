package com.demo.repository;

import com.demo.model.EventApply;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Description: 행사 신청 레포지토리 클래스
 */
@Repository
public class EventApplyRepository {
    private static final Logger logger = LoggerFactory.getLogger(EventApplyRepository.class);
    private static String namespace = "mappers.EventApplyMapper";

    @Autowired
    private SqlSessionTemplate sql;

    // INSERT 작업                                                                                                      INSERT 작업

    /**
     * @INSERT: 행사 신청자 등록
     */
    public int insertEventApply(EventApply eventApply) {
        return sql.insert(namespace + ".insertEventApply", eventApply);
    }

    // SELECT 작업                                                                                                      SELECT 작업

    /**
     * @SELECT: 행사 신청자 목록 조회
     */
    public List<EventApply> selectEventApplyList(String applyEventId) {
        return sql.selectList(namespace + ".selectEventApplyList", applyEventId);
    }

    /**
     * @SELECT: 행사 신청자 상세 조회
     */
    public EventApply selectEventApply(EventApply eventApply) {
        return sql.selectOne(namespace + ".selectEventApply", eventApply);
    }

    // UPDATE 작업                                                                                                      UPDATE 작업
    // DELETE 작업                                                                                                      DELETE 작업

    /**
     * @DELETE: 행사 신청자 삭제
     */
    public int deleteEventApply(EventApply eventApply) {
        return sql.delete(namespace + ".deleteEventApply", eventApply);
    }
}