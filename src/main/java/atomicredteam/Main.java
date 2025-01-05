package atomicredteam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import atomicredteam.api.MitreApiFetcher;
import atomicredteam.api.AtomicApiFetcher;
import atomicredteam.api.CollectData;
import atomicredteam.api.ExcelExporter;
import atomicredteam.model.AtomicTest;
import atomicredteam.model.MitreTechnique;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<MitreTechnique> mitreTechniques = new ArrayList<>();
        List<AtomicTest> atomicTests = new ArrayList<>();
        List<AtomicTest> intergratedData = new ArrayList<>();

        while (true) {
            System.out.println("\n\033[1;34m===== CHUONG TRINH THU THAP DU LIEU ATOMIC RED TEAM =====\033[0m");
            System.out.println("1. Thu thap du lieu tu GitHub");
            System.out.println("2. Xuat du lieu ra file Excel");
            System.out.println("3. Thong ke ty le bao phu MITRE ATT&CK");
            System.out.println("4. Thoat chuong trinh");
            System.out.print("Lua chon cua ban: ");

            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    System.out.println("\033[1;33mDang thu thap du lieu...\033[0m");
                    try {
                        mitreTechniques = MitreApiFetcher.fetchMitreTechniques();
                        atomicTests = AtomicApiFetcher.fetchAtomicTest();
                        intergratedData = CollectData.integrateMitreAndAtomic(mitreTechniques, atomicTests);
                        System.out.println("Thu thap du lieu thanh cong!\nKet qua thu thap duoc:");
                        System.out.println("\033[1;32m-So luong ky thuat tu Atomic la: " + atomicTests.size() + "\033[0m");
                        System.out.println("\033[1;32m-So luong ky thuat tu Mitre Att&Ck la: " + mitreTechniques.size() + "\033[0m");
                        System.out.println("\033[1;32m-So luong ky thuat duoc bao phu la: " + intergratedData.size() + "\033[0m");
                    } catch (IOException e) {
                        System.err.println("\033[1;31mLoi khi thu thap du lieu: " + e.getMessage() + "\033[0m");
                    } catch (Exception e) {
                        System.err.println("\033[1;31mLoi khac: " + e.getMessage() + "\033[0m");
                    }
                    break;
                case 2:
                    if (intergratedData.isEmpty()) {
                        System.out.println("\033[1;31mDu lieu trong! Vui long thu thap du lieu truoc.\033[0m");
                    } else {
                        System.out.print("Nhap ten file Excel (vi du: AtomicData.xlsx): ");
                        String fileName = br.readLine();
                        try {
                            ExcelExporter.exportToExcel(atomicTests, mitreTechniques, intergratedData, fileName);
                        } catch (IOException e) {
                            System.err.println("\033[1;31mLoi khi xuat du lieu: " + e.getMessage() + "\033[0m");
                        }
                    }
                    break;
                case 3:
                    if (intergratedData.isEmpty()) {
                        System.out.println("\033[1;31mDu lieu trong! Vui long thu thap du lieu truoc.\033[0m");
                    } else {
                        double coveragePercentage = (double) intergratedData.size() / mitreTechniques.size() * 100;
                        System.out.println("ti le bao phu la: " + intergratedData.size() + "/" + mitreTechniques.size() +
                                " ki thuat duoc bao phu (" + String.format("%.2f", coveragePercentage) + "%).");
                    }
                    break;
                case 4:
                    System.out.println("\033[1;34mThoat chuong trinh!\033[0m");
                    br.close();
                    return;
                default:
                    System.out.println("\033[1;31mLua chon khong hop le. Vui long thu lai.\033[0m");
            }
        }
    }
}
