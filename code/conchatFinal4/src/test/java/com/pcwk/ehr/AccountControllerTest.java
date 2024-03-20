package com.pcwk.ehr;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
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
import com.pcwk.ehr.user.domain.UserVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountControllerTest {
	
	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;

	// 브라우저 대역
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	public void changePw(UserVO inVO) throws Exception {
		LOG.debug("======================================");
		LOG.debug("changePw()");
		String result = "";
		
		// MockHttpServletRequestBuilder: param 데이터 저장
		// MockMvc: 호출
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/changePw.do")
				.param("pw", inVO.getPw())
				.param("id", inVO.getId());

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());

		result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("##########################");
		LOG.debug(result);
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("messageVO: " + messageVO);
	}
	
	public void withdraw(UserVO inVO) throws Exception {
		LOG.debug("======================================");
		LOG.debug("withdraw()");
		String result = "";
		
		// MockHttpServletRequestBuilder: param 데이터 저장
		// MockMvc: 호출
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/withdraw.do")
				.param("id", inVO.getId());

		ResultActions resultActions = this.mockMvc.perform(requestBuilder).andExpect(status().isOk());

		result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		LOG.debug("##########################");
		LOG.debug(result);
		
		MessageVO messageVO = new Gson().fromJson(result, MessageVO.class);
		assertEquals(String.valueOf(1), messageVO.getMsgId());
		LOG.debug("messageVO: " + messageVO);
	}

}
