/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.skill;

import org.openqa.selenium.WebDriver;
import vn.phuong.tester.automation.visitor.SkillVisitor;

import javax.swing.*;

/**
 * Unknown -
 *
 * @author thinhnguyen
 */
public class UnknownSkill extends AbstractTestSkill  {

  @Override
  public boolean accept(SkillVisitor visitor){
    return false;
  }

  @Override
  public String getName() {
    return "Unknown Skill";
  }

  @Override
  public boolean isValid() {
    return false;
  }

  @Override
  public void buildLog(JTextArea log, WebDriver driver) {

  }

}
