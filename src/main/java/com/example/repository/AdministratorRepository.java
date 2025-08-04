package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;


/**
 * 管理者情報に関するデータベース処理を行うリポジトリクラス
 * 
 * @author higuchi
 */
@Repository
public class AdministratorRepository {
  /**
   * 名前付きパラメータを使用した実行用のテンプレート
   * データベースアクセスに使用
   */
  @Autowired
  private NamedParameterJdbcTemplate templete;

  private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
    Administrator administrator = new Administrator();
    administrator.setId(rs.getInt("id"));
    administrator.setName(rs.getString("name"));
    administrator.setMailAddres(rs.getString("mailAddres"));
    administrator.setPassword(rs.getString("password"));
    return administrator;
  };

  /**
   * 管理者をデータベースに登録するメソッド
   * 
   * 引数で受け取った値をSqlParameterSourceで実行する
   * 
   * @param administrator 登録する管理者情報
   */
  public void insert(Administrator administrator) {
    SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

    String insertSql 
      = "insert into administrators(name, mailAddres, password) "
        + "values(:name, :mailAddres, :password);";
      templete.update(insertSql, param);
  }

  /**
   * メールアドレスと、パスワードに一致する管理者情報を取得
   * 一致する管理者がいない場合、nullを返します。
   * 
   * @param mailAddres メールアドレス
   * @param password パスワード
   * @return 管理者情報、検索できない場合はnull
   */
  public Administrator findByMailAddresAndPassword(String mailAddres, String password) {
    String sql = "select id, name, mailAddres, password "
                  + "from administrators where mailAddres = :mailAddres and password = :password;";
    
    SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddres", mailAddres).addValue("password", password);

    List<Administrator> administratorList = templete.query(sql, param, ADMINISTRATOR_ROW_MAPPER);

    if (administratorList.size() == 0) {
      return null;
    }
    return administratorList.get(0);
  }

}
