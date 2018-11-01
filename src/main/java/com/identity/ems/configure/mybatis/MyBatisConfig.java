package com.identity.ems.configure.mybatis;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

public abstract class MyBatisConfig {
	
	public static final String BASE_PACKAGE = "com.identity.ems.mapper";
	public static final String TYPE_ALIASES_PACKAGE = "com.identity.ems.domain";
	public static final String CONFIG_LOCATION_PATH = "classpath:META-INF/mybatis/mybatis-config.xml";
	public static final String MAPPER_LOCATIONS_PATH = "classpath:META-INF/mybatis/mapper/**/*.xml";
	
	protected void configureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource) throws IOException {
		PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
		sessionFactoryBean.setConfigLocation(pathResolver.getResource(CONFIG_LOCATION_PATH));
		sessionFactoryBean.setMapperLocations(pathResolver.getResources(MAPPER_LOCATIONS_PATH));
	}
}