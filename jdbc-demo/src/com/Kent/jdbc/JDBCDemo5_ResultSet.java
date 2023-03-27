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
 * Statement 註解
 */
public class JDBCDemo5_ResultSet {

    /**
     * 執行 DQL 查詢語句
     * @throws Exception
     */
    @Test
    public void testResultSet() throws Exception {
        //1. 註冊驅動
        Class.forName("com.mysql.jdbc.Driver");

        //2. 獲得連接
        String url = "jdbc:mysql://127.0.0.1:3306/practice";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3. 定義 sql
        String sql = "select * from practice.student";

        //4. 獲取 statement 物件
        Statement stmt = conn.createStatement();

        //5. 執行 sql
        ResultSet rs = stmt.executeQuery(sql);

        //6. 處理結果，遍歷 rs 中的所有資料
        //6.1 指標向下移動一行，並且判斷當前行是否有資料
        while(rs.next()) {
//            6.2 獲取資料 getXXX()
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String birthday = rs.getString(3);
            String sex = rs.getString(4);

            System.out.println(id);
            System.out.println(name);
            System.out.println(birthday);
            System.out.println(sex);

            System.out.println("----------");
        }

//        while(rs.next()) {
//            //6.2 獲取資料 getXXX()
//            String id = rs.getString("sid");
//            String name = rs.getString("sname");
//            String birthday = rs.getString("sage");
//            String sex = rs.getString("ssex");
//
//            System.out.println(id);
//            System.out.println(name);
//            System.out.println(birthday);
//            System.out.println(sex);
//
//            System.out.println("----------");
//        }

        // 7. 釋放資源
        rs.close();
        stmt.close();
        conn.close();
    }

    /**
     * 查詢 student list 表資料，封裝到 StudentData 的物件中，並且儲存到 ArrayList 集合中
     * 1. 定義實體類 StudentData
     * 2. 查詢資料，封裝到 StudentData 物件中
     * 3. 將 Student 物件存入 ArrayList 集合中
     * @throws Exception
     */
    @Test
    public void testResultSet2() throws Exception {
        //1. 註冊驅動
        Class.forName("com.mysql.jdbc.Driver");

        //2. 獲得連接
        String url = "jdbc:mysql://127.0.0.1:3306/practice";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3. 定義 sql
        String sql = "select * from practice.student";

        //4. 獲取 statement 物件
        Statement stmt = conn.createStatement();

        //5. 執行 sql
        ResultSet rs = stmt.executeQuery(sql);

        //6. 處理結果，遍歷 rs 中的所有資料

        // 創建集合
        List<StudentData> list = new ArrayList<>();

        //6.1 指標向下移動一行，並且判斷當前行是否有資料
        while(rs.next()) {
            StudentData studentData = new StudentData();
//            6.2 獲取資料 getXXX()
            String id = rs.getString(1);
            String name = rs.getString(2);
            String birthday = rs.getString(3);
            String sex = rs.getString(4);

            studentData.setId(id);
            studentData.setName(name);
            studentData.setBirthday(birthday);
            studentData.setSex(sex);

            // 存入集合
            list.add(studentData);
        }

        System.out.println(list);
        // 7. 釋放資源
        rs.close();
        stmt.close();
        conn.close();
    }
}

