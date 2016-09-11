/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.template;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import vn.phuong.tester.automation.bean.FunctionBean;
import vn.phuong.tester.automation.constraint.TemplateColumnFieldEnum;
import vn.phuong.tester.automation.constraint.TemplateSettingFieldEnum;
import vn.phuong.tester.automation.model.Function;

import java.util.List;
import java.util.Map;

/**
 * AbstractTemplate
 *
 * @author phuong.nguyenthi
 */
public abstract class AbstractTemplate {

    protected Workbook workBook;

    public AbstractTemplate(Workbook workBook){
        this.workBook = workBook;
    }

    protected abstract CellStyle getTitleCellStyle();

    public abstract void write(FunctionBean job);

    protected void writeCell(Sheet sheet, int row, int col, String value){
        writeCell(sheet, row, col, value, null);
    }

    protected void writeCell(Sheet sheet, int row, int col, String value, CellStyle cellStyle){
        Row sheetRow = sheet.getRow(row) != null ? sheet.getRow(row): sheet.createRow(row);
        Cell cell = sheetRow.createCell(col);
        if(cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
        cell.setCellValue(value);
    }

    protected void writeCell(Sheet sheet, TemplateSettingFieldEnum enumFieldEnum, String value){
        writeCell(sheet, enumFieldEnum.getlRow(), enumFieldEnum.getlCol(), enumFieldEnum.getRawName());
        writeCell(sheet, enumFieldEnum.getvRow(), enumFieldEnum.getvCol(), value);
    }

    protected void writeCol(Sheet sheet, Map<TemplateColumnFieldEnum, List<String>> data, int titleRow){
        CellStyle titleCellStyle = getTitleCellStyle();
        for(TemplateColumnFieldEnum key : data.keySet()){
            //Write title
            writeCell(sheet, titleRow, key.getCol(), key.getRawName(), titleCellStyle);
            int initRow = titleRow;
            //Write data
            for(String dataValue : data.get(key)){
                writeCell(sheet, ++initRow, key.getCol(), dataValue);
            }
        }
    }

}
