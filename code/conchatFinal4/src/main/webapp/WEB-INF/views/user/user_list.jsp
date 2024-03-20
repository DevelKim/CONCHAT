<%@page import="com.pcwk.ehr.user.domain.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
    UserVO dto = (UserVO)request.getAttribute("searchVO");

%>  
<!DOCTYPE html>
<html>
<head>
<style> 
    table, th, td {
      border:1px solid #ccc;
      border-collapse: collapse;
    }
  
    th, td {padding:10px 20px;}
    
    
    /* 정렬 */
    .txt-left{
        text-align: left;
    }
    
    .txt-center{
        text-align: center;
    }

    .txt-right{
        text-align: right;
    }
    
    
    .select {
        height: 30px;    
        vertical-align: middle;
        font-size: 12px;    
    }    
    .btn{
        height: 30px;    
        vertical-align: middle;
        font-size: 12px;
        background-color:skyblue;
        color:#ffffff;      
    }
    
    .input {
        height: 26.5px;
        vertical-align: middle;
        font-size: 12px;
    }
                
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtil.js"></script>

</head>
<body>
  <h2>회원관리</h2>
  <hr/>
    
   <div>
    <form action="/ehr/user/doRetrieve.do" method="get" name="userFrm">
           <input type="hidden" name="pageNo" >
        <div>
            <!-- 검색구분 -->
            <select name="searchDiv" id="searchDiv" class="select">
              <option value="">전체</option>
              <option value="10" <% if("10".equals(dto.getSearchDiv())){out.print("selected");} %>  >사용자ID</option>
              <option value="20" <% if("20".equals(dto.getSearchDiv())){out.print("selected");} %>  >이름</option>
              <option value="30" <% if("30".equals(dto.getSearchDiv())){out.print("selected");} %>  >이메일</option>
            </select>
            <!-- 검색어 -->
            <input type="text"  class="input" value="${searchVO.searchWord }" name="searchWord" id="searchWord" placeholder="검색어를 입력하세요">
            <!-- pageSize: 10,20,30,50,10,200 -->
            <select name="pageSize" id="pageSize"  class="select">
               <option value="10"   <% if(10L==dto.getPageSize()){out.print("selected");} %>  >10</option>                 
               <option value="20"   <% if(20L==dto.getPageSize()){out.print("selected");} %>  >20</option>
               <option value="30"   <% if(30L==dto.getPageSize()){out.print("selected");} %>  >30</option>
               <option value="50"   <% if(50L==dto.getPageSize()){out.print("selected");} %>  >50</option>
               <option value="100"  <% if(100L==dto.getPageSize()){out.print("selected");} %> >100</option>
               <option value="200"  <% if(200L==dto.getPageSize()){out.print("selected");} %> >200</option>
            </select>    
            <!-- button -->
            <input type="button" class="btn" value="조회" id="doRetrieve" onclick="window.doRetrieve(1);">
            <input type="button" class="btn" value="등록" id="moveToReg"  onclick="window.moveToReg();">
        </div>
    </form>
    
    <!-- table -->
    <table id="userTable">
        <thead>
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>생년월일</th>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>이메일</th>
            <th>등록일</th>
            <th>음성채팅 여부</th>
            <th>적극성</th>
            <th>티어</th>
            <th>클릭</th>
            <th>포트</th>
            <th>팝스코어</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vo" items="${list}">
            <tr>
                <td class="txt-center">${vo.no}</td>
                <td class="txt-left">${vo.nm}</td>
                <td class="txt-left">${vo.birth }</td>
                <td class="txt-left">${vo.id }</td>
                <td class="txt-left">${vo.pw }</td>
                <td class="txt-right">${vo.email}</td>
                <td class="txt-right">${vo.reg_ymd }</td>   
                <td class="txt-center">${vo.chat }</td>         
                <td class="txt-center">${vo.act }</td>
                <td class="txt-center">${vo.tier }</td>
                <td class="txt-center">${vo.click }</td>
                <td class="txt-center">${vo.port }</td>
                <td class="txt-center">${vo.pop_scr }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!--// table -------------------------------------------------------------->
   </div>
 <script type="text/javascript">
    
   //jquery:table 데이터 선택     
   $("#userTable>tbody").on("click","tr" , function(e){
       console.log('----------------------------');
       console.log('userTable>tbody');
       console.log('----------------------------');    
       
       let tdArray = $(this).children();//td
       
       let userId = tdArray.eq(1).text();
       console.log('userId:'+userId);
       
       window.location.href ="/ehr/user/doSelectOne.do?userId="+userId;
       
   });
    
    function moveToReg(){
        console.log('----------------------------');
        console.log('moveToReg');
        console.log('----------------------------');  
        
        let frm = document.userFrm;
        frm.action = "/ehr/user/moveToReg.do";
        frm.submit();
        
       //window.location.href= '/ehr/user/moveToReg.do';
      
    }
    
    function  doRetrieve(pageNo){
        console.log('----------------------------');
        console.log('doRetrieve');
        console.log('----------------------------');
        
        let frm = document.forms['userFrm'];//form
        let pageSize = frm.pageSize.value;
        console.log('pageSize:'+pageSize);
        
        let searchDiv = frm.searchDiv.value;
        console.log('searchDiv:'+searchDiv);
        
        let searchWord = frm.searchWord.value;
        console.log('searchWord:'+searchWord);
        
        console.log('pageNo:'+pageNo);
        frm.pageNo.value = pageNo;
        
        console.log('pageNo:'+frm.pageNo.value);
        //pageNo
        //서버 전송
        frm.submit();
    }
</script>  
</body>
</html>