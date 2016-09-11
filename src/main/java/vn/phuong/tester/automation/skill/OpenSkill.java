/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.phuong.tester.automation.skill;

import javax.swing.JTextArea;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import vn.phuong.tester.automation.config.UserConfiguration;
import vn.phuong.tester.automation.constraint.MessageEnum;
import vn.phuong.tester.automation.constraint.SkillType;
import vn.phuong.tester.automation.visitor.SkillVisitor;

/**
 *
 * @author ADMIN
 */
public class OpenSkill extends AbstractTestSkill {
    
    private String url;
    
    public OpenSkill(String url){
       this(url, null);
    }

    public OpenSkill(String url, String description){
        this.url = url;
        this.description = description;
    }
    
    public OpenSkill(){

    }

    @Override
    public String getName() {
        return SkillType.OPEN.name();
    } 

    @Override
    public boolean isValid() {
        return StringUtils.isNotEmpty(url);
    }

    @Override
    public void buildLog(JTextArea log, WebDriver driver) {
        if(isValid()) {
            String description = String.format("Open [URL:%s]", url);
            log.append(String.format(MessageEnum.VALID_LOG_TEMPLATE, timerFommat.format(getCurrentSystemTime()), getName(), description, getResult() ? UserConfiguration.PASS : UserConfiguration.FAIL));
        } else {
            log.append(String.format(MessageEnum.INVALID_LOG_TEMPLATE, timerFommat.format(getCurrentSystemTime()), getName()));
        }
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean getResult() {
        return true;
    }

    @Override
    public boolean accept(SkillVisitor visitor) {
        return visitor.visit(this);
    }

}
