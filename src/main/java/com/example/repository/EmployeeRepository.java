package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {
  /**
   * 名前付きパラメータを使用した実行用のテンプレート
   * データベースアクセスに使用
   */
  @Autowired
  private NamedParameterJdbcTemplate templete;

  private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
    Employee employee = new Employee();
    employee.setId(rs.getInt("id"));
    employee.setName(rs.getString("name"));
    employee.setImage(rs.getString("image"));
    employee.setGender(rs.getString("gender"));
    employee.setHiredate(rs.getDate("hiredate"));
    employee.setMailAddres(rs.getString("mailAddres"));
    employee.setZipCode(rs.getString("zipCode"));
    employee.setAddress(rs.getString("addres"));
    employee.setTelephone(rs.getString("telephone"));
    employee.setSalary(rs.getInt("salary"));
    employee.setCharacteristics(rs.getString("characteristics"));
    employee.setDependentsCount(rs.getInt("dependentsCount"));
    return employee;
  };

  public List<Employee> findAll() {
    String sql = "select id, name, image, gender, hiredate, mailAdres, zipCode, addres, telephone, salary, characteristics, dependentsCount "
                  + "from employees order by hiredate desc;";
    
    List<Employee> employeeList = templete.query(sql, EMPLOYEE_ROW_MAPPER);

    if (employeeList.size() == 0) {
      return null;
    }
    return employeeList;
  }

  public Employee load(Integer id) {
    String sql = "select * from employees where id = :id;";

    SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

    try {
      return templete.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
    } catch (Exception e) {
      return null;
    }
  }

  public void update(Employee employee) {
    SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

    String updatesql = "update employees set name = :name, image = :image, gender = :gender, hiredate = :hiredate, mailAddres = :mailAddres, "
                  + "zipCode = :zipCode, addres = :addres, telephone = :telephone, salary = :salary, characteristics = :characteristics, dependentsCount = :dependentsCount "
                  + " where id = :id";
    
    templete.update(updatesql, param);

  }
}
