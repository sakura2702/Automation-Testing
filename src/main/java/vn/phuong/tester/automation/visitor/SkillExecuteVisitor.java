/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.visitor;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.phuong.tester.automation.config.UserConfiguration;
import vn.phuong.tester.automation.service.MyExcelHelper;
import vn.phuong.tester.automation.skill.*;

import javax.swing.JTextArea;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SkillExecuteVisitor
 *
 * @author phuong.nguyenthi
 */
public class SkillExecuteVisitor implements SkillVisitor {

  private final WebDriver webDriver;
  private final JTextArea log;
  private final MyExcelHelper myExcelHelper;
  private Logger LOGGER = Logger.getLogger(this.getClass().getName());

  public SkillExecuteVisitor(WebDriver webDriver, JTextArea log, MyExcelHelper excelHepler){
    this.webDriver = webDriver;
    this.log = log;
    this.myExcelHelper = excelHepler;
  }

  public boolean visit(OpenSkill openSkill){
    new WebDriverWait(webDriver, 50)
            .until(new ExpectedCondition<Boolean>() {
              @Override
              public Boolean apply(WebDriver input) {
                try {

                  Thread.sleep(5000);
                } catch (InterruptedException ex) {
                  LOGGER.log(Level.SEVERE, "", ex);
                }
                return true;
              }
            });
    try {
      webDriver.switchTo().defaultContent();
    } catch (Exception ex){
      LOGGER.log(Level.SEVERE, "No alert" , ex);
    }
    try {
      if(openSkill.isValid()) {webDriver.get(openSkill.getUrl());}
    }catch (Exception ex){
      LOGGER.log(Level.SEVERE, "Cannot access url:" + openSkill.getUrl(), ex);
    }
    writeBackResult(openSkill, true);
    return true;
  }

  public boolean visit(InputSkill inputSkill) {
    boolean result = false;
    try {
      //Execute
      if (checkValid(inputSkill) && inputSkill.getSelection().accept(this)) {
          WebElement webElement = inputSkill.getSelection().getSelectedWebElement();
          if (webElement != null) {
            try {
              webElement.click();
              webElement.clear();
              webElement.sendKeys(inputSkill.getInputValue());
              new WebDriverWait(webDriver, 50)
                      .until(new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(WebDriver input) {
                          try {

                            Thread.sleep(5000);
                          } catch (InterruptedException ex) {
                            LOGGER.log(Level.SEVERE, "", ex);
                          }
                          return true;
                        }
                      });
              inputSkill.getSelection().accept(this);
              if(!webElement.getAttribute("value").equals(inputSkill.getInputValue())) {
                inputSkill.getSelection().accept(this);
                webElement.sendKeys(inputSkill.getInputValue());
              }
              result = true;
            }catch (StaleElementReferenceException | InvalidElementStateException ex){
              inputSkill.getSelection().accept(this);
              return this.visit(inputSkill);
            }
          }
        }
      } catch(Exception ex){
        LOGGER.log(Level.SEVERE, "Cannot execute input skill", ex);
      }
    writeBackResult(inputSkill, result);
    return result;

  }

  public boolean visit(ClickSkill clickSkill){
    boolean result = false;
    //Execute
    try {
      if (checkValid(clickSkill) && clickSkill.getSelection().accept(this)) {
        WebElement webElement = clickSkill.getSelection().getSelectedWebElement();
        if (webElement != null) {
          scroll2Element(webElement);
//          ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", webElement);
          webElement.click();
          result = true;
        }
      }
    }catch (Exception ex){
      LOGGER.log(Level.SEVERE, "Cannot execute click skill. Element has been invisible", ex);
    }
    //Build result
    writeBackResult(clickSkill, result);
    new WebDriverWait(webDriver, 50)
            .until(new ExpectedCondition<Boolean>() {
              @Override
              public Boolean apply(WebDriver input) {
                try {

                  Thread.sleep(5000);
                } catch (InterruptedException ex) {
                  LOGGER.log(Level.SEVERE, "", ex);
                }
                return true;
              }
            });
    return result;
  }

  public boolean visit(VerifySkill verifySkill){
    boolean result = false;

    new WebDriverWait(webDriver, 50)
            .until(new ExpectedCondition<Boolean>() {
              @Override
              public Boolean apply(WebDriver input) {
                try {

                  Thread.sleep(5000);
                } catch (InterruptedException ex) {
                  LOGGER.log(Level.SEVERE, "", ex);
                }
                return true;
              }
            });
    //Execute
    try {
      if (checkValid(verifySkill) && verifySkill.getSelection().accept(this)) {
        WebElement webElement = verifySkill.getSelection().getSelectedWebElement();
        if (webElement != null) {
          try{
            String textContent = verifySkill.getSelection().getTextContent(webDriver, this);
            if(StringUtils.isEmpty(textContent)){
              verifySkill.getSelection().accept(this);
              textContent = verifySkill.getSelection().getTextContent(webDriver, this);
            }
            writeBackData(verifySkill, textContent, UserConfiguration.ACTUAL_COL);
            if (textContent.trim().equals(verifySkill.getExpectedValue().trim())) {
              result = true;
            }
          } catch(StaleElementReferenceException ex){
            verifySkill.getSelection().accept(this);
            return this.visit(verifySkill);
          }
        }
      }
    }catch(Exception e){
      LOGGER.log(Level.SEVERE, "Cannot execute verify skill", e);
    }
    //Build result
    writeBackResult(verifySkill, result);
    return result;
  }

  public boolean visit(SelectionSkill selectionSkill){
    boolean result = false;
    //Execute
    if(checkValid(selectionSkill)) {
      try {
        String selector = selectionSkill.getSelector().trim();
        By by;
        switch (selectionSkill.getType()) {
          case ID:
            by = By.id(selector);
            break;
          case NAME:
            by = By.name(selector);
            break;
          case CLASS:
            by = By.className(selector);
            break;
          case LINK_TEXT:
            by = By.linkText(selector);
            break;
          case PARTIAL_LINK_TEXT:
            by = By.partialLinkText(selector);
            break;
          case CSS_SELECTOR:
            by = By.cssSelector(selector);
            break;
          case XPATH:
            by = By.xpath(selector);
            break;
          default:
            return false;
        }
        WebDriverWait wait= new WebDriverWait(webDriver, 20);// Explicit wait()
        wait/*.withTimeout(15000, TimeUnit.SECONDS)*/.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement selectedElement = webDriver.findElement(by);
        selectionSkill.setSelectedWebElement(selectedElement);
        result = selectedElement != null;
      } catch (Exception ex) {
        LOGGER.log(Level.SEVERE, "Element does not exist", ex);
      }
    }
    //Build result
    writeBackResult(selectionSkill, result);
    return result;
  }

  public boolean visit(UnknownSkill unknownSkill){
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private boolean checkValid(AbstractTestSkill skill){
    return skill.isValid();
  }

  private void writeBackData(AbstractTestSkill testSkill, String data, int col){
    if(myExcelHelper != null) {
      myExcelHelper.setData(data, testSkill.getResultRow(), col);
      myExcelHelper.setData(UserConfiguration.DATE_TIME_FORMAT.format(System.currentTimeMillis()), testSkill.getResultRow(), UserConfiguration.MESSAGE_COL);
    }
  }
  private void writeBackResult(AbstractTestSkill testSkill, boolean bool){
    testSkill.setResult(bool);
    testSkill.buildLog(log, webDriver);
    writeBackData(testSkill, bool ? UserConfiguration.PASS : UserConfiguration.FAIL, UserConfiguration.RESULT_COL);
  }
  private void scroll2Element(WebElement webElement){
    int elementPosition = webElement.getLocation().getY();
    String js = String.format("window.scroll(0, %s)", elementPosition);
    ((JavascriptExecutor) webDriver).executeScript(js);
  }

}
