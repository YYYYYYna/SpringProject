package com.sist.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration

@ComponentScan(basePackages = "com.sist.*")

@MapperScan(basePackages = "com.sist.mapper")

public class SeoulGoodsConfig {
	@Bean("ds")
	   public BasicDataSource dataSource()
	   {
	      BasicDataSource ds=new  BasicDataSource();
	      // setter
	      ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	      ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	      ds.setUsername("hr");
	      ds.setPassword("happy");
	      ds.setMaxActive(10);
	      ds.setMinIdle(10);
	      ds.setMaxWait(-1);
	      return ds;
	   }
	 @Bean("ssf")
	   public SqlSessionFactory sqlSessionFactory() throws Exception
	   {
	      SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
	      ssf.setDataSource(dataSource());
	      return ssf.getObject();
	   }
}
