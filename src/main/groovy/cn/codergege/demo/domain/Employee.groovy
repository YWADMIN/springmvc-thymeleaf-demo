package cn.codergege.demo.domain

import groovy.transform.Canonical

/**
 * Created by codergege on 17-3-2.
 */
@Canonical
class Employee {
    Integer id
    String name
    Integer gender
    Date birthday
    String email
    Department department
}
