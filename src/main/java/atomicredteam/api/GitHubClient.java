package atomicredteam.api;

import atomicredteam.model.Technique;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GitHubClient {
    private static final String REPO_NAME = "redcanaryco/atomic-red-team";
    private static final String ATOMICS_PATH = "atomics";

    public List<Technique> fetchTechniques() throws IOException {
        GitHub github = new GitHubBuilder().build();
        GHRepository repository = github.getRepository(REPO_NAME);

        // Lấy danh sách thư mục atomics
        List<GHContent> entries = repository.getDirectoryContent(ATOMICS_PATH);
        List<Technique> techniques = new ArrayList<>();

        for (GHContent entry : entries) {
            if (entry.isDirectory()) { // Kiểm tra xem entry có phải thư mục không
                String techniqueName = entry.getName();
                String techniqueUrl = entry.getHtmlUrl().toString(); // Lấy URL dưới dạng String
                Technique technique = new Technique(techniqueName, techniqueUrl);
                techniques.add(technique);
            }
        }
        return techniques;
    }
}
