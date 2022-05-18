package mappers.dto;

import app.domain.model.HealthCareCenter;
import app.domain.model.VaccinationCenter;

import java.util.ArrayList;
import java.util.List;

public class mapperVaccinationFacility {

    public dtoVaccinationFacility toDTOVaccinationCenter (VaccinationCenter center){
        return new dtoVaccinationFacility(center.getName(),center.getAddress(),center.getPhoneNumber(),center.getEmailAddress(),center.getFaxNumber(), center.getWebsiteAddress(),center.getOpeningHours(),center.getClosingHours(), center.getSlotDuration(), center.getMaximumNumberOfVaccinesPerSlot(), center.getTypeOfVaccine());
    }

    public VaccinationCenter toVaccinationCenter(dtoVaccinationFacility dto){
        return new VaccinationCenter(dto.getName(),dto.getAddress(),dto.getPhoneNumber(),dto.getEmailAddress(),dto.getFaxNumber(), dto.getWebsiteAddress(),dto.getOpeningHours(),dto.getClosingHours(), dto.getSlotDuration(), dto.getMaximumNumberOfVaccinesPerSlot(), dto.getTypeVaccine());
    }

    public dtoVaccinationFacility toDTOHealthCareCenter (HealthCareCenter center){
        return new dtoVaccinationFacility(center.getName(),center.getAddress(),center.getPhoneNumber(),center.getEmailAddress(),center.getFaxNumber(), center.getWebsiteAddress(),center.getOpeningHours(),center.getClosingHours(), center.getSlotDuration(), center.getMaximumNumberOfVaccinesPerSlot(), center.getTypeVaccineList());
    }

    public HealthCareCenter toHeathCareCenter(dtoVaccinationFacility dto){
        return new HealthCareCenter(dto.getName(),dto.getAddress(),dto.getPhoneNumber(),dto.getEmailAddress(),dto.getFaxNumber(), dto.getWebsiteAddress(),dto.getOpeningHours(),dto.getClosingHours(), dto.getSlotDuration(), dto.getMaximumNumberOfVaccinesPerSlot(), dto.getTypeVaccineList());
    }

    public List<dtoVaccinationFacility> toDTOVaccinationCenterList (List<VaccinationCenter> center){
        List<dtoVaccinationFacility> list=new ArrayList<>();
        for(int i=0;i<center.size();i++) {
            list.add(new dtoVaccinationFacility(center.get(i).getName(), center.get(i).getAddress(), center.get(i).getPhoneNumber(), center.get(i).getEmailAddress(), center.get(i).getFaxNumber(), center.get(i).getWebsiteAddress(), center.get(i).getOpeningHours(), center.get(i).getClosingHours(), center.get(i).getSlotDuration(), center.get(i).getMaximumNumberOfVaccinesPerSlot(), center.get(i).getTypeOfVaccine()));
        }
        return list;
    }

    public List<VaccinationCenter> toVaccinationCenterList(List<dtoVaccinationFacility> dto){
        List<VaccinationCenter> list=new ArrayList<>();
        for(int i=0;i<dto.size();i++) {
            list.add(new VaccinationCenter(dto.get(i).getName(), dto.get(i).getAddress(), dto.get(i).getPhoneNumber(), dto.get(i).getEmailAddress(), dto.get(i).getFaxNumber(), dto.get(i).getWebsiteAddress(), dto.get(i).getOpeningHours(), dto.get(i).getClosingHours(), dto.get(i).getSlotDuration(), dto.get(i).getMaximumNumberOfVaccinesPerSlot(), dto.get(i).getTypeVaccine()));
        }
        return list;
    }

    public List<dtoVaccinationFacility> toDTOHealthCareCenterList (List <HealthCareCenter> center){
        List<dtoVaccinationFacility> list=new ArrayList<>();
        for(int i=0;i<center.size();i++) {
            list.add(new dtoVaccinationFacility(center.get(i).getName(), center.get(i).getAddress(), center.get(i).getPhoneNumber(), center.get(i).getEmailAddress(), center.get(i).getFaxNumber(), center.get(i).getWebsiteAddress(), center.get(i).getOpeningHours(), center.get(i).getClosingHours(), center.get(i).getSlotDuration(), center.get(i).getMaximumNumberOfVaccinesPerSlot(), center.get(i).getTypeVaccineList()));
        }
        return list;
    }

    public List<HealthCareCenter> toHeathCareCenterList(List<dtoVaccinationFacility> dto){
        List<HealthCareCenter> list=new ArrayList<>();
        for(int i=0;i<dto.size();i++) {
            list.add(new HealthCareCenter(dto.get(i).getName(), dto.get(i).getAddress(), dto.get(i).getPhoneNumber(), dto.get(i).getEmailAddress(), dto.get(i).getFaxNumber(), dto.get(i).getWebsiteAddress(), dto.get(i).getOpeningHours(), dto.get(i).getClosingHours(), dto.get(i).getSlotDuration(), dto.get(i).getMaximumNumberOfVaccinesPerSlot(), dto.get(i).getTypeVaccineList()));
        }
        return list;
    }
}
