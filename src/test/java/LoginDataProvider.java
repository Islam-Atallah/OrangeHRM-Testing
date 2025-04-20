
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginDataProvider {

    @DataProvider(name = "loginDataFromExcel")
    public static Object[][] readLoginData() {
        String filePath = "src/test/resources/loginData.xlsx";
        Object[][] data = null;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();
            data = new Object[rows - 1][3]; // username, password, expectedResult (true/false)

            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);

                Cell usernameCell = row.getCell(0);
                Cell passwordCell = row.getCell(1);
                Cell resultCell = row.getCell(2);

                if (usernameCell == null || passwordCell == null || resultCell == null) {
                    continue; 
                }

                String username = usernameCell.toString();
                String password = passwordCell.toString();
                boolean expectedResult = Boolean.parseBoolean(resultCell.toString().toLowerCase());


                data[i - 1][0] = username;
                data[i - 1][1] = password;
                data[i - 1][2] = expectedResult;
            }

            workbook.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
