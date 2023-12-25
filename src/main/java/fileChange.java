import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

import static org.apache.poi.ss.usermodel.Cell.*;

class fileChange

{

    static void xls(File inputFile)

    {

        // For storing data into CSV files
        File outputFile = new File(inputFile.getName() + "out.csv");
        StringBuffer data = new StringBuffer();
        try
        {
            FileOutputStream fos = new FileOutputStream(outputFile);

            // Get the workbook object for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputFile));
            // Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell;
            Row row;

            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                row = rowIterator.next();
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    cell = cellIterator.next();

                    switch (cell.getCellType())
                    {//
                        case BOOLEAN:
                            data.append(cell.getBooleanCellValue() + ",");
                            break;

                        case NUMERIC:
                            data.append(cell.getNumericCellValue() + ",");
                            break;

                        case STRING:
                            data.append(cell.getStringCellValue() + ",");
                            break;

                        case BLANK:
                            data.append("" + ",");
                            break;

                        default:
                            data.append(cell + ",");
                    }

                    data.append('\n');
                }
            }

            fos.write(data.toString().getBytes());
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}