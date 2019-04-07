package com.findjob;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;

@EnableTransactionManagement
@MapperScan(value = "com.findjob.mapper")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class, scanBasePackages = "com.findjob.*")
public class JobApplication {

	private final DataSource dataSource;

	@Autowired
	public JobApplication(DataSource dataSource) {
	    this.dataSource = dataSource;
    }

	public static void main(String[] args) {
		SpringApplication.run(JobApplication.class, args);
	}

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(10 * 1024 * 1024));
        return factory.createMultipartConfig();
    }

	@Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
	    DataSourceTransactionManager manager = new DataSourceTransactionManager();
	    manager.setDataSource(dataSource);
	    return manager;
    }

    @Bean
    public ObjectMapper objectMapper() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	    return mapper;
    }
}
