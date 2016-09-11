package vn.phuong.tester.automation.testcase;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import vn.phuong.tester.automation.constraint.Browser;
import vn.phuong.tester.automation.skill.AbstractTestSkill;

import java.util.List;

/**
 * Created by phuong.nguyenthi on 8/17/2015.
 */
public class TestCase {
    private String id;
    private String name;
    private String parent;
    @JsonIgnore
    private Browser browser;
    private String description;
    private List<AbstractTestSkill> parentActions = Lists.newArrayList();
    private List<AbstractTestSkill> actions = Lists.newArrayList();

    public TestCase(){

    }
    public  TestCase(TestCase clone){
        this.id = clone.getId();
        this.browser = clone.getBrowser();
        this.name = clone.getName();
        this.actions = clone.getActions();
        this.description = clone.getDescription();
    }
    public TestCase(String id, String name, List<AbstractTestSkill> actions){
        this.id = id;
        this.name = name;
        this.actions = actions;
    }

    @JsonIgnore
    public boolean isHasParent(){
        return StringUtils.isNoneEmpty(parent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AbstractTestSkill> getActions() {
        return actions;
    }

    public void setActions(List<AbstractTestSkill> actions) {
        this.actions = actions;
    }

    public List<AbstractTestSkill> getParentActions() {
        return parentActions;
    }

    public void setParentActions(List<AbstractTestSkill> parentActions) {
        actions.addAll(0, parentActions);
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TestCase){
            TestCase aCase = (TestCase) obj;
            return  id.equals((aCase.id)) && browser.equals(aCase.browser) && name.equals(aCase.name);
        }
        return false;
    }
}
