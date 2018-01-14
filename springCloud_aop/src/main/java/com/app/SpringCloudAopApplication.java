package com.app;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.app.utils.sys.ApplicationContextProvider;


@SpringBootApplication
public class SpringCloudAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudAopApplication.class, args);
	}
	@Bean
	public SessionFactory sessionFactory(@Qualifier("entityManagerFactory") EntityManagerFactory emf) {
	    return emf.unwrap(SessionFactory.class);
	}
	
	@Bean
	public HibernateTemplate hibernateTemplate() {
		SessionFactory sessionFactory = ApplicationContextProvider.getBean(SessionFactory.class);
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}
    @Bean  
    public WebMvcConfigurerAdapter corsConfigurer() {  
    	WebMvcConfigurerAdapter configurer = new WebMvcConfigurerAdapter() {  
            @Override  
            public void addCorsMappings(CorsRegistry registry) {  
                registry.addMapping("/**")  
                .allowedOrigins("*")  
                .allowedMethods("PUT", "DELETE","GET","POST")  
                .allowedHeaders("*")  
                .exposedHeaders("access-control-allow-headers",  
                      "access-control-allow-methods",  
                      "access-control-allow-origin",  
                      "access-control-max-age",  
                      "X-Frame-Options")  
                .allowCredentials(false)				//允许接收cookie信息这样post的sessionid才一致 无session方式
                .maxAge(3600);  
            }

			@Override
			public void configurePathMatch(PathMatchConfigurer configurer) {
				configurer.setUseSuffixPatternMatch(false);//路径匹配.的扩展这样才能匹配后缀/浮点数等
			}  
            
        };
        return configurer;
    }  
	@Bean
	public CorsFilter corsFilter() {
	   final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	   final CorsConfiguration config = new CorsConfiguration();
	   config.setAllowCredentials(true); // 允许cookies跨域
	   config.addAllowedOrigin("*");// #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
	   config.addAllowedHeader("*");// #允许访问的头信息,*表示全部
	   config.addAllowedMethod("*");
	   config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
	   source.registerCorsConfiguration("/**", config);
	   return new CorsFilter(source);
	}
}
