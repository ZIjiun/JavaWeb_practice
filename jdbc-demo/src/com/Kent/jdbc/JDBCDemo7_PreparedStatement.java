 package com.Kent.jdbc;

import org.junit.Test;

import java.sql.*;

 /**
  * API 詳解: PreparedStatement
  */
public class JDBCDemo7_PreparedStatement {

    @Test
    public void testPreparedStatement() throws Exception {

        //2. 獲得連接
        String url = "jdbc:mysql://127.0.0.1:3306/practice?useSSL=false";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接收使用者的帳號和密碼
        String name = "Nina";
        String pwd = "123";

        // 定義 sql
        String sql = "select * from db1.tb_user where username = ? and password = ?";

        // 取得 PreparedStatement 物件
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 設置 ? 的值
        pstmt.setString(1, name);
        pstmt.setString(2, pwd);

        // 執行 sql
        ResultSet rs = pstmt.executeQuery();

        // 判斷登陸是否成功
        if (rs.next()) {
            System.out.println("登入成功");
        }else{
            System.out.println("登入失敗");
        }

        // 7. 釋放資源
        rs.close();
        pstmt. close();
        conn.close();
    }


     /**
      * 使用 PreparedStatement 預防 sql injection
      * @throws Exception
      */
     @Test
     public void testLogin_Inject() throws Exception {

         //2. 獲得連接
         String url = "jdbc:mysql://127.0.0.1:3306/practice?useSSL=false";
         String username = "root";
         String password = "root";
         Connection conn = DriverManager.getConnection(url, username, password);

         // 接收使用者的帳號和密碼
         String name = "Nina789456";
         String pwd = "' or '1' = '1";

         String sql = "select * from db1.tb_user where username = ? and password = ?";

         // 取得 PreparedStatement 物件
         PreparedStatement pstmt = conn.prepareStatement(sql);

         // 執行 sql
         // 使用 setString()，如果遇到某些關鍵字，例如【'】 會自動加上 \，變成【\'】，把這些當成 sql 的文本，不會再把【'】當成是 sql 語句的一部分
         pstmt.setString(1, name);
         pstmt.setString(2, pwd);

         ResultSet rs = pstmt.executeQuery();

         // 判斷登陸是否成功
         if (rs.next()) {
             System.out.println("登入成功");
         }else{
             System.out.println("登入失敗");
         }

         // 7. 釋放資源
         rs.close();
         pstmt.close();
         conn.close();
     }


     /**
      * PreparedStatement 原理
      * @throws Exception
      */
     @Test
     public void testPreparedStatement2() throws Exception {

         //2. 獲得連接
         // *** useServerPrepStmts=true 開啟 mysql 的預編譯功能 ***
         String url = "jdbc:mysql://127.0.0.1:3306/practice?useSSL=false&useServerPrepStmts=true";
         String username = "root";
         String password = "root";
         Connection conn = DriverManager.getConnection(url, username, password);

         // 接收使用者的帳號和密碼
         String name = "Nina";
         String pwd = "123";

         // 定義 sql
         String sql = "select * from db1.tb_user where username = ? and password = ?";

         // 取得 PreparedStatement 物件
         // *** 把 sql 語句傳入到 pstmt 裡面時就會先執行預編譯 ***
         PreparedStatement pstmt = conn.prepareStatement(sql);

         // 設置 ? 的值
         // *** 因為是同一個模板的 sql 語句，只有參數不同，只需要編譯一次，就可以執行 ***
         pstmt.setString(1, name);
         pstmt.setString(2, pwd);

         // 執行 sql
         ResultSet rs = pstmt.executeQuery();

         // 判斷登陸是否成功
         if (rs.next()) {
             System.out.println("登入成功");
         }else{
             System.out.println("登入失敗");
         }

         // 7. 釋放資源
         rs.close();
         pstmt. close();
         conn.close();
     }
}
