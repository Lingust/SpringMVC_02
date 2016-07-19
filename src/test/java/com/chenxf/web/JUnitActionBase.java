package com.chenxf.web;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 配置文件的载入类
 * @description:若要实现Spring自动注入，必须继承此类
 * @author Chenxf
 * @since 2016年7月19日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
	"classpath*:/applicationContext.xml",
	"classpath*:/MVC-servlet.xml"
})
//添加事务，回滚对数据库的操作
//@Transactional
public class JUnitActionBase {

}