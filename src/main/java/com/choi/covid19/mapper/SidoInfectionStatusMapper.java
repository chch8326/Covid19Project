package com.choi.covid19.mapper;

import java.util.List;
import com.choi.covid19.model.SidoInfectionStatusVO;

public interface SidoInfectionStatusMapper {
	
	// 시도별 코로나 감염 현황 저장
	public void save(List<SidoInfectionStatusVO> sidoInfecStatusList);

	// 시도별 코로나 감염 현황 삭제
	public void remove();
	
	// 시도별 코로나 감염 현황 조회
	public List<SidoInfectionStatusVO> view();
	
}
