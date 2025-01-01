package atomicredteam.api;

import atomicredteam.model.manhMitreTechnique;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.io.IOException;

public class manhMitreApiFetcher {
    private static final String MITRE_API_URL = "https://raw.githubusercontent.com/mitre/cti/ATT%26CK-v6.0/enterprise-attack/enterprise-attack.json";

    public static List<manhMitreTechnique> fetchMitreTechniques() throws Exception {
        URL url = new URL(MITRE_API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Failed to fetch data. HTTP response code: " + responseCode);
        }

        try (InputStream inputStream = connection.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(inputStream);

            JsonNode objectsNode = rootNode.get("objects");
            if (objectsNode == null || !objectsNode.isArray()) {
                throw new IOException("Invalid JSON format: 'objects' field is missing or not an array.");
            }

            return objectMapper.convertValue(objectsNode,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, manhMitreTechnique.class));
        }
    }
}