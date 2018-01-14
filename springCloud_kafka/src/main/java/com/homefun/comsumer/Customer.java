package com.homefun.comsumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
/**
 * todo   	定义输入通道并绑定输入通道配置信息
 * 			消费者
 * author 	liangguohun
 * datetime 2017年12月25日 上午2:30:01
 */
public interface Customer {
	/**
	 * 接收队列1
	 */
	String INPUT_1 = "customer1";
	
	@Input(Customer.INPUT_1)
	SubscribableChannel input1();
}
