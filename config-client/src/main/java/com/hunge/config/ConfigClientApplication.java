package com.hunge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Spring Cloud Bus的更新只对三种情况有效
//@ConfigurationProperties的改变时直接重新绑定
@RefreshScope//热更 不仅仅是重新绑定,它是重新再绑定一个bean.@RefreshScope的工作原理Spring创建一个代理Proxy,Proxy中包含注入的依赖及调用目标bean的方法,当更新Refresh时,新Proxy就会指向改变的bean,而老的Proxy还指向老的bean.所以它更安全
//日志级别
@SpringBootApplication
@RestController
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
	@Value("${foo}")
	String foo;
	@RequestMapping(value="/fuck")
	public String fuck() {
		return foo;
	}
}
