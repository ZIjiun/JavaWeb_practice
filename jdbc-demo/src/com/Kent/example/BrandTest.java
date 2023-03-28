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
}
