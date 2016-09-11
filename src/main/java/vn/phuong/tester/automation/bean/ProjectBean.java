/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.bean;


import java.util.List;

/**
 * ProjectBean -
 *
 * @author phuong.nguyenthi
 */
public class ProjectBean {
  private Long id;
  private String name;
  private String description;
  private UserBean owner;
  private List<FunctionBean> functions;

  public ProjectBean(Long id, String name, String description, UserBean owner, List<FunctionBean> functions){
    this.id = id;
    this.name = name;
    this.description = description;
    this.owner = owner;
    this.functions = functions;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public UserBean getOwner() {
    return owner;
  }

  public void setOwner(UserBean owner) {
    this.owner = owner;
  }

  public List<FunctionBean> getFunctions() {
    return functions;
  }

  public void setFunctions(List<FunctionBean> functions) {
    this.functions = functions;
  }
}
