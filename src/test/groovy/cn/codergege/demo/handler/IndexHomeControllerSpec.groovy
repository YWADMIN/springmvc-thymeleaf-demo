package cn.codergege.demo.handler

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IndexHomeControllerSpec extends Specification {
    IndexController controller = new IndexController()
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    def "test index"() {
        //given:
        //HelloService.getHelloMessage() >> 'hello world'
        expect:
        mockMvc.perform(get('/'))
            .andExpect(model().attributeExists('date'))
            .andExpect(view().name('index'))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
    }

    def "test homePage"() {
        expect:
        mockMvc.perform(get('/homepage'))
            .andExpect(view().name('redirect:/index'))
            .andExpect(status().is3xxRedirection())
            .andDo(MockMvcResultHandlers.print())
    }
}
