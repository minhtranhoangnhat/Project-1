package atomicredteam.api;

import atomicredteam.model.AtomicTest;
import atomicredteam.model.MitreTechnique;

import java.util.ArrayList;
import java.util.List;

public class CollectData {

    public static List<AtomicTest> integrateMitreAndAtomic(List<MitreTechnique> mitreTechniques, List<AtomicTest> atomicTests) {
        List<AtomicTest> integratedTests = new ArrayList<>();

        if (mitreTechniques == null || mitreTechniques.isEmpty()) {
            System.err.println("Danh sach MITRE Techniques trong!");
            return integratedTests;
        }
        if (atomicTests == null || atomicTests.isEmpty()) {
            System.err.println("Danh sach Atomic Tests trong!");
            return integratedTests;
        }

        for (MitreTechnique technique : mitreTechniques) {
            if (technique.getId() == null) continue; 

            for (AtomicTest atomicTest : atomicTests) {
                if (atomicTest.getTechniqueId() == null) continue; 

                if (technique.getId().equals(atomicTest.getTechniqueId())) {
                    AtomicTest integratedTest = new AtomicTest();
                    integratedTest.setTechniqueId(atomicTest.getTechniqueId());
                    integratedTest.setName(technique.getName());

                    integratedTests.add(integratedTest);
                }
            }
        }

        return integratedTests;
    }
}
