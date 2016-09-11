/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.visitor;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import vn.phuong.tester.automation.skill.ClickSkill;
import vn.phuong.tester.automation.skill.InputSkill;
import vn.phuong.tester.automation.skill.OpenSkill;
import vn.phuong.tester.automation.skill.SelectionSkill;
import vn.phuong.tester.automation.skill.UnknownSkill;
import vn.phuong.tester.automation.skill.VerifySkill;

import java.util.List;
import java.util.logging.Logger;

/**
 * SkillExecuteVisitor
 *
 * @author phuong.nguyenthi
 */
public class SkillCollectorVisitor implements SkillVisitor {

  private final List<String> actionName;
  private final List<String> selectors;
  private final List<String> selectorsType;
  private final List<String> inputs;
  private final List<String> actuals;
  private final List<String> results;
  private final List<String> message;
  private Logger LOGGER = Logger.getLogger(this.getClass().getName());

  public SkillCollectorVisitor(){
    actionName = Lists.newArrayList();
    selectors  = Lists.newArrayList();
    inputs = Lists.newArrayList();
    actuals = Lists.newArrayList();
    results = Lists.newArrayList();
    message = Lists.newArrayList();
    selectorsType = Lists.newArrayList();
  }

  public boolean visit(OpenSkill openSkill){
    getActionName().add(openSkill.getName());
    getSelectorsType().add(StringUtils.EMPTY);
    getSelectors().add(StringUtils.EMPTY);
    getInputs().add(openSkill.getUrl());
    getActuals().add(StringUtils.EMPTY);
    getResults().add(String.valueOf(openSkill.getResult()));
    getMessage().add(openSkill.getDescription());
    return true;
  }

  public boolean visit(InputSkill inputSkill) {
    getActionName().add(inputSkill.getName());
    getSelectorsType().add(inputSkill.getSelection().getType().name());
    getSelectors().add(inputSkill.getSelection().getSelector());
    getInputs().add(inputSkill.getInputValue());
    getActuals().add(StringUtils.EMPTY);
    getResults().add(String.valueOf(inputSkill.getResult()));
    getMessage().add(inputSkill.getDescription());
    return true;
  }

  public boolean visit(ClickSkill clickSkill){
    getActionName().add(clickSkill.getName());
    getSelectorsType().add(clickSkill.getSelection().getType().name());
    getSelectors().add(clickSkill.getSelection().getSelector());
    getInputs().add(StringUtils.EMPTY);
    getActuals().add(StringUtils.EMPTY);
    getResults().add(String.valueOf(clickSkill.getResult()));
    getMessage().add(clickSkill.getDescription());
    return true;
  }

  public boolean visit(VerifySkill verifySkill){
    getActionName().add(verifySkill.getName());
    getSelectorsType().add(verifySkill.getSelection().getType().name());
    getSelectors().add(verifySkill.getSelection().getSelector());
    getInputs().add(StringUtils.EMPTY);
    getActuals().add(verifySkill.getExpectedValue());
    getResults().add(String.valueOf(verifySkill.getResult()));
    getMessage().add(verifySkill.getDescription());
    return true;
  }

  public boolean visit(SelectionSkill selectionSkill){
    getActionName().add(selectionSkill.getName());
    getSelectorsType().add(selectionSkill.getType().name());
    getSelectors().add(selectionSkill.getSelector());
    getInputs().add(StringUtils.EMPTY);
    getActuals().add(StringUtils.EMPTY);
    getResults().add(String.valueOf(selectionSkill.getResult()));
    getMessage().add(selectionSkill.getDescription());
    return true;
  }

  public boolean visit(UnknownSkill unknownSkill){
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public List<String> getActionName() {
    return actionName;
  }

  public List<String> getSelectors() {
    return selectors;
  }

  public List<String> getSelectorsType() {
    return selectorsType;
  }

  public List<String> getInputs() {
    return inputs;
  }

  public List<String> getActuals() {
    return actuals;
  }

  public List<String> getMessage() {
    return message;
  }

  public List<String> getResults() {
    return results;
  }
}
