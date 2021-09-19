package com.choi.covid19.service;

import java.util.List;
import com.choi.covid19.model.SidoInfectionStatusVO;

public interface SidoInfectionStatusService {
	
	// 시도별 코로나 감염 현황 저장
	public void sidoInfecStatusSave(List<SidoInfectionStatusVO> sidoInfecStatusList);
	
	// 시도별 코로나 감염 현황 삭제
	public void sidoInfecStatusRemove();
	
	// 시도별 코로나 감염 현황 조회
	public List<SidoInfectionStatusVO> sidoInfecStatusView();

}
