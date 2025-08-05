package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;

/**
 * 管理者機能のコントローラークラス
 * 
 * @author higuchi
 */
@Controller
@RequestMapping("/")
public class AdiministratorController {

  /**
   * 
   * 管理者の登録画面へ遷移
   * 
   * @param form insertフォームオブジェクト
   * @return 管理者情報登録画面
   */
  @GetMapping("/toInsert")
  public String toInsert(InsertAdministratorForm form) {
    return "administrator/insert";
  }
  

}
