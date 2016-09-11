package vn.phuong.tester.automation.constraint;

/**
 * Created by ADMIN on 29/11/2015.
 */
public enum TemplateSettingFieldEnum {
    REFERENCE("Reference:", 1, 1, 2, 1),
    BROWSER("Browser:", 1, 2, 2, 2),
    WEBSITE("Website:", 1, 3, 2, 3),
    WATING_TIME("Waiting time:", 3, 3, 4, 3)
    ;




    private String rawName;
    private int lCol;
    private int lRow;
    private int vCol;
    private int vRow;

    TemplateSettingFieldEnum(String rawName, int lCol, int lRow, int vCol, int vRow){
        this.rawName = rawName;
        this.lCol = lCol;
        this.lRow = lRow;
        this.vCol = vCol;
        this.vRow = vRow;
    }



    public String getRawName() {
        return rawName;
    }

    public int getlCol() {
        return lCol - 1;
    }

    public int getlRow() {
        return lRow - 1;
    }

    public int getvCol() {
        return vCol - 1;
    }

    public int getvRow() {
        return vRow - 1;
    }
}
