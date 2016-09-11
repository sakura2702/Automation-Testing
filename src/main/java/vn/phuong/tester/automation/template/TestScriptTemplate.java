/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.template;

import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import vn.phuong.tester.automation.bean.FunctionBean;
import vn.phuong.tester.automation.constraint.TemplateColumnFieldEnum;
import vn.phuong.tester.automation.constraint.TemplateSettingFieldEnum;
import vn.phuong.tester.automation.skill.AbstractTestSkill;
import vn.phuong.tester.automation.testcase.TestCase;
import vn.phuong.tester.automation.visitor.SkillCollectorVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TestScriptTemplate -
 *
 * @author phuong.nguyenthi
 */
public class TestScriptTemplate extends AbstractTemplate{


  public TestScriptTemplate(Workbook workBook) {
    super(workBook);
  }

  @Override
  protected CellStyle getTitleCellStyle() {
    CellStyle style = workBook.createCellStyle();//Create style
    Font font = workBook.createFont();//Create font
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
    style.setFont(font);//set it to bold
    return style;
  }

  @Override
  public void write(FunctionBean job) {
    //Writing data
    Map<TemplateColumnFieldEnum, List<String>> data = new HashMap<>();
    for(String key : job.getTestScriptData().getTestCases().keySet()) {
      Sheet sheet = workBook.createSheet(key);
      //Writing the setting
      writeCell(sheet, TemplateSettingFieldEnum.REFERENCE, String.valueOf(TemplateSettingFieldEnum.REFERENCE.getvRow() + 3));
      TestCase testCase =  job.getTestScriptData().getTestCases().get(key);
      SkillCollectorVisitor collector = new SkillCollectorVisitor();
      List<String> indexs = Lists.newArrayList();
      int index = 0;
      for(AbstractTestSkill skill : testCase.getActions()) {
        ++index;
        indexs.add(String.valueOf(index));
        skill.accept(collector);
      }
      //Mapping data
      data.put(TemplateColumnFieldEnum.INDEX, indexs);
      data.put(TemplateColumnFieldEnum.ACTION, collector.getActionName());
      data.put(TemplateColumnFieldEnum.SELECTOR_TYPE, collector.getSelectorsType());
      data.put(TemplateColumnFieldEnum.SELECTOR_STRING, collector.getSelectors());
      data.put(TemplateColumnFieldEnum.INPUT, collector.getInputs());
      data.put(TemplateColumnFieldEnum.ACTUAL, collector.getActuals());
      data.put(TemplateColumnFieldEnum.RESULT, collector.getResults());
      data.put(TemplateColumnFieldEnum.MESSAGE, collector.getMessage());

      //Write the title below the column REFERENCE
      writeCol(sheet, data, TemplateSettingFieldEnum.REFERENCE.getvRow() + 1);
    }
  }
}
