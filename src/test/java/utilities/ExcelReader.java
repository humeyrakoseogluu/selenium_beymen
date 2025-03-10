package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

   // Method to search and retrieve a specific cell value from an Excel file
   public String searchFromExcel(String excelFilePath, int sheet, int row, int cell){
       try (FileInputStream fis = new FileInputStream(excelFilePath); // Open the Excel file for reading
            Workbook workbook = new XSSFWorkbook(fis)) { // Create a Workbook instance for .xlsx files

           // Get the sheet by its index
           Sheet tableSheet = workbook.getSheetAt(sheet);
           if (tableSheet == null) return "Sheet not found"; // Check if the sheet exists

           // Get the row by its index
           Row tableRow = tableSheet.getRow(row);
           if (tableRow == null) return "Row not found"; // Check if the row exists

           // Get the cell by its index
           Cell tableCell = tableRow.getCell(cell);
           if (tableCell == null) return "Cell not found"; // Check if the cell exists

           // Return the string value of the cell
           return tableCell.getStringCellValue();
       } catch (IOException e) {
           e.printStackTrace(); // Print the exception if there is an error reading the file
           return "Error reading file"; // Return a custom error message
       }
   }
}
