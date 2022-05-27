package app.domain.model;


import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import mappers.dto.MapperSNSuser;
import mappers.dto.dtoEmployee;
import mappers.dto.dtoSNSuser;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static app.domain.model.Employee.fillRoleList;


/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {
    private String designation;
    private AuthFacade authFacade;

    private static List<Employee> employeeList = new ArrayList<>();

    private static List<Employee> employeesListRole = new ArrayList<Employee>();
    private static List<TypeVaccine> typeVaccineList = new ArrayList<>();
    private static List<VaccineAdministration> vaccineAdministrationList = new ArrayList<>();

    private static List<Vaccine> vaccineList = new ArrayList<>();
    private static List<VaccinationFacility> vaccinationFacilityList = new ArrayList<>();

    private static List<SNSuser> SNSuserList = new ArrayList<>();
    private static MapperSNSuser mapperSNSuser = new MapperSNSuser();

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");
        this.designation = designation;
        this.authFacade = new AuthFacade();
        demo();
    }

    public void demo() {
        vaccinationFacilityList.add(Constants.VACCINATION_CENTER_TESTER);
        vaccinationFacilityList.get(0).addSchedule(Constants.VACCINATION_SCHEDULE_TESTER);
        vaccinationFacilityList.get(0).addSchedule(Constants.VACCINATION_SCHEDULE_TESTER);
        vaccinationFacilityList.get(0).addSchedule(Constants.VACCINATION_SCHEDULE_TESTER);
        vaccinationFacilityList.get(0).addSchedule(Constants.VACCINATION_SCHEDULE_TESTER);
        vaccinationFacilityList.get(0).addSchedule(Constants.VACCINATION_SCHEDULE_TESTER);
        employeeList.add(Constants.EMPLOYEE_TESTER);
        SNSuserList.add(Constants.SN_SUSER_TESTER);
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

    public VaccinationFacility getVaccinationFacilityFromList(int index) {
        return vaccinationFacilityList.get(index);
    }

    public SNSuser SNSuserExistsNumber(int SNSnumber) {
        boolean flag = false;
        for (int i = 0; i < SNSuserList.size(); i++) {
            if (SNSuserList.get(i).SNSnumberSame(SNSnumber)) {
                return SNSuserList.get(i);
            }
        }
        return null;
    }

    public SNSuser SNSuserExistsEmail(String email) {
        boolean flag = false;
        for (int i = 0; i < SNSuserList.size(); i++) {
            if (SNSuserList.get(i).emailSame(email)) {
                return SNSuserList.get(i);
            }
        }
        return null;
    }

    public Employee createEmployee(dtoEmployee dto) {
        return new Employee(dto.getName(), dto.getAdress(), dto.getPhone(), dto.getCc(), dto.getEmail(), dto.getRoleId());
    }

    public boolean validateEmployee(Employee employee) {
        if (employee == null) {
            return false;
        }
        return !this.employeeList.contains(employee);
    }

    public boolean saveEmployees(dtoEmployee dto) {
        boolean flag = false;
        if (employeeList.isEmpty()) {
            authFacade.addUserWithRole(dto.getName(), dto.getEmail(), Utils.generatePwd(Constants.PWD_LENGHT), dto.getRoleId());
            employeeList.add(createEmployee(dto));
        } else {
            for (int i = 0; i < employeeList.size(); i++) {
                if (!validateEmployeeUnique(dto, i)) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }

            }
        }
        if (flag) {
            authFacade.addUser(dto.getName(), dto.getEmail(), Utils.generatePwd(Constants.PWD_LENGHT));
            employeeList.add(createEmployee(dto));
        } else {
            System.out.println("EMPLOYEE ALREADY EXISTS");
        }
        return flag;

    }

    private boolean validateEmployeeUnique(dtoEmployee dto, int i) {
        boolean email = employeeList.get(i).getEmail().equalsIgnoreCase(dto.getEmail());
        boolean cc = employeeList.get(i).getCcNumber() == dto.getCc();
        boolean phone = employeeList.get(i).getPhoneNumber() == dto.getPhone();

        return email || cc || phone;
    }


    public void printEmployee(Employee employee) {
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

    public boolean validateVaccineAdministration(VaccineAdministration vaxAdm) {
        if (vaxAdm == null) {
            return false;
        }
        return !vaccineAdministrationList.contains(vaxAdm);
    }

    public boolean saveVaccineAdministration(VaccineAdministration vaxAdm) {
        if (!validateVaccineAdministration(vaxAdm)) {
            return false;
        }
        return addVaccineAdministration(vaxAdm);
    }

    private boolean addVaccineAdministration(VaccineAdministration vaxAdm) {
        return vaccineAdministrationList.add(vaxAdm);
    }

    public MassVaccinationCenter createVaccinationCenter(String name, String address, int phoneNumber, String emailAddress, int faxNumber, String websiteAddress, LocalTime openingHours, LocalTime closingHours, int slotDuration, int maximumNumberOfVaccinesPerSlot, TypeVaccine typeOfVaccine) {
        return new MassVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, closingHours, slotDuration, maximumNumberOfVaccinesPerSlot, typeOfVaccine);
    }

    public boolean validateVaccinationCenter(MassVaccinationCenter vaccinationCenter) {
        if (vaccinationCenter == null) {
            return false;
        }
        return !this.vaccinationFacilityList.contains(vaccinationCenter);
    }

    public boolean saveVaccinationCenter(MassVaccinationCenter vaccinationCenter) {
        if (!validateVaccinationCenter(vaccinationCenter)) {
            return false;
        }
        return this.vaccinationFacilityList.add(vaccinationCenter);
    }

    public void printVaccinationCenter(MassVaccinationCenter vaccinationCenter) {
        System.out.println(vaccinationCenter.toString());
    }

    public SNSuser createSNSuser(dtoSNSuser dto) {
        return mapperSNSuser.toSNSuser(dto);
    }

    public boolean validateSNSuser(SNSuser snSuser) {
        if (snSuser == null) {
            return false;
        }
        return !this.SNSuserList.contains(snSuser);
    }

    public boolean saveSNSuser(dtoSNSuser dto) {
        boolean flag = false;
        if (SNSuserList.isEmpty()) {
            authFacade.addUserWithRole(dto.getName(), dto.getEmail(), dto.getPassword(), Constants.ROLE_SNS);
            SNSuserList.add(mapperSNSuser.toSNSuser(dto));
        } else {
            for (int i = 0; i < SNSuserList.size(); i++) {
                if (!validateSNSuserDTO(dto, i)) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            authFacade.addUserWithRole(dto.getName(), dto.getEmail(), dto.getPassword(), Constants.ROLE_SNS);
            SNSuserList.add(mapperSNSuser.toSNSuser(dto));
        } else {
            System.out.println("SNS ALREADY EXISTS");
        }
        return flag;
    }

    public String printSNSuser(SNSuser us) {
        return us.toString();
    }

    public List<SNSuser> getSNSuserList() {
        return SNSuserList;
    }


    private boolean validateSNSuserDTO(dtoSNSuser dto, int i) {
        boolean email = SNSuserList.get(i).getEmail().equalsIgnoreCase(dto.getEmail());
        boolean cc = SNSuserList.get(i).getCcNumber() == dto.getCcNumber();
        boolean phone = SNSuserList.get(i).getPhoneNumber() == dto.getPhoneNumber();
        boolean snsNumber = SNSuserList.get(i).getSNSnumber() == dto.getSNSnumber();

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
        if (!validateTypeVaccine(typeVaccine)) {
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

    private void addVaccine(Vaccine vaccine) {
        vaccineList.add(vaccine);
    }

    public boolean checkOtherCentersForVaccination(TypeVaccine vaccine, int SNSnumber) {
        boolean flag = true;
        for (int i = 0; i < vaccinationFacilityList.size(); i++) {
            for (int j = 0; j < vaccinationFacilityList.get(i).getVaccinationScheduleList().size(); j++) {
                if (vaccinationFacilityList.get(i).getVaccinationScheduleList().get(j).getSNSnumber() == SNSnumber) {
                    if (Objects.equals(vaccinationFacilityList.get(i).getVaccinationScheduleList().get(j).getTypeVaccine(), vaccine)) {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public List getTypeVaccineList() {
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

    public ArrayList<String> snsUsersInWaitingRoom(int index) {
        ArrayList<String> snsUsers = new ArrayList<String>();

        for (int i = 0; i < SNSuserList.size(); i++) {
            for (int j = 0; j < getWaitingList(index).size(); j++) {

                if (SNSuserList.get(i).equals(getWaitingList(index).get(j))) {

                    snsUsers.add(j, "Name: " + snsUserName(j, index).getName() + " Sex: " + snsUserName(j, index).getSex() + " Birth Date: " + snsUserName(j, index).getBirth() + " SNS User Number: " + snsUserName(j, index).getSNSnumber() + " Phone Number: " + snsUserName(j, index).getPhoneNumber());

                    //  Name, Sex, Birth Date, SNS User Number and Phone Number.

                }

            }

        }

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
        return addSnsUserToWaitingList(index, arrival);
    }

    public boolean addSnsUserToWaitingList(int index, Arrival arrival) {
        return getWaitingList(index).add(arrival);
    }

    public SNSuser snsUserName(int index, int j) {
        return getWaitingList(index).get(j).getSnSuser();
    }

}