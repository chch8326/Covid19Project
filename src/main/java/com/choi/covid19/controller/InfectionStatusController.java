package com.choi.covid19.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.choi.covid19.model.TriageRoomVO;
import com.choi.covid19.service.InfectionStatusService;
import com.choi.covid19.service.SidoInfectionStatusService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/covid19/*")
public class InfectionStatusController {
	
	private InfectionStatusService isService;
	private SidoInfectionStatusService sidoService;
	
	/*
	 * 코로나 감염 현황
	 */
	@GetMapping("/main")
	public void infectionStatusController(Model model) {
		model.addAttribute("item", isService.infecStatusView());
	}
	
	/* 
	 * 시 · 도별 코로나 감염 현황
	 */
	@GetMapping("/cityInfec")
	public void cityInfectionStatusController(Model model) {
		model.addAttribute("itemList", sidoService.sidoInfecStatusView());
	}
	
	/*
	 * 선별 진료소 json 파싱
	 */
	@GetMapping("/map")
	public void mapController(Model model) {
		String url = "https://api.odcloud.kr/api/15077586/v1/centers";
		String perPage = "500";
		String serviceKey = "0OarssUfF93kEVRAJz8lgdKcSCmIIciAWHHfdH%2F8nXKCP37QbJ%2BvYlKiDsZ7Y76pIykyBbBeVdDDCJw9HHVosA%3D%3D";
		String result = "";
		TriageRoomVO trData = null;
		
		try {
			URL connUrl = new URL(url + "?perPage=" + perPage + "&serviceKey=" + serviceKey);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(connUrl.openStream(), "UTF-8"));
			result = reader.readLine();
						
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject)parser.parse(result);
			JSONArray jsonArr = (JSONArray)jsonObj.get("data");
			
			List<TriageRoomVO> dataList = new ArrayList<>();
			
			for(int i = 0; i < jsonArr.size(); i++) {
				trData = new TriageRoomVO();
				JSONObject dataObj = (JSONObject)jsonArr.get(i);
				
				trData.setId(Integer.parseInt(dataObj.get("id").toString())); // 예방접종 센터 고유 식별자
				trData.setCenterName(dataObj.get("centerName").toString()); // 예방접종 센터명
				trData.setSido(dataObj.get("sido").toString()); // 시도명
				trData.setSigungu(dataObj.get("sigungu").toString()); // 시군구명
				trData.setFacilityName(dataObj.get("facilityName").toString()); // 시설명
				trData.setZipCode(dataObj.get("zipCode").toString()); // 우편번호
				trData.setAddress(dataObj.get("address").toString()); // 주소
				trData.setLat(dataObj.get("lat").toString()); // 위도
				trData.setLng(dataObj.get("lng").toString()); // 경도
				trData.setCenterType(dataObj.get("centerType").toString()); // 예방접종 센터 유형
				trData.setOrg(dataObj.get("org").toString()); // 운영기관
				trData.setPhoneNumber(dataObj.get("phoneNumber").toString()); // 예방접종 센터 전화번호
				dataList.add(trData);
			}
			
			model.addAttribute("dataList", dataList);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
