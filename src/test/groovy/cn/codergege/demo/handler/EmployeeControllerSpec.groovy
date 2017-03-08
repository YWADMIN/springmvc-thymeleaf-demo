package cn.codergege.demo.handler

import cn.codergege.demo.dao.DepartmentDao
import cn.codergege.demo.domain.Department
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import spock.lang.Specification

class EmployeeControllerSpec extends Specification {
    EmployeeController controller = new EmployeeController()
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    def "test emps"() {
        expect:
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/emps"))
            .andExpect(view().name('employee/list'))
            .andExpect(status().isOk())
            .andDo(print())
    }
}
