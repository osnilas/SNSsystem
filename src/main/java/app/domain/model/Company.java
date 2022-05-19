package app.domain.model;


import app.domain.shared.Constants;
import mappers.dto.dtoSNSuser;
import mappers.dto.dtoScheduleVaccine;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;
import java.util.*;

import static app.domain.model.Employee.fillRoleList;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {
    private String designation;
    private AuthFacade authFacade;

    private static List<Employee> EmployeesList= new ArrayList<>();

    private static List<Employee> EmployeesListRole=new ArrayList<Employee>();
    private static List<User> UserList=new ArrayList<>();

    private static List<TypeVaccine> typeVaccineList=new ArrayList<>();
    private static List<VaccineAdministration> VaccineAdministrationList = new ArrayList<>();

    private static List<VaccinationFacility> VaccinationFacilityList=new ArrayList<>();

    private static List<SNSuser> SNSuserList=new ArrayList<>();

    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.designation = designation;
        this.authFacade = new AuthFacade();
        VaccinationFacilityList.add(Constants.VACCINATION_CENTER_TESTER);
        EmployeesList.add(Constants.EMPLOYEE_TESTER);
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public static List<Employee> getEmployeesList() {
        return EmployeesList;
    }

    public static List<VaccinationFacility> getVaccinationFacilityList() {
        return VaccinationFacilityList;
    }



    public SNSuser SNSuserExists(int SNSnumber){
        boolean flag=false;
        for(int i=0;i<SNSuserList.size();i++){
            if(SNSuserList.get(i).getSNSnumber()==SNSnumber){
                return  SNSuserList.get(i);
            }
        }
        return null;
    }

    public  void addCredencials(String name, String email, String roleId){

    }

    public User createUser(String id){
        return new User(id);
    }

    public boolean validateUser(User user){
        if(user==null){
            return false;
        }
        return ! this.UserList.contains(user);
    }

    public boolean saveUser(User user){
        if(!validateUser(user)){
            return false;
        }
        return this.UserList.add(user);
    }

    public  Employee createEmployee(String name, String email, int phone, int cc,String adress, String roleId){
        return new Employee(name,adress,phone,cc,email,roleId);
    }

    public boolean validateEmployee(Employee employee){
        if(employee==null){
            return false;
        }
        return  ! this.EmployeesList.contains(employee);
    }


    public boolean saveEmployees(Employee employee){
        if(!validateEmployee(employee)){
            return false;
        }
        return this.EmployeesList.add(employee);
    }

    public void printUser(User user){
        System.out.println(user.toString());
    }
    public void printEmployee(Employee employee){
        System.out.println(employee.toString());
    }

    public ArrayList<Employee> FillRoleArray(String role) {
        return fillRoleList(role, EmployeesList);
    }

    public boolean validateRoleArray(ArrayList<Employee> EmployeesRoleList){
        if(EmployeesRoleList== null){
            return false;
        }
        return true;

    }


    public VaccineAdministration createVaccineAdministration(String brand, List<Integer> minAge, List<Integer> maxAge, List<Double> dosage, List<Integer> doses, List<Integer> vaccineInterval) {
        return new VaccineAdministration(brand, minAge, maxAge, dosage, doses, vaccineInterval);
    }

    public boolean validateVaccineAdministration (VaccineAdministration vaxAdm) {
        if(vaxAdm == null)  {
            return false;
        }
        return !VaccineAdministrationList.contains(vaxAdm);
    }

    public boolean saveVaccineAdministration (VaccineAdministration vaxAdm) {
        if (!validateVaccineAdministration(vaxAdm)) {
            return false;
        }
        return addVaccineAdministration(vaxAdm);
    }

    public void printVaccineAdministration (VaccineAdministration vaxAdm) { vaxAdm.toString(); }

    private boolean addVaccineAdministration (VaccineAdministration vaxAdm) {
        return VaccineAdministrationList.add(vaxAdm);
    }

    public  VaccinationCenter createVaccinationCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalTime openingHours, LocalTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot, TypeVaccine typeOfVaccine){
        return new VaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours,closingHours, slotDuration, maximumNumberOfVaccinesPerSlot, typeOfVaccine);
    }

    public boolean validateVaccinationCenter(VaccinationCenter vaccinationCenter){
        if(vaccinationCenter==null){
            return false;
        }
        return  ! this.VaccinationFacilityList.contains(vaccinationCenter);
    }

    public boolean saveVaccinationCenter(VaccinationCenter vaccinationCenter){
        if(!validateVaccinationCenter(vaccinationCenter)){
            return false;
        }
        return this.VaccinationFacilityList.add(vaccinationCenter);
    }

    public void printVaccinationCenter(VaccinationCenter vaccinationCenter){
        System.out.println(vaccinationCenter.toString());
    }

    public  SNSuser createSNSuser( dtoSNSuser dto){
        return new SNSuser(dto.getName(), dto.getSex(), dto.getBirth(), dto.getAddress(), dto.getEmail(), dto.getPhoneNumber(), dto.getSNSnumber(), dto.getCcNumber());
    }
    public boolean validateSNSuser(SNSuser us){
        if(us==null){
            return false;
        }
        return  ! this.SNSuserList.contains(us);
    }

    public boolean saveSNSuser(dtoSNSuser dto){
        boolean flag=false;
        if(SNSuserList.isEmpty()){
            SNSuserList.add(createSNSuser(dto));
        }
        else {
            for(int i=0;i<SNSuserList.size();i++){
                if(!validateSNSuserDTO(dto,i)){
                    flag=true;
                }else{
                    flag=false;
                    break;
                }

            }
        }
        if(flag){
            SNSuserList.add(createSNSuser(dto));
        }
        else {
            System.out.println("SNS ALREADY EXISTS");
        }
        return flag;
    }

    public void printSNSuser(SNSuser us){
        System.out.println(us.toString());
    }

    public List<SNSuser> getSNSuserList(){return SNSuserList;}


    private boolean validateSNSuserDTO(dtoSNSuser dto,int i){
        boolean email=Objects.equals(SNSuserList.get(i).getEmail(),dto.getEmail());
        boolean cc=Objects.equals(SNSuserList.get(i).getCcNumber(),dto.getCcNumber());
        boolean phone=Objects.equals(SNSuserList.get(i).getPhoneNumber(),dto.getPhoneNumber());
        boolean snsNumber=Objects.equals(SNSuserList.get(i).getSNSnumber(),dto.getSNSnumber());

        return email && cc && phone && snsNumber;
    }



    public TypeVaccine createTypeVaccine(String name, String description, String code, String vaccineTechnology) {
        return new TypeVaccine(name, description, code, vaccineTechnology);
    }

    public boolean validateTypeVaccine(TypeVaccine typeVaccine) {
        if (typeVaccine == null) {
            return false;
        }
        return !typeVaccineList.contains(typeVaccine);
    }

    public boolean saveTypeVaccine(TypeVaccine typeVaccine) {
        if(!validateTypeVaccine(typeVaccine)){
            return false;
        }
        return typeVaccineList.add(typeVaccine);
    }

    public VaccinationSchedule createSchedule(dtoScheduleVaccine dto){
        return new VaccinationSchedule(dto.getSNSnumber(),dto.getAppointmentDate(),dto.getTypeVaccine());
    }

    public boolean saveSchedule(VaccinationSchedule schedule,VaccinationFacility facility,SNSuser snSuser){
        boolean flagVaccination=false ,flagSNSuser=false;
        for(int i=0;i<VaccinationFacilityList.size();i++){
            if(Objects.equals(VaccinationFacilityList.get(i),facility)){
                VaccinationFacilityList.get(i).addSchedule(schedule);
                flagVaccination= true;
            }
        }
        for(int i=0;i<SNSuserList.size();i++){
            if(Objects.equals(SNSuserList.get(i),snSuser)){
                SNSuserList.get(i).getVaccinationRecord().addVaccinationSchedule(schedule);
                flagSNSuser= true;
            }
        }
        return flagSNSuser&&flagVaccination;
    }

}