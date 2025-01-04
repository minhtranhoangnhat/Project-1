package atomicredteam.api;

import atomicredteam.model.AtomicTest;
import atomicredteam.model.MitreTechnique;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExcelExporter {
    private static final String EXPORT_FOLDER = "Exported files";

    public static void exportToExcel(
            List<AtomicTest> atomicTests, 
            List<MitreTechnique> mitreTechniques, 
            List<AtomicTest> integratedTests, 
            String fileName) throws IOException {


        createExportFolder();

        String outputPath = EXPORT_FOLDER + File.separator + fileName;

        Workbook workbook = new XSSFWorkbook();

        createAtomicTestSheet(workbook, atomicTests);

        createMitreTechniqueSheet(workbook, mitreTechniques);

        createIntegratedTestSheet(workbook, integratedTests);

        try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
        }
        workbook.close();

        System.out.println("File Excel da duoc xuat vao thu muc: " + EXPORT_FOLDER + " voi ten file: " + fileName);
    }

    private static void createAtomicTestSheet(Workbook workbook, List<AtomicTest> atomicTests) {
        Sheet sheet = workbook.createSheet("Atomic Tests");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Technique ID");
        header.createCell(1).setCellValue("Name");

        int rowNum = 1;
        for (AtomicTest test : atomicTests) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(test.getTechniqueId());
            row.createCell(1).setCellValue(test.getName());
        }
    }

    private static void createMitreTechniqueSheet(Workbook workbook, List<MitreTechnique> mitreTechniques) {
        Sheet sheet = workbook.createSheet("Mitre Techniques");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Technique ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Type");

        int rowNum = 1;
        for (MitreTechnique technique : mitreTechniques) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(technique.getId());
            row.createCell(1).setCellValue(technique.getName());
            row.createCell(2).setCellValue(technique.getDescription());
            row.createCell(3).setCellValue(technique.getType());
        }
    }

    private static void createIntegratedTestSheet(Workbook workbook, List<AtomicTest> integratedTests) {
        Sheet sheet = workbook.createSheet("Integrated Tests");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Technique ID");
        header.createCell(1).setCellValue("Name");

        int rowNum = 1;
        for (AtomicTest test : integratedTests) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(test.getTechniqueId());
            row.createCell(1).setCellValue(test.getName());
        }
    }

    private static void createExportFolder() throws IOException {
        Path folderPath = Paths.get(EXPORT_FOLDER);
        if (!Files.exists(folderPath)) {
            Files.createDirectory(folderPath);
            System.out.println("Thu muc \"" + EXPORT_FOLDER + "\" da duoc tao.");
        }
    }
}
