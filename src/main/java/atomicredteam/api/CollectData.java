package atomicredteam.api;


import atomicredteam.model.AtomicTest;
import atomicredteam.model.MitreTechnique;

import java.util.ArrayList;
import java.util.List;


public class CollectData {
    public static List<AtomicTest> integrateMitreAndAtomic(List<MitreTechnique> mitreTechniques, List<AtomicTest> atomicTests) {
        List<AtomicTest> integratedTests = new ArrayList<>();

        for (MitreTechnique technique : mitreTechniques) {
            for (AtomicTest atomicTest : atomicTests) {
                if (technique.getId().equals(atomicTest.getTechniqueId())) {

                    atomicTest.setDescription(technique.getDescription());
                    atomicTest.setName(technique.getName());
                    integratedTests.add(atomicTest);
                }
            }
        }

        return integratedTests;
    }
}