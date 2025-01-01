package atomicredteam.api;


import atomicredteam.model.manhAtomicTest;
import atomicredteam.model.manhMitreTechnique;

import java.util.ArrayList;
import java.util.List;


public class CollectData {
    public static List<manhAtomicTest> integrateMitreAndAtomic(List<manhMitreTechnique> mitreTechniques, List<manhAtomicTest> atomicTests) {
        List<manhAtomicTest> integratedTests = new ArrayList<>();

        for (manhMitreTechnique technique : mitreTechniques) {
            for (manhAtomicTest atomicTest : atomicTests) {
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