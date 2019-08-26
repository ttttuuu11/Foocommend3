package com.school.foocommend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan(basePackages= {"com.school.foocommend"})
@Import({
	ContextDataSource.class,
	ContextSqlMapper.class
})
@EnableWebMvc
public class RootConfig {

}
