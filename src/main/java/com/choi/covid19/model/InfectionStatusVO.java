package com.choi.covid19.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InfectionStatusVO {

	private int seq; // 게시글 번호(감염현황 고유값)
	private String stateDt; // 기준일
	private int decideCnt; // 오늘 확진자 수
	private int deathCnt; // 오늘 사망자 수
	private int totalDecideCnt; // 확진자 수
	private int totalDeathCnt; // 사망자 수
	private double accDefRate; // 누적 확진률
	private int careCnt; // 치료중인 환자 수
	private int clearCnt; // 격리해제 수
	private int examCnt; // 검사진행 수
	private int accExamCnt; // 누적 검사 수
	private int accExamCompCnt; // 누적 검사 완료 수
	private int resutlNegCnt; // 결과 음성 수
	private String createDt; // 등록일
	
}
