package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
