package com.demo.locker;

import java.util.Vector;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.demo.model.LockerDTO;

@Repository("LockerDAO")
public class LockerDAO {
	private static String namespace = "mappers.LockerMapper";

    @Inject
    private SqlSession sql;
    
	public Vector<LockerDTO> getLockerList() {
		Vector<LockerDTO> lockerList = new Vector<LockerDTO>();
		for(Object locker : sql.selectList(namespace + ".getLockerList")) {
			lockerList.add((LockerDTO)locker);
		}
		return lockerList;
	}

	public void applyLocker(LockerDTO locker) {
		sql.update(namespace + ".applyLocker", locker);
	}

	public LockerDTO getUsingLocker(String userId) {
		return (LockerDTO) sql.selectOne(namespace + ".getUsingLocker", userId);
	}

}
