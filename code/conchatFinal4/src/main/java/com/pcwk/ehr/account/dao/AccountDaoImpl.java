package com.pcwk.ehr.account.dao;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pcwk.ehr.user.domain.UserVO;

@Repository
public class AccountDaoImpl implements AccountDao {
	
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public AccountDaoImpl() {}
	
	final String NAMESPACE = "com.pcwk.ehr.account";
	final String DOT = ".";

	@Override
	public int changePw(UserVO inVO) throws SQLException {
		int flag = 0;
		String statement = NAMESPACE + DOT + "changePw";
		LOG.debug("1. statement  \n" + statement);
		LOG.debug("2. param \n" + inVO.toString());

		flag = this.sqlSessionTemplate.update(statement, inVO);

		LOG.debug("3. flag: " + flag);

		return flag;
	}

	@Override
	public int withdraw(UserVO inVO) throws SQLException {
		int flag = 0;
		String statement = NAMESPACE + DOT + "withdraw";
		LOG.debug("1. statement  \n" + statement);
		LOG.debug("2. param \n" + inVO.toString());

		flag = this.sqlSessionTemplate.delete(statement, inVO);

		LOG.debug("3. flag: " + flag);

		return flag;
	}

}
