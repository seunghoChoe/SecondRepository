package com.demo.service;

import com.demo.model.EventApply;
import com.demo.repository.EventApplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 행사 신청 서비스 클래스 (개발 완료 시, 인터페이스 클래스를 생성하도록 한다.)
 */
@Service
public class EventApplyServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private EventApplyRepository eventApplyRepository;

    /**
     * @INSERT: 행사 신청자 등록
     */
    public int insertEventApply(EventApply eventApply) {
        return eventApplyRepository.insertEventApply(eventApply);
    }

    /**
     * @SELECT: 행사 신청자 목록 조회
     */
    public List<EventApply> selectEventApplyList(String applyEventId) {
        return eventApplyRepository.selectEventApplyList(applyEventId);
    }

    /**
     * @SELECT: 행사 신청자 상세 조회
     */
    public EventApply selectEventApply(EventApply eventApply) {
        return eventApplyRepository.selectEventApply(eventApply);
    }

    /**
     * @DELETE: 행사 신청자 삭제
     */
    public int deleteEventApply(EventApply eventApply) {
        return eventApplyRepository.deleteEventApply(eventApply);
    }
}