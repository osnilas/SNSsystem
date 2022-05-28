package app.domain.shared;

import app.domain.model.*;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    //Roles
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_NURSE = "NURSE";
    public static final String ROLE_RES = "RECEPTIONIST";
    public static final String ROLE_SNS= "SNS USER";
    public static final String ROLE_COR="CENTER COORDINATOR";
    public static final String[] RoleList={ROLE_NURSE,ROLE_COR,ROLE_RES,ROLE_SNS,ROLE_ADMIN};

    public static final String[] SexListFull={"Male","Female","Other"};
    public static final String[] SexListShort={"M","F","O"};
    //Config
    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";

    //Validation of int attributes
    public static final int PHONE_NUMBER_LENGHT=9;
    public static final int SNS_LENGHT=9;
    public static final int CC_LENGHT=8;

    //Password Lenght
    public static final int PWD_LENGHT=7;

    public static final List<String> YES_OR_NO = new ArrayList<>(List.of("Yes", "No"));

    //Date
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static final String DATE_FORMAT_FULL= "dd/MM/yyyy HH:mm";

    public static final DateFormat df = new SimpleDateFormat(DATE_FORMAT);

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_FULL);
    public static final String TIME_FORMAT = "HH:mm";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static final ArrayList<Employee> EMPLOYEE_ARRAY_LIST = new ArrayList<>();
    public static final LocalTime OPENING_TIME_FOR_VACCINATION_FACILITY = LocalTime.of(9,00);

    public static final LocalTime CLOSING_TIME_FOR_VACCINATION_FACILITY =LocalTime.of(16,00);

    public static final LocalDate BIRTH_TESTER=LocalDate.of(2004,9,11);

    public static final Employee EMPLOYEE_TESTER=new Employee("Carlos Santos","Rua da Casa",911356879,15467765,"carlos@gmail.com",ROLE_NURSE);

    public static final SNSuser SN_SUSER_TESTER=new SNSuser("Carlos Silva", SexListFull[0], BIRTH_TESTER, "Rua General", "joao@gmail.com",912422195, 22207750, 17555697,"911");
    public static final TypeVaccine TYPE_VACCINE_TESTER1= new TypeVaccine("Covid-19", "COVID-19 (coronavirus disease 2019) is a disease caused by a virus named SARS-CoV-2 and was discovered in December 2019 in Wuhan, China.", "covid", "Toxoid");
    public static final TypeVaccine TYPE_VACCINE_TESTER2= new TypeVaccine("Smallpox", "Smallpox was an infectious disease caused by one of two virus variants, Variola major and Variola minor", "smallP818", "Inactivated");
    private static final List<TypeVaccine> TYPE_VACCINE_LIST_TESTER=List.of(TYPE_VACCINE_TESTER1,TYPE_VACCINE_TESTER2);
    public static final MassVaccinationCenter VACCINATION_CENTER_TESTER=new MassVaccinationCenter("CV Porto","Rua de Paranhos",222077500,"sns24@gov.pt",222077500, "www.sns24.pt",OPENING_TIME_FOR_VACCINATION_FACILITY,CLOSING_TIME_FOR_VACCINATION_FACILITY,30,5,TYPE_VACCINE_TESTER1);
    public static final HealthCareCenter HEALTH_CARE_CENTER_TESTER=new HealthCareCenter("USF Pedrouços","Rua da Giesta", 229774710, "sns@email.com",229774710,"www.USF.pt",OPENING_TIME_FOR_VACCINATION_FACILITY,CLOSING_TIME_FOR_VACCINATION_FACILITY,60,3,TYPE_VACCINE_LIST_TESTER);
    public static final TypeVaccine TYPE_VACCINE_RECOMMENDED= new TypeVaccine("Covid-19", "COVID-19 (coronavirus disease 2019) is a disease caused by a virus named SARS-CoV-2 and was discovered in December 2019 in Wuhan, China.", "covid", "Toxoid");

    public static final List<String> VACCINE_TECHNOLOGY = List.of("Live-attenuated", "Inactivated", "Subunit", "Toxoid", "Viral vector", "Messenger RNA");

    private static final List<Integer> MIN_AGE_TESTER= List.of(11,21);

    private static final List<Integer> Max_AGE_TESTER= List.of(20,50);

    private static final List<Integer> DOSAGE_TESTER=List.of(1,3);

    private static final List<Integer> DOSE_TESTER=List.of(2,3);

    private static final ArrayList<Integer> TIME_VACCINE_TESTER1= new ArrayList<>(Arrays.asList(20));

    private static final ArrayList<Integer> TIME_VACCINE_TESTER2=  new ArrayList<>(Arrays.asList(20,30));


    private static final ArrayList<ArrayList<Integer>> TIME_VACCINE_TESTER= new ArrayList<>(Arrays.asList(TIME_VACCINE_TESTER1,TIME_VACCINE_TESTER2));

    private static final VaccineAdministration VACCINE_ADMINISTRATION_TESTER= new VaccineAdministration("Pfizer",MIN_AGE_TESTER,Max_AGE_TESTER,DOSAGE_TESTER,DOSE_TESTER,TIME_VACCINE_TESTER);

    private static final Vaccine VACCINE_TESTER=new Vaccine(VACCINE_ADMINISTRATION_TESTER,TYPE_VACCINE_TESTER1);

    public static final VaccinationRecord VACCINATION_RECORD_TESTER=new VaccinationRecord(VACCINE_TESTER,LocalDateTime.of(2022,4,1,16,30,00),1);
    public static final VaccinationRecord VACCINATION_RECORD_TESTER2=new VaccinationRecord(VACCINE_TESTER,LocalDateTime.of(2022,5,8,12,30,00),2);

    public static final VaccinationAppointment VACCINATION_SCHEDULE_TESTER=new VaccinationAppointment(12207750,LocalDateTime.of(2022,6,5,12,00),TYPE_VACCINE_TESTER1);
}

