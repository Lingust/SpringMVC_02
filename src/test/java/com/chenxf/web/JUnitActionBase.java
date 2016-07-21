package com.chenxf.web;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 配置文件的载入类
 * @description:若要实现Spring自动注入，必须继承此类
 * @author Chenxf
 * @since 2016年7月19日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value="src/main/webapp")
@ContextHierarchy({
	@ContextConfiguration(name="parent",locations="classpath*:/applicationContext.xml"),
	@ContextConfiguration(name="child", locations="classpath*:/MVC-servlet.xml")
})
/*@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value="src/main/webapp")
@ContextHierarchy({
	@ContextConfiguration(name="parent",classes=AppConfig.class),
	@ContextConfiguration(name="child", classes=MvcConfig.class)
})*/
//添加事务，回滚对数据库的操作
//@Transactional
public abstract class JUnitActionBase {

}