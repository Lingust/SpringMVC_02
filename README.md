# SpringMVC_02

环境：Windows 7 X64 7601<br/>
IDE：Eclipse Java EE IDE for Web Developers.<br/>
版本：Mars.2 Release (4.5.2)<br/>
项目构建工具：Apache Maven 3.3.9<br/>
<br/>
<h2>spring数据验证</h2>
<p>导入HIbernate-validator包，包内类实现了javax.validation内的接口。配合spring validator使用。</p>
<p>在控制层验证时出现验证信息无法写入BindingResult类的情况，原因是未加载<br>
LocalValidtorFactoryBean，applicationContext.xml和servlet配置文件中启用<br>
<mvc:annotation-driven/>即可。</p>