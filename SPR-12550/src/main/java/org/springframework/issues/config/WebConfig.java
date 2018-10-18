package org.springframework.issues.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackages="org.springframework.issues")
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	private static final String SERVLET_CONTEXT = "/main";

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		VersionResourceResolver versionResolver = new VersionResourceResolver().addContentVersionStrategy("/**");
//		The first path resolves the file from the http call.
//		The second path adds the hash to the css while resolving the c:url call. (Because the servlet context is not being removed from the resource)
		registry.addResourceHandler("/static/**", SERVLET_CONTEXT + "/static/**")
				.addResourceLocations("classpath:/static/")
				.resourceChain(false)
				.addResolver(versionResolver);
	}
}
