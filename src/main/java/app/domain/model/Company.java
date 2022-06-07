package app.domain.model;


import app.domain.shared.Constants;
import app.domain.shared.DGSReportTask;
import app.ui.console.utils.Utils;
import mappers.dto.MapperSNSuser;
import mappers.dto.dtoEmployee;
import mappers.dto.dtoSNSuser;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static app.domain.model.Employee.fillRoleList;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {
    private String designation;
    private AuthFacade authFacade;

    private List<String> snsUsers = new ArrayList<String>();

    private static List<Employee> employeeList = new ArrayList<>();

    private static List<Employee> employeesListRole = new ArrayList<Employee>();
    private static List<TypeVaccine> typeVaccineList = new ArrayList<>();
    private static List<VaccineAdministration> vaccineAdministrationList = new ArrayList<>();

    private static List<Vaccine> vaccineList = new ArrayList<>();
    private static List<VaccinationFacility> vaccinationFacilityList =new ArrayList<>();

    private static List<SNSuser> SNSuserList=new ArrayList<>();
    private static MapperSNSuser mapperSNSuser=new MapperSNSuser();
    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.designation = designation;
        this.authFacade = new AuthFacade();
        demo();
        //bootstrap();
        dgsReportAuto();
    }


    private void bootstrap()
    {
        if(checkIfBinFilesExist()){
            if(!checkIfBinFilesEmpty()){
                load();
            }else{
                deleteBinFiles();
                demo();
            }
        }else{
            demo();
        }
    }

    public void deleteBinFiles(){
        Utils.deleteFile(Constants.FILEPATH_SNSUSERS);
        Utils.deleteFile(Constants.FILEPATH_EMPLOYEES);
        Utils.deleteFile(Constants.FILEPATH_VACCINES);
        Utils.deleteFile(Constants.FILEPATH_VACCINATION_FACILITIES);
        Utils.deleteFile(Constants.FILEPATH_VACCINE_ADMINISTRATIONS);
        Utils.deleteFile(Constants.FILEPATH_TYPE_VACCINES);
    }

    private boolean checkIfBinFilesExist(){
        return Utils.checkIfFileExists(Constants.FILEPATH_EMPLOYEES) &&
                Utils.checkIfFileExists(Constants.FILEPATH_VACCINES) &&
                Utils.checkIfFileExists(Constants.FILEPATH_VACCINATION_FACILITIES) &&
                Utils.checkIfFileExists(Constants.FILEPATH_VACCINE_ADMINISTRATIONS) &&
                Utils.checkIfFileExists(Constants.FILEPATH_TYPE_VACCINES) &&
                Utils.checkIfFileExists(Constants.FILEPATH_SNSUSERS);
    }
    private boolean checkIfBinFilesEmpty(){
        return Utils.checkIfFileEmpty(Constants.FILEPATH_EMPLOYEES) &&
                Utils.checkIfFileEmpty(Constants.FILEPATH_VACCINES) &&
                Utils.checkIfFileEmpty(Constants.FILEPATH_VACCINATION_FACILITIES) &&
                Utils.checkIfFileEmpty(Constants.FILEPATH_VACCINE_ADMINISTRATIONS) &&
                Utils.checkIfFileEmpty(Constants.FILEPATH_TYPE_VACCINES) &&
                Utils.checkIfFileEmpty(Constants.FILEPATH_SNSUSERS);
    }


    @SuppressWarnings("unchecked")
    public void load(){
        employeeList=(List<Employee>) Utils.readFile(Constants.FILEPATH_EMPLOYEES);
        typeVaccineList=(List<TypeVaccine>) Utils.readFile(Constants.FILEPATH_TYPE_VACCINES);
        vaccineAdministrationList=(List<VaccineAdministration>) Utils.readFile(Constants.FILEPATH_VACCINE_ADMINISTRATIONS);
        vaccineList=(List<Vaccine>) Utils.readFile(Constants.FILEPATH_VACCINES);
        vaccinationFacilityList=(List<VaccinationFacility>) Utils.readFile(Constants.FILEPATH_VACCINATION_FACILITIES);
        SNSuserList=(List<SNSuser>) Utils.readFile(Constants.FILEPATH_SNSUSERS);
    }

    public void saveAll(){
        saveEmployees();
        saveTypeVaccines();
        saveVaccineAdministrations();
        saveVaccines();
        saveVaccinationFacilities();
        saveSNSusers();
    }

    public void saveSNSusers(){
        Utils.save(Constants.FILEPATH_SNSUSERS,SNSuserList);
    }
    public void saveEmployees(){
        Utils.save(Constants.FILEPATH_EMPLOYEES,employeeList);
    }
    public void saveTypeVaccines(){
        Utils.save(Constants.FILEPATH_TYPE_VACCINES,typeVaccineList);
    }
    public void saveVaccineAdministrations(){
        Utils.save(Constants.FILEPATH_VACCINE_ADMINISTRATIONS,vaccineAdministrationList);
    }
    public void saveVaccines(){
        Utils.save(Constants.FILEPATH_VACCINES,vaccineList);
    }
    public void saveVaccinationFacilities(){
        Utils.save(Constants.FILEPATH_VACCINATION_FACILITIES,vaccinationFacilityList);
    }


    public void demo(){
        vaccineList.add(Constants.VACCINE_TESTER);
        typeVaccineList.add(Constants.TYPE_VACCINE_TESTER1);
        typeVaccineList.add(Constants.TYPE_VACCINE_TESTER2);

        vaccinationFacilityList.add(Constants.VACCINATION_CENTER_TESTER);
        vaccinationFacilityList.add(Constants.HEALTH_CARE_CENTER_TESTER);

        vaccinationFacilityList.get(0).addSchedule(Constants.VACCINATION_SCHEDULE_TESTER);
        vaccinationFacilityList.get(0).addSchedule(new VaccinationAppointment(Constants.SNS_USER_TESTER_EMPTY.getSNSnumber(),LocalDateTime.of(2022,06,4,15,30),Constants.TYPE_VACCINE_TESTER1));
        vaccinationFacilityList.get(0).getWaitingList().add(new Arrival(Constants.SNS_USER_TESTER_EMPTY));
        vaccinationFacilityList.get(0).getWaitingList().add(new Arrival(Constants.SNS_USER_TESTER_ONE));

        employeeList.add(Constants.EMPLOYEE_TESTER);

        SNSuserList.add(Constants.SN_SUSER_TESTER_FULL);
        SNSuserList.add(Constants.SNS_USER_TESTER_ONE);
        SNSuserList.add(Constants.SNS_USER_TESTER_EMPTY);

        SNSuserList.get(0).getVaccinationRecord().add(Constants.VACCINATION_RECORD_TESTER);
        SNSuserList.get(0).getVaccinationRecord().add(Constants.VACCINATION_RECORD_TESTER2);
        SNSuserList.get(1).getVaccinationRecord().add(Constants.VACCINATION_RECORD_TESTER);

    }

    public void dgsReportAuto(){
        DGSReportTask task=new DGSReportTask();

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 14);
        today.set(Calendar.MINUTE, 40);
        today.set(Calendar.SECOND, 0);

        // every night at am you run your task
        Timer timer = new Timer();
        timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
    }

    public List<String> generateDGSreportContent(){
        int count=0;
        List<String> list=new ArrayList<>();
        for(int i=0;i<vaccinationFacilityList.size();i++){
            count=0;
           for (int j=0;j<vaccinationFacilityList.get(i).getVaccinationAdminstrationRecordList().size();j++){
               if(vaccinationFacilityList.get(i).getVaccinationAdminstrationRecordList().get(j).getLeavingDateTime().toLocalDate().isEqual(LocalDate.now())){
                   count++;
               }
           }
           String report=LocalDate.now().format(Constants.FORMATTER)+";"+vaccinationFacilityList.get(i).getName()+";"+count;
           list.add(report);
        }
        return list;
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }
    public static List<Employee> getEmployeeList() {
        return employeeList;
    }

    public static List<VaccinationFacility> getVaccinationFacilityList() {
        return vaccinationFacilityList;
    }

    public VaccinationFacility getVaccinationFacilityFromList(int index){
        return vaccinationFacilityList.get(index);
    }

    public static List<Vaccine> getVaccineList() {
        return vaccineList;
    }

    public SNSuser SNSuserExistsNumber(int SNSnumber){
        boolean flag=false;
        for(int i=0;i<SNSuserList.size();i++){
            if(SNSuserList.get(i).SNSnumberSame(SNSnumber)){
                return  SNSuserList.get(i);
            }
        }
        return null;
    }

    public SNSuser SNSuserExistsEmail(String email){
        boolean flag=false;
        for(int i=0;i<SNSuserList.size();i++){
            if(SNSuserList.get(i).emailSame(email)){
                return  SNSuserList.get(i);
            }
        }
        return null;
    }

    public  Employee createEmployee(dtoEmployee dto){
        return new Employee(dto.getName(),dto.getAdress(),dto.getPhone(),dto.getCc(),dto.getEmail(),dto.getRoleId());
    }

    public boolean validateEmployee(Employee employee) {
        if (employee == null) {
            return false;
        }
        return !this.employeeList.contains(employee);
    }

    public boolean saveEmployees(dtoEmployee dto){
        boolean flag=false;
        if(employeeList.isEmpty()){
            authFacade.addUserWithRole(dto.getName(),dto.getEmail(), Utils.generatePwd(Constants.PWD_LENGTH),dto.getRoleId());
            employeeList.add(createEmployee(dto));
        }
        else {
            for(int i = 0; i< employeeList.size(); i++){
                if(!validateEmployeeUnique(dto,i)){
                    flag=true;
                }else{
                    flag=false;
                    break;
                }

            }
        }
        if(flag){
            authFacade.addUser(dto.getName(),dto.getEmail(), Utils.generatePwd(Constants.PWD_LENGTH));
            employeeList.add(createEmployee(dto));
        }
        else {
            System.out.println("EMPLOYEE ALREADY EXISTS");
        }
        return flag;

    }

    private boolean validateEmployeeUnique(dtoEmployee dto,int i){
        boolean email= employeeList.get(i).getEmail().equalsIgnoreCase(dto.getEmail());
        boolean cc= employeeList.get(i).getCcNumber()==dto.getCc();
        boolean phone= employeeList.get(i).getPhoneNumber()==dto.getPhone();

        return email || cc || phone;
    }


    public void printEmployee(Employee employee){
        System.out.println(employee.toString());
    }

    public ArrayList<Employee> FillRoleArray(String role) {
        return fillRoleList(role, employeeList);
    }

    public boolean validateRoleArray(ArrayList<Employee> EmployeesRoleList) {
        if (EmployeesRoleList.isEmpty()) {
            return false;
        }
        return true;
    }

    public VaccineAdministration createVaccineAdministration(String brand, List<Integer> minAge, List<Integer> maxAge, List<Integer> dosage, List<Integer> doses, ArrayList<ArrayList<Integer>> vaccineInterval) {
        return new VaccineAdministration(brand, minAge, maxAge, dosage, doses, vaccineInterval);
    }

    public boolean validateVaccineAdministration (VaccineAdministration vaxAdm) {
        if(vaxAdm == null)  {
            return false;
        }
        return !vaccineAdministrationList.contains(vaxAdm);
    }

    public boolean saveVaccineAdministration (VaccineAdministration vaxAdm) {
        if (!validateVaccineAdministration(vaxAdm)) {
            return false;
        }
        return addVaccineAdministration(vaxAdm);
    }

    private boolean addVaccineAdministration (VaccineAdministration vaxAdm) {
        return vaccineAdministrationList.add(vaxAdm);
    }

    public MassVaccinationCenter createVaccinationCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalTime openingHours, LocalTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot, TypeVaccine typeOfVaccine){
        return new MassVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours,closingHours, slotDuration, maximumNumberOfVaccinesPerSlot, typeOfVaccine);
    }

    public boolean validateVaccinationCenter(MassVaccinationCenter vaccinationCenter){
        if(vaccinationCenter==null){
            return false;
        }
        return  ! this.vaccinationFacilityList.contains(vaccinationCenter);
    }

    public boolean saveVaccinationCenter(MassVaccinationCenter vaccinationCenter){
        if(!validateVaccinationCenter(vaccinationCenter)){
            return false;
        }
        return this.vaccinationFacilityList.add(vaccinationCenter);
    }

    public void printVaccinationCenter(MassVaccinationCenter vaccinationCenter){
        System.out.println(vaccinationCenter.toString());
    }

    public  SNSuser createSNSuser( dtoSNSuser dto){
        return mapperSNSuser.toSNSuser(dto);
    }

    public boolean validateSNSuser(SNSuser snSuser){
        if(snSuser==null){
            return false;
        }
        return  ! this.SNSuserList.contains(snSuser);
    }

    public boolean saveSNSuser(dtoSNSuser dto){
        boolean flag=false;
        if(SNSuserList.isEmpty()){
            authFacade.addUserWithRole(dto.getName(),dto.getEmail(), dto.getPassword(),Constants.ROLE_SNS);
            SNSuserList.add(mapperSNSuser.toSNSuser(dto));
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
            authFacade.addUserWithRole(dto.getName(),dto.getEmail(), dto.getPassword(),Constants.ROLE_SNS);
            SNSuserList.add(mapperSNSuser.toSNSuser(dto));
        }
        else {
            Utils.printText("SNS ALREADY EXISTS");
        }
        return flag;
    }

    public String printSNSuser(SNSuser us){
        return us.toString();
    }

    public List<SNSuser> getSNSuserList(){return SNSuserList;}


    private boolean validateSNSuserDTO(dtoSNSuser dto, int i){
        boolean email=SNSuserList.get(i).getEmail().equalsIgnoreCase(dto.getEmail());
        boolean cc=SNSuserList.get(i).getCcNumber()==dto.getCcNumber();
        boolean phone=SNSuserList.get(i).getPhoneNumber()==dto.getPhoneNumber();
        boolean snsNumber=SNSuserList.get(i).getSNSnumber()==dto.getSNSnumber();

        return email || cc || phone || snsNumber;
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

    public Vaccine createVaccine(VaccineAdministration vaccineAdministration, TypeVaccine typeVaccine) {
        return new Vaccine(vaccineAdministration, typeVaccine);
    }

    public boolean validateVaccine(Vaccine vaccine) {
        return vaccine != null;
    }

    public void saveVaccine(Vaccine vaccine) {
        if (!validateVaccine(vaccine)) {
        } else {
            addVaccine(vaccine);
        }
    }

    private void addVaccine (Vaccine vaccine) {
        vaccineList.add(vaccine);
    }

    public boolean checkOtherCentersForVaccination(TypeVaccine vaccine,int SNSnumber){
        boolean flag=true;
        for(int i = 0; i< vaccinationFacilityList.size(); i++){
            for(int j = 0; j< vaccinationFacilityList.get(i).getVaccinationScheduleList().size(); j++){
                if(vaccinationFacilityList.get(i).getVaccinationScheduleList().get(j).getSNSnumber()==SNSnumber){
                    if(vaccinationFacilityList.get(i).getVaccinationScheduleList().get(j).getTypeVaccine().equals(vaccine)){
                        flag=false;
                    }
                }
            }
        }
        return flag;
    }

    public List<TypeVaccine> getTypeVaccineList() {
        return typeVaccineList;
    }

    public boolean checkAppointment(int index, SNSuser snSuser) {
        for (int i = 0; i < vaccinationFacilityList.get(index).getVaccinationScheduleList().size(); i++) {
            if (getSnsUserAppointment(index, i).getSNSnumber() == snSuser.getSNSnumber()) {
                return true;
            }
        }
        return false;
    }

    public int getSnsUserAppointmentIndex(int index, SNSuser snSuser) {
        int appointmentIndex = 0;
        for (int i = 0; i < vaccinationFacilityList.get(index).getVaccinationScheduleList().size(); i++) {
            if (vaccinationFacilityList.get(index).getVaccinationScheduleList().get(i).getSNSnumber() == snSuser.getSNSnumber()) {
                appointmentIndex = i;
            }
        }
        return appointmentIndex;
    }


    public boolean checkAppointmentDay(int index, int snsUserIndex) {
        if (LocalDateTime.now().getDayOfMonth() != getSnsUserAppointment(index, snsUserIndex).getAppointmentTime().getDayOfMonth()
        || LocalDateTime.now().getMonthValue() != getSnsUserAppointment(index,snsUserIndex).getAppointmentTime().getMonthValue()
        || LocalDateTime.now().getYear() != getSnsUserAppointment(index, snsUserIndex).getAppointmentTime().getYear()) {
            return false;
        }
        return true;
    }

    public VaccinationAppointment getSnsUserAppointment(int index, int snsUserIndex) {
        return vaccinationFacilityList.get(index).getVaccinationScheduleList().get(snsUserIndex);
    }



    public List<Arrival> getWaitingList(int index) {
        return vaccinationFacilityList.get(index).getWaitingList();
    }

    public List<String> snsUsersInWaitingRoom(int index) {

        for (int i = 0; i < SNSuserList.size(); i++) {
            for (int j = 0; j < getWaitingList(index).size(); j++) {


                if (SNSuserList.get(i).getSNSnumber() == (getWaitingList(index).get(j).getSnSuser().getSNSnumber())) {

                    snsUsers.add(j, "Name: " + snsUser(j, index).getName() + " Sex: " + snsUser(j, index).getSex() + " Birth Date: " + snsUser(j, index).getBirth() + " SNS User Number: " + snsUser(j, index).getSNSnumber() + " Phone Number: " + snsUser(j, index).getPhoneNumber());

                    //  Name, Sex, Birth Date, SNS User Number and Phone Number.

                }

            }

        }

        return snsUsers;
    }

    public List<String> getSNSusers(){
        return snsUsers;
    }


    public boolean validateWaitingList(int index) {
        if (snsUsersInWaitingRoom(index).isEmpty()) {
            return false;
        }
        return true;
    }

    public Arrival createArrival(SNSuser snSuser) {
        return new Arrival(snSuser);
    }

    public boolean validateArrival(Arrival arrival) {
        if (arrival == null) {
            return false;
        }
        return true;
    }

    public boolean saveSNSuserArrival(int index, Arrival arrival) {
        if (!validateArrival(arrival)) {
            return false;
        }
        clearWaitingList(index);
        return addSnsUserToWaitingList(index, arrival);
    }

    private void clearWaitingList(int index) {
        if(getWaitingList(index).size()!=0) {
            if (LocalDateTime.now().getDayOfMonth() != getWaitingList(index).get(0).getTimeOfArrival().getDayOfMonth()) {
                getWaitingList(index).clear();
            }
        }
    }

    public boolean addSnsUserToWaitingList(int index, Arrival arrival) {
        return getWaitingList(index).add(arrival);
    }

    public SNSuser snsUser(int j, int index) {
        return getWaitingList(index).get(j).getSnSuser();
    }


}