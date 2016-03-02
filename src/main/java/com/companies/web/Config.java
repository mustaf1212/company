package com.companies.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.companies.persistance.CompanyDao;
import com.companies.persistance.CompanyDaoHibernateImpl;
import com.companies.services.CompanyService;
import com.companies.services.CompanyServiceImpl;

@Configuration
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/WebContent/WEB-INF/",
			"classpath:/WEB-INF/", "classpath:/static/", "classpath:/public/" };

	@Bean
	public CompanyService companyService() {
		return new CompanyServiceImpl();
	}

	@Bean
	public CompanyDao companyDao() {
		return new CompanyDaoHibernateImpl();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}

}
