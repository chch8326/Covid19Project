package com.choi.covid19.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SidoInfectionStatusVO {

	private int seq; // 게시글 번호(국내 시도별 발생 현황 고유값)
	private String stdDay; // 기준일
	private String gubun; // 시도명
	private int defCnt; // 시도별 오늘 확진자 수
	private int deathCnt; // 시도별 오늘 사망자 수
	private int totalDefCnt; // 누적 확진자 수
	private int totalDeathCnt; // 누적 사망자 수
	private int isolIngCnt; // 격리중인 환자 수
	private int isolClearCnt; // 격리 해제 수
	private int localOccCnt; // 지역 발생 수
	private int overFlowCnt; // 해외 유입 수
	private String createDt; // 등록일
	
}
