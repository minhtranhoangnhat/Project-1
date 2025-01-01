package atomicredteam.api;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import atomicredteam.model.AtomicTest;

public class AtomicApiFetcher {
    private static final String ATOMIC_API_URL = "https://raw.githubusercontent.com/redcanaryco/atomic-red-team/master/atomics/T1003/T1003.yaml";

    public static AtomicTest fetchAtomicTest() throws Exception {
        URL url = new URL(ATOMIC_API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream()) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);

            AtomicTest test = new AtomicTest();
            test.setTechniqueId((String) data.get("attack_technique"));
            test.setName((String) data.get("display_name"));
            test.setDescription((String) data.get("description"));
            test.setCommands((List<String>) data.get("executor.command"));

            return test;
        }
    }
}