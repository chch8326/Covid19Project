package com.choi.covid19.mapper;

import com.choi.covid19.model.InfectionStatusVO;

public interface InfectionStatusMapper {
	
	// 코로나 감염 현황 저장
	public void save(InfectionStatusVO infecStatus);
	
	// 코로나 감염 현황 삭제
	public void remove();
	
	// 코로나 감염 현황 조회
	public InfectionStatusVO view();

}
