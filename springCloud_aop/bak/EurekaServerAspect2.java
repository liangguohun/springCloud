package com.homefun;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
@Aspect
@Component
@Order(1000)
public class EurekaServerAspect2 {
	private final Logger logger = LoggerFactory.getLogger(EurekaServerAspect2.class);

	@Autowired
	IRegisterSevice registerSevice;

	@Pointcut("execution(public * org.springframework.cloud.netflix.eureka.server.EurekaServerAutoConfiguration.peerAwareInstanceRegistry(..))")
	public void instanceAspect() {
	}

	@Pointcut("execution(public * org.springframework.cloud.netflix.eureka.server.InstanceRegistry.register(..))")
	public void registerAspect() {
		
	}
	

	@Pointcut("execution(public * org.springframework.cloud.netflix.eureka.server.InstanceRegistry.cancel(..))")
	public void cancelAspect() {
		
	}

	@Pointcut("execution(public * org.springframework.cloud.netflix.eureka.server.InstanceRegistry.renew(..))")
	public void renewAspect() {
	}

	@Around("instanceAspect()")
	public Object instance(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("aspect joinPoint = " + joinPoint);
		// 暂时不做修改，待后续扩展
		return joinPoint.proceed();
	}

	@Around("registerAspect()")
	public void register(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("registerAspect()");
		logger.info("aspect joinPoint = " + joinPoint);
		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0) {
			InstanceInfo arg0 = (InstanceInfo) args[0];
			if (registerSevice.containInstanceInfo(arg0.getInstanceId(), arg0.getAppName())) {
				return;
			}
			// 注册新服务实例,保存入数据库
			registerSevice.insertMicroServerInstance((InstanceInfo) arg0);
		}
		// 加入自定义的注册的拦截逻辑
		joinPoint.proceed();
	}
	@Around("cancelAspect()")
	public Object cancel(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("cancelAspect()");
		logger.info("aspect joinPoint = " + joinPoint);
		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0) {
			String appName = (String) args[0];
			String serverId = (String) args[1];
			registerSevice.cancelInstanceInfo(serverId, appName);
		}
		// 加入自定义的反注册逻辑
		return joinPoint.proceed();
	}

	@Around("renewAspect()")
	public Object renew(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("renewAspect()");
		logger.info("aspect joinPoint = " + joinPoint);
		// 加入自定义的反注册逻辑
		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0) {
			String appName = (String) args[0];
			String serverId = (String) args[1];
			registerSevice.updateInstanceInfoHeartbeatTime(serverId, appName, System.currentTimeMillis(), null);
		}
		return joinPoint.proceed();
	}

}