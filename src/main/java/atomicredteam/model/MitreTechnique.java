package atomicredteam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MitreTechnique {
    private String techniqueId;
    private String name;
    private String description;
    private String type;
    private List<String> x_mitre_tactic_type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return techniqueId;
    }

    public void setId(String techniqueId) {
        this.techniqueId = techniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getX_mitre_tactic_type() {
        return x_mitre_tactic_type;
    }

    public void setX_mitre_tactic_type(List<String> x_mitre_tactic_type) {
        this.x_mitre_tactic_type = x_mitre_tactic_type;
    }
}

