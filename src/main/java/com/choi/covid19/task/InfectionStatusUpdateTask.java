package com.choi.covid19.task;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.choi.covid19.model.InfectionStatusVO;
import com.choi.covid19.model.SidoInfectionStatusVO;
import com.choi.covid19.service.InfectionStatusService;
import com.choi.covid19.service.SidoInfectionStatusService;
import com.choi.covid19.util.XmlToJson;
import lombok.Setter;

@Component
public class InfectionStatusUpdateTask {
	
	@Setter(onMethod_ = @Autowired)
	private InfectionStatusService isService;
	
	@Setter(onMethod_ = @Autowired)
	private SidoInfectionStatusService sidoService;
	
	/*
	 * 오전 10시, 오후 5시, 오후 8시에 데이터 업데이트
	 * 오전 10시까지 데이터베이스에 저장된 데이터는 어제 자 데이터이므로 어제 자 데이터를 삭제하고 오늘 자 데이터를 저장
	 */
	@Scheduled(cron = "0 0 10,15,20 * * *")
	public void infecStatusUpdate() {
		String url = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson";
		String serviceKey = "0OarssUfF93kEVRAJz8lgdKcSCmIIciAWHHfdH%2F8nXKCP37QbJ%2BvYlKiDsZ7Y76pIykyBbBeVdDDCJw9HHVosA%3D%3D";
		InfectionStatusVO item = null;
		
		try {
			// 데이터베이스에 저장된 데이터 삭제
			isService.infecStatusRemove();
			
			// xml을 json으로 변환
			String todayJsonData = XmlToJson.getJsonData(url, serviceKey);
			String yesterdayJsonData = XmlToJson.getYesterdayJsonData(url, serviceKey);
			
			JSONParser parser = new JSONParser();
			
			// 오늘 자 json 데이터 파싱 
			JSONObject jsonObj = (JSONObject)parser.parse(todayJsonData);
			JSONObject resObj = (JSONObject)jsonObj.get("response");
			JSONObject bodyObj = (JSONObject)resObj.get("body");
			JSONObject itemsObj = (JSONObject)bodyObj.get("items");
			JSONObject itemObj = (JSONObject)itemsObj.get("item");
			
			// 어제 자 json 데이터 파싱
			JSONObject yestJsonObj = (JSONObject)parser.parse(yesterdayJsonData);
			JSONObject yestResObj = (JSONObject)yestJsonObj.get("response");
			JSONObject yestBodyObj = (JSONObject)yestResObj.get("body");
			JSONObject yestItemsObj = (JSONObject)yestBodyObj.get("items");
			JSONObject yestItemObj = (JSONObject)yestItemsObj.get("item");
			
			item = new InfectionStatusVO();
			double accDefRate = Double.parseDouble(itemObj.get("accDefRate").toString());
			int yestDecideCnt = Integer.parseInt(yestItemObj.get("decideCnt").toString());
			int yestDeathCnt = Integer.parseInt(yestItemObj.get("deathCnt").toString());
			
			item.setSeq(Integer.parseInt(itemObj.get("seq").toString())); // 게시글 번호
			item.setStateDt(itemObj.get("stateDt").toString()); // 기준일
			item.setTotalDecideCnt(Integer.parseInt(itemObj.get("decideCnt").toString())); // 확진자 수 
			item.setTotalDeathCnt(Integer.parseInt(itemObj.get("deathCnt").toString())); // 사망자 수
			item.setAccDefRate(Double.parseDouble(String.format("%.3f", accDefRate))); // 누적 확진률
			item.setCareCnt(Integer.parseInt(itemObj.get("careCnt").toString())); // 치료중인 환자 수
			item.setClearCnt(Integer.parseInt(itemObj.get("clearCnt").toString())); // 격리 해제 수
			item.setExamCnt(Integer.parseInt(itemObj.get("examCnt").toString())); // 검사 진행 수
			item.setAccExamCnt(Integer.parseInt(itemObj.get("accExamCnt").toString())); // 누적 검사 수
			item.setAccExamCompCnt(Integer.parseInt(itemObj.get("accExamCompCnt").toString())); // 누적 검사 완료 수
			item.setResutlNegCnt(Integer.parseInt(itemObj.get("resutlNegCnt").toString())); // 결과 음성 수
			item.setCreateDt(itemObj.get("createDt").toString()); // 등록일
			
			int todayDecideCnt = item.getTotalDecideCnt();
			int todayDeathCnt = item.getTotalDeathCnt();
			int decideCnt = todayDecideCnt - yestDecideCnt; // 오늘 자 확진자 수
			int deathCnt = todayDeathCnt - yestDeathCnt; // 오늘 자 사망자 수
			item.setDecideCnt(decideCnt);
			item.setDeathCnt(deathCnt);
			
			// 오늘 자 코로나 감염 현황 데이터베이스에 저장
			isService.infecStatusSave(item);
		} catch(Exception e) {
			e.printStackTrace();
		} // end try
	} // end infecStatusUpdate
	
	/*
	 * 오전 10시, 오후 5시, 오후 8시에 데이터 업데이트
	 * 오전 10시까지 데이터베이스에 저장된 데이터는 어제 자 데이터이므로 어제 자 데이터를 삭제하고 오늘 자 데이터를 저장
	 */
	@Scheduled(cron = "0 0 10,15,20 * * *")
	public void sidoInfecStatusUpdate() {
		String url = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson";
		String serviceKey = "0OarssUfF93kEVRAJz8lgdKcSCmIIciAWHHfdH%2F8nXKCP37QbJ%2BvYlKiDsZ7Y76pIykyBbBeVdDDCJw9HHVosA%3D%3D";
		SidoInfectionStatusVO item = null;
		
		try {
			// 데이터베이스에 저장된 데이터 삭제
			sidoService.sidoInfecStatusRemove();
			
			// xml을 json으로 변환
			String todayJsonData = XmlToJson.getJsonData(url, serviceKey);
			String yesterdayJsonData = XmlToJson.getYesterdayJsonData(url, serviceKey);
			
			JSONParser parser = new JSONParser();
			
			// 오늘 자 json 데이터 파싱
			JSONObject jsonObj = (JSONObject)parser.parse(todayJsonData);
			JSONObject resObj = (JSONObject)jsonObj.get("response");
			JSONObject bodyObj = (JSONObject)resObj.get("body");
			JSONObject itemsObj = (JSONObject)bodyObj.get("items");
			JSONArray jsonArr = (JSONArray)itemsObj.get("item");
			
			// 어제 자 json 데이터 파싱
			JSONObject yestJsonObj = (JSONObject)parser.parse(yesterdayJsonData);
			JSONObject yestResObj = (JSONObject)yestJsonObj.get("response");
			JSONObject yestBodyObj = (JSONObject)yestResObj.get("body");
			JSONObject yestItemsObj = (JSONObject)yestBodyObj.get("items");
			JSONArray yestJsonArr = (JSONArray)yestItemsObj.get("item");
			
			List<SidoInfectionStatusVO> itemList = new ArrayList<>();
			
			for(int i = 0; i < jsonArr.size(); i++) {
				item = new SidoInfectionStatusVO();
				JSONObject itemObj = (JSONObject)jsonArr.get(i);
				JSONObject yestItemObj = (JSONObject)yestJsonArr.get(i);
				
				int todayTotalDefCnt = Integer.parseInt(itemObj.get("defCnt").toString());
				int todayTotalDeathCnt = Integer.parseInt(itemObj.get("deathCnt").toString());
				int yestTotalDefCnt = Integer.parseInt(yestItemObj.get("defCnt").toString());
				int yestTotalDeathCnt = Integer.parseInt(yestItemObj.get("deathCnt").toString());
				int defCnt = todayTotalDefCnt - yestTotalDefCnt;
				int deathCnt = todayTotalDeathCnt - yestTotalDeathCnt;
				
				item.setSeq(Integer.parseInt(itemObj.get("seq").toString())); // 게시글 번호
				item.setStdDay(itemObj.get("stdDay").toString()); // 기준일
				item.setGubun(itemObj.get("gubun").toString()); // 시도명
				item.setDefCnt(defCnt); // 오늘 확진자 수
				item.setDeathCnt(deathCnt); // 오늘 사망자 수
				item.setTotalDefCnt(todayTotalDefCnt); // 누적 확진자 수
				item.setTotalDeathCnt(todayTotalDeathCnt); // 누적 사망자 수
				item.setIsolIngCnt(Integer.parseInt(itemObj.get("isolIngCnt").toString())); // 격리 중인 환자 수
				item.setIsolClearCnt(Integer.parseInt(itemObj.get("isolClearCnt").toString())); // 격리 해제 수
				item.setLocalOccCnt(Integer.parseInt(itemObj.get("localOccCnt").toString())); // 지역 발생 수
				item.setOverFlowCnt(Integer.parseInt(itemObj.get("overFlowCnt").toString())); // 해외 유입 수
				item.setCreateDt(itemObj.get("createDt").toString()); // 등록일
				itemList.add(item);
			} // end for
			
			// 오늘 자 시도별 코로나 감염 현황 데이터베이스에 저장
			sidoService.sidoInfecStatusSave(itemList);
		} catch(Exception e) {
			e.printStackTrace();
		} // end try
	} // end sidoInfecStatusUpdate

}
