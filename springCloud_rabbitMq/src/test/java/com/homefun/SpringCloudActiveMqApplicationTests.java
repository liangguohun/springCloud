package com.homefun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.homefun.producter.Producter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCloudActiveMqApplicationTests {
	@Autowired
	private Producter producter;
	
	@Test
	public void contextLoads() {
		for(int i=0;i<100;i++) {
			producter.send("你妹"+i);
		}
	}

}
