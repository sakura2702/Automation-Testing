/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.phuong.tester.automation.skill;

import org.openqa.selenium.WebDriver;
import vn.phuong.tester.automation.config.UserConfiguration;
import vn.phuong.tester.automation.constraint.MessageEnum;
import vn.phuong.tester.automation.constraint.SkillType;
import vn.phuong.tester.automation.visitor.SkillVisitor;

import javax.swing.JTextArea;

/**
 *
 * @author ADMIN
 */
public class InputSkill extends AbstractTestSkill {

    private String inputValue;
    private SelectionSkill selection;

    public InputSkill() {
       this(new SelectionSkill(), null);
    }
    
    public InputSkill(SelectionSkill selection, String inputValue) {
       this(selection, inputValue, null);
    }
    
    public InputSkill(SelectionSkill selection, String inputValue, String description) {
        this.selection = selection;
        this.inputValue = inputValue;
        this.description = description;
    }

    public String getName() {
        return SkillType.INPUT.name();
    }

    @Override
    public boolean isValid() {
        return selection.isValid();
    }

    @Override
    public void buildLog(JTextArea log, WebDriver driver) {
        if(isValid()) {
            String description = String.format("Input element [BY:%s][%s]", selection.getType().name(), selection.getSelector());
            log.append(String.format(MessageEnum.VALID_LOG_TEMPLATE, timerFommat.format(getCurrentSystemTime()), getName(), description, getResult() ? UserConfiguration.PASS : UserConfiguration.FAIL));
        } else {
            log.append(String.format(MessageEnum.INVALID_LOG_TEMPLATE, timerFommat.format(getCurrentSystemTime()), getName()));
        }
    }

    /**
     * @return the inputValue
     */
    public String getInputValue() {
        return inputValue;
    }

    /**
     * @param inputValue the inputValue to set
     */
    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
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
}
