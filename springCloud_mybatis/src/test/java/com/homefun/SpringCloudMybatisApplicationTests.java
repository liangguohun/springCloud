package com.homefun;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.homefun.controllers.UserController;


@RunWith(SpringRunner.class)
//运行完整的服务端测试用随机端口
@SpringBootTest(classes=SpringCloudMybatisApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class SpringCloudMybatisApplicationTests {
    @Autowired  
    private UserController userController; 
	//findUser/{userName}
	@Test
	public void findUser() throws UnsupportedEncodingException, Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(userController).build();
        System.out.println("=======================2x===================");
        String responseString = mock
        		.perform(
        				post("/findUser/")
        				.contentType( MediaType.APPLICATION_JSON)  //数据的格式
        		)
        		.andDo(print())         	//打印出请求和相应的内容
        		.andExpect(status().isOk())
        		.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        System.out.println(responseString);
	}

}
