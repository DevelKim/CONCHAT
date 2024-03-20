package com.pcwk.ehr.account.service;

import java.sql.SQLException;

import com.pcwk.ehr.user.domain.UserVO;

public interface AccountService {

	public int changePw(UserVO inVO) throws SQLException;
	
	public int withdraw(UserVO inVO) throws SQLException;
	
}
