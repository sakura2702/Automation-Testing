package vn.phuong.tester.automation.skill;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.phuong.tester.automation.config.UserConfiguration;
import vn.phuong.tester.automation.constraint.MessageEnum;
import vn.phuong.tester.automation.constraint.SelectionType;
import vn.phuong.tester.automation.constraint.SkillType;
import vn.phuong.tester.automation.visitor.SkillExecuteVisitor;
import vn.phuong.tester.automation.visitor.SkillVisitor;

import javax.swing.JTextArea;

public class SelectionSkill extends AbstractTestSkill {
    private SelectionType type;
    private String selector;

    private String textContent;
    @JsonIgnore
    private WebElement selectedWebElement;

    public SelectionSkill() {
        this(SelectionType.ID, null, null);
    }

    public SelectionSkill(SelectionType type, String selector) {
        this(type, selector, null);
    }

    public SelectionSkill(SelectionType type, String selector, String description) {
        this.type = type;
        this.selector = selector;
        this.description = description;
    }


    @Override
    public boolean accept(SkillVisitor visitor) {
        return visitor.visit(this);
    }


    @Override
    public String getName() {
        return SkillType.SELECT.name();
    }

    @Override
    public boolean isValid() {
        return StringUtils.isNotEmpty(selector) && type != null;
    }

    @Override
    public void buildLog(JTextArea log, WebDriver driver) {
        if(isValid()) {
            String description = String.format("Select element [BY:%s][%s]", getType().name(), getSelector());
            log.append(String.format(MessageEnum.VALID_LOG_TEMPLATE, timerFommat.format(getCurrentSystemTime()), getName(), description, getResult() ? UserConfiguration.PASS : UserConfiguration.FAIL));
        } else {
            log.append(String.format(MessageEnum.INVALID_LOG_TEMPLATE, timerFommat.format(getCurrentSystemTime()), getName()));
        }
    }

    /**
     * @return the type
     */
    public SelectionType getType() {
        return type;
    }


    /**
     * @return the selector
     */
    public String getSelector() {
        return selector;
    }

    /**
     * @param selector the selector to set
     */
    public void setSelector(String selector) {
        this.selector = selector;
    }

    /**
     * @param type the type to set
     */
    public void setType(SelectionType type) {
        this.type = type;
    }

    public String getTextContent(WebDriver webDriver, SkillExecuteVisitor visitor) {
        String result = selectedWebElement != null ? selectedWebElement.getText() : StringUtils.EMPTY;
        if (StringUtils.isEmpty(result)) {
            result = ((JavascriptExecutor) webDriver).executeScript("return arguments[0].textContent", selectedWebElement).toString();
        }
        textContent = result;
        return textContent;
    }

    @JsonIgnore
    public WebElement getSelectedWebElement() {
        return selectedWebElement;
    }

    @JsonIgnore
    public void setSelectedWebElement(WebElement selectedWebElement) {
        this.selectedWebElement = selectedWebElement;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
