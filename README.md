# Open API를 활용한 코로나 바이러스 현황 알리미
### 개발환경
* 운영체제: Windows 10
* 개발 도구: STS 3.9.12 RELEASE
* 서버: Apache Tomcat 9.0.26
* DB: MySQL 8.0
* 언어: Java, JSP, HTML/CSS, Javascript
* 프레임워크: Spring, MyBatis
* 라이브러리: JQuery

### 기능
**1. 코로나 감염 현황**
<img width="700" height="450" src="https://user-images.githubusercontent.com/42902371/132116740-a1ede0e0-cb0f-46fd-be80-ab309450f2cd.PNG">

**2. 시 · 도별 코로나 감염 현황**
  * [xml을 json으로 변환(XmlToJson.java)](https://github.com/chch8326/Covid19Project/blob/main/Covid19Project/src/main/java/com/choi/covid19/util/XmlToJson.java?ts=4)
  * [json 파싱 및 스케쥴링으로 정해진 시간에 데이터 업데이트(InfectionStatusUpdateTask.java)](https://github.com/chch8326/Covid19Project/blob/main/Covid19Project/src/main/java/com/choi/covid19/task/InfectionStatusUpdateTask.java?ts=4)
<img width="700" height="450" src="https://user-images.githubusercontent.com/42902371/132116333-5c0f5ef9-722a-4631-a15d-15c9f483ee3f.PNG">   
    
**3. 선별 진료소 위치 및 정보 제공**
  * [네이버 지도 API 사용(map.jsp)](https://github.com/chch8326/Covid19Project/blob/main/Covid19Project/src/main/webapp/WEB-INF/views/covid19/map.jsp?ts=4)
<img width="700" height="450" src="https://user-images.githubusercontent.com/42902371/132116334-07912b39-ab00-4c6a-83b2-506ab6ef3e31.PNG">
