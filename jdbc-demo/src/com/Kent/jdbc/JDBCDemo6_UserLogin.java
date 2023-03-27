package com.Kent.jdbc;

import com.Kent.pojo.StudentData;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用者登入
 */
public class JDBCDemo6_UserLogin {

    @Test
    public void testLogin() throws Exception {

        //2. 獲得連接
        String url = "jdbc:mysql://127.0.0.1:3306/practice";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接收使用者的帳號和密碼
        String name = "Nina";
        String pwd = "123";
        String sql = "select * from db1.tb_user where username = '" + name + "' and password = '" + pwd + "'";

        // 取得 statement 物件
        Statement stmt = conn.createStatement();

        // 執行 sql
        ResultSet rs = stmt.executeQuery(sql);

        // 判斷登陸是否成功
        if (rs.next()) {
            System.out.println("登錄成功");
        }else{
            System.out.println("登陸失敗");
        }

        // 7. 釋放資源
        rs.close();
        stmt.close();
        conn.close();
    }

    /**
     * SQL injection 示範
     * @throws Exception
     */
    @Test
    public void testLogin_Inject() throws Exception {

        //2. 獲得連接
        String url = "jdbc:mysql://127.0.0.1:3306/practice";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接收使用者的帳號和密碼
        String name = "Nina789456";
        String pwd = "' or '1' = '1";
        String sql = "select * from db1.tb_user where username = '" + name + "' and password = '" + pwd + "'";
        System.out.println(sql);
        // 取得 statement 物件
        Statement stmt = conn.createStatement();

        // 執行 sql
        ResultSet rs = stmt.executeQuery(sql);

        // 判斷登陸是否成功
        if (rs.next()) {
            System.out.println("登錄成功");
        }else{
            System.out.println("登陸失敗");
        }

        // 7. 釋放資源
        rs.close();
        stmt.close();
        conn.close();
    }
}

