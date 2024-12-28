package atomicredteam.api;

import atomicredteam.model.Technique;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MitreCoverageAnalyzer {
    public void analyzeCoverage(List<Technique> techniques) {
        System.out.println("Phan tich ty le bao phu MITRE ATT&CK...");
        Set<String> uniqueTechniques = new HashSet<>();
        for (Technique technique : techniques) {
            uniqueTechniques.add(technique.getId());
        }
        System.out.println("Tong so techniques: " + techniques.size());
        System.out.println("So luong techniques duy nhat: " + uniqueTechniques.size());
    }
}
