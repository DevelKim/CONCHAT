package com.pcwk.ehr.user.domain;

public class MatchVO {
	private int no;
	private String id1;
	private String id2;
	private String match_ymd;

	public MatchVO() {
		super();
	}

	public MatchVO(int no, String id1, String id2, String match_ymd) {
		super();
		this.no = no;
		this.id1 = id1;
		this.id2 = id2;
		this.match_ymd = match_ymd;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getMatch_ymd() {
		return match_ymd;
	}

	public void setMatch_ymd(String match_ymd) {
		this.match_ymd = match_ymd;
	}

	@Override
	public String toString() {
		return "MatchVO [no=" + no + ", id1=" + id1 + ", id2=" + id2 + ", match_ymd=" + match_ymd + "]";
	}

}
