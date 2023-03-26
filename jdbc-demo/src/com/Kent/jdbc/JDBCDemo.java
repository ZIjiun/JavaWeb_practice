package com.Kent.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * JDBC Demo
 */
public class JDBCDemo {
    public static void main(String args[]) throws Exception{
        //1. 註冊驅動
        Class.forName("com.mysql.jdbc.Driver");

        //2. 獲得連接
        String url = "jdbc:mysql://127.0.0.1:3306/practice";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3. 定義 SQL
        String sql = "update practice.student set sname = 'Nina' where sid = '01';";

        //4. 取得執行 sql 的物件 Statement
        Statement stmt = conn.createStatement();

        //5. 執行 sql
        int count = stmt.executeUpdate(sql); //受影響的行數

        //6. 處理結果
        System.out.println(count);

        //7. 釋放資源
        stmt.close();
        conn.close();
    }
}
