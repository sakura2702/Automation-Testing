/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.phuong.tester.automation.config;

import vn.phuong.tester.automation.constraint.Browser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author phuong.nguyenthi
 */
public class UserConfiguration {
    public static final String START_SHEET_NAME = "Summary";
    public static final int BROWSER_COL = 1;
    public static final int WATTING_TIME_COL = 3;
    public static final int SUMMARY_INDEX = 0, SUMMARY_SHEET = 1, SUMMARY_DESCRIPTION = 2;
    public static final int INDEX_COL = 0, ACTION_COL = 1,  SELECTOR_TYPE_COL = 2, SELECTOR_STRING_COL = 3, INPUT_COL = 4, ACTUAL_COL = 5, RESULT_COL = 6, MESSAGE_COL = 7, NOTE_COL = 8;
    public static final int REFERENCE_COL = 1;
    public static final String PASS = "PASS";
    public static final String FAIL = "FAIL";
    public static final String INVALID_NULL = "NULL";
    public static final String INVALID_SKILL_TYPE = "INVALID SKILL TYPE";
    public static final String INVALID_SELECTION_TYPE_MESSAGE = "INVALID SELECTION TYPE";
    public static final String INVALID_SELECTION_MESSAGE = "INVALID SELECTION";
    public static final String ERROR_WEB_ELMENT_NOT_FOUND_MESSAGE = "WEB ELEMENT NOT FOUND";
    public static DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
    private String browser;
    private int pageLoad;
    private int implicityWait;

    private final int DEFAULT_PAGE_LOAD = 10000;

    public UserConfiguration(){
        this.implicityWait = 15;
        this.browser = Browser.CHROME.name();
        this.pageLoad = DEFAULT_PAGE_LOAD;
    }

    public int getPageLoad() {
        return pageLoad;
    }

    public void setPageLoad(int pageLoad) {
        this.pageLoad = pageLoad <= 1 ? DEFAULT_PAGE_LOAD : pageLoad;
    }

    public int getImplicityWait() {
        return implicityWait;
    }

    public void setImplicityWait(int implicityWait) {
        this.implicityWait = implicityWait;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
