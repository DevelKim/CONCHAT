package com.pcwk.ehr.account.controller;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.account.service.AccountService;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.user.domain.UserVO;

@Controller
@RequestMapping("account")
public class AccountController {

	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	AccountService accountService;
	
	@RequestMapping(value="/moveToAccount_setup.do", method = RequestMethod.GET)
	public String moveToAccount_setup() throws SQLException {
		String view = "account/account_setup";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToAccount_setup                                 │");
		LOG.debug("└───────────────────────────────────────────┘");	
		
		return view;
	}
	
	@RequestMapping(value="/moveToChangePw.do", method = RequestMethod.GET)
	public String moveToChangePw() throws SQLException {
		String view = "account/changePw";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToChangePw                                 │");
		LOG.debug("└───────────────────────────────────────────┘");	
		
		return view;
	}
	
	@RequestMapping(value = "/changePw.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String changePw(UserVO inVO) throws SQLException {
		String jsonString = "";
		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  changePw()   │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");
		
		int flag = this.accountService.changePw(inVO);
		
		String message = "";
		if(flag == 1) message = "매칭이 시작되었습니다.";
		else message = "매칭 실패";
		
		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);
		
		return jsonString;
	}
	
	@RequestMapping(value="/moveToWithdraw.do", method = RequestMethod.GET)
	public String moveToWithdraw() throws SQLException {
		String view = "account/withdraw";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToWithdraw                                 │");
		LOG.debug("└───────────────────────────────────────────┘");	
		
		return view;
	}
	
	@RequestMapping(value="/withdraw.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String withdraw(UserVO inVO) throws SQLException {
		String jsonString = "";
		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  withdraw()   │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");
		
		int flag = this.accountService.withdraw(inVO);
		
		String message = "";
		if(flag == 1) message = "회원 탈퇴 되었습니다.";
		else message = "탈퇴 실패";
		
		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);
		
		return jsonString;
	}
	
}