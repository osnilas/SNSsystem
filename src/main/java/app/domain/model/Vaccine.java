package app.domain.model;

public class Vaccine {

    private VaccineAdministration vaccineAdministration;
    private TypeVaccine typeVaccine;
    private String name;

    public Vaccine(VaccineAdministration vaccineAdministration,TypeVaccine typeVaccine){
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

    public int getAgeGroup(int age){
        int ageGroup=-1;
        for(int i=0;i<vaccineAdministration.getMinAge().size();i++){
            if(vaccineAdministration.getMinAge().get(i)<age && age<vaccineAdministration.getMaxAge().get(i)){
                return i;
            }
        }
        return ageGroup;
    }

    public VaccineAdministration getVaccineAdministration() {
        return vaccineAdministration;
    }
}
