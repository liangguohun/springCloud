package com.homefun;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/****************************************************************************************
实现AOP的切面主要有以下几个要素：

使用@Aspect注解将一个java类定义为切面类
使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
根据需要在切入点不同位置的切入内容
使用@Before在切入点开始处切入内容
使用@After在切入点结尾处切入内容
使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
使用@Order(i)注解来标识切面的优先级。i的值越小，优先级越高

执行日志顺序:
总结：doBefore 方法先从高优先级到低优先级依次执行完，然后 doAfterReturning 方法从低优先级到高优先级依次执行完；也就是进入从高到低，出来从低到高；
****************************************************************************************/

/**
 * todo   	监控数据访问的性能
 * author 	liangguohun
 * datetime 2017年12月31日 上午12:38:29
 */
@Aspect
@Component
@Order(2)
public class DaoAspect {
	private final Logger logger = LoggerFactory.getLogger(DaoAspect.class);
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	ThreadLocal<String> daoName = new ThreadLocal<>();
	private static final String PRE_TAG = "(Order(1))************** ";
	/**
	 * todo:		对controller做切面	
	 * author:		梁国魂
	 * datetime:	2017年12月31日 上午12:46:09
	 */
	@Pointcut("execution(public * com.app.dao..*.*(..))")
	public void doDao() {
		
	}
	
    @Before("doDao()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
//        logger.info(PRE_TAG + "(doBefore) URL : " + request.getRequestURL().toString());
//        logger.info(PRE_TAG + "(doBefore) HTTP_METHOD : " + request.getMethod());
//        logger.info(PRE_TAG + "(doBefore) IP : " + request.getRemoteAddr());
//        logger.info(PRE_TAG + "(doBefore) CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info(PRE_TAG + "(doBefore) ARGS : " + Arrays.toString(joinPoint.getArgs()));
        daoName.set(joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "ret", pointcut = "doDao()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
//        logger.info(PRE_TAG + "(doAfterReturning) RESPONSE : " + ret);
//        logger.info(PRE_TAG + "(doAfterReturning) SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    	logger.info(String.format("调用dao[%s], 耗时[%d 毫秒]", daoName.get(), (System.currentTimeMillis() - startTime.get())  ));
    }
}