package com.pcwk.ehr.account.dao;

import java.sql.SQLException;

import com.pcwk.ehr.user.domain.UserVO;

public interface AccountDao {
	
	int changePw(UserVO inVO) throws SQLException;

	int withdraw(UserVO inVO) throws SQLException;
}
