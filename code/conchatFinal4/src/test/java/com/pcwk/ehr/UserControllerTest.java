package com.pcwk.ehr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.user.domain.MatchVO;
import com.pcwk.ehr.user.domain.UserVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;

	// 브라우저 대역
	MockMvc mockMvc;

	List<UserVO> users;

	UserVO searchVO;

	@Before
	public void setUp() throws Exception {
		LOG.debug("======================================");
		LOG.debug("setUp()");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	public void doCompleteMatch(MatchVO inVO) throws Exception {
		LOG.debug("======================================");
		LOG.debug("doCompleteMatch()");
		String result = "";
		
		// MockHttpServletRequestBuilder: param 데이터 저장
		// MockMvc: 호출
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/doCompleteMatch.do")
				.param("id1", inVO.getId1())
				.param("id2", inVO.getId2());

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());

		result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("##########################");
		LOG.debug(result);
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("messageVO: " + messageVO);
	}
	
	public void doFinishMatch(UserVO inVO) throws Exception {
		LOG.debug("======================================");
		LOG.debug("doFinishMatch()");
		String result = "";
		
		// MockHttpServletRequestBuilder: param 데이터 저장
		// MockMvc: 호출
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/doFinishMatch.do")
				.param("id", inVO.getId());

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());

		result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("##########################");
		LOG.debug(result);
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("messageVO: " + messageVO);
	}
	
	public Integer doFindChatValue(UserVO inVO) throws Exception {
		Integer result = 0;
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doFindChatValue.do")
				.param("id", inVO.getId());

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		
		try {
		    String contentAsString = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		    // 문자열 앞뒤의 공백을 제거한 후 정수로 변환
		    result = Integer.parseInt(contentAsString.trim());
		} catch (NumberFormatException e) {}
		catch(IllegalArgumentException e) {}
		
		LOG.debug("findValue: " + result);
		
		return result;
	}

    public void doMatch(UserVO inVO) throws Exception {
    	String id = inVO.getId();
        int tier = inVO.getTier(); // 필수 조건 (티어)
        int chat = inVO.getChat(); // 채팅
        int act = inVO.getAct(); // 적극성
        
        String result = "";
        
    	MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/doMatch.do")
			.param("tier", inVO.getTier() + "")
			.param("chat", inVO.getChat() + "")
			.param("act", inVO.getAct() + "")
			.param("click", inVO.getClick() + "")
			.param("id", inVO.getId());

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());

		result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("##########################");
		LOG.debug(result);
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("messageVO: " + messageVO);
      }
	
	public void doUpdate(UserVO inVO) throws Exception {
		LOG.debug("======================================");
		LOG.debug("doUpdate()");
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/doUpdate.do")
				.param("tier", inVO.getTier() + "").param("click", inVO.getClick() + "").param("id", inVO.getId() + "");
		
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());

		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("##########################");
		LOG.debug(result);
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("messageVO: " + messageVO);
	}
	
	public UserVO doSelectOne(UserVO inVO) throws Exception {
		LOG.debug("======================================");
		LOG.debug("doSelectOne()");
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doSelectOne.do")
				.param("id", inVO.getId());
		
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("##########################");
		LOG.debug(result);
		
		UserVO outVO = new Gson().fromJson(result, UserVO.class);
		LOG.debug("##########################");
		LOG.debug("outVO: " + outVO);
		assertNotNull(outVO);
		
		return outVO;
	}
	
	public void doDelete(UserVO inVO) throws Exception {
		LOG.debug("======================================");
		LOG.debug("doDelete()");
		
		// URL + 호출방식(get) + param(userId)
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doDelete.do")
				.param("id", inVO.getId());
		
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		LOG.debug("##########################");
		LOG.debug(result);
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
//		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("messageVO: " + messageVO);
	}
	
	public int doCheckPassword(UserVO inVO) throws Exception {
		int result = 3;
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doCheckPassword.do")
				.param("pw", inVO.getPw());
		
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		
		try {
			result = Integer.parseInt(resultActions.andDo(print()).andReturn().getResponse().getContentAsString());
		} catch (NumberFormatException e) {}
		
		return result;
	}
	
	public int doCheckEmail(UserVO inVO) throws Exception {
		int result = 3;
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doCheckEmail.do")
				.param("email", inVO.getEmail());
		
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());

		try {
			result = Integer.parseInt(resultActions.andDo(print()).andReturn().getResponse().getContentAsString());
		} catch (NumberFormatException e) {}
		
		return result;
	}
	
	public int doCheckId(UserVO inVO) throws Exception {
		int result = 3;
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doCheckId.do")
				.param("id", inVO.getId());
		
		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());
		
		try {
			result = Integer.parseInt(resultActions.andDo(print()).andReturn().getResponse().getContentAsString());
		} catch (NumberFormatException e) {}
		
		return result;
	}

	public void doSave(UserVO inVO) throws Exception {
		LOG.debug("======================================");
		LOG.debug("doSave()");
		String result = "";
		
		// MockHttpServletRequestBuilder: param 데이터 저장
		// MockMvc: 호출
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/doSave.do")
				.param("nm", inVO.getNm())
				.param("birth", inVO.getBirth())
				.param("id", inVO.getId())
				.param("pw", inVO.getPw())
				.param("email", inVO.getEmail())
				.param("chat", inVO.getChat() + "")
				.param("act", inVO.getAct() + "");

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());

		result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("##########################");
		LOG.debug(result);
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("messageVO: " + messageVO);
	}
		
	

	@Test
	public void beans() {
		LOG.debug("======================================");
		LOG.debug("beans()");
		LOG.debug("webApplicationContext: " + webApplicationContext);
		LOG.debug("mockMvc: " + mockMvc);

		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
	}

}
