package com.hunge.config;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 * @todo   	do	对外暴露接口
 * @author 	liangguohun
 * @date  	2017年8月3日 下午9:26:30
 */
@SpringBootApplication
@RestController
public class ZipkinHiApplication {
	private static final Logger LOG = Logger.getLogger(ZipkinHiApplication.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(ZipkinHiApplication.class, args);
	}
	@Autowired
	private RestTemplate restTemplate;
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@RequestMapping("/hi")
	public String callHome() {
		LOG.log(Level.INFO, "calling trace service-hi");
		return restTemplate.getForObject("http://localhost:8989/miya", String.class);
	}
	@RequestMapping("/info")
	public String info() {
		LOG.log(Level.INFO, "calling trace service-hi2");
		return "i'm hunge";
	}
	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}
}
