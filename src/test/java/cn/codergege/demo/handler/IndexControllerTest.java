package cn.codergege.demo.handler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class IndexControllerTest {
    private IndexController controller;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        controller = new IndexController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void testHome() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("date"))
//                .andExpect(MockMvcResultMatchers.view().name("forward:/index.html"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()) // 输出 MvcResult 对象到控制台
                .andReturn(); // 返回 MvcResult 对象

        // 得到的 result 可以自定义断言
        Assert.assertNotNull(result.getModelAndView());
    }
}

			