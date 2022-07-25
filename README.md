# board

## 1.환경설정

h2 database 다운로드 및 설치  
1.4.200 버전으로 진행  

h2.bat 실행  

드라이버 클래스 : org.h2.Driver  
JDBC URL  : jdbc:h2:~/test  
사용자명 : sa  
비밀번호 :  

실행하여 디비 파일 생성  


## 2. 톰캣 동작
웹서버 실행 전 h2 데이타베이스 실행 (미연결시 동작 실패)  
BoardApplication.main() 함수 실행  
Board 테이블 자동 생성됨  
100개의 테스트 데이터 자동 생성  
