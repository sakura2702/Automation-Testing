package vn.phuong.tester.automation.bean;

import vn.phuong.tester.automation.testcase.TestCase;

import java.util.LinkedHashMap;

/**
 * Created by phuong.nguyenthi on 02/12/2015.
 */
public class TestScriptDataBean {
    private LinkedHashMap<String, TestCase> testCases;

    public LinkedHashMap<String, TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(LinkedHashMap<String, TestCase> testCases) {
        this.testCases = testCases;
    }
}
