/*
 * Copyright (c) 2015 Ekino
 */
package vn.phuong.tester.automation.constraint;

import javax.swing.JOptionPane;

/**
 * MessageConstraint - manage all message
 *
 * @author phuong.nguyenthi
 */
public enum MessageEnum {
  MAIN_CHOOSE_FILE("Tải file TestCase hoàn tất!", true),
  MAIN_EXCEL_RUN("Vui lòng chọn một testcase để thực thi", false),
  MAIN_INIT_HELPER("Lỗi khi đang xử lý file. Xem log để biết thêm chi tiết", false),
  MAIN_EXPORT_INFO("Xuất tập tin template thành công", true),
  MAIN_EXPORT_ERROR("Xuất tập tin template thất bại", false),
  MAIN_INIT_JOB("Lỗi khi đang thu thập dữ liệu.\nChi tiết: [%s]\t", false);



  private String message;
  private String title;
  private int type;

  public static final String VALID_LOG_TEMPLATE = "\n" +
    "[%s]-[%s] Mô tả: \n[%s]\n" +
    "Kết quả: %s\n";

  public static final String INVALID_LOG_TEMPLATE = "\n" +
    "[%s]-[%s] Skill này không hợp lệ! Vui lòng kiểm tra lại TestCse!";

  private static final String ERROR = "Lỗi";
  private static final String INFO = "Thông báo";

  MessageEnum(String message, boolean isInfo){
    this.title = isInfo ? INFO : ERROR;
    this.type = isInfo ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
    this.message = message;

  }

  public String getMessage() {
    return message;
  }

  public String getTitle() {
    return title;
  }

  public int getType() {
    return type;
  }
}
