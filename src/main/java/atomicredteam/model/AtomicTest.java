package atomicredteam.model;

public class AtomicTest{
    private String techniqueId;
    private String name;

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