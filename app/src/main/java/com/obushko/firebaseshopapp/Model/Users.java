package com.obushko.firebaseshopapp.Model;

public class Users {
  private String userName;
  private String password;
  private String phone;

  public Users() {
  }

  public Users(String userName, String password, String phone) {
    this.userName = userName;
    this.phone = phone;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
