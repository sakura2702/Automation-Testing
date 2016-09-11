/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.bean;

import vn.phuong.tester.automation.config.UserConfiguration;

/**
 * FunctionBean -
 *
 * @author phuong.nguyenthi
 */
public class FunctionBean {
  private Long id;
  private String name;
  private int totalScript;
  private ProjectBean projectBean;
  private TestScriptDataBean testScriptData = new TestScriptDataBean();
  ///User configuration
  private UserConfiguration userConfiguration;

  public FunctionBean(){

  }

  public FunctionBean(Long id, String name, int totalScript, ProjectBean projectBean, TestScriptDataBean testCases, UserConfiguration configuration){
    this.id = id;
    this.name = name;
    this.totalScript = totalScript;
    this.projectBean = projectBean;
    this.testScriptData = testCases;
    this.userConfiguration = configuration != null ? configuration : new UserConfiguration();
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

  public int getTotalScript() {
    return totalScript;
  }

  public void setTotalScript(int totalScript) {
    this.totalScript = totalScript;
  }

  public ProjectBean getProjectBean() {
    return projectBean;
  }

  public void setProjectBean(ProjectBean projectBean) {
    this.projectBean = projectBean;
  }

  public TestScriptDataBean getTestScriptData() {
    return testScriptData;
  }

  public void setTestScriptData(TestScriptDataBean testScriptData) {
    this.testScriptData = testScriptData;
  }

  public UserConfiguration getUserConfiguration() {
    if (this.userConfiguration != null) {
        return userConfiguration;
    }
    return new UserConfiguration();
  }

  public void setUserConfiguration(UserConfiguration userConfiguration) {
    this.userConfiguration = userConfiguration;
  }
}
