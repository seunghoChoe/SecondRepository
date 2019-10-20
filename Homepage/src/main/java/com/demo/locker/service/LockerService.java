package com.demo.locker.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.inject.Inject;

import com.demo.locker.dao.LockerDAO;
import com.demo.locker.dto.LockerDTO;
import org.springframework.stereotype.Service;

@Service("LockerService")
public class LockerService {
	
	@Inject
	private LockerDAO lockerDAO;

	public Vector<LockerDTO> getLockerList() {
		return lockerDAO.getLockerList();
	}

	public void applyLocker(LockerDTO locker) {
		lockerDAO.applyLocker(locker);
	}
	
	public long calculateFee(String finishDate, String startDate) {
		long fee = 0;
		try {
			// Calculate Fee
			DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			Date dFinishDate = format.parse(finishDate);
			Date dStartDate = format.parse(startDate);
			long dTime = dFinishDate.getTime() - dStartDate.getTime();
			long dDate = Math.abs(dTime / (24 * 60 * 60 * 1000));
			fee = (int) (dDate * 100);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fee;
	}

	public boolean isUsingLocker(String userId) {
		LockerDTO usingLocker = lockerDAO.getUsingLocker(userId);
		if(usingLocker != null) {
			System.out.println("service finishdate : " + usingLocker.getFinishDate());
			return true;
		}else {
			return false;
		}
		
	}

}
