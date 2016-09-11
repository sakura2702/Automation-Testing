/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */

import org.junit.Test;
import vn.phuong.tester.automation.bean.FunctionBean;
import vn.phuong.tester.automation.bean.ProjectBean;
import vn.phuong.tester.automation.bean.TestScriptDataBean;
import vn.phuong.tester.automation.bean.UserBean;
import vn.phuong.tester.automation.constraint.SelectionType;
import vn.phuong.tester.automation.model.Function;
import vn.phuong.tester.automation.model.Project;
import vn.phuong.tester.automation.model.User;
import vn.phuong.tester.automation.skill.AbstractTestSkill;
import vn.phuong.tester.automation.skill.ClickSkill;
import vn.phuong.tester.automation.skill.InputSkill;
import vn.phuong.tester.automation.skill.OpenSkill;
import vn.phuong.tester.automation.skill.SelectionSkill;
import vn.phuong.tester.automation.testcase.TestCase;
import vn.phuong.tester.automation.util.ProjectConvertor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JsonMappingTesting -
 *
 * @author phuong.nguyenthi
 */
public class BeanConvertTest {

  @Test
  public void test_mapping_from_modal_to_bean() throws Exception{
    Project project = ProjectConvertor.convertProject.apply(createBean());
    //Check
    assertTrue(project.getId().equals(1l));
    assertEquals(project.getName(), "Testing Project");
    assertEquals(project.getDescription(), "no description");
    //Owner
    assertNotNull(project.getOwner());
    User user = project.getOwner();
    assertEquals(user.getLogin(), "tester");
    assertEquals(user.getPassword(), "testfortest");
    assertEquals(user.getUsername(), "imtester");
    //Functions
    assertEquals(project.getFunctions().size(),1);
    Function function = project.getFunctions().iterator().next();
    assertTrue(function.getId().equals(1l));
    assertEquals(function.getName(), "Function name");
    assertEquals(function.getTotalScript(), 10);
    assertEquals(function.getData(), "[\"vn.phuong.tester.automation.bean.TestScriptDataBean\",{\"testCases\":[\"java.util.LinkedHashMap\",{\"TestCase1\":[\"vn.phuong.tester.automation.testcase.TestCase\",{\"id\":\"1\",\"name\":\"TestCase1\",\"parent\":null,\"description\":null,\"parentActions\":[\"java.util.ArrayList\",[]],\"actions\":[\"java.util.ArrayList\",[[\"vn.phuong.tester.automation.skill.OpenSkill\",{\"description\":\"Vao trang google\",\"result\":true,\"url\":\"https://www.google.com.vn/?gws_rd=ssl\"}],[\"vn.phuong.tester.automation.skill.InputSkill\",{\"description\":\"Tim tu khoa: ptit\",\"result\":false,\"inputValue\":\"ptit\",\"selection\":[\"vn.phuong.tester.automation.skill.SelectionSkill\",{\"description\":\"Tim element input\",\"result\":false,\"type\":\"ID\",\"selector\":\"lst-ib\"}]}],[\"vn.phuong.tester.automation.skill.ClickSkill\",{\"description\":\"Bam nut search\",\"result\":false,\"selection\":[\"vn.phuong.tester.automation.skill.SelectionSkill\",{\"description\":\"Tim nut Search\",\"result\":false,\"type\":\"XPATH\",\"selector\":\"//*[@id=\\\"sblsbb\\\"]/button/span\"}]}]]]}]}]}]");

    //Mapping back
    ProjectBean projectBean = ProjectConvertor.convertProjectBean.apply(project);
    //Check
    assertTrue(projectBean.getId().equals(1l));
    assertEquals(projectBean.getName(), "Testing Project");
    assertEquals(projectBean.getDescription(), "no description");
    //Owner
    assertNotNull(projectBean.getOwner());
    UserBean userBean = projectBean.getOwner();
    assertEquals(userBean.getLogin(), "tester");
    assertEquals(userBean.getPassword(), "testfortest");
    assertEquals(userBean.getUsername(), "imtester");
    //Functions
    assertEquals(projectBean.getFunctions().size(),1);
    FunctionBean functionBean = projectBean.getFunctions().iterator().next();
    assertTrue(functionBean.getId().equals(1l));
    assertEquals(functionBean.getName(), "Function name");
    assertEquals(functionBean.getTotalScript(), 10);
    assertEquals(functionBean.getTestScriptData().getTestCases().size(), 1);
    TestCase testCase = functionBean.getTestScriptData().getTestCases().get("TestCase1");
    assertEquals(testCase.getId(), "1");
    assertEquals(testCase.getName(), "TestCase1");
    assertEquals(testCase.getActions().size(), 3);
    for(AbstractTestSkill testSkill : testCase.getActions()){
      if(testSkill instanceof OpenSkill){
        assertEquals(((OpenSkill) testSkill).getUrl(), "https://www.google.com.vn/?gws_rd=ssl");
        assertEquals(testSkill.getDescription(), "Vao trang google");
      }
      if(testSkill instanceof SelectionSkill){
        assertEquals(((SelectionSkill) testSkill).getType(), SelectionType.ID);
        assertEquals(((SelectionSkill) testSkill).getSelector(), "lst-ib");
        assertEquals(testSkill.getDescription(), "Tim element input");
      }
      if(testSkill instanceof InputSkill){
        assertEquals(testSkill.getDescription(), "Tim tu khoa: ptit");
        assertEquals(((InputSkill) testSkill).getInputValue(),"ptit");
        assertEquals(((InputSkill) testSkill).getSelection().getType(), SelectionType.ID);
        assertEquals(((InputSkill) testSkill).getSelection().getSelector(), "lst-ib");
        assertEquals(((InputSkill) testSkill).getSelection().getDescription(), "Tim element input");
      }
      if(testSkill instanceof ClickSkill){
        assertEquals(testSkill.getDescription(), "Bam nut search");
        assertEquals(((ClickSkill) testSkill).getSelection().getType(), SelectionType.XPATH);
        assertEquals(((ClickSkill) testSkill).getSelection().getSelector(), "//*[@id=\"sblsbb\"]/button/span");
        assertEquals(((ClickSkill) testSkill).getSelection().getDescription(), "Tim nut Search");
      }
    }

  }

  private ProjectBean createBean(){
    UserBean userBean = new UserBean("tester","testfortest","imtester");
    ProjectBean projectBean = new ProjectBean(1l, "Testing Project", "no description", userBean, null);
    TestScriptDataBean testScriptDataBean = new TestScriptDataBean();
    TestCase testCase = new TestCase("1", "TestCase1", createTestScript());
    LinkedHashMap<String, TestCase> testcases = new LinkedHashMap<>();
    testcases.put("TestCase1", testCase);
    testScriptDataBean.setTestCases(testcases);
    FunctionBean functionBean = new FunctionBean(1l, "Function name",  10, projectBean, testScriptDataBean, null);
    List<FunctionBean> functionBeans = new ArrayList<>();
    functionBeans.add(functionBean);
    projectBean.setFunctions(functionBeans);
    return projectBean;
  }


  private List<AbstractTestSkill> createTestScript(){
    List<AbstractTestSkill> listAction = new ArrayList<>();
    OpenSkill open = new OpenSkill("https://www.google.com.vn/?gws_rd=ssl","Vao trang google");
    listAction.add(open);
    SelectionSkill inputSelection = new SelectionSkill(SelectionType.ID, "lst-ib", "Tim element input");
    InputSkill input = new InputSkill(inputSelection, "ptit", "Tim tu khoa: ptit");
    listAction.add(input);
    SelectionSkill clickSearchSelection = new SelectionSkill(SelectionType.XPATH, "//*[@id=\"sblsbb\"]/button/span", "Tim nut Search");
    ClickSkill clickSearch = new ClickSkill(clickSearchSelection, "Bam nut search");
    listAction.add(clickSearch);
    return listAction;
  }

}
