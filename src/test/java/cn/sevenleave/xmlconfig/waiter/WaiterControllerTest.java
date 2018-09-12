package cn.sevenleave.xmlconfig.waiter;

import base.SpringMvcTestBase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author SevenLeave
 * @date 2018-08-03 11:37
 */
public class WaiterControllerTest extends SpringMvcTestBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaiterControllerTest.class);

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    // 该测试有问题
    @Test
    public void getCacheRecordsTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/waiter/cacherecords")
                        .param("pageNum", "1")
                        .param("pageSize", "3"))
//                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        String content = response.getContentAsString();
//        System.out.println("content = " + content);
    }

}
