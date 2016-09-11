/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.phuong.tester.automation.gui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.lang3.StringUtils;
import vn.phuong.tester.automation.config.UserConfiguration;

import vn.phuong.tester.automation.skill.AbstractTestSkill;
import vn.phuong.tester.automation.testcase.TestCase;


/**
 *
 * @author phuong.nguyenthi
 */
public class ResultModel extends AbstractTableModel  implements Serializable{

    private static final int INDEX_COL = 0;
    private static final int TESTCASE_COL = 1;
    private static final int BROWSER_COL = 2;
    private static final int TEST_SCRIPT_COL = 3;
    private static final int VALID_COL = 4;
    private static final int DESCRIPTION_COL = 5;
    private static final int RESULT_COL = 6;

    private List<ResultSkill> testSkills = new ArrayList<ResultSkill>();
    private final String[] columns = {"Trình tự", "TestCase", "Trình duyệt", "Hành động", "Hợp lệ", "Mô tả hành động", "Kết quả"};
    
    public ResultModel(List<TestCase> testCases) {
        for(TestCase testCase : testCases){
            for (AbstractTestSkill skill : testCase.getActions()) {
                testSkills.add(new ResultSkill(testCase.getName(), testCase.getBrowser().name(), skill));
            }
        }
    }
    
    @Override
    public int getRowCount() {
        return testSkills.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ResultSkill result = testSkills.get(rowIndex);
        switch(columnIndex) {
          case INDEX_COL: return rowIndex+1;
          case TESTCASE_COL: return result.testCase;
            case BROWSER_COL: return result.browser;
          case TEST_SCRIPT_COL: return result.testSkill.getName();
          case VALID_COL: return result.testSkill.isValid();
          case DESCRIPTION_COL: return result.testSkill.getDescription();
          case RESULT_COL: return result.result;
          default: return null;
        }
    }
    
    public ResultSkill getSkill(int row){
        return testSkills.get(row);
    }
    
    public void setResult(int row, boolean result){
        testSkills.get(row).setResult(result ? UserConfiguration.PASS : UserConfiguration.FAIL);
        fireTableCellUpdated(row, RESULT_COL);
    }
    public void resetResult(){
        for(ResultSkill resultSkill : testSkills){
            resultSkill.setResult(StringUtils.EMPTY);
        }
        fireTableDataChanged();
    }
    
    public void insertSkill(ResultSkill skill){
        testSkills.add(skill);
    }
    
    public void insertSkillAt(int row, ResultSkill skill){
        testSkills.add(row, skill);
    }

    public boolean removeSkill(int row){
        return testSkills.remove(row) != null;
    }
    
    public boolean editSkill(int row, ResultSkill editedSkill){
        if(removeSkill(row)){
            try{
                testSkills.add(row, editedSkill);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            
        }
        return false;
    }
    
    public List<ResultSkill> getAll(){
        return testSkills;
    }


    public class ResultSkill{
        private String testCase;
        private String browser;
        private String result;
        private AbstractTestSkill testSkill;
        public ResultSkill(String testCase, String browser, AbstractTestSkill testSkill){
            this.browser = browser;
            this.testCase = testCase;
            this.testSkill = testSkill;
        }

        /**
         * @return the testCase
         */
        public String getTestCase() {
            return testCase;
        }

        /**
         * @param testCase the testCase to set
         */
        public void setTestCase(String testCase) {
            this.testCase = testCase;
        }

        /**
         * @return the result
         */
        public String getResult() {
            return result;
        }

        /**
         * @param result the result to set
         */
        public void setResult(String result) {
            this.result = result;
        }

        /**
         * @return the testSkill
         */
        public AbstractTestSkill getTestSkill() {
            return testSkill;
        }

        /**
         * @param testSkill the testSkill to set
         */
        public void setTestSkill(AbstractTestSkill testSkill) {
            this.testSkill = testSkill;
        }

        public String getBrowser() {
            return browser;
        }

        public void setBrowser(String browser) {
            this.browser = browser;
        }
    }
}
