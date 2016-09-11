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
import vn.phuong.tester.automation.constraint.SelectionType;
import vn.phuong.tester.automation.skill.ClickSkill;
import vn.phuong.tester.automation.skill.InputSkill;
import vn.phuong.tester.automation.skill.OpenSkill;
import vn.phuong.tester.automation.skill.SelectionSkill;
import vn.phuong.tester.automation.skill.AbstractTestSkill;


/**
 *
 * @author ADMIN
 */
public class SkillModel extends AbstractTableModel  implements Serializable{
    private List<AbstractTestSkill> testSkills = new ArrayList<AbstractTestSkill>();
    private final String[] columns = {"Trình tự","Hành động", "Hợp lệ", "Mô tả hành động"};
    
    public SkillModel(List<AbstractTestSkill> skill){
        this.testSkills = skill != null ? skill : new ArrayList<AbstractTestSkill>();
    }
    
    public SkillModel(){
        //Hard-code for testing..
        OpenSkill open = new OpenSkill("https://www.google.com.vn/?gws_rd=ssl","Vao trang google");
        testSkills.add(open);
        SelectionSkill inputSelection = new SelectionSkill(SelectionType.ID, "lst-ib", "Tim element input");
        InputSkill input = new InputSkill(inputSelection, "ptit", "Tim tu khoa: ptit");
        testSkills.add(input);
        SelectionSkill clickSearchSelection = new SelectionSkill(SelectionType.XPATH, "//*[@id=\"sblsbb\"]/button/span", "Tim nut Search");
        ClickSkill clickSearch = new ClickSkill(clickSearchSelection, "Bam nut search");
        testSkills.add(clickSearch);
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
        AbstractTestSkill skill = testSkills.get(rowIndex);
        switch(columnIndex) {
          case 0: return rowIndex+1;
          case 1: return skill.getName();
          case 2: return Boolean.valueOf(skill.isValid());
          case 3: return skill.getDescription();
          default: return null;
        }
    }
    
    public AbstractTestSkill getSkill(int row){
        return testSkills.get(row);
    }
    
    public void insertSkill(AbstractTestSkill skill){
        testSkills.add(skill);
    }
    
    public void insertSkillAt(int row, AbstractTestSkill skill){
        if(row >= testSkills.size()){
            testSkills.add(skill);
        } else {
            testSkills.add(row, skill);
        }
    }

    public boolean removeSkill(int row){
        return testSkills.remove(row) != null;
    }
    
    public boolean editSkill(int row, AbstractTestSkill editedSkill){
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
    
    public List<AbstractTestSkill> getAll(){
        return testSkills;
    }
}
