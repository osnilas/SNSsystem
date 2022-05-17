package app.domain.model;

public class TypeVaccine {

    private String name;
    private String description;

    private String code;

    private String technology;


    public TypeVaccine (String name,String description){
        this.name=name;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
