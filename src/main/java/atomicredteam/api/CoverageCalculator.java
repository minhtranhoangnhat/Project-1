package atomicredteam.api;

import atomicredteam.model.manhAtomicTest;
import atomicredteam.model.manhMitreTechnique;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoverageCalculator {

    public static double calculateCoveragePercentage(List<manhMitreTechnique> mitreTechniques, List<manhAtomicTest> atomicTests) {
        if (mitreTechniques == null || mitreTechniques.isEmpty()) {
            throw new IllegalArgumentException("The MITRE techniques list is empty or null.");
        }

        if (atomicTests == null || atomicTests.isEmpty()) {
            System.out.println("Warning: The atomic tests list is empty. Coverage will be 0%.");
            return 0.0;
        }

        Set<String> mitreTechniqueIds = new HashSet<>();
        for (manhMitreTechnique technique : mitreTechniques) {
            mitreTechniqueIds.add(technique.getId());
        }

        Set<String> coveredTechniqueIds = new HashSet<>();
        for (manhAtomicTest test : atomicTests) {
            if (mitreTechniqueIds.contains(test.getTechniqueId())) {
                coveredTechniqueIds.add(test.getTechniqueId());
            }
        }

        double coveragePercentage = (double) coveredTechniqueIds.size() / mitreTechniqueIds.size() * 100;
        System.out.println("Coverage: " + coveredTechniqueIds.size() + "/" + mitreTechniqueIds.size() +
                " techniques covered (" + String.format("%.2f", coveragePercentage) + "%).");

        return coveragePercentage;
    }
}
