package com.example.domain;

/**
 * 管理者の情報を表すクラス
 * 
 * @author higuchi
 */
public class Administrator {
  private Integer id;
  private String name;
  private String mailAddres;
  private String password;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMailAddres() {
    return mailAddres;
  }

  public void setMailAddres(String mailAddres) {
    this.mailAddres = mailAddres;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Administrator [id=" + id + ", name=" + name + ", mailAddres=" + mailAddres + ", password=" + password + "]";
  }

}
