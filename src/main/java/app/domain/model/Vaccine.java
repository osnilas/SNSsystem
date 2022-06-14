package app.domain.model;

import java.io.Serializable;

public class Vaccine implements Serializable {

    private VaccineAdministration vaccineAdministration;
    private TypeVaccine typeVaccine;
    private String name;

    /**
     * @author João Veiga
     * @Description Constructor.
     * Creates a vaccine with the following attributes.
     * @param vaccineAdministration
     * @param typeVaccine
     */
    public Vaccine(VaccineAdministration vaccineAdministration,TypeVaccine typeVaccine){
        this.name=vaccineAdministration.getBrand();
        this.typeVaccine=typeVaccine;
        this.vaccineAdministration=vaccineAdministration;
    }

    public TypeVaccine getTypeVaccine() {
        return typeVaccine;
    }

    public String getName() {
        return name;
    }

    /**
     * @author João Veiga
     * @Description This method Gets the vaccine administration index for the age group.
     * @param age
     * @return Vaccine administration index for the age group.
     */
    public int getAgeGroup(int age){
        int ageGroup=-1;
        for(int i=0;i<vaccineAdministration.getMinAge().size();i++){
            if(age>= vaccineAdministration.getMinAge().get(i) && age<=vaccineAdministration.getMaxAge().get(i)){
                return i;
            }
        }
        return ageGroup;
    }

    public boolean nameSame(String name){
        return this.name.equalsIgnoreCase(name);
    }

    public VaccineAdministration getVaccineAdministration() {
        return vaccineAdministration;
    }
}
