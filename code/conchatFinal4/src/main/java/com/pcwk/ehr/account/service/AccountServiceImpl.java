package com.pcwk.ehr.account.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.account.dao.AccountDao;
import com.pcwk.ehr.user.domain.UserVO;

@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
	public AccountServiceImpl() {}

	@Override
	public int changePw(UserVO inVO) throws SQLException {
		return accountDao.changePw(inVO);
	}

	@Override
	public int withdraw(UserVO inVO) throws SQLException {
		return accountDao.withdraw(inVO);
	}
	
	

}
