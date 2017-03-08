package cn.codergege.demo.handler;

import cn.codergege.demo.dao.DepartmentDao;
import cn.codergege.demo.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private EmployeeDao employeeDao;

    // forward to /WEB-INF/templates/employee/list.html
    @RequestMapping("/emps")
    public String emps(Map<String, Object> map) {
        map.put("departments", departmentDao.getAll());
        System.out.println(departmentDao.getAll());
        return "employee/list";
    }

}
