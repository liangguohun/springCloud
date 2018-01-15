package com.homefun;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.homefun.beans.UserInfo;
import com.homefun.controllers.UserController;
import com.homefun.dao.UserInfoDao;


@RunWith(SpringRunner.class)
//运行完整的服务端测试用随机端口
@SpringBootTest(classes=SpringCloudMybatisApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
//相当于  --spring.profiles.active=dev  
@ActiveProfiles(value="dev")
public class SpringCloudMybatisApplicationTests {
    @Autowired  
    private UserController userController; 
    @Autowired
    private UserInfoDao userInfoDao;
	//findUser/{userName}
	@Test
	public void findUser() throws UnsupportedEncodingException, Exception {
//		MockMvc mock = MockMvcBuilders.standaloneSetup(userController).build();
//        System.out.println("=======================2x===================");
//        String responseString = mock
//        		.perform(
//        				post("/findUser/test")
//        				.contentType( MediaType.APPLICATION_JSON)  //数据的格式
//        		)
//        		.andDo(print())         	//打印出请求和相应的内容
//        		.andExpect(status().isOk())
//        		.andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
//        System.out.println(responseString);
		
		//測試插入
//		UserInfo user = new UserInfo();
//		user.setUserName("啊哈");
//		userInfoDao.save(user);
		
		//不能為空集合
//		List<UserInfo> users = new ArrayList<UserInfo>();
//		for(int i=0;i<10;i++) {
//			UserInfo user = new UserInfo();
//			user.setUserName("啊哈"+i);
//			users.add(user);
//		}
//		userInfoDao.insertBatch(users);
		
//		UserInfo user = new UserInfo();
//		user.setUserName("哥哥x");
//		user.setXiaoMing("xQ");
//		user.setId(1);
//		userInfoDao.updatePart(user);
		
		
		List<UserInfo> users = new ArrayList<UserInfo>();
		for(int i=1;i<10;i++) {
			UserInfo user = new UserInfo();
			user.setUserName("哥哥"+i);
			user.setId(i);
			users.add(user);
		}
		userInfoDao.upBatch(users);
	}

}
