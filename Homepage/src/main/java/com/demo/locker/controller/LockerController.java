package com.demo.locker.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.demo.locker.dto.LockerDTO;
import com.demo.locker.service.LockerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.global.utils.UserAuth;

/**
 *  사물함신청 등의 기능을 구현하는 컨트롤러 클래스 
 */
@Controller("lockerController")
@RequestMapping(value = "/locker/*")
public class LockerController {
	
	/**
	 *  Log출력을 위한 Logger객체 
	 */
	private static final Logger logger = LoggerFactory.getLogger(LockerController.class);

	/**
	 *  Locker의 로직을 수행하는 LockerService 객체 
	 */
	@Inject
	private LockerService lockerService;

	/**
	 *  GET Request : 사물함 기능 목차 페이지  
	 */
	@UserAuth(role = UserAuth.Roles.GUEST)
	@GetMapping(value = "/index")
	public void getLockerIndex() throws Exception {
		logger.info("getLockerIndex()");
	}

	/**
	 *  GET Request : 사물함 목록 페이지  
	 */
	@UserAuth(role = UserAuth.Roles.GUEST)
	@GetMapping(value = "/lockerList")
	public void getLockerList(Model model) throws Exception {
		logger.info("getLockerList()");
		model.addAttribute("lockerList", lockerService.getLockerList());
	}

	/**
	 *  GET Request : 사물함 신청을 위한 목록 페이지  
	 */
	@UserAuth(role = UserAuth.Roles.USER)
	@GetMapping(value = "/lockerApplyList")
	public void getLockerApplyList(Model model) throws Exception {
		logger.info("getLockerApplyList()");
		model.addAttribute("lockerList", lockerService.getLockerList());
	}

	/**
	 *  POST Request : 사물함 신청 Form에서 신청 버튼을 누르면 요청되는 메소드   
	 */
	@UserAuth(role = UserAuth.Roles.USER)
	@RequestMapping(value = "/LockerApplyForm", method = RequestMethod.POST)
	public String postLockerApplyForm(HttpServletRequest request, RedirectAttributes rttr, LockerDTO locker) throws Exception {
		long fee = lockerService.calculateFee(locker.getFinishDate(), locker.getStartDate());
		request.getSession().setAttribute("appliedLocker", locker);

		// 이미 사용중인 사물함이 없는 경우
		if (!lockerService.isUsingLocker(locker.getUserId())) {
			rttr.addFlashAttribute("fee", fee);
			return "redirect:/locker/payFee";

			// 이미 사용중인 사물함이 있는 경우
		} else {
			rttr.addFlashAttribute("failMessage", "You have already had a Locker");
			return "redirect:/locker/lockerApplyList";
		}
	}

	@UserAuth(role = UserAuth.Roles.USER)
	@GetMapping(value = "/payFee")
	public String getPayFee() throws Exception {
		logger.info("getPayFee()");
		return null;
	}

	@UserAuth(role = UserAuth.Roles.USER)
	@GetMapping(value = "/payFeeCompleted")
	public String getPayFeeCompleted(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("getPayFeeCompleted()");
		LockerDTO appliedLocker = (LockerDTO) request.getSession().getAttribute("appliedLocker");
		request.getSession().removeAttribute("appliedLocker");
		lockerService.applyLocker(appliedLocker);
		rttr.addFlashAttribute("successMessage", "Success to Apply Locker Num" + appliedLocker.getId());
		return "redirect:/locker/lockerApplyList";
	}
	
	@UserAuth(role = UserAuth.Roles.USER)
	@GetMapping(value = "/lockerExtend")
	public String getlockerExtend() throws Exception {
		logger.info("getLockerExtend()");

		return null;
	}
}
