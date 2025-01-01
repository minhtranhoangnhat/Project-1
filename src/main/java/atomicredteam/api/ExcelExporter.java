package atomicredteam.api;

import atomicredteam.model.AtomicTest;
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

    public static void exportToExcel(List<AtomicTest> atomicTests, String fileName) throws IOException {
        // Tạo thư mục "Exported files" nếu chưa tồn tại
        createExportFolder();

        // Xác định đường dẫn file đầu ra
        String outputPath = EXPORT_FOLDER + File.separator + fileName;

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Atomic Tests");

        // Tạo tiêu đề
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Technique ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Commands");

        // Ghi dữ liệu vào các hàng
        int rowNum = 1;
        for (AtomicTest test : atomicTests) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(test.getTechniqueId());
            row.createCell(1).setCellValue(test.getName());
            row.createCell(2).setCellValue(test.getDescription());

            // Ghi các lệnh (commands) vào một cell
            String commands = String.join("\n", test.getCommands());
            row.createCell(3).setCellValue(commands);
        }

        // Ghi file Excel vào đường dẫn chỉ định
        try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
        }
        workbook.close();

        System.out.println("File Excel đã được xuất vào thư mục: " + EXPORT_FOLDER + " với tên file: " + fileName);
    }

    // Phương thức tạo thư mục "Exported files"
    private static void createExportFolder() throws IOException {
        Path folderPath = Paths.get(EXPORT_FOLDER);
        if (!Files.exists(folderPath)) {
            Files.createDirectory(folderPath);
            System.out.println("Thư mục \"" + EXPORT_FOLDER + "\" đã được tạo.");
        }
    }
}
