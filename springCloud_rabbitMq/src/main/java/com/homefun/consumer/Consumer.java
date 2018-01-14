package com.homefun.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="fuck")	//对hello队列监听
public class Consumer {
	/**
	 * todo:		指定消息的处理方法
	 * author:		梁国魂
	 * datetime:	2018年1月1日 上午2:33:34
	 */
	@RabbitHandler
	public void process(String msg) {
		System.out.println("Receiver : " + msg);
	}
}
