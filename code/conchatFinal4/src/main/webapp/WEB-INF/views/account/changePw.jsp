<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">                                       
<link rel="stylesheet"  href="/ehr/resources/css/user.css">
<style>
    .p-label{
        display:block;
        margin-bottom : 5px;
        font-weight:bold;
    }
    .p-input{
        width:300px;
        height:26.5px;
        vertical-align: middle;
        font-size: 12px;
        border: 1px solid #ccc;
        border-radius:4px;
    }
    
    .p-div{
        margin-bottom : 5px;
        border:1px solid #ccc;
    }
    
</style>
<title>conchat</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtill.js"></script>
<script >

    function changePw(){
    	
    }
    
    function moveToAccount_setup(){
    	console.log('----------------------------');
        console.log('moveToAccount_setup');
        console.log('----------------------------'); 
        
        let frm = document.createElement("form");
        frm.method = "GET"; // 또는 "GET", 필요에 따라 변경할 수 있습니다.
        frm.action = "/ehr/account/moveToAccount_setup.do";

        document.body.appendChild(frm);
        frm.submit();
    }
    
    
</script>
</head>
<body>
    <div>
        <h2>비밀번호 변경</h2>
        <hr/>
        <div class="p-div"> 
            <form action="#" name="userRegFrm">
                <div class="p-div">
                    <label class="p-label">아이디</label>
                    <input type="text" class="p-input ppl_input" name="id" id="id" placeholder ="아이디 입력" size="20" maxlength="20">
                </div>
                <div class="p-div">
                    <label class="p-label">현재 비밀번호</label>
                    <input type="password" class="p-input ppl_input" name="pw" id="pw" placeholder ="현재 비밀번호 입력" size="20" maxlength="20">
                </div >
                <div class="p-div">
                    <label class="p-label">변경할 비밀번호</label>
                    <input type="password" class="p-input ppl_input" name="pw" id="pw" placeholder ="변경할 비밀번호 입력" size="20" maxlength="20">
                </div >
            </form>
        </div>
        <div class="p-div">
             <input type="button" class="btn" value="변경하기" id="doSave"     onclick="window.doUpdate();">
             <input type="button" class="btn" value="돌아가기" id="moveToAccount_setup" onclick="window.moveToAccount_setup()">
        </div>
    </div>
</body>
</html>