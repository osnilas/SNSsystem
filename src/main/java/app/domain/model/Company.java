package app.domain.model;


import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import jdk.jshell.Snippet;
import mappers.dto.dtoSNSuser;
import mappers.dto.dtoScheduleVaccine;
import mappers.dto.dtoVaccinationFacility;
import org.apache.commons.lang3.Validate;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
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

    private static List<VaccinationCenter> VaccinationCentersList= new ArrayList<>();

    private static List<HealthCareCenter> HealthCareCenterList= new ArrayList<>();

    private static List<SNSuser> SNSuserList=new ArrayList<>();

    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.designation = designation;
        this.authFacade = new AuthFacade();
        VaccinationCentersList.add(Constants.VACCINATION_CENTER_TESTER);
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

    public static List<VaccinationCenter> getVaccinationCenterList() {
        return VaccinationCentersList;
    }

    public static List<HealthCareCenter> getHealthCareCenterList() {
        return HealthCareCenterList;
    }



    public boolean SNSuserExists(int SNSnumber){
        boolean flag=false;
        for(int i=0;i<SNSuserList.size();i++){
            if(SNSuserList.get(i).getSNSnumber()==SNSnumber){
                flag = true;
            }
        }
        return flag;
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
        return  ! this.VaccinationCentersList.contains(vaccinationCenter);
    }

    public boolean saveVaccinationCenter(VaccinationCenter vaccinationCenter){
        if(!validateVaccinationCenter(vaccinationCenter)){
            return false;
        }
        return this.VaccinationCentersList.add(vaccinationCenter);
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


    public VaccinationSchedule createVaccinationSchedule(dtoScheduleVaccine dtoScheduleVaccine){
        return new VaccinationSchedule(dtoScheduleVaccine.getSNSnumber(),dtoScheduleVaccine.getAppointmentDate(),dtoScheduleVaccine.getTypeVaccine());
    }

    public boolean ValidateCreationVaccinationSchedule(VaccinationSchedule vaccintion,dtoVaccinationFacility dto){
        if(vaccintion==null){
            return false;
        }
        return !dto.getVaccinationScheduleList().contains(vaccintion);
    }

    public int findListVaccinationFacilities(Object facility){
        if(facility instanceof VaccinationCenter){
            return findVaccinationCenter(facility);
        }
        if(facility instanceof HealthCareCenter){
            return findHealthCareCenter(facility);
        }
        return -1;
    }

    public boolean saveSchedule(Object facility,dtoScheduleVaccine dto){
        boolean flag=false;
        int index=findListVaccinationFacilities(facility);
        if(index==-1){
            Utils.printText("ERROR vaccination facility not found");
            flag=false;
        }
        else{
            addSchedule(index,facility,createVaccinationSchedule(dto));
            flag=true;
        }
        return flag;
    }

    public void addSchedule(int index,Object facility,VaccinationSchedule schedule){
        if(facility instanceof VaccinationCenter){
            VaccinationCentersList.get(index).addScheduleList(schedule);
        }
        if(facility instanceof HealthCareCenter){
            HealthCareCenterList.get(index).addScheduleList(schedule);
        }
    }

    public int findVaccinationCenter(Object vaccinationCenter){
        for(int i=0;i<VaccinationCentersList.size();i++){
            if(Objects.equals(vaccinationCenter,VaccinationCentersList.get(i))){
                return i;
            }
        }
        return -1;
    }

    public int findHealthCareCenter (Object healthCareCenter){
        for(int i=0;i<HealthCareCenterList.size();i++){
            if(Objects.equals(HealthCareCenterList,VaccinationCentersList.get(i))){
                return i;
            }
        }
        return -1;
    }

    public boolean ValidateAppoimentTime(LocalDateTime date,dtoVaccinationFacility dto){
        boolean flag=false;
        int count=0;
        List<VaccinationSchedule> scheduleList=dto.getVaccinationScheduleList();
        for (int i=0;i<scheduleList.size();i++){
            if(scheduleList.get(i).getAppointmentTime().isEqual(date)){
                count++;
                if(count==dto.getMaximumNumberOfVaccinesPerSlot()) {
                    flag = false;
                    break;
                }
            }
            flag=true;
        }
        return !flag;
    }



}