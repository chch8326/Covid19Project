package com.choi.covid19.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.json.JSONObject;
import org.json.XML;

public class XmlToJson {
	
	private static final int INDENT_FACTOR = 4;
	
	/*
	 * xml을 json으로 바꾸고 데이터를 반환
	 */
	public static String getJsonData(String url, String serviceKey) {
		try {
			HttpURLConnection urlConn = (HttpURLConnection)new URL(
					url + "?serviceKey=" + serviceKey).openConnection();
			urlConn.connect();
			
			BufferedInputStream bis = new BufferedInputStream(urlConn.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
			
			StringBuffer sb = new StringBuffer();
			String line;
			
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			JSONObject xmlJsonObj = XML.toJSONObject(sb.toString());
			String jsonPrettyPrintString = xmlJsonObj.toString(INDENT_FACTOR);
			
			return jsonPrettyPrintString;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	/*
	 * xml을 json으로 바꾸고 어제 자 데이터를 반환
	 * 누적 확진자/사망자 수에 어제 자 누적 확진자/사망자 수를 빼서 오늘 자 확진자/사망자 수를 얻기 위함 
	 */
	public static String getYesterdayJsonData(String url, String serviceKey) {
		try {
			HttpURLConnection urlConn = (HttpURLConnection)new URL(
					url + "?serviceKey=" + serviceKey + "&startCreateDt=" + getYesterdayDate() + 
					"&endCreateDt=" + getYesterdayDate()).openConnection();
			urlConn.connect();
			
			BufferedInputStream bis = new BufferedInputStream(urlConn.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
			
			StringBuffer sb = new StringBuffer();
			String line;
			
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			JSONObject xmlJsonObj = XML.toJSONObject(sb.toString());
			String jsonPrettyPrintString = xmlJsonObj.toString(INDENT_FACTOR);
			
			return jsonPrettyPrintString;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * 어제 날짜 반환
	 */
	private static String getYesterdayDate() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		String yesterday = sdf.format(calendar.getTime());
		return yesterday;
	}

}
