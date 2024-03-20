<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset = "UTF-8">
<title>CONCHAT</title>
</head>
<body>
    <h2>콘챗 메인화면</h2>
    <input type = "button" class = "btn" value = "회원 가입" id = "singUp" onclick = "moveToReg();">
    <hr/>
    <h2>매칭 시작하기</h2>
    <input type = "button" class = "btn" value = "리그 오브 레전드" id = "lol" onclick = "moveToLol_match();">
    <input type = "button" class = "btn" value = "롤토체스" id = "lolche" onclick = "moveToLolche_match();">
    <input type = "button" class = "btn" value = "발로란트" id = "balo" onclick = "moveToVal_match();">
    <hr/>
    <h2>계정 관리</h2>
    <input type = "button" class = "btn" value = "계정 관리 " id = "singUp" onclick = "moveToAccount_setup();">
</body>
<script type = "text/javascript">
    function moveToReg(){
        console.log('----------------------------');
        console.log('moveToReg');
        console.log('----------------------------');  
        
        // 적절한 방법으로 폼을 가져와야 합니다. 여기서는 document에 직접 접근합니다.
        let frm = document.createElement("form");
        frm.method = "GET"; // 또는 "GET", 필요에 따라 변경할 수 있습니다.
        frm.action = "/ehr/user/moveToReg.do";

        document.body.appendChild(frm);
        frm.submit();
    }
    
    function moveToLol_match(){
        console.log('----------------------------');
        console.log('moveToLol_match');
        console.log('----------------------------');  
        
        // 적절한 방법으로 폼을 가져와야 합니다. 여기서는 document에 직접 접근합니다.
        let frm = document.createElement("form");
        frm.method = "GET"; // 또는 "GET", 필요에 따라 변경할 수 있습니다.
        frm.action = "/ehr/user/moveToLol_match.do";

        document.body.appendChild(frm);
        frm.submit();
    }
    
    function moveToLolche_match(){
        console.log('----------------------------');
        console.log('moveToLolche_match');
        console.log('----------------------------');  
        
        // 적절한 방법으로 폼을 가져와야 합니다. 여기서는 document에 직접 접근합니다.
        let frm = document.createElement("form");
        frm.method = "GET"; // 또는 "GET", 필요에 따라 변경할 수 있습니다.
        frm.action = "/ehr/user/moveToLolche_match.do";

        document.body.appendChild(frm);
        frm.submit();
    }
    
    function moveToVal_match(){
        console.log('----------------------------');
        console.log('moveToVal_match');
        console.log('----------------------------');  
        
        // 적절한 방법으로 폼을 가져와야 합니다. 여기서는 document에 직접 접근합니다.
        let frm = document.createElement("form");
        frm.method = "GET"; // 또는 "GET", 필요에 따라 변경할 수 있습니다.
        frm.action = "/ehr/user/moveToVal_match.do";

        document.body.appendChild(frm);
        frm.submit();
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
</html>