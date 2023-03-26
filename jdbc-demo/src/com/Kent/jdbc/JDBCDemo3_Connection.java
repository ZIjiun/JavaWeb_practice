package com.Kent.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo3_Connection {
    public static void main(String[] args) throws Exception {
        //1. 註冊驅動
        // 可以省略掉，因為在 lib/jar包/META-INF/services/java.sql.Driver 中已經有定義好了
        Class.forName("com.mysql.jdbc.Driver");

        //2. 獲得連接
        // 如果連接的是本地的 mysql 且 port 是3306，可以省略掉 url
        // 因為 mysql SSL 連接設定的問題，會一直出現警告，可以使用 useSSL = fasle 把警告消除掉
        String url = "jdbc:mysql:///practice?useSSL=false";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3. 定義 SQL
        String sql1 = "update practice.student set sname = 'Nina3' where sid = '01';";
        String sql2 = "update practice.student set sname = 'Tiffany3' where sid = '02';";
        //4. 取得執行 sql 的物件 Statement
        Statement stmt = conn.createStatement();

        try {
            // 開啟交易
            conn.setAutoCommit(false);

            //5. 執行 sql1
            int count1 = stmt.executeUpdate(sql1); //受影響的行數

            //6. 處理結果
            System.out.println(count1);

            // 故意製造異常測試
//            int i = 3/0;

            //5. 執行 sql2
            int count2 = stmt.executeUpdate(sql2); //受影響的行數

            //6. 處理結果
            System.out.println(count2);

            // commit 交易
            conn.commit();
        } catch (Exception e) {
            // rollback 交易
            // 出現異常時，就會將上面執行的結果做 rollback
            conn.rollback();
            e.printStackTrace();
        }

        // 提交事務

        //7. 釋放資源
        stmt.close();
        conn.close();
    }
}
