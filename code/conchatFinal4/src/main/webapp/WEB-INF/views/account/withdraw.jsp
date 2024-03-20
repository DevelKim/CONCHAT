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

	function doCheckId() {
	    console.log("----------------------------");
	    console.log("-------doCheckId-----------");
	    console.log("----------------------------");
	    return $.ajax({
	        type: "GET",
	        url: "/ehr/user/doCheckId.do",
	        async: true,
	        dataType: "json",
	        data: {
	        	id: document.querySelector("#id").value
	        }
	    });
	}
	
	function doCheckPassword() {
	    console.log("----------------------------");
	    console.log("-------doCheckPassword-----------");
	    console.log("----------------------------");
	    return $.ajax({
	        type: "GET",
	        url: "/ehr/user/doCheckPassword.do",
	        async: true,
	        dataType: "json",
	        data: {
	        	pw: document.querySelector("#pw").value
	        }
	    });
	}

    function withdraw(){
    	console.log('----------------------------');
        console.log('withdraw');
        console.log('----------------------------');
        
        doCheckId()
        .then(function (data) {
            console.log("Id: " + data);
            if (data == 0) {
                alert("존재하지 않는 아이디입니다.");
                return $.Deferred().reject("Invalid Id");
            } else {
                return doCheckPassword();
            }
        })
        .then(function (data) {
            console.log("Password: " + data);
            if (data == 0) {
                alert("비밀번호가 틀렸습니다.");
                return $.Deferred().reject("Invalid Password");
            } else {
                // doFindChatValue 호출 후 성공 시 doMatch 호출
                // return doFindChatValue();
            }
        })
        .then(function (data) {
            console.log("withdraw 로직을 실행합니다.");

            if (eUtil.isEmpty($("#id").val())) {
                alert('아이디를 입력하세요.');
                return $.Deferred().reject("Invalid Input");
            }
            
            if (eUtil.isEmpty($("#pw").val())) {
                alert('비밀번호를 입력하세요.');
                return $.Deferred().reject("Invalid Input");
            }

            // confirm
            if (!confirm("정말 탈퇴하시겠습니까?")) {
                return $.Deferred().reject("User Cancel");
            }
            
            return $.ajax({
                type: "GET",
                url: "/ehr/account/withdraw.do",
                async: true,
                dataType: "json", // 반환 데이터 타입을 json으로 설정
                data: {
                    id: document.querySelector("#id").value
                }
            });
        })
        .done(function (data) {
            console.log("doMatch success data:" + data);
            if (!confirm("정상적으로 탈퇴 되었습니다.")) {
            	moveToMain();
            }
            else moveToMain();
        })
        .fail(function (error) {
            console.log("doUpdate error:" + error);
            // 에러 처리 추가
        });
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
    
    function moveToMain(){
    	console.log("----------------------------");
        console.log("-------moveToMain-----------");
        console.log("----------------------------");
        
        window.location.href = "/ehr/user/moveToMain.do";
    }
    
    
</script>
</head>
<body>
    <div>
        <h2>회원 탈퇴</h2>
        <hr/>
        <div class="p-div"> 
            <form action="#" name="userRegFrm">
                <div class="p-div">
                    <label class="p-label">아이디</label>
                    <input type="text" class="p-input ppl_input" name="id" id="id" placeholder ="아이디 입력" size="20" maxlength="20">
                </div>
                <div class="p-div">
                    <label class="p-label">비밀번호</label>
                    <input type="password" class="p-input ppl_input" name="pw" id="pw" placeholder ="비밀번호 입력" size="20" maxlength="20">
                </div >
            </form>
        </div>
        <div class="p-div">
             <input type="button" class="btn" value="탈퇴하기" id="doSave" onclick="window.withdraw();">
             <input type="button" class="btn" value="돌아가기" id="moveToAccount_setup" onclick="window.moveToAccount_setup()">
        </div>
    </div>
</body>
</html>