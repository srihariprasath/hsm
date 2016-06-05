package com.hsm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

import com.hsm.security.CORSFilter;
import com.hsm.security.JwtAuthFilter;

@SpringBootApplication
@ComponentScan({"com.hsm.controllers","com.hsm.dao","com.hsm","com.hsm.bo,com.hsm.security"})
@EnableAutoConfiguration
public class HSMStarterWebApplication {
	
	public static void main(String[] args) {
		
		HSMStarterWebApplication hsmStarterWebApplication = new HSMStarterWebApplication();
		
		SpringApplication.run(HSMStarterWebApplication.class, args);	
	}
	
	@Bean
	public FilterRegistrationBean corsFilter(){
		
		final FilterRegistrationBean registratioBean=new FilterRegistrationBean();
		registratioBean.setFilter(new CORSFilter());
		registratioBean.addUrlPatterns("/hsm/*");
		return registratioBean;
	}
	
	@Bean
	public FilterRegistrationBean jwtSecureFilter(){
		
		final FilterRegistrationBean registratioBean=new FilterRegistrationBean();
		registratioBean.setFilter(new JwtAuthFilter());
		registratioBean.addUrlPatterns("/hsm/secure/*");
		return registratioBean;
	}

}
