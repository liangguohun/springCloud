package com.hunge.eureka;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class) // JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@SpringBootTest(classes = EurekaClientApplication.class)
// @SpringApplicationConfiguration(classes = EurekaClientApplication.class)
// 是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下文。这是一个元注解（meta-annoation），
// 它包含了@ContextConfiguration( loader =
// SpringApplicationContextLoader.class)这个注解，
// 测试框架通过这个注解使用Spring Boot框架的SpringApplicationContextLoader加载器创建应用上下文

// @WebIntegrationTest("server.port:0")
/**
 * 这个注解表示当前的测试是集成测试（integration test），因此需要初始化完整的上下文并启动应用程序。
 * 这个注解一般和@SpringApplicationConfiguration一起出现。server.port:0指的是让Spring
 * Boot在随机端口上启动Tomcat服务，
 * 随后在测试中程序通过@Value("${local.server.port}")获得这个端口号，并赋值给port变量。当在Jenkins或其他持续集成服务器上运行测试程序时，
 * 这种随机获取端口的能力可以提供测试程序的并行性
 */
public class EurekaClientApplicationTests {
	// @Value("${local.server.port}")
	// private int port;

	@Autowired
	private WebApplicationContext context;
//	@Autowired
//	private BookRepository bookRepository;
	@Value("${local.server.port}")
	private int port;
	private MockMvc mockMvc;
//	private RestTemplate restTemplate = new TestRestTemplate();

	
	/**
	 * 	MockMvc对象提供一组工具函数用来执行assert判断，都是针对web请求的判断。
	 * 	这组工具的使用方式是函数的链式调用，允许程序员将多个测试用例链接在一起，
	 * 	并进行多个判断。在这个例子中我们用到下面的一些工具函数：

    	perform(get(...))建立web请求。在我们的第三个用例中，通过MockMvcRequestBuilder执行GET请求。
    	andExpect(...)可以在perform(...)函数调用后多次调用，表示对多个条件的判断，这个函数的参数类型是ResultMatcher接口，
    	在MockMvcResultMatchers这这个类中提供了很多返回ResultMatcher接口的工具函数。这个函数使得可以检测同一个web请求的多个方面，
    	包括HTTP响应状态码（response status），响应的内容类型（content type），会话中存放的值，检验重定向、
    	model或者header的内容等等。这里需要通过第三方库json-path检测JSON格式的响应数据：检查json数据包含正确的元素类型和对应的值，
    	例如jsonPath("$.name").value("中文测试")用于检查在根目录下有一个名为name的节点，并且该节点对应的值是“中文测试”
	 */
	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/**
	 * 在第一个测试用例——contextLoads()方法中，我仅仅需要确认BookRepository连接已经建立，并且数据库中已经包含了对应的测试数据。
	 */
	@Test
	public void contextLoads() {
//		assertEquals(1, bookRepository.count());
	}

	/**
	 * 第二个测试用例用来测试我们提供的RESTful URL——通过ISBN查询一本书，即“/books/{isbn}”。在这个测试用例中我们使用TestRestTemplate对象发起RESTful请求。
	 */
	@Test
	public void webappBookIsbnApi() {
//		Book book = restTemplate.getForObject("http://localhost:" + port + "/books/9876-5432-1111", Book.class);
//		assertNotNull(book);
//		assertEquals("中文测试", book.getPublisher().getName());
	}

	/**
	 * 第三个测试用例中展示了如何通过MockMvc对象实现跟第二个测试类似的功能。Spring测试框架提供MockMvc对象，
	 * 可以在不需要客户端-服务端请求的情况下进行MVC测试，完全在服务端这边就可以执行Controller的请求，跟启动了测试服务器一样
	 * @throws Exception
	 */
	@Test 
	public void webappPublisherApi() throws Exception { 
		// MockHttpServletRequestBuilder.accept方法是设置客户端可识别的内容类型
		// //MockHttpServletRequestBuilder.contentType,设置请求头中的Content-Type字段,表示请求体的内容类型
		 mockMvc.perform(get("/publishers/1")
		 .accept(MediaType.APPLICATION_JSON_UTF8))
		 .andExpect(status().isOk())
		 .andExpect(content().string(containsString("中文测试")))
		 .andExpect(jsonPath("$.name").value("中文测试")); 
	}


}
