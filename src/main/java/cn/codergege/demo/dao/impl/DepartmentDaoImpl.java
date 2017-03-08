package cn.codergege.demo.dao.impl;

import cn.codergege.demo.dao.DepartmentDao;
import cn.codergege.demo.domain.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private final Logger log = LoggerFactory.getLogger(DepartmentDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Department> getAll() {
        String sql = "select id, name from t_department";
        List<Department> departments = jdbcTemplate.query(sql, new RowMapper<Department>() {

            @Override
            public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                return department;
            }
        });
        return departments;
    }
}
