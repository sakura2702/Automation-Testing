package vn.phuong.tester.automation.constraint;

/**
 * Define the template column. Start index is 1, but inside code we start with 0
 *
 * Created by phuong.nguyenthi.
 */
public enum TemplateColumnFieldEnum {

    //Summary
    SUMMARY_INDEX("Test Case ID",1),
    SUMMARY_SHEET_NAME("Sheet", 2),
    SUMMARY_SHEET_DESCRIPTION("Description", 3),
    SUMMARY_OVERALL_RESULT("Overall Result", 4),

    //Col data
    INDEX("Step",1),
    ACTION("Action", 2),
    SELECTOR_TYPE("Selector Type", 3),
    SELECTOR_STRING("Selector String", 4),
    INPUT("Input Value - Expected value", 5),
    ACTUAL("Actual Value", 6),
    RESULT("Result", 7),
    MESSAGE("Message", 8);

    private String rawName;
    private int col;
    TemplateColumnFieldEnum(String rawName, int col){
        this.rawName = rawName;
        this.col = col;
    }

    public String getRawName() {
        return rawName;
    }

    public int getCol() {
        return col - 1;
    }
}
