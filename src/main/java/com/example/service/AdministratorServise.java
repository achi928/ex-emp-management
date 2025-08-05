package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * 管理者に関するデータベース処理を行うクラス
 * 
 * @author higuchi
 */
@Service
@Transactional
public class AdministratorServise {
  @Autowired
  private AdministratorRepository repository;

  /* 管理者情報を登録します。
   *
   * @param administrator 登録する管理者情報
   */
  public void insert(Administrator administrator) {
     repository.insert(administrator);
  }

}
