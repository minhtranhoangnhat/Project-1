package atomicredteam;

import java.io.IOException;
import java.util.*;

import atomicredteam.api.GitHubClient;
import atomicredteam.api.MitreCoverageAnalyzer;
import atomicredteam.api.ExcelExporter;
import atomicredteam.model.Technique;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GitHubClient gitHubClient = new GitHubClient();
        MitreCoverageAnalyzer analyzer = new MitreCoverageAnalyzer();
        List<Technique> techniques = new ArrayList<>();

        while (true) {
            System.out.println("\n\033[1;34m===== Atomic Red Team Data Fetcher =====\033[0m");
            System.out.println("1. Thu thap du lieu tu GitHub");
            System.out.println("2. Xuat du lieu ra file Excel");
            System.out.println("3. Thong ke ty le bao phu MITRE ATT&CK");
            System.out.println("4. Thoat chuong trinh");
            System.out.print("Lua chon cua ban: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự newline còn lại

            switch (choice) {
                case 1:
                    System.out.println("\033[1;33mDang thu thap du lieu...\033[0m");
                    try {
                        techniques = gitHubClient.fetchTechniques();
                        System.out.println("\033[1;32mThu thap du lieu thanh cong! So luong ky thuat: " + techniques.size() + "\033[0m");
                    } catch (IOException e) {
                        System.err.println("\033[1;31mLoi khi thu thap du lieu: " + e.getMessage() + "\033[0m");
                    }
                    break;
                case 2:
                    if (techniques.isEmpty()) {
                        System.out.println("\033[1;31mDu lieu trong! Vui long thu thap du lieu truoc.\033[0m");
                    } else {
                        System.out.print("Nhap ten file Excel (vi du: AtomicData.xlsx): ");
                        String fileName = scanner.nextLine();
                        try {
                            ExcelExporter.exportToExcel(techniques, fileName);
                        } catch (IOException e) {
                            System.err.println("\033[1;31mLoi khi xuat du lieu: " + e.getMessage() + "\033[0m");
                        }
                    }
                    break;
                case 3:
                    if (techniques.isEmpty()) {
                        System.out.println("\033[1;31mDu lieu trong! Vui long thu thap du lieu truoc.\033[0m");
                    } else {
                        analyzer.analyzeCoverage(techniques);
                    }
                    break;
                case 4:
                    System.out.println("\033[1;34mThoat chuong trinh!\033[0m");
                    scanner.close();
                    return;
                default:
                    System.out.println("\033[1;31mLua chon khong hop le. Vui long thu lai.\033[0m");
            }
        }
    }
}
