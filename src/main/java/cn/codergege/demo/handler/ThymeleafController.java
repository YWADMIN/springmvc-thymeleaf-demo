package cn.codergege.demo.handler;

import cn.codergege.demo.dao.DepartmentDao;
import cn.codergege.demo.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/demo1")
    public String demo1(Map<String, Object> map) {
        map.put("departments", departmentDao.getAll());
        map.put("employees", employeeDao.getAll());
        return "thymeleaf/demo1";
    }

    @RequestMapping("/demo2")
    public String demo2(Map<String, Object> map) {
        map.put("departments", departmentDao.getAll());
        map.put("employees", employeeDao.getAll());
        return "thymeleaf/demo2";
    }

    @RequestMapping("/demo3")
    public String demo3(Map<String, Object> map) {
        map.put("departments", departmentDao.getAll());
        map.put("employees", employeeDao.getAll());
        return "thymeleaf/demo3";
    }

    @RequestMapping("/demo4")
    public String demo4(Map<String, Object> map) {
        map.put("departments", departmentDao.getAll());
        map.put("employees", employeeDao.getAll());
        return "thymeleaf/demo4";
    }
}
