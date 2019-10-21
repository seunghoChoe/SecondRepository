package com.demo.controller;

import com.demo.model.Event;
import com.demo.service.EventApplyServiceImpl;
import com.demo.service.EventServiceImpl;
import com.demo.utility.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import static com.demo.utility.Constants.SERVER_MESSAGE;
import static com.demo.utility.UserSession.getUserSession;

/**
 * @Description: 행사 및 행사 신청 컨트롤러 클래스 (GET/POST 메소드만 사용한다.)
 */
@Controller
@RequestMapping("/event/*")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private EventApplyServiceImpl eventApplyService;

    /**
      * @COMMON: 행사 작성자와 로그인 사용자 ID 비교 (동일할 경우, True)
      */
    private boolean eventUserIdCheck(int eventId, HttpServletRequest request) {
        return eventService.countEventUserId(eventId, getUserSession(request).getUserId());
    }

    /**
     * @GET: 행사 목록 조회 (페이징 X, 검색 X) (테스트 통과)
     */
    @UserRole(type = UserRole.Type.GUEST)
    @GetMapping("/eventList")
    public String eventListGET(Model model) {
        logger.info("eventListGET()");

        model.addAttribute("eventList", eventService.selectEventList(true, false));

        return "event/eventList";
    }

    /**
     * @GET: 행사 상세 조회
     */
    @UserRole(type = UserRole.Type.GUEST)
    @GetMapping("/event")
    public String eventGET(@RequestParam int eventId, Model model) {
        logger.info("eventGET()");

        model.addAttribute("event", eventService.selectEvent(eventId, true, false));
        eventService.updateEventView(eventId); // 행사 조회수 수정

        return "event/event";
    }

    /**
     * @GET: 행사 등록 폼 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @GetMapping("/newEvent")
    public String newEventGET(Model model) {
        logger.info("newEventGET()");

        model.addAttribute("event", new Event());

        return "event/newEvent";
    }

    /**
     * @POST: 행사 등록 (테스트 통과)
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping("/newEvent")
    public String newEventPOST(@ModelAttribute("event") @Valid Event event, BindingResult result, HttpServletRequest request, RedirectAttributes rttr) {
        logger.info("newEventPOST()");

        if (result.hasErrors()) { // 유효성 검증 실패
            logger.info(result.toString());
            return "event/newEvent";

        } else {
            event.setEventUserId(getUserSession(request).getUserId());
            eventService.insertEvent(event);
            rttr.addFlashAttribute(SERVER_MESSAGE, "행사가 등록되었습니다.");
            return "redirect:/event/eventList";
        }
    }

    /**
     * @GET: 행사 수정 폼
     */
    @UserRole(type = UserRole.Type.USER)
    @GetMapping("/eventEdit")
    public String eventEditGET(@RequestParam int eventId, Model model, RedirectAttributes rttr, HttpServletRequest request) {
        logger.info("eventEditGET()");

        if (! eventUserIdCheck(eventId, request)) {
            // URI 접속 시, 수정/삭제가 가능하므로 작성자와 로그인 사용자를 비교해야 한다.
            rttr.addFlashAttribute(SERVER_MESSAGE, "행사 작성자만 수정할 수 있습니다.");
            return "redirect:/event/event?eventId=" + eventId;
        }

        model.addAttribute("event", eventService.selectEvent(eventId, true, false));

        return "event/eventEdit";
    }

    /**
     * @POST: 행사 수정
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping("/eventEdit")
    public String eventEditPOST(@ModelAttribute("event") @Valid Event event, BindingResult result, RedirectAttributes rttr, HttpServletRequest request) {
        logger.info("eventEditPOST()");

        if (! eventUserIdCheck(event.getEventId(), request)) {
            rttr.addFlashAttribute(SERVER_MESSAGE, "행사 작성자만 수정할 수 있습니다.");
            return "redirect:/event/event?eventId=" + event.getEventId();

        } else {
            if (result.hasErrors()) { // 유효성 검증 실패
                logger.info(result.toString());
                return "event/eventEdit";

            } else {
                event.setEventUserId(getUserSession(request).getUserId());
                eventService.updateEvent(event);
                rttr.addFlashAttribute(SERVER_MESSAGE, "행사가 수정되었습니다.");
                return "redirect:/event/event?eventId=" + event.getEventId();
            }
        }
    }

    /**
     * @POST: 행사 삭제 수정
     */
    @UserRole(type = UserRole.Type.USER)
    @PostMapping("/eventDelete")
    public @ResponseBody int eventDeletePOST(@RequestParam int eventId, HttpServletRequest request) {
        logger.info("eventDeletePOST()");

        if (! eventUserIdCheck(eventId, request)) { // 작성자/로그인 사용자 불일치
            return 0;
        } else {
            eventService.updateEventIsDeleted(eventId, getUserSession(request).getUserId(), true);
            return 1;
        }
    }





    /**
     * @GET: 행사 신청 폼
     */

    /**
     * @POST: 행사 신청
     */

    /**
     * @POST: 행사 신청 삭제
     */









}