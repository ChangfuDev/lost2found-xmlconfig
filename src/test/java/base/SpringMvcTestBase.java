package base;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author SevenLeave
 * @date 2018-08-03 10:58
 */
// 1.加载上下文配置文件
@ContextConfiguration(locations = {"classpath:entry/dev/spring-entry.xml"})
// 2.先启动spring容器，把junit运行在spring容器中
@RunWith(SpringJUnit4ClassRunner.class)
// 3.所继承的类默认支持事务回滚，但是设置默认不回滚；在需要事务回滚的测试类或方法上使用@Rollback注解即可
@Rollback(value = false)
// 4.mvc测试，启动一个web服务
@WebAppConfiguration
public class SpringMvcTestBase {

    // web应用上下文
    @Autowired
    protected WebApplicationContext context;
}
