/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.phuong.tester.automation.gui;


import com.google.common.io.Files;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.lang3.StringUtils;

import vn.phuong.tester.automation.bean.FunctionBean;
import vn.phuong.tester.automation.bean.TestScriptDataBean;
import vn.phuong.tester.automation.constraint.Browser;
import vn.phuong.tester.automation.constraint.MessageEnum;
import vn.phuong.tester.automation.gui.dialog.EditSkillDialog;
import vn.phuong.tester.automation.gui.dialog.RunningDialog;
import vn.phuong.tester.automation.gui.model.SkillModel;
import vn.phuong.tester.automation.service.MyExcelHelper;
import vn.phuong.tester.automation.skill.AbstractTestSkill;
import vn.phuong.tester.automation.testcase.TestCase;

/**
 *
 * @author phuong.nguyenthi
 */
public class TestAutomationTool extends javax.swing.JFrame {

    /**
     * Creates new form TestAutomationTool
     */
    private Logger logger = Logger.getLogger(TestAutomationTool.class.getName());
    private File excelFile;
    private FunctionBean job;
    private MyExcelHelper helper;
    public static final String NEW_EXCEL_EXTENSION = "xlsx";
    public static final String EXCEL_EXTENSION = "xls";
        
    public TestAutomationTool() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbManual = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnThucThi = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        cbSingleStopError = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        btChoose = new javax.swing.JButton();
        lbName = new javax.swing.JLabel();
        btnExcelRunAll = new javax.swing.JButton();
        lbName1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listTestcase = new javax.swing.JList();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableAction = new javax.swing.JTable();
        btnExcelRunOne = new javax.swing.JButton();
        cbAllStopWhenError = new javax.swing.JCheckBox();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        jFileChooser1.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return "Excel file";
            }

            @Override
            public boolean accept(File f) {
                String fileExtension = Files.getFileExtension(f.getName());
                return EXCEL_EXTENSION.equalsIgnoreCase(fileExtension) || NEW_EXCEL_EXTENSION.equalsIgnoreCase(fileExtension) || f.isDirectory();
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chương trình Selenium TestAutomation");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setName("Selenium"); // NOI18N

        tbManual.setModel(new vn.phuong.tester.automation.gui.model.SkillModel());
        jScrollPane1.setViewportView(tbManual);

        btnXoa.setText("Xóa hành động");
        btnXoa.setName("btnXoa"); // NOI18N
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });

        btnSua.setText("Sửa hành động");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });

        btnThem.setText("Thêm hành động");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        btnThucThi.setText("Thực thi");
        btnThucThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runScript(evt);
            }
        });

        btnExport.setText("Export");
        btnExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExportMouseClicked(evt);
            }
        });

        cbSingleStopError.setSelected(true);
        cbSingleStopError.setText("Dừng khi lỗi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cbSingleStopError)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThucThi)
                                .addGap(18, 18, 18)
                                .addComponent(btnExport)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoa)
                                .addGap(6, 6, 6))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnSua)
                                                .addComponent(btnThem)
                                                .addComponent(btnThucThi)
                                                .addComponent(btnExport)
                                                .addComponent(cbSingleStopError))
                                        .addComponent(btnXoa))
                                .addContainerGap())
        );

        jTabbedPane1.addTab("Tạo mới TestCase", jPanel1);

        btChoose.setText("Choose...");
        btChoose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btChooseMouseClicked(evt);
            }
        });

        lbName.setName("lbFile"); // NOI18N

        btnExcelRunAll.setText("Thực thi tất cả testcase");
        btnExcelRunAll.setEnabled(excelFile != null);
        btnExcelRunAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExcelRunAllMouseClicked(evt);
            }
        });
        btnExcelRunAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelRunAllActionPerformed(evt);
            }
        });

        lbName1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbName1.setText("File: ");
        lbName1.setName("lbFile"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("TestCase"));

        listTestcase.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listTestcase.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listTestcaseValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listTestcase);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
          jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách các hành động"));

        tableAction.setModel(new vn.phuong.tester.automation.gui.model.SkillModel(new ArrayList<AbstractTestSkill>()));
        jScrollPane4.setViewportView(tableAction);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
          jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
          jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
          jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
              .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnExcelRunOne.setText("Thực thi testcase đang chọn");
        btnExcelRunOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExcelRunOneMouseClicked(evt);
            }
        });

        cbAllStopWhenError.setSelected(true);
        cbAllStopWhenError.setText("Dừng khi lỗi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExcelRunOne)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcelRunAll))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lbName1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btChoose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbAllStopWhenError)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btChoose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbAllStopWhenError))
                    .addComponent(lbName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcelRunAll)
                    .addComponent(btnExcelRunOne))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tải TestCase từ file Excel", jPanel2);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Tệp");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Export..");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Inport..");
        fileMenu.add(saveMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Cài đặt");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cài đặt chung");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Cài đặt template");
        editMenu.add(copyMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Trợ giúp");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Hướng dẫn");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Thông tin");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private FunctionBean getManualJob(){
        SkillModel model = (SkillModel) tbManual.getModel();
        FunctionBean manualJob = new FunctionBean();
        List<AbstractTestSkill> allScript = model.getAll();
        TestCase testCase = new TestCase();
        testCase.setId("01");
        testCase.setName("Manual TestCase");
        testCase.setParent(StringUtils.EMPTY);
        testCase.setActions(allScript);
        TestScriptDataBean dataBean = new TestScriptDataBean();
        LinkedHashMap<String, TestCase> caseMap = new LinkedHashMap<>();
        caseMap.put(testCase.getName(), testCase);
        dataBean.setTestCases(caseMap);
        manualJob.setTestScriptData(dataBean);
        manualJob.setTotalScript(allScript.size());
        return manualJob;
    }

    private void runScript(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runScript
        FunctionBean manualJob = getManualJob();
        RunningDialog dialog = new RunningDialog(this, manualJob, null, cbSingleStopError.isSelected());
        dialog.setVisible(true);
    }//GEN-LAST:event_runScript

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        EditSkillDialog dialog = new EditSkillDialog(this, true, tbManual);        
        dialog.setVisible(true);       
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        Object[] options = {"Đồng ý",
            "Không"};
        int n = JOptionPane.showOptionDialog(this,
                "Bạn có thực sự muốn xóa hành động này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[1]); //default button title
        if(n == 0){
            SkillModel model = (SkillModel) tbManual.getModel();
            model.removeSkill(tbManual.getSelectedRow());
            tbManual.addNotify();
            if(model.getRowCount() == 0){
                btnXoa.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btnXoaMouseClicked
    
    
    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        EditSkillDialog dialog = new EditSkillDialog(this, true,true, tbManual);        
        dialog.setVisible(true);
        if(tbManual.getModel().getRowCount() != 0){
            btnXoa.setEnabled(true);
        }
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnExcelRunAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcelRunAllMouseClicked
        if(excelFile != null){
            if(job !=null){
                RunningDialog dialog = new RunningDialog(this, job, helper, cbAllStopWhenError.isSelected());
                dialog.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnExcelRunAllMouseClicked

    private void btChooseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btChooseMouseClicked
        int returnVal = jFileChooser1.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            excelFile =  jFileChooser1.getSelectedFile();
            if(loadingData()) {
                btnExcelRunAll.setEnabled(true);
                lbName.setText(excelFile.getName());
                JOptionPane.showMessageDialog(this, MessageEnum.MAIN_CHOOSE_FILE.getMessage(), MessageEnum.MAIN_CHOOSE_FILE.getTitle(), MessageEnum.MAIN_CHOOSE_FILE.getType());
            }
        }
        setCursor(null);
    }//GEN-LAST:event_btChooseMouseClicked

    private void listTestcaseValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listTestcaseValueChanged
        if(listTestcase.getSelectedValue() != null) {
            List<AbstractTestSkill> actions = job.getTestScriptData().getTestCases().get(listTestcase.getSelectedValue()).getActions();
            tableAction.setModel(new SkillModel(actions));
        }
    }//GEN-LAST:event_listTestcaseValueChanged

    private void btnExcelRunAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelRunAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcelRunAllActionPerformed

    private void btnExcelRunOneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcelRunOneMouseClicked
        if(listTestcase.getSelectedValue() != null){
            TestCase testCase = job.getTestScriptData().getTestCases().get(listTestcase.getSelectedValue());
            if(testCase.isHasParent()){
                try {
                    TestCase parentTestCase = job.getTestScriptData().getTestCases().get(testCase.getParent());
                    testCase.setParentActions(parentTestCase.getActions());
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
            RunningDialog dialog = new RunningDialog(this, helper, testCase, cbAllStopWhenError.isSelected(), job.getUserConfiguration());
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, MessageEnum.MAIN_EXCEL_RUN.getMessage(), MessageEnum.MAIN_EXCEL_RUN.getTitle(), MessageEnum.MAIN_EXCEL_RUN.getType());
        }
    }//GEN-LAST:event_btnExcelRunOneMouseClicked

    private void btnExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportMouseClicked
        int returnVal = jFileChooser1.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser1.getSelectedFile();
                helper = new MyExcelHelper();
                try {
                    helper.writeBack(file, getManualJob());
                    JOptionPane.showMessageDialog(this, "Xuất file excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Xuất file excel thất bại", "Thông báo loi", JOptionPane.ERROR_MESSAGE);
                }
            }
    }//GEN-LAST:event_btnExportMouseClicked

    private boolean loadingData(){
        DefaultListModel listModel = new DefaultListModel();
        try {
            helper = new MyExcelHelper(excelFile, null);
        } catch (Exception ex) {
            try {
                JOptionPane.showMessageDialog(this, MessageEnum.MAIN_INIT_HELPER.getMessage(), MessageEnum.MAIN_INIT_HELPER.getTitle(), MessageEnum.MAIN_INIT_HELPER.getType());
            } catch (HeadlessException e) {
                e.printStackTrace();
            }
            logger.log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            job = helper.collectJob();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, String.format(MessageEnum.MAIN_INIT_JOB.getMessage(), ex.getMessage()), MessageEnum.MAIN_INIT_JOB.getTitle(), MessageEnum.MAIN_INIT_JOB.getType());
            return false;
        }
        for(String key : job.getTestScriptData().getTestCases().keySet()){
            listModel.addElement(key);
        }
        listTestcase.setModel(listModel);
        return true;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(java.lang.String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestAutomationTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestAutomationTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestAutomationTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestAutomationTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestAutomationTool().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton btChoose;
    private javax.swing.JButton btnExcelRunAll;
    private javax.swing.JButton btnExcelRunOne;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThucThi;
    private javax.swing.JButton btnXoa;
    private javax.swing.JCheckBox cbAllStopWhenError;
    private javax.swing.JCheckBox cbSingleStopError;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbName1;
    private javax.swing.JList listTestcase;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTable tableAction;
    private javax.swing.JTable tbManual;
    // End of variables declaration//GEN-END:variables

}