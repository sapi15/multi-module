package com.multi.module.system.spring.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Slf4j
@Configuration
@PropertySource("classpath:/application.properties")
@MapperScan(basePackages = {"com.multi.module.member.repository"
                            , "com.multi.module.history.repository"
                            , "com.multi.module.board.repository"
                            , "com.multi.module.attachFile.repository"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class DatabaseConfig {

//    private final ApplicationContext applicationContext;
//
//    public DatabaseConfig(ApplicationContext applicationContext){
//        this.applicationContext = applicationContext;
//    }

    @Resource
    private ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    @Bean(name = "dataSource1")
    public DataSource dataSource() throws Exception{
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "dataSource1") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:/mybatis/config/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mapper/**/*-mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(value = "sqlSessionFactory1") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
