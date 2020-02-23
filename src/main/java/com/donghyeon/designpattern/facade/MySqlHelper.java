package com.donghyeon.designpattern.facade;

import java.sql.Connection;

public class MySqlHelper {

    public static Connection getMySqlDBConnection(){
        //Connection을 이용해서 Mysql 연결 맺기
        return null;
    }

    public void generateMySqlPDFReport(String tableName, Connection con){
        // 테이블에서 데이터를 가져와 pdf 만들기
    }

    public void generateMySqlHTMLReport(String tableName, Connection con){
        // 테이블에서 데이터를 가져와 html 만들기
    }
}
