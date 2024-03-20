//package com.pcwk.ehr.match;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import com.pcwk.ehr.user.domain.UserVO;
//
//public class UserMatching {
//
//    private static final long TIMEOUT_MILLIS = 30000; // 최대 30초간 탐색
//    public static List<UserVO> clickedUser = new ArrayList<>();
//
//    public static String doMatch(UserVO inVO) throws SQLException {
//    	String id = inVO.getId();
//        int tier = inVO.getTier(); // 필수 조건 (티어)
//        int chat = inVO.getChat(); // 채팅
//        int act = inVO.getAct(); // 적극성
//
//        // 서버로부터 불규칙적으로 제공되는 조건 비교 대상 리스트
////        List<UserVO> clickedUser = new ArrayList<>();
////        clickedUser.add("대상1");
////        clickedUser.add("대상2");
////        clickedUser.add("대상3");
//
//        String matchedUser = checkConditions(id, tier, chat, act, clickedUser);
//        
//        
//        
//        return matchedUser;
//    }
//
//    public static String checkConditions(String id, int tier, int chat, int act,
//                                         List<UserVO> clickedUser) {
//        String twoSatisfiedUser = "";
//        List<String> oneSatisfiedUserList = new ArrayList<>();
//        Set<String> comparedUserList = new HashSet<>();
//
//        int index = 0;
//        long startTime = System.currentTimeMillis();
//
//        // 서버로부터 제공된 대상들과 조건을 비교
//        while (System.currentTimeMillis() - startTime <= TIMEOUT_MILLIS || oneSatisfiedUserList.size() != 10) {
//        	
//        	String matchedId;
//        	int matchedTier;
//        	int matchedChat;
//        	int matchedAct;
//        	
//			try {
//				matchedId = clickedUser.get(index).getId();
//				matchedTier = clickedUser.get(index).getTier();
//				matchedChat = clickedUser.get(index).getChat();
//				matchedAct = clickedUser.get(index).getAct();
//			} catch(IndexOutOfBoundsException e){
//				continue;
//			}
//
//            // 이미 비교한 대상은 무시
//            if (comparedUserList.contains(matchedId) || id.equals(matchedId)) {
//                index++;
//                continue;
//            }
//
//            // 필수 조건을 만족하는 경우에만 나머지 두 조건을 검사
//            if (Math.abs(tier - matchedTier) <= 1) {
//                if ((chat == matchedChat) && (act == matchedAct)) {	// 모든 조건을 만족할 경우 탐색 종료
//                	twoSatisfiedUser = matchedId;
//					break;
//                } 
//                else if ((chat == matchedChat) || (act == matchedAct)) {
//                    oneSatisfiedUserList.add(matchedId);
//                    
//                    // 대상이 추가되었으므로 인덱스를 증가시키고 비교 대상에 추가
//		            index++;
//		            comparedUserList.add(matchedId);
//                }
//            }
//        }  // while
//
//        String tmp = "";
//        // 10명이 채워지지 않았을 경우, 리스트의 마지막 대상과 묶어줌
//        if(!twoSatisfiedUser.equals("")) {
//        	tmp = twoSatisfiedUser;
//        }
//        else {
//			tmp = oneSatisfiedUserList.get(oneSatisfiedUserList.size() - 1);		// 마지막으로 추가된 대상으로 매칭
//		}
//        
//        return tmp;
//    }
//}