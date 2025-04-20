import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class EmployeeDataProvider {

  
    public static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            System.out.println("Cell is null!");
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    
    @DataProvider(name = "employeeDataFromExcel")
    public static Object[][] getEmployeeData() throws Exception {
        FileInputStream fis = null;
        Workbook workbook = null;
        Object[][] data = null;
        
        try {
            fis = new FileInputStream(new File("src/test/resources/EmployeeData.xlsx"));
            System.out.println("Reading from: " + new File("src/test/resources/EmployeeData.xlsx").getAbsolutePath());

            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // حساب عدد الأسطر
            int rowCount = 0;
            while (rowIterator.hasNext()) {
                rowIterator.next();
                rowCount++;
            }

            data = new Object[rowCount - 1][6];  
            rowIterator = sheet.iterator();
            int rowIndex = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
               
                data[rowIndex - 1][0] = getCellValueAsString(row.getCell(0)); // الاسم الأول
                data[rowIndex - 1][1] = getCellValueAsString(row.getCell(1)); // الاسم الأوسط
                data[rowIndex - 1][2] = getCellValueAsString(row.getCell(2)); // الاسم الأخير
                data[rowIndex - 1][3] = getCellValueAsString(row.getCell(3)); // المسمى الوظيفي
                data[rowIndex - 1][4] = getCellValueAsString(row.getCell(4)); // 
                data[rowIndex - 1][5] = getCellValueAsString(row.getCell(5)); // 
                  
           
                System.out.println("Row " + rowIndex + ": " + 
                                   data[rowIndex - 1][0] + " " + 
                                   data[rowIndex - 1][1] + " " + 
                                   data[rowIndex - 1][2] + " " + 
                                   data[rowIndex - 1][3] + " " + 
                                   data[rowIndex - 1][4]+ " " +
                                    data[rowIndex - 1][5]);
                
                rowIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return data;
    }
}
