package guan.spider.src;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductDao extends JdbcTemplate {


     public  ProductDao(){

         ComboPooledDataSource cmbSource = new ComboPooledDataSource();

         cmbSource.setUser("root");
         cmbSource.setPassword("admin");
         cmbSource.setJdbcUrl("jdbc:mysql://node1:3306/JdSpider?characterEncoding=utf-8");

         setDataSource(cmbSource);
     }


     public void saveProduct(Product product){

         String sql = "INSERT INTO jd_product(id,name,url,title,price,maidian,pinpai,xinhao) VALUES(?,?,?,?,?,?,?,?);";

         update(sql,product.getId(),product.getName(),product.getUrl(),product.getTitle(),product.getPrice(),product.getMaidian(),product.getPinpai(),product.getXinhao());
     }

}
