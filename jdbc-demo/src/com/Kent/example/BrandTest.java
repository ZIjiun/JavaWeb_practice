package com.Kent.example;

import com.Kent.pojo.Brand;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 品牌資料的增刪改查操作
 */
public class BrandTest {
    /**
     *  查詢所有
     *  1. SQL: select * from tb_brand;
     *  2. 參數: 不需要
     *  3. 結果: List<Brand>
     */
    @Test
    public void testSelectAll() throws Exception {
        System.out.println(System.getProperty("user.dir"));
        // 1. 取得 Connection
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        // 2. 定義 SQL
        String sql = "select * from tb_brand;";

        // 3. 獲取 pstmt 物件
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 4. 設置參數

        // 5. 執行 SQL
        ResultSet rs = pstmt.executeQuery();

        // 6. 處理結果 List<Brand> 封裝 Brand 物件，裝載 List 集合

        Brand brand = null;
        List<Brand> brands = new ArrayList<>();
        while(rs.next()) {
            // 取得資料
            int id = rs.getInt("id");
            String brandName = rs.getString("brand_name");
            String computerName = rs.getString("company_name");
            int ordered = rs.getInt("ordered");
            String description = rs.getString("description");
            int status = rs.getInt("status");

            // 裝載 Brand 物件
            brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setCompanyName(computerName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);

            // 裝載集合
            brands.add(brand);
        }
        System.out.println(brands);
        // 7. 釋放資源
        rs.close();
        pstmt.close();
        conn.close();
    }

    /**
     * 插入
     * 1. SQL: insert into tb_brand(brand_name, company_name, ordered, description, status) values (?, ?, ?, ?, ?);
     * 2. 參數: 除了 id 之外的所有參數訊息
     * 3. 結果: boolean
     * @throws Exception
     */
    @Test
    public void testAdd() throws Exception {
        // 模擬接收前端提交的參數
        String brandName = "香飄飄";
        String companyName = "香飄飄";
        int ordered = 1;
        String description = "繞地球一圈";
        int status = 1;

        // 1. 取得 Connection
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        // 2. 定義 SQL
        String sql = "insert into tb_brand(brand_name, company_name, ordered, description, status) values (?, ?, ?, ?, ?);";

        // 3. 獲取 pstmt 物件
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 4. 設置參數
        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);

        // 5. 執行 SQL
        int count = pstmt.executeUpdate(); // 影響的行數

        // 6. 處理結果
        System.out.println(count > 0);

        // 7. 釋放資源
        pstmt.close();
        conn.close();
    }

    /**
     * 修改
     * 1. SQL:
     update tb_brand
        set brand_name = ?,
        company_name = ?,
        ordered = ?,
        description = ?,
        status = ?
     where id = ?
     * 2. 參數: 需要所有的參數
     * 3. 結果: boolean
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        // 模擬接收前端提交的參數
        String brandName = "香飄飄";
        String companyName = "香飄飄";
        int ordered = 1000;
        String description = "繞地球三圈";
        int status = 1;
        int id = 4;

        // 1. 取得 Connection
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        // 2. 定義 SQL
        String sql = "update tb_brand\n" +
                "        set brand_name = ?,\n" +
                "        company_name = ?,\n" +
                "        ordered = ?,\n" +
                "        description = ?,\n" +
                "        status = ?\n" +
                "     where id = ?";

        // 3. 獲取 pstmt 物件
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 4. 設置參數
        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);
        pstmt.setInt(6,id);

        // 5. 執行 SQL
        int count = pstmt.executeUpdate(); // 影響的行數

        // 6. 處理結果
        System.out.println(count > 0);

        // 7. 釋放資源
        pstmt.close();
        conn.close();
    }

    /**
     * 刪除
     * 1. SQL:
     delete from tb_brand where id = ?
     * 2. 參數: 只需要 id
     * 3. 結果: boolean
     * @throws Exception
     */
    @Test
    public void testDeleteById() throws Exception {
        // 模擬接收前端提交的參數
        int id = 6;

        // 1. 取得 Connection
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        // 2. 定義 SQL
        String sql = "delete from tb_brand where id = ?";

        // 3. 獲取 pstmt 物件
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 4. 設置參數
        pstmt.setInt(1,id);

        // 5. 執行 SQL
        int count = pstmt.executeUpdate(); // 影響的行數

        // 6. 處理結果
        System.out.println(count > 0);

        // 7. 釋放資源
        pstmt.close();
        conn.close();
    }
}
