package vn.phuong.tester.automation.gui.dialog;

import org.openqa.selenium.WebDriver;
import vn.phuong.tester.automation.config.UserConfiguration;
import vn.phuong.tester.automation.gui.model.ResultModel;
import vn.phuong.tester.automation.service.MyExcelHelper;
import vn.phuong.tester.automation.skill.AbstractTestSkill;
import vn.phuong.tester.automation.testcase.TestCase;
import vn.phuong.tester.automation.visitor.SkillExecuteVisitor;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by phuong.nguyenthi on 12/23/2015.
 */
public class RunningTask extends SwingWorker<Void, Void> {

    private final RunningDialog parent;
    private final MyExcelHelper excelHelper;
    private final UserConfiguration userConfiguration;
    private final List<TestCase> testCases;
    private WebDriver currentWebDriver;
    private int progress;
    private long startingTime;
    private long endTime;


    private Logger LOGGER = Logger.getLogger(RunningTask.class.getName());

    public RunningTask(RunningDialog parent, List<TestCase> testCases, UserConfiguration configuration, MyExcelHelper excelHelper) {
        this.parent = parent;
        this.testCases = testCases;
        this.excelHelper = excelHelper;
        this.userConfiguration = configuration;
    }

    public RunningTask(RunningTask clone){
        this.excelHelper = clone.getExcelHelper();
        this.testCases = clone.getTestCases();
        this.parent = clone.getParent();
        this.userConfiguration = clone.getUserConfiguration();
        parent.getjProgressBar1().setValue(0);
    }

    /*
    * Main task. Executed in background thread.
    */

    @Override
    public Void doInBackground() throws InterruptedException{
        
        progress = 0;
        setProgress(0);
        if(parent.isCancel()) return null;
        List<AbstractTestSkill> listScript = new ArrayList<>();
        for (TestCase testCase : testCases){
            if (parent.isCancel()) break;
//                        parent.getLog().append("*-o0o-* [" + testCase + "] *-o0o-* \n");
            listScript.addAll(testCase.getActions());
        }
        executeActions(listScript.size());
//            webDriver.close();
        return null;
    }

    private void executeActions(int totalScript) throws InterruptedException {
                 int progressPercent;
        startingTime = System.currentTimeMillis();
        for (TestCase testCase : testCases) {
            currentWebDriver = testCase.getBrowser().getDriver(userConfiguration);
            SkillExecuteVisitor visitorImp = new SkillExecuteVisitor( currentWebDriver, parent.getLog(), excelHelper);
            for (AbstractTestSkill skill : testCase.getActions()) {
                if (parent.isCancel()) return;
                while (parent.isPause()) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        LOGGER.log(Level.SEVERE, null, ex);
                    }
                }
                progress++;
                try {
                    boolean result = skill.accept(visitorImp);
                    parent.getResultModel().setResult(progress - 1, result);
                    if (!result) {
                        parent.setFinallyResult(false);
                        if (parent.isStopWhenError()) return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(parent, "Lỗi không xác định!", "Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
                    LOGGER.log(Level.SEVERE, "Lỗi lúc thực thi test script", ex);
                    return;
                }
                progressPercent = progress * 100 / totalScript;
                setProgress(Math.min(progressPercent, 100));
                parent.getjProgressBar1().setValue(Math.min(progressPercent, 100));
            }
            try {
                currentWebDriver.switchTo().defaultContent();
                currentWebDriver.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /*
     * Executed in event dispatch thread
     */
    public void done() {
        endTime = System.currentTimeMillis();
        Toolkit.getDefaultToolkit().beep();
        if(!parent.isFinallyResult() && parent.isStopWhenError()) {
            ResultModel.ResultSkill skill = parent.getResultModel().getSkill(progress - 1);
            JOptionPane.showMessageDialog(parent, String.format("Kiểm thử thất bại. Testscript [%s][%s][%s] xảy ra lỗi!", progress, skill.getTestCase(), skill.getTestSkill().getName()), "Thông báo lỗi!", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(parent, "Kiểm thử hoàn tất!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        parent.getBtPause().setText("Khởi chạy lại");
        parent.setIsReload(true);
        parent.getLog().append("Done!\n");
        parent.getLog().append("Total time: " + new SimpleDateFormat("mm:ss").format(startingTime - endTime));
        try {
            if(excelHelper!=null) excelHelper.flush();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent, "Không thể lưu file excel!", "Thông báo lỗi!", JOptionPane.ERROR_MESSAGE);
            LOGGER.log(Level.SEVERE, "Không thể lưu file excel!!", ex);
        }
        currentWebDriver.close();
    }

    public RunningDialog getParent() {
        return parent;
    }

    public MyExcelHelper getExcelHelper() {
        return excelHelper;
    }

    public UserConfiguration getUserConfiguration() {
        return userConfiguration;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }
}
