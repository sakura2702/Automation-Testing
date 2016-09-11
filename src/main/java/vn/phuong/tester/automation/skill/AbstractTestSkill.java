/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.phuong.tester.automation.skill;

import org.apache.poi.ss.usermodel.Row;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.openqa.selenium.WebDriver;
import vn.phuong.tester.automation.visitor.SkillVisitor;

import javax.swing.JTextArea;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author phuong.nguyenthi
 */
public abstract class AbstractTestSkill {
    protected String description;
    private boolean result;

    @JsonIgnore
    protected Row resultRow;
    @JsonIgnore
    protected DateFormat timerFommat = new SimpleDateFormat("HH:mm:ss:SSS");
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public abstract boolean accept(SkillVisitor visitor);

    @JsonIgnore
    public abstract String getName();

    @JsonIgnore
    public abstract boolean isValid();

    @JsonIgnore
    public abstract void buildLog(JTextArea log, WebDriver driver);

    /**
     * @return the resultRow
     */
    @JsonIgnore
    public Row getResultRow() {
        return resultRow;
    }

    /**
     * @param resultRow the resultRow to set
     */
    @JsonIgnore
    public void setResultRow(Row resultRow) {
        this.resultRow = resultRow;
    }

    @JsonIgnore
    protected long getCurrentSystemTime(){
        return System.currentTimeMillis();
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
