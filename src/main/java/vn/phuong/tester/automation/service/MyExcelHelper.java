/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.phuong.tester.automation.bean.FunctionBean;
import vn.phuong.tester.automation.config.UserConfiguration;
import vn.phuong.tester.automation.constraint.SelectionType;
import vn.phuong.tester.automation.constraint.SkillType;
import vn.phuong.tester.automation.gui.TestAutomationTool;
import vn.phuong.tester.automation.skill.AbstractTestSkill;
import vn.phuong.tester.automation.skill.ClickSkill;
import vn.phuong.tester.automation.skill.InputSkill;
import vn.phuong.tester.automation.skill.OpenSkill;
import vn.phuong.tester.automation.skill.SelectionSkill;
import vn.phuong.tester.automation.skill.UnknownSkill;
import vn.phuong.tester.automation.skill.VerifySkill;
import vn.phuong.tester.automation.template.SummaryTemplate;
import vn.phuong.tester.automation.template.TestScriptTemplate;
import vn.phuong.tester.automation.testcase.TestCase;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ExcelHelper - Control all action on excel file
 *
 * @author phuong.nguyenthi
 */
public class MyExcelHelper extends AbstractExcelDataHelper {

  private Logger LOGGER = Logger.getLogger(MyExcelHelper.class.getName());

  private Sheet currentSheet;
  private Iterator<Row> rowIterator;
  private UserConfiguration cuzConfig;

  public MyExcelHelper(){
  }

  public MyExcelHelper(File file, UserConfiguration cuzConfig) throws Exception {
    super(file);
    this.cuzConfig = cuzConfig != null ? cuzConfig : new UserConfiguration();
  }

  public boolean writeBack(File file, FunctionBean currentJob){
    //Write the workbook in file system
    String fileExtention = FilenameUtils.getExtension(file.getName());
    try {
      if (fileExtention.equalsIgnoreCase(TestAutomationTool.NEW_EXCEL_EXTENSION)) {
        workBook = new XSSFWorkbook();
      } else if(StringUtils.isEmpty(fileExtention)){
        file = new File(file.toString() + "." + TestAutomationTool.EXCEL_EXTENSION);
      }
      //
      SummaryTemplate summaryTemplate = new SummaryTemplate(workBook);
      summaryTemplate.write(currentJob);
      //
      TestScriptTemplate scriptTemplate = new TestScriptTemplate(workBook);
      scriptTemplate.write(currentJob);
      //
      FileOutputStream out = new FileOutputStream(file);
      workBook.write(out);
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }



  @Override
  public FunctionBean collectJob() throws Exception {
    int startRow = 0, totalScript =0;
    FunctionBean result = new FunctionBean();
    Sheet summary = getSheet(UserConfiguration.START_SHEET_NAME);
    Iterator<Row> summaryRow = summary.rowIterator();
    LinkedHashMap<String , TestCase> testCases = new LinkedHashMap<>();
    while (summaryRow.hasNext()) {
      Row currentRow = summaryRow.next();
      //is reference row
      if (currentRow.getRowNum() == 0) {
        try {
          startRow = getIntCellValue(currentRow, UserConfiguration.REFERENCE_COL);
          startRow = startRow < 3 ?  3 : startRow;
        } catch (NumberFormatException e){

          throw new Exception("Không thể tìm thấy dòng bắt đầu! Vui lòng kiểm tra lại testcase.\n",e);
        }
        continue;
      }
      //is browser row
      if (currentRow.getRowNum() == 1) {
        cuzConfig.setBrowser(getStringCellValue(currentRow, UserConfiguration.BROWSER_COL));
      }
      //is Website row
      if (currentRow.getRowNum() == 2) {
        cuzConfig.setPageLoad(getIntCellValue(currentRow, UserConfiguration.WATTING_TIME_COL));
        result.setUserConfiguration(cuzConfig);
      }
      if (currentRow.getRowNum() >= startRow -1) {
        String testScriptID = getStringCellValue(currentRow, UserConfiguration.SUMMARY_INDEX);
        String sheet = getStringCellValue(currentRow, UserConfiguration.SUMMARY_SHEET);
        String description = getStringCellValue(currentRow, UserConfiguration.SUMMARY_DESCRIPTION);
        TestCase testCase = new TestCase();
        testCase.setId(testScriptID);
        testCase.setName(sheet);
        testCase.setDescription(description);
        if (getSheet(testCase)) {
          List<AbstractTestSkill> testSkills = processTestCase();
          totalScript += testSkills.size();
          testCase.setActions(testSkills);
        } else {
          throw new Exception("Không thể tìm thấy Sheet: " + testCase.getName());
        }
        testCases.put(sheet, testCase);
      }
    }
    result.getTestScriptData().setTestCases(testCases);
    result.setTotalScript(totalScript);
    return result;
  }

  @Override
  public List<AbstractTestSkill> processTestCase() throws Exception{
    List<AbstractTestSkill> list = new ArrayList<>();
    //Go to next row
    int startRow = 0;
    while (rowIterator.hasNext()) {
      Row currentRow  = rowIterator.next();
      if(currentRow.getRowNum() == 0) {try{
        startRow = getIntCellValue(currentRow, UserConfiguration.REFERENCE_COL);
        startRow = startRow < 3 ? 3 : startRow;
      } catch (NumberFormatException e){
        e.printStackTrace();
        throw new Exception("Không thể tìm thấy dòng bắt đầu! ",e);
      }
        continue;
      }
      if(currentRow.getRowNum() >= startRow -1) {
        try {
          String type = getStringCellValue(currentRow, UserConfiguration.ACTION_COL);
          if(StringUtils.isEmpty(type)) continue;
          SkillType skilltype = SkillType.valueOf(SkillType.preProcess(type));
          if (SkillType.OPEN.equals(skilltype)) {
            list.add(processOpenSkill(currentRow));
          }
          if (SkillType.CLICK.equals(skilltype)) {
            list.add(processClickSkill(currentRow));
          }
          if (SkillType.INPUT.equals(skilltype)) {
            list.add(processInput(currentRow));
          }
          if (SkillType.SELECT.equals(skilltype)) {
            list.add(processSelectionSkill(currentRow));
          }
          if (SkillType.VERIFY.equals(skilltype)) {
            list.add(processVerify(currentRow));
          }
        } catch (IllegalArgumentException | NullPointerException e) {
          list.add(new UnknownSkill());
          LOGGER.log(Level.SEVERE, "Unrecording test skill", e);
        }
      }
    }
    return list;
  }

  private OpenSkill processOpenSkill(Row currentRow) {
    OpenSkill skill = new OpenSkill();
    skill.setResultRow(currentRow);
    String url = getStringCellValue(currentRow, UserConfiguration.INPUT_COL);
    skill.setUrl(url);
    return skill;
  }

  private ClickSkill processClickSkill(Row row) {
    ClickSkill skill = new ClickSkill();
    skill.setResultRow(row);
    SelectionSkill innerSkill = processSelectionSkill(row);
    skill.setSelection(innerSkill);
    return skill;
  }

  private SelectionSkill processSelectionSkill(Row row) {
    SelectionSkill innerSelection = new SelectionSkill();
    try {
      innerSelection.setResultRow(row);
      SelectionType selectionType = SelectionType.valueOf(getStringCellValue(row, UserConfiguration.SELECTOR_TYPE_COL).toUpperCase());
      innerSelection.setType(selectionType);
      String selectorString = getStringCellValue(row, UserConfiguration.SELECTOR_STRING_COL);
      innerSelection.setSelector(selectorString);
    } catch (Exception e){
      LOGGER.log(Level.SEVERE, null, e);
    }
    return innerSelection;
  }

  private InputSkill processInput(Row row) {
    InputSkill skill = new InputSkill();
    skill.setResultRow(row);
    SelectionSkill innerSkill = processSelectionSkill(row);
    skill.setSelection(innerSkill);
    String inputValue = getStringCellValue(row, UserConfiguration.INPUT_COL);
    skill.setInputValue(inputValue);
    return skill;
  }

  private VerifySkill processVerify(Row row) {
    VerifySkill skill = new VerifySkill();
    skill.setResultRow(row);
    SelectionSkill innerSkill = processSelectionSkill(row);
    skill.setSelection(innerSkill);
    String expectedValue = getStringCellValue(row, UserConfiguration.INPUT_COL);
    skill.setExpectedValue(expectedValue);
    return skill;
  }

  public boolean getSheet(TestCase testCase) {
    currentSheet = getSheet(testCase.getName());
    rowIterator = currentSheet !=null ? currentSheet.iterator() : null;
    return currentSheet != null;
  }

  public String getCurrentSheetName() {
    return currentSheet.getSheetName();
  }

}
