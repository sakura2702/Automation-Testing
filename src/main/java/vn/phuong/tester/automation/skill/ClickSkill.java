package vn.phuong.tester.automation.skill;

import org.openqa.selenium.WebDriver;
import vn.phuong.tester.automation.config.UserConfiguration;
import vn.phuong.tester.automation.constraint.MessageEnum;
import vn.phuong.tester.automation.constraint.SkillType;
import vn.phuong.tester.automation.visitor.SkillVisitor;

import javax.swing.JTextArea;

public class ClickSkill extends AbstractTestSkill {
  private SelectionSkill selection;

  public ClickSkill() {
     
    this(new SelectionSkill());
  }

  public ClickSkill(SelectionSkill selection) {
    this(selection, null);
  }

  public ClickSkill(SelectionSkill selection, String description) {
    this.selection = selection;
    this.description = description;
  }

  public String getName() {
    return SkillType.CLICK.name();
  }

  @Override
  public boolean isValid() {
    if (selection != null) {
      return selection.isValid();
    }
    return false;
  }

  /**
   * @return the selection
   */
  public SelectionSkill getSelection() {
    return selection;
  }

  /**
   * @param selection the selection to set
   */
  public void setSelection(SelectionSkill selection) {
    this.selection = selection;
  }

  @Override
  public boolean accept(SkillVisitor visitor){
    return visitor.visit(this);
  }

  @Override
  public void buildLog(JTextArea log, WebDriver driver) {
    if(isValid()) {
      String description = String.format("Click on element [BY:%s][%s]", selection.getType().name(), selection.getSelector());
      log.append(String.format(MessageEnum.VALID_LOG_TEMPLATE, timerFommat.format(getCurrentSystemTime()), getName(), description, getResult() ? UserConfiguration.PASS : UserConfiguration.FAIL));
    } else {
      log.append(String.format(MessageEnum.INVALID_LOG_TEMPLATE, timerFommat.format(getCurrentSystemTime()), getName()));
    }
  }

}
