package app.domain.model;

import java.io.Serializable;
import java.util.List;

public class TypeVaccine implements Serializable {

    private String name;
    private String description;

    private String code;

    private String technology;



    public TypeVaccine (String name, String description, String code, String technology){
        this.name=name;
        this.description=description;
        this.code = code;
        this.technology = technology;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public String getTechnology() {
        return technology;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof TypeVaccine)) {
            return false;
        }
        TypeVaccine typeVaccine = (TypeVaccine) o;
        return this.name.equals(typeVaccine.name) && this.description.equals(typeVaccine.description)
                && this.code.equals(typeVaccine.code) && this.technology.equals(typeVaccine.technology);
    }
}
