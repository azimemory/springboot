package com.kh.bookmanager.common.code;

public enum MemberGrade {

	ME00("일반",1),
	ME01("성실",2),
	ME02("우수",3),
	ME03("ADMIN",999);
	
	public final String desc;
	public final int extendableCnt;
	
	private MemberGrade(String desc, int extendableCnt) {
		this.desc = desc;
		this.extendableCnt = extendableCnt;
	}
}
