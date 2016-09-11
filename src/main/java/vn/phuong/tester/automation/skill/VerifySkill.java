package vn.phuong.tester.automation.skill;

import javax.swing.JTextArea;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import vn.phuong.tester.automation.config.UserConfiguration;
import vn.phuong.tester.automation.constraint.MessageEnum;
import vn.phuong.tester.automation.constraint.SkillType;
import vn.phuong.tester.automation.visitor.SkillVisitor;

public class VerifySkill extends AbstractTestSkill {

  private String expectedValue;
  private SelectionSkill selection;

  public VerifySkill() {
    this(new SelectionSkill(), null);
  }

  public VerifySkill(SelectionSkill selection, String expectedValue) {
    this(selection, expectedValue, null);
  }

  public VerifySkill(SelectionSkill selection, String expectedValue, String description) {
    this.selection = selection;
    this.expectedValue = expectedValue;
    this.description = description;
  }


  public String getName() {
    return SkillType.VERIFY.name();
  }

  @Override
  public boolean isValid() {
    return selection != null && selection.isValid();
  }

  @Override
  public void buildLog(JTextArea log, WebDriver driver) {
    if(isValid()) {
      String description = String.format("Kiểm tra phần tử [BY:%s][%s]\n. Kiểm tra nội dung [%s] trùng khớp với nội dung mong muốn [%s]", selection.getType().name(), selection.getSelector(), selection.getSelectedWebElement() != null ? selection.getTextContent() : StringUtils.EMPTY, expectedValue);
      log.append(String.format(MessageEnum.VALID_LOG_TEMPLATE, timerFommat.format(getCurrentSystemTime()), getName(), description, getResult() ? UserConfiguration.PASS : UserConfiguration.FAIL));
    } else {
      log.append(String.format(MessageEnum.INVALID_LOG_TEMPLATE, timerFommat.format(getCurrentSystemTime()), getName()));
    }
  }

  /**
   * @return the expectedValue
   */
  public String getExpectedValue() {
    return expectedValue;
  }

  /**
   * @param expectedValue the expectedValue to set
   */
  public void setExpectedValue(String expectedValue) {
    this.expectedValue = expectedValue;
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
  public boolean accept(SkillVisitor visitor) {
    return visitor.visit(this);
  }

}
