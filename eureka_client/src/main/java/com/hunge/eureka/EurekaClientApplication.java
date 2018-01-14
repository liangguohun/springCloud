package com.hunge.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@EnableEurekaClient // 通过注解@EnableEurekaClient 表明自己是一个eurekaclient.
//使用@EnableEurekaClient注解，那么只能使用eureka来发现服务实例
//@EnableDiscoveryClient
/**
 * 	1，@EnableDiscoveryClient注解是基于spring-cloud-commons依赖，并且在classpath中实现；
	2，@EnableEurekaClient注解是基于spring-cloud-netflix依赖，只能为eureka作用；
	如果你的classpath中添加了eureka，则它们的作用是一样的。
 */
@RestController 	// 同事具备controller与responseBody两个注解的功能
//断路器
//@EnableHystrix
//@EnableHystrixDashboard
//@EnableCircuitBreaker
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
	
	@Value("${server.port}")
	String port;
	
	@RequestMapping("/hi")
	@HystrixCommand(fallbackMethod = "hiError")
	public String home(@RequestParam String name){
		return "fuck "+name+", i am from port:"+port;
	}
	public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
