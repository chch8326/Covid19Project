package com.choi.covid19.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TriageRoomVO {
	
	private int id; // 예방접종 센터 고유 식별자
	private String centerName; // 예방접종 센터명
	private String sido; // 시도명
	private String sigungu; // 시군구명
	private String facilityName; // 시설명
	private String zipCode; // 우편번호
	private String address; // 주소
	private String lat; // 위도
	private String lng; // 경도
	private String centerType; // 예방 접종 센터 유형
	private String org; // 운영기관
	private String phoneNumber; // // 예방접종 센터 전화번호

}
