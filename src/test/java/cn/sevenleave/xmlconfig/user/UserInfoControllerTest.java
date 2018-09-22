package cn.sevenleave.xmlconfig.user;

import base.SpringMvcTestBase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author SevenLeave
 * @date 2018-08-03 10:50
 */
public class UserInfoControllerTest extends SpringMvcTestBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoControllerTest.class);

    // 虚拟的MVC对象
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    // 该测试有问题
    @Test
    public void toRegisterTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user/toRegister").param("key1", "value1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @Rollback
    public void registerTest() throws Exception {

//        MvcResult mvcResult = mockMvc.perform(post("/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .params()
//        )
    }

}
