package atomicredteam.api;

import org.yaml.snakeyaml.Yaml;
import org.kohsuke.github.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import atomicredteam.model.AtomicTest;

public class AtomicApiFetcher {
    private static final String BASE_URL = "https://raw.githubusercontent.com/redcanaryco/atomic-red-team/master/atomics"; 
    private static final String REPO_NAME = "redcanaryco/atomic-red-team";
    private static final String ATOMICS_PATH = "atomics";

    public static List<AtomicTest> fetchAtomicTest() throws Exception {
        GitHub github = new GitHubBuilder().build();
        GHRepository repository = github.getRepository(REPO_NAME);
        List<String> folders = new ArrayList<>();
        // Lấy danh sách thư mục atomics
        List<GHContent> entries = repository.getDirectoryContent(ATOMICS_PATH);
        for (GHContent entry : entries) {
            if (entry.isDirectory()) {
                String techniqueUrl = entry.getHtmlUrl().toString();
                String[] parts = techniqueUrl.split("/");
                String result = parts[parts.length - 1];
                folders.add(result);
            }
        }

        List<AtomicTest> atomicTests = new ArrayList<>();

        for (String folder : folders) {
            if (folder.equalsIgnoreCase("Indexes")) continue; 

            String yamlFileUrl = BASE_URL + "/" + folder + "/" + folder + ".yaml"; 
            System.out.println("Dang xu li file .yaml tu URL: " + yamlFileUrl);
            try {
                AtomicTest test = fetchAtomicTestFromYaml(yamlFileUrl); 
                System.out.println("ID ki thuat tan cong: " + test.getTechniqueId());
                System.out.println("Ten ki thuat tan cong: " + test.getName());
                System.out.println("-----------------------------------");
                if (test != null) {
                    atomicTests.add(test);
                }
            } catch (Exception e) {
                System.err.println("Loi xu li file .yaml tu thu muc: " + folder + ". Chi tiet: " + e.getMessage());
            }
        }

        return atomicTests;
    }

    private static AtomicTest fetchAtomicTestFromYaml(String yamlUrl) throws Exception {
        URL url = new URL(yamlUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream()) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);

            AtomicTest test = new AtomicTest();
            test.setTechniqueId((String) data.get("attack_technique")); 
            test.setName((String) data.get("display_name")); 
            return test;
        } catch (Exception e) {
            System.err.println("Loi khi doc file .yaml tu URL: " + yamlUrl); 
            return null;
        }
    }
}
