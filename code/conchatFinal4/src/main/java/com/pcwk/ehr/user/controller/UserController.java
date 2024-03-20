package com.pcwk.ehr.user.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.user.domain.MatchVO;
import com.pcwk.ehr.user.domain.UserVO;
import com.pcwk.ehr.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/doCompleteMatch.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String doCompleteMatch(MatchVO inVO) throws Exception {
		
		String jsonString;

		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doCompleteMatch()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");

		int flag = userService.doCompleteMatch(inVO);
		
		String message = "";

		if (flag == 1)
			message = "매칭 진행 중";
		else
			message = "매칭 진행 실패";

		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);

		return jsonString;
	}
	
	@RequestMapping(value="/doFinishMatch.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String doFinishMatch(UserVO inVO) throws Exception {
		
		String jsonString;

		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doFinishMatch()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");

		int flag = userService.doFinishMatch(inVO);
		
		String message = "";

		if (flag == 1)
			message = "매칭 시도 종료";
		else
			message = "매칭 시도 종료 실패";

		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);

		return jsonString;
	}
	
	@RequestMapping(value="/doFindChatValue.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Integer doFindChatValue(UserVO inVO) throws SQLException {
		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doFindChatValue()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");
		
		Integer flag = userService.doFindChatValue(inVO);

		return flag;
	}
	
	@RequestMapping(value="/moveToVal_match.do", method = RequestMethod.GET)
	public String moveToVal_match() throws SQLException {
		String view = "user/val_match";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToVal_match                                 │");
		LOG.debug("└───────────────────────────────────────────┘");	
		
		return view;
	}
	
	@RequestMapping(value="/moveToLolche_match.do", method = RequestMethod.GET)
	public String moveToLolche_match() throws SQLException {
		String view = "user/lolche_match";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToLolche_match                                 │");
		LOG.debug("└───────────────────────────────────────────┘");	
		
		return view;
	}
	
	@RequestMapping(value="/moveToLol_match.do", method = RequestMethod.GET)
	//http://localhost:8080/ehr/user/moveToMain.do
	public String moveToLol_match() throws SQLException {
		String view = "user/lol_match";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToLol_match                                 │");
		LOG.debug("└───────────────────────────────────────────┘");	
		
		return view;
	}
	
	@RequestMapping(value="/moveToMain.do", method = RequestMethod.GET)
	// http://localhost:8080/ehr/user/moveToMain.do
	public String moveToMain() throws SQLException {
		String view = "user/main";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToMain                                 │");
		LOG.debug("└───────────────────────────────────────────┘");	
		
		return view;
	}
	
	@RequestMapping(value="/moveToReg.do", method = RequestMethod.GET)
	public String moveToReg() throws SQLException {
		String view = "user/user_reg";
		LOG.debug("┌───────────────────────────────────────────┐");
		LOG.debug("│ moveToReg                                 │");
		LOG.debug("└───────────────────────────────────────────┘");	
		
		return view;
	}
	
	// 매칭
	@RequestMapping(value = "/doMatch.do", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
	public String doMatch(UserVO inVO) throws SQLException {
		String jsonString;
		
		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doMatch()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");
		
		String flag = userService.doMatch(inVO);
		String message = "";

		if (flag != null)
			message = "매칭 되었습니다.";
		else
			message = "매칭 실패";

//		MessageVO messageVO = new MessageVO(flag + "님과", message);
//		jsonString = new Gson().toJson(messageVO);
//		LOG.debug("jsonString: " + jsonString);
//
//		return jsonString;
		return flag;
	}
	
	// 목록 조회
	// http://localhost:8080/ehr/user/doRetrieve.do?searchDiv=10&searchWord=점심시간
	@RequestMapping(value = "/doRetrieve.do", method = RequestMethod.GET)
	public String doRetrieve(UserVO searchVO, HttpServletRequest req, Model model) throws SQLException {
		String view = "user/user_list";
		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doRetrieve()   │ DTO: " + searchVO);
		LOG.debug("└───────────────────┘");
		
		String searchDiv = req.getParameter("searchDiv");
		String searchWord = req.getParameter("searchWord");
		
		LOG.debug("searchDiv: " + searchDiv);
		LOG.debug("searchWord: " + searchWord);
		
		//브라우저에서 숫자 : 문자로 들어 온다.
				//페이지 사이즈: null -> 10
				//페이지 번호: null -> 1
				String pageSize   = StringUtil.nvl(req.getParameter("pageSize"),"10");
				String pageNo     = StringUtil.nvl(req.getParameter("pageNo"),"1");
				
				long tPageNo      = Long.parseLong(pageNo);
				long tPageSize    = Long.parseLong(pageSize);
				
				if(0==tPageNo) {
					searchVO.setPageNo(1);
				}else {
					searchVO.setPageNo(tPageNo);
				}
				
				if(0 == tPageSize ) {
					searchVO.setPageSize(10);
				}else {
					searchVO.setPageSize(tPageSize);
				}
				
				
				LOG.debug("pageSize:"+searchVO.getPageSize());	
				LOG.debug("pageNo:"+searchVO.getPageNo());
				
				LOG.debug("searchDiv:"+searchDiv);	
				LOG.debug("searchWord:"+searchWord);
				
				
				LOG.debug("searchVO:"+searchVO);
				
				List<UserVO>  list = this.userService.doRetrieve(searchVO);
				
				//화면에 데이터 전달
				model.addAttribute("list", list);
				//검색조건
				model.addAttribute("searchVO", searchVO);
		
		return view;
	}
	
	// 수정
	@RequestMapping(value = "/doUpdate.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(UserVO inVO) throws SQLException {
		String jsonString = "";
		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doUpdate()   │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");
		
		int flag = this.userService.doUpdate(inVO);
		
		String message = "";
		if(flag == 1) message = "매칭이 시작되었습니다.";
		else message = "매칭 실패";
		
		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);
		
		return jsonString;
	}

	// 조회
	@RequestMapping(value = "/doSelectOne.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectOne(UserVO inVO, HttpServletRequest req) throws SQLException, EmptyResultDataAccessException {
		String jsonString = "";
		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doSelectOne()   │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");
		
		String userId = req.getParameter("userId");
		LOG.debug("userId: " + userId);
		
		UserVO outVO = this.userService.doSelectOne(inVO);
		LOG.debug("outVO: " + outVO);
		
		String message = "";
		if(outVO != null) {
			message = inVO.getId() + "가 조회 되었습니다.";
			jsonString = new Gson().toJson(outVO);
		}
		else {
			message = inVO.getId() + "조회 실패";
			jsonString = new Gson().toJson(new MessageVO("0", message));
		}
		
		LOG.debug("jsonString: " + jsonString);

		return jsonString;
	}

	// 삭제
	@RequestMapping(value = "/doDelete.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(UserVO inVO, HttpServletRequest req) throws SQLException {

		String jsonString = "";
		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doDelete()   │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");

		String userId = req.getParameter("userId");
		LOG.debug("userId: " + userId);

		int flag = userService.doDelete(inVO);
		String message = "";

		if (flag == 1)
			message = inVO.getId() + "가 삭제 되었습니다.";
		else
			message = inVO.getId() + "삭제 실패";

		MessageVO messageVO = new MessageVO(String.valueOf(flag), message);
		jsonString = new Gson().toJson(messageVO);

		LOG.debug("jsonString: " + jsonString);

		return jsonString;
	}

	// 등록
	@RequestMapping(value = "/doSave.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
	public String doSave(UserVO inVO) throws SQLException {
		String jsonString = "";

		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doSave()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");

		int flag = userService.doSave(inVO);
		String message = "";

		if (flag == 1)
			message = "회원가입 되었습니다";
		else
			message = inVO.getId() + "등록 실패";

		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);

		return jsonString;
	}
	
	// id 검사
	@RequestMapping(value = "/doCheckId.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
	public int doCheckId(UserVO inVO) throws SQLException {
		
		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doCheckId()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");
		
		int flag = userService.doCheckId(inVO);

		return flag;
	}
	
	// email 검사
	@RequestMapping(value = "/doCheckEmail.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
	public int doCheckEmail(UserVO inVO) throws SQLException {
		
		LOG.debug("┌───────────────────┐");
		LOG.debug("┃  doCheckEmail()     │ inVO: " + inVO);
		LOG.debug("└───────────────────┘");
		
		int flag = userService.doCheckEmail(inVO);

		return flag;
	}
	
	// password 검사
		@RequestMapping(value = "/doCheckPassword.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@ResponseBody // HTTP 요청 부분의 body 부분이 그대로 브라우저에 전달
		public int doCheckPassword(UserVO inVO) throws SQLException {
			
			LOG.debug("┌───────────────────┐");
			LOG.debug("┃  doCheckPassword()     │ inVO: " + inVO);
			LOG.debug("└───────────────────┘");
			
			int flag = userService.doCheckPassword(inVO);

			return flag;
		}
}
