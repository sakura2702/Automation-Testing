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
import vn.phuong.tester.automation.constraint.Browser;
import vn.phuong.tester.automation.constraint.TemplateColumnFieldEnum;
import vn.phuong.tester.automation.constraint.TemplateSettingFieldEnum;
import vn.phuong.tester.automation.testcase.TestCase;

import java.util.*;

/**
 * SummaryTemplate - The template for summary sheet
 *
 * @author phuong.nguyenthi
 */
public class SummaryTemplate extends AbstractTemplate {

  private static final String SHEET_NAME = "Summary";

  public SummaryTemplate(Workbook workbook) {
    super(workbook);
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
    Sheet sheet = workBook.createSheet(SHEET_NAME);
    //Writing the setting
    writeCell(sheet, TemplateSettingFieldEnum.REFERENCE, String.valueOf(TemplateSettingFieldEnum.BROWSER.getvRow() + 3));
    writeCell(sheet, TemplateSettingFieldEnum.BROWSER, Browser.FIREFOX.name());
    //Writing data
    Map<TemplateColumnFieldEnum, List<String>> data = new HashMap<>();
    List<String> indexString = Lists.newArrayList();
    List<String> testCases = Lists.newArrayList();
    List<String> descriptions = Lists.newArrayList();
    int index = 0;
    for(String key : job.getTestScriptData().getTestCases().keySet()) {
      indexString.add(String.valueOf(++index));
      TestCase testCase =  job.getTestScriptData().getTestCases().get(key);
      testCases.add(key);
      descriptions.add(testCase.getDescription());
    }
    data.put(TemplateColumnFieldEnum.SUMMARY_INDEX, indexString);
    data.put(TemplateColumnFieldEnum.SUMMARY_SHEET_NAME, testCases);
    data.put(TemplateColumnFieldEnum.SUMMARY_SHEET_DESCRIPTION, descriptions);
    writeCol(sheet, data, TemplateSettingFieldEnum.BROWSER.getvRow() + 1);
  }
}

