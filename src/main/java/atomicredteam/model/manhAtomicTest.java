package atomicredteam.model;

import java.util.List;

public class manhAtomicTest{
    private String techniqueId;
    private String name;
    private String description;
    private List<String> commands;

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
    public List<String> getCommands() {
        return commands;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setTechniqueId(String techniqueId){
        this.techniqueId = techniqueId;
    }
    public String getTechniqueId(){
        return techniqueId;
    }

}