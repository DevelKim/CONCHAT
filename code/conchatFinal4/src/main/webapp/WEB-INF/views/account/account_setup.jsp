<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset = "UTF-8">
<title>CONCHAT</title>
</head>
<body>
    <h2>계정 관리하기</h2>
    <hr/>
    <input type = "button" class = "btn" value = "비밀번호 변경" id = "singUp" onclick = "moveToChangePw();">
    <input type = "button" class = "btn" value = "회원 탈퇴" id = "singUp" onclick = "moveToWithdraw()">
    <hr/>
    <input type = "button" class = "btn" value = "돌아가기" id = "singUp" onclick = "moveToMain()">
    <hr/>
</body>
<script type = "text/javascript">

    function moveToChangePw(){
        console.log('----------------------------');
        console.log('changePw');
        console.log('----------------------------');  
        
        // 적절한 방법으로 폼을 가져와야 합니다. 여기서는 document에 직접 접근합니다.
        let frm = document.createElement("form");
        frm.method = "GET"; // 또는 "GET", 필요에 따라 변경할 수 있습니다.
        frm.action = "/ehr/account/moveToChangePw.do";

        document.body.appendChild(frm);
        frm.submit();
    }
    
    function moveToWithdraw(){
        console.log('----------------------------');
        console.log('withdraw');
        console.log('----------------------------');  
        
        // 적절한 방법으로 폼을 가져와야 합니다. 여기서는 document에 직접 접근합니다.
        let frm = document.createElement("form");
        frm.method = "GET"; // 또는 "GET", 필요에 따라 변경할 수 있습니다.
        frm.action = "/ehr/account/moveToWithdraw.do";

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
</html>