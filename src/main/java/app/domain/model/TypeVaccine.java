package app.domain.model;

import java.util.List;

public class TypeVaccine {

    private String name;
    private String description;

    private String code;

    private String technology;

    private List<VaccineAdministration> administration;



    public TypeVaccine (String name, String description, String code, String technology){
        this.name=name;
        this.description=description;
        this.code = code;
        this.technology = technology;
        this.administration = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setAdministration(VaccineAdministration vaccineAdministration) {
        this.administration.add(vaccineAdministration);
    }
}
