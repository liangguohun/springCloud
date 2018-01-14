package com.homefun.productor;
/**
 * todo   	定义输出通道并绑定输出通道配置信息
 * 			生产者
 * author 	liangguohun
 * datetime 2017年12月25日 上午2:30:33
 */

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Productor {
	/**
	 *	发送队列1 
	 */
	String OUTPUT_1 = "productor1";
	@Output(Productor.OUTPUT_1)
	MessageChannel output1();
}
