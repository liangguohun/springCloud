package com.homefun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.homefun.redis.RedisConn;
import com.homefun.redis.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCloudRedisApplicationTests {
	@Autowired  
    private RedisConn redisConn;
	@Autowired
	private RedisUtil redisUtil;
	@Test
	public void contextLoads() {
		System.out.println("simpleProp: " + redisConn.getHost());
//		redisUtil.set("test", "goods2");
		System.out.println(redisUtil.get("test"));
	}

}
