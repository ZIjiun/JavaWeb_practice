package druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Druid 資料庫連接池使用
 */
public class DruidDemo {
    public static void main(String[] args) throws Exception {
        // 1. 導入jar包
        // druid-1.1.12.jar

        // 2. 定義配置文件
        // druid.properties

        // 3. 加載配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("jdbc-demo/src/druid.properties"));

        // 4. 獲取連接池物件
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        // 5. 獲取資料庫連接 Connection
        Connection connection = dataSource.getConnection();

        System.out.println(connection);

        // 找到目前所在的資料夾路徑
//        System.out.println(System.getProperty("user.dir"));
    }
}
