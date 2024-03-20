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
<title>전략적 팀 전투 매칭</title>
<script src="/ehr/resources/js/jquery-3.7.1.js"></script>
<script src="/ehr/resources/js/eUtill.js"></script>
<script >

	var matchedId;
	
	function doCompleteMatch(){
		console.log("----------------------------");
        console.log("-------doCompleteMatch-------");
        console.log("----------------------------");
        
        $.ajax({
            type: "POST",
            url: "/ehr/user/doCompleteMatch.do",
            async: true,
            dataType: "json",
            data: {
                id1: document.querySelector("#id").value,
                id2: matchedId
            }
        });
	}
	
	function doFinishMatch(){
		console.log("------------------------------");
        console.log("-------doFinishMatch-------");
        console.log("------------------------------");
        
        $.ajax({
            type: "POST",
            url: "/ehr/user/doFinishMatch.do",
            async: true,
            dataType: "json",
            data: {
                id: document.querySelector("#id").value
            }
        });
	}
	
	function moveToMain(){
        console.log("----------------------------");
        console.log("-------moveToMain-----------");
        console.log("----------------------------");
        
        window.location.href = "/ehr/user/moveToMain.do";
    }
	
	function doSaveMatch(){
		console.log("------------------------------------");
        console.log("-------doFindChatValue-----------");
        console.log("-------------------------------------");
        
        
	}
	
	function doFindChatValue() {
	    console.log("------------------------------------");
	    console.log("-------doFindChatValue-----------");
	    console.log("-------------------------------------");

	    $.ajax({
	        type: "GET",
	        url: "/ehr/user/doFindChatValue.do",
	        async: true,
	        dataType: "json",
	        data: {
	        	id: document.querySelector("#id").value
	        }
	    });
	}
	
	function doMatch() {
	    console.log("----------------------------");
	    console.log("-------doMatch-----------");
	    console.log("----------------------------");

	    return $.ajax({
	        type: "GET",
	        url: "/ehr/user/doMatch.do",
	        async: true,
	        dataType: "html",
	        data: {
	            tier: $("#tier").val(),
	            chat: $("#chat").val(),
	            act: $("#act").val(),
	            click: 2,
	            id: $("#id").val()
	        },
	        success:function(data){//통신 성공
                console.log("success data:"+data);
                if(data != ""){
                    alert(data + "님과 매칭되었습니다.");
                    matchedId = data;
                    console.log("matchedID: " + matchedId);
                    doCompleteMatch();
                    doFinishMatch();
                    moveToMain();
                }else{
                    alert("매칭된 유저가 없습니다. 다시 시도해주세요.");
                }
            },
            error:function(data){//실패시 처리
                console.log("error:"+data);
            },
            complete:function(data){//성공/실패와 관계없이 수행!
            	moveToMain();
            }
	    });
	}
    
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

    function doUpdate() {
        console.log("----------------------------");
        console.log("----------doUpdate------------");
        console.log("----------------------------");

        // 중복 체크 수행
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
                console.log("doUpdate 로직을 실행합니다.");

                // 나머지 doUpdate 로직을 여기에 추가
                // ...

                if (eUtil.isEmpty($("#id").val()) || eUtil.isEmpty($("#pw").val()) || eUtil.isEmpty($("#tier").val())) {
                    alert('아이디, 비밀번호, 티어를 입력하세요.');
                    return $.Deferred().reject("Invalid Input");
                }

                // confirm
                if (!confirm("매칭 시작")) {
                    return $.Deferred().reject("User Cancel");
                }
                
                return $.ajax({
                    type: "POST",
                    url: "/ehr/user/doUpdate.do",
                    async: true,
                    dataType: "json", // 반환 데이터 타입을 json으로 설정
                    data: {
                        tier: document.querySelector("#tier").value,
                        chat: document.querySelector("#chat").value,
                        act: document.querySelector("#act").value,
                        click: 2,
                        id: document.querySelector("#id").value
                    }
                });
            })
            .done(function (data) {
                console.log("doMatch success data:" + data);
                return doMatch();
            })
            .fail(function (error) {
                console.log("doUpdate error:" + error);
                // 에러 처리 추가
            });
    }
</script>
</head>
<body>
    <div>
        <h2>전략적 팀 전투 매칭</h2>
        <hr/>
        <div class="p-div">
             <input type="button" class="btn" value="매칭 시작" id="doSave"     onclick="window.doUpdate();">
             <input type="button" class="btn" value="돌아가기" id="moveToList" onclick="window.moveToMain()">
        </div>
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
                <div class="p-div">
                    <label class="p-label">티어 선택</label>
                    <select name ="tier" id="tier">
                        <option value="1">아이언</option>
                        <option value="2">브론즈</option>
                        <option value="3">실버</option>
                        <option value="4">골드</option>
                        <option value="5">플래티넘</option>
                        <option value="6">에메랄드</option>
                        <option value="7">다이아몬드</option>
                    </select>
                </div>
                <div class="p-div">
                    <label class="p-label">음성채팅 여부</label>
                    <select name ="chat" id="chat">
                        <option value="1">사용</option>
                        <option value="2">미사용</option>
                    </select>
                </div>
                <div class="p-div">
                    <label class="p-label">적극성 여부</label>
                    <select name ="act" id="act">
                        <option value="1">적극적</option>
                        <option value="2">소극적</option>
                    </select>
                </div>
            </form>
        </div>
    </div>
</body>
</html>