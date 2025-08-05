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

/**
 * employees テーブルにアクセスするためのリポジトリクラス。
 * 
 * 従業員情報の検索、取得、更新処理をします
 * @author higuchi
 */
@Repository
public class EmployeeRepository {
  /**
   * 名前付きパラメータを使用した実行用のテンプレート
   * データベースアクセスに使用
   */
  @Autowired
  private NamedParameterJdbcTemplate templete;

  /**
   * ResultSet の 1 行分のデータを Employee オブジェクトに変換する RowMapper。
   */
  private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
    Employee employee = new Employee();
    employee.setId(rs.getInt("id"));
    employee.setName(rs.getString("name"));
    employee.setImage(rs.getString("image"));
    employee.setGender(rs.getString("gender"));
    employee.setHireDate(rs.getDate("hire_date"));
    employee.setMailAddress(rs.getString("mail_address"));
    employee.setZipCode(rs.getString("zip_code"));
    employee.setAddress(rs.getString("address"));
    employee.setTelephone(rs.getString("telephone"));
    employee.setSalary(rs.getInt("salary"));
    employee.setCharacteristics(rs.getString("characteristics"));
    employee.setDependentsCount(rs.getInt("dependents_count"));
    return employee;
  };

  /**
   * 全従業員情報を入社日の降順で取得します。
   * 
   * @return 従業員のリスト、ない場合はnullが返ります。
   */
  public List<Employee> findAll() {
    String sql = "select id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count "
                  + "from employees order by hire_date desc;";
    
    List<Employee> employeeList = templete.query(sql, EMPLOYEE_ROW_MAPPER);

    if (employeeList.size() == 0) {
      return null;
    }
    return employeeList;
  }

  /**
   * 指定されたIDの従業員情報を取得します。
   * 
   * @param id 取得対象の従業員ID
   * @return 指定された従業員情報、ない場合はnull
   */
  public Employee load(Integer id) {
    String sql = "select * from employees where id = :id;";

    SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

    try {
      return templete.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 指定したidから、従業員情報を更新します
   * @param employee 更新対象の従業員情報
   */
  public void update(Employee employee) {
    SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

    String updatesql = "update employees set name = :name, image = :image, gender = :gender, hire_date = :hireDate, mail_address = :mailAddress, "
                  + "zip_code = :zipCode, address = :address, telephone = :telephone, salary = :salary, characteristics = :characteristics, dependents_count = :dependentsCount "
                  + " where id = :id";
    
    templete.update(updatesql, param);

  }
}
