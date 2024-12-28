package atomicredteam.model;

public class Technique {
    private String id;
    private String name;
    private String url;

    public Technique(String name, String url) {
        this.id = extractId(name);
        this.name = name;
        this.url = url;
    }

    private String extractId(String name) {
        return name.split("_")[0]; // Tách ID từ tên thư mục
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
