package com.hunge.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * 以/api-a/ 开头的请求都指向service-ribbon；
 * 以/api-b/开头的请求都指向service-feign
 * @todo   	do
 * @author 	liangguohun
 * @date  	2017年7月25日 下午10:27:13
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ServiceZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceZuulApplication.class, args);
	}
}
