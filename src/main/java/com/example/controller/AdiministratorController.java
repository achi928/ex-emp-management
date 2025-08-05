package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorServise;

/**
 * 管理者機能のコントローラークラス
 * 
 * @author higuchi
 */
@Controller
@RequestMapping("/")
public class AdiministratorController {

  @Autowired
  private AdministratorServise servise;

  /**
   * 
   * 管理者の登録画面へ遷移
   * 
   * @param form insertフォームオブジェクト
   * @return 管理者情報登録画面
   */

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

  @PostMapping("/insert")
  public String insert(InsertAdministratorForm form) {
    Administrator administrator = new Administrator();
    BeanUtils.copyProperties(form, administrator);
    servise.insert(administrator);
    return "redirect:/";
  }
  

}
