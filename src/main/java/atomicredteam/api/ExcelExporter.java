package atomicredteam.api;

import atomicredteam.model.Technique;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    public static void exportToExcel(List<Technique> techniques, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Techniques");

        // Tạo tiêu đề
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Technique ID");
        header.createCell(1).setCellValue("Technique Name");
        header.createCell(2).setCellValue("URL");

        int rowNum = 1;
        for (Technique technique : techniques) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(technique.getId());
            row.createCell(1).setCellValue(technique.getName());
            row.createCell(2).setCellValue(technique.getUrl());
        }

        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }
        workbook.close();
        System.out.println("Du lieu da duoc xuat ra file: " + fileName);
    }
}
