package cn.codergege.demo.dao.impl;

import cn.codergege.demo.dao.EmployeeDao;
import cn.codergege.demo.domain.Department;
import cn.codergege.demo.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private final Logger log = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAll() {
        log.info("Querying all employees by getAll() ...");

        String sql = "select a.id, a.name, a.gender, a.birthday, a.email, b.id as deptId, b.name as deptName" +
                " from t_employee a left outer join t_department b " +
                " on a.dept_id = b.id " +
                " where 1 = 1";
        List<Employee> employees = new ArrayList<>();
        employees = jdbcTemplate.query(sql, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setGender(rs.getInt("gender"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setEmail(rs.getString("email"));
                Department department = new Department();
                department.setId(rs.getInt("deptId"));
                department.setName(rs.getString("deptName"));
                employee.setDepartment(department);
                return employee;
            }
        });
        return employees;
    }
}
