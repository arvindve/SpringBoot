package com.ai.springboot;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@SpringBootApplication
@EnableAsync
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SpringtestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringtestApplication.class, args);
	}

	@Bean
	public TilesConfigurer tilesConfigure() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();

		String[] defs = { "/WEB-INF/tiles.xml" };
		tilesConfigurer.setDefinitions(defs);

		return tilesConfigurer;
	}

	@Bean
	public UrlBasedViewResolver tilesViewResolver() {

		UrlBasedViewResolver view = new UrlBasedViewResolver();

		view.setViewClass(TilesView.class);

		return view;

	}

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringtestApplication.class);

	}
	
	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	EmbeddedServletContainerCustomizer errorHandler() {
		
		return new EmbeddedServletContainerCustomizer(){

			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/403"));
				
			}
			
		};
		
	}
	
	@Bean
	PolicyFactory getUserHtmlPolicy(){
		
		return new HtmlPolicyBuilder()
				.allowCommonBlockElements()
				.allowCommonInlineFormattingElements()
				.toFactory();
		
	}

}
