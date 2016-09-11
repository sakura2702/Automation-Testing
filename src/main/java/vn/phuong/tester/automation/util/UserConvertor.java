/*
 * Copyright (c) 2015 Ekino
 */
package vn.phuong.tester.automation.util;

import vn.phuong.tester.automation.bean.UserBean;
import vn.phuong.tester.automation.model.User;

import java.util.function.Function;

/**
 * UserConvertor -
 *
 * @author phuong.nguyenthi
 */
public class UserConvertor {

  public static Function<UserBean, User> convertUser = userBean -> {
    if (userBean == null) {
      return null;
    }
    User user = new User();
    user.setLogin(userBean.getLogin());
    user.setPassword(userBean.getPassword());
    user.setUsername(userBean.getUsername());
    return user;
  };

  public static Function<User, UserBean> convertUserBean = user -> {
    if (user == null) {
      return null;
    }
    return  new UserBean (
      user.getLogin(),
      user.getPassword(),
      user.getUsername());
  };

}
