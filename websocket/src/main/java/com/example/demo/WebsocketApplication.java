package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}
    @Bean  //  2.0 开始用TomcatServletWebServerFactory 替换 EmbeddedServletContainerFactory 
    public TomcatServletWebServerFactory createEmbeddedServletContainerFactory()  
    {
    	TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory(888);
        tomcatFactory.addConnectorCustomizers(new IndividualTomcatConnectorCustomizer());  
        return tomcatFactory;  
    }
}
