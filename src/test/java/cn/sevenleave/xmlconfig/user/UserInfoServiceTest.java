package cn.sevenleave.xmlconfig.user;

import base.SpringTestBase;
import cn.sevenleave.xmlconfig.user.model.UserInfo;
import cn.sevenleave.xmlconfig.user.service.IUserInfoService;
import cn.sevenleave.xmlconfig.support.utils.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @author SevenLeave
 * @date 2018-07-30 15:46
 */
public class UserInfoServiceTest extends SpringTestBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceTest.class);

    @Autowired
    private IUserInfoService userInfoService;

    @Test
    @Rollback
    public void addUserTest() throws Exception {
        UserInfo user = new UserInfo();
        user.setUuid(StringUtils.uuid());
        user.setUserName("user1");
        user.setUserPsd("psd1");
        user.setPhone("15858137654");
        int rows = userInfoService.addUser(user);
        System.out.println("rows = " + rows);
    }

}
