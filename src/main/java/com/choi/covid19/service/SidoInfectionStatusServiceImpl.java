package com.choi.covid19.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.choi.covid19.mapper.SidoInfectionStatusMapper;
import com.choi.covid19.model.SidoInfectionStatusVO;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
public class SidoInfectionStatusServiceImpl implements SidoInfectionStatusService {
	
	@Setter(onMethod_ = @Autowired)
	private SidoInfectionStatusMapper mapper;

	@Override
	public void sidoInfecStatusSave(List<SidoInfectionStatusVO> sidoInfecStatusList) {
		mapper.save(sidoInfecStatusList);
	}

	@Override
	public void sidoInfecStatusRemove() {
		mapper.remove();
	}

	@Override
	public List<SidoInfectionStatusVO> sidoInfecStatusView() {
		return mapper.view();
	}

	
	
}
