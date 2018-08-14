package cn.sevenleave.xmlconfig.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * 描述：${description}.<br>
 *
 * @author SevenLeave
 * @date 2018-08-11 22:51
 */
public class InlRealmTest {

    @Test
    public void authenticationTest() {

        IniRealm iniRealm = new IniRealm("classpath:shiro/user.ini");

        // 设置realm
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        // 登录
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "12345");
        subject.login(token);
        // 认证判断
        System.out.println("isAuthenticated:" + subject.isAuthenticated());

        // 角色验证
        subject.checkRole("admin");

        // 权限验证
        subject.checkPermission("user:delete");
        subject.checkPermission("user:update");

    }
}
