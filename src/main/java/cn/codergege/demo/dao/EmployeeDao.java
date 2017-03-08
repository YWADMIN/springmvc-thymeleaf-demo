package cn.codergege.demo.dao;

import cn.codergege.demo.domain.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAll();
}
