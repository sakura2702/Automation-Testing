/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.model;


import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * User
 *
 * @author phuong.nguyenthi
 */
@Entity
public class User {

  private String login;
  private String password;
  private String username;


  @Column (length = 128, nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(length = 64, nullable = false, unique = true)
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Column(length = 200, nullable = false)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
