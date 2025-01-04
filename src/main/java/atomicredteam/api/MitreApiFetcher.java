package atomicredteam.api;

import atomicredteam.model.MitreTechnique;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class MitreApiFetcher {
    private static final String MITRE_API_URL = "https://raw.githubusercontent.com/mitre/cti/ATT%26CK-v6.0/enterprise-attack/enterprise-attack.json";

    public static List<MitreTechnique> fetchMitreTechniques() throws Exception {
        URL url = new URL(MITRE_API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Failed to fetch data. HTTP response code: " + responseCode);
        }

        List<MitreTechnique> techniques = new ArrayList<>();

        try (InputStream inputStream = connection.getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputStream);

            JsonNode objectsNode = rootNode.get("objects");
            if (objectsNode == null || !objectsNode.isArray()) {
                throw new IOException("Invalid JSON format: 'objects' field is missing or not an array.");
            }

            // Lọc các đối tượng có trường 'external_id' bắt đầu bằng "T"
            for (JsonNode objectNode : objectsNode) {
                JsonNode externalIdNode = objectNode.get("external_references");
                if (externalIdNode != null && externalIdNode.isArray()) {
                    for (JsonNode refNode : externalIdNode) {
                        JsonNode idNode = refNode.get("external_id");
                        if (idNode != null && idNode.asText().startsWith("T")) { // Kiểm tra external_id bắt đầu bằng "T"
                            MitreTechnique technique = new MitreTechnique();
                            technique.setId(idNode.asText()); 
                            techniques.add(technique);
                            System.out.println("ID du lieu thu thap duoc tu Mitre Att&Ck: " + idNode.asText());
                            System.out.println("-----------------------------------");
                        }
                    }
                }
            }
        }

        return techniques;
    }
}
