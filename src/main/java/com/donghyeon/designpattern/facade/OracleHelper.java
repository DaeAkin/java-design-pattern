package com.donghyeon.designpattern.facade;

import java.sql.Connection;

public class OracleHelper {

    public static Connection getOracleDBConnection(){
        //Connection을 이용해서 Oracle 연결 맺기
        return null;
    }

    public void generateOraclePDFReport(String tableName, Connection con){
        // 테이블에서 데이터를 가져와 pdf 만들기
    }

    public void generateOracleHTMLReport(String tableName, Connection con){
        // 테이블에서 데이터를 가져와 html 만들기
    }

}
