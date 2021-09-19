package com.choi.covid19.service;

import com.choi.covid19.model.InfectionStatusVO;

public interface InfectionStatusService {
	
	// 코로나 감염 현황 저장
	public void infecStatusSave(InfectionStatusVO infecStatus);
	
	// 코로나 감염 현황 삭제
	public void infecStatusRemove();
	
	// 코로나 감염 현황 조회
	public InfectionStatusVO infecStatusView();
	
}
