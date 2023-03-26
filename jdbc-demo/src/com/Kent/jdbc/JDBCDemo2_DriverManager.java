package com.Kent.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo2_DriverManager {
    public static void main(String[] args) throws Exception {
        //1. 註冊驅動
        // 可以省略掉，因為在 lib/jar包/META-INF/services/java.sql.Driver 中已經有定義好了
//        Class.forName("com.mysql.jdbc.Driver");

        //2. 獲得連接
        // 如果連接的是本地的 mysql 且 port 是3306，可以省略掉 url
        // 因為 mysql SSL 連接設定的問題，會一直出現警告，可以使用 useSSL = fasle 把警告消除掉
        String url = "jdbc:mysql:///practice?useSSL=false";
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
