package com.homefun.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * todo   	读取Redis链接信息
 * author 	liangguohun
 * datetime 2017年12月27日 下午4:35:06
 */
@Component
//prefix+参数名  对应于配置文件config.properties中的spring.redis.*信息
@ConfigurationProperties(prefix="spring.redis")
public class RedisConn {
	private String host;
	private int port;
	private int timeout;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
