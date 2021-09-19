package com.choi.covid19.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.choi.covid19.mapper.InfectionStatusMapper;
import com.choi.covid19.model.InfectionStatusVO;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
public class InfectionStatusServiceImpl implements InfectionStatusService {

	@Setter(onMethod_ = @Autowired)
	private InfectionStatusMapper mapper;
	
	@Override
	public void infecStatusSave(InfectionStatusVO infecStatus) {
		mapper.save(infecStatus);
	}

	@Override
	public void infecStatusRemove() {
		mapper.remove();
	}

	@Override
	public InfectionStatusVO infecStatusView() {
		return mapper.view();
	}

}
