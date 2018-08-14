package cn.sevenleave.xmlconfig.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 描述：${description}.<br>
 *
 * @author SevenLeave
 * @date 2018-08-12 16:08
 */
public class JdbcRealmTest {

    @Test
    public void jdbcRealmTest() {

        JdbcRealm jdbcRealm = new JdbcRealm();

        // 设置realm
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        // 登录
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "12345");
        subject.login(token);
        // 认证判断
        System.out.println("isAuthenticated:" + subject.isAuthenticated());


    }
}
