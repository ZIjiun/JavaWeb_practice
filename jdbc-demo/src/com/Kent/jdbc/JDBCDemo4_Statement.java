package com.Kent.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Statement 註解
 */
public class JDBCDemo4_Statement {

    /**
     * 測試 DML 語句
     * @throws Exception
     */
    @Test
    public void testDML() throws Exception {
        //1. 註冊驅動
        Class.forName("com.mysql.jdbc.Driver");

        //2. 獲得連接
        String url = "jdbc:mysql://127.0.0.1:3306/practice";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3. 定義 SQL
        String sql = "update practice.student set sname = 'Nina3' where sid = '01';";

        //4. 取得執行 sql 的物件 Statement
        Statement stmt = conn.createStatement();

        //5. 執行 sql
        int count = stmt.executeUpdate(sql); //執行 DML 語句，受影響的行數

        //6. 處理結果
//        System.out.println(count);

        if (count > 0 ) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失敗");
        }

        //7. 釋放資源
        stmt.close();
        conn.close();
    }

    /**
     * 測試 DDL 語句
     * @throws Exception
     */
    @Test
    public void testDDL() throws Exception {
        //1. 註冊驅動
        Class.forName("com.mysql.jdbc.Driver");

        //2. 獲得連接
        String url = "jdbc:mysql://127.0.0.1:3306/practice";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3. 定義 SQL
        String sql = "drop database db2;";

        //4. 取得執行 sql 的物件 Statement
        Statement stmt = conn.createStatement();

        //5. 執行 sql
        int count = stmt.executeUpdate(sql); //執行DDL語句，可能返回的是 0

        //6. 處理結果
//        System.out.println(count);

        //7. 釋放資源
        stmt.close();
        conn.close();
    }
}

