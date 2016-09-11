/*
 * Copyright (c) 2015 Ekino
 */
package vn.phuong.tester.automation.util;

import vn.phuong.tester.automation.bean.FunctionBean;
import vn.phuong.tester.automation.bean.TestScriptDataBean;
import vn.phuong.tester.automation.model.Function;

import java.io.IOException;

/**
 * FunctionConvertor -
 *
 * @author phuong.nguyenthi
 */
public class FunctionConvertor {

  private static TestScriptDataConvertor<TestScriptDataBean> convertor = new TestScriptDataConvertor();

  public static java.util.function.Function<FunctionBean, Function> convertFunction = functionBean -> {
    if (functionBean == null) {
      return null;
    }
    vn.phuong.tester.automation.model.Function function = new vn.phuong.tester.automation.model.Function();
    function.setTotalScript(functionBean.getTotalScript());
    function.setName(functionBean.getName());
    function.setId(functionBean.getId());
    //Infinite loop
    //    function.setProject(ProjectConvertor.convertProject.apply(functionBean.getProjectBean()));
    try {
      function.setData(convertor.saveFrom(functionBean.getTestScriptData()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return function;
  };

  public static java.util.function.Function<Function, FunctionBean> convertFunctionBean = function -> {
    if (function == null) {
      return null;
    }
    TestScriptDataBean testCases = null;
    try {
      testCases = convertor.loadFrom(function.getData(), TestScriptDataBean.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new FunctionBean (
      function.getId(),
      function.getName(),
      function.getTotalScript(),
      //Infinite loop
//      ProjectConvertor.convertProjectBean.apply(function.getProject()),
      null,
      testCases,
      null
    );
  };
}
