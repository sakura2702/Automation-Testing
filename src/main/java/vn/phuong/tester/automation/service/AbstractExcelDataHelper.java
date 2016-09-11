package vn.phuong.tester.automation.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vn.phuong.tester.automation.bean.FunctionBean;
import vn.phuong.tester.automation.model.Function;
import vn.phuong.tester.automation.skill.AbstractTestSkill;

public abstract class AbstractExcelDataHelper {

    protected Workbook workBook;
    private File file;


    public AbstractExcelDataHelper(File file) throws Exception {
        this.file = file;
        final FileInputStream excelFile = new FileInputStream(file);
        BufferedInputStream stream = new BufferedInputStream(excelFile);
        boolean isFormat2007 = POIXMLDocument.hasOOXMLHeader(stream);
        if (isFormat2007) {
            workBook = new XSSFWorkbook(stream);
        } else {
            workBook = new HSSFWorkbook(stream);
        }
    }

    public AbstractExcelDataHelper(){
        this.workBook = new HSSFWorkbook();
    }

    protected Sheet getSheet(int position) {
       return workBook.getSheetAt(position);
    }

    protected Sheet getSheet(String sheetName) {
        return workBook.getSheet(sheetName);
    }

    public abstract FunctionBean collectJob() throws Exception;

    public abstract List<AbstractTestSkill> processTestCase() throws Exception;

    /**
     *
     * @param rowNum
     * @param colNum
     * @return
     */
    protected Double getNumericCellValue(int rowNum, int colNum, Sheet sheet) {
        Object number = readCell(rowNum, colNum, sheet);
        if (number != null && number instanceof Double) {
            return (Double) number;
        }
        return null;
    }
    protected Double getNumericCellValue(Row row, int col) throws NumberFormatException {
        Object number = readCell(row, col);
        if(number != null ) {
            if (number != null && number instanceof Double) {
                return (Double) number;
            } else {
                return Double.parseDouble(number.toString());
            }
        }
        return null;
    }

    protected int getIntCellValue(Row row, int col) throws NumberFormatException{
        Double aDouble = getNumericCellValue(row, col);
        if(aDouble!=null) {
            return aDouble.intValue();
        }
        return 0;
    }

    protected String getStringCellValue(Row row, int col){
        Object obj = readCell(row, col);
        return obj != null ? obj.toString() : null;
    }

    protected String getStringCellValue(Integer rowNum, int colNum, Sheet sheet){
        Object obj = readCell(rowNum, colNum, sheet);
        return obj != null ? obj.toString() : null;
    }


    protected Object readCell(Integer rowNum, int colNum, Sheet sheet) {
        if(rowNum == null || sheet == null) return null;
        Row row = sheet.getRow(rowNum);
        return row !=null ? readCell(row, colNum) : null;
    }

    protected Object readCell(Row row, int col) {
        Cell excelCell = row.getCell(col, Row.RETURN_NULL_AND_BLANK);

        if(excelCell == null) return null;
        switch (excelCell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return excelCell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return processNumbericCellValue(excelCell);
            case Cell.CELL_TYPE_BOOLEAN:
                return excelCell.getBooleanCellValue();
            case Cell.CELL_TYPE_FORMULA:
                switch (excelCell.getCachedFormulaResultType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        return excelCell.getNumericCellValue();
                    case Cell.CELL_TYPE_STRING:
                        return excelCell.getStringCellValue();
                }
            default:
                return null;
        }
    }
    private Object processNumbericCellValue(Cell excelCell){
        double doubleValue =  excelCell.getNumericCellValue();
        Object result;
        if(doubleValue == Math.floor(doubleValue) && !Double.isInfinite(doubleValue)){
            result = new Integer((int) doubleValue);
        } else {
            result = doubleValue;
        }
        return result;
    }



    public void write(String result, Integer rowNum, int colNum, Sheet sheet) throws IOException {
        Row row = sheet.getRow(rowNum);
        setData(result, row, colNum);
        flush();
    }

    public void write(String result, Row row, int col) throws IOException {
        setData(result, row, col);
        flush();
    }

    public void setData(String result, Row row, int col){
        Cell excelCell = row.getCell(col, Row.RETURN_BLANK_AS_NULL);
        if (excelCell == null) {
            excelCell = row.createCell(col);
            excelCell.setCellValue(result);
        } else {
            excelCell.setCellValue(result);
        }
    }

    public Cell getCell(Row row, int col) {
        Cell excelCell = row.getCell(col, Row.RETURN_BLANK_AS_NULL);
        if (excelCell == null) {
            excelCell = row.createCell(col);
        }
        return excelCell;
    }

    public void flush() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        workBook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
}
