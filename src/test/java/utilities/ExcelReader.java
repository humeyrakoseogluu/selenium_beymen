package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    //null durumları kontrol et
    public String searchFromExcel(String excelFilePath, int sheet, int row, int cell){
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet tableSheet = workbook.getSheetAt(sheet);
            Row tableRow = tableSheet.getRow(row);
            Cell tableCell = tableRow.getCell(cell);
            return tableCell.getStringCellValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
