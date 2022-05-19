package app.domain.model;

public class Vaccine {

    private VaccineAdministration vaccineAdministration;
    private TypeVaccine typeVaccine;
    private String name;

    public Vaccine(VaccineAdministration vaccineAdministration,TypeVaccine typeVaccine,String name){
        this.name=typeVaccine.getName();
        this.typeVaccine=typeVaccine;
        this.vaccineAdministration=vaccineAdministration;
    }

    public TypeVaccine getTypeVaccine() {
        return typeVaccine;
    }

    public String getName() {
        return name;
    }

    public VaccineAdministration getVaccineAdministration() {
        return vaccineAdministration;
    }
}
