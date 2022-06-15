package app.domain.shared;

import app.domain.model.*;
import app.ui.console.utils.Utils;

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
    public static final int PHONE_NUMBER_LENGTH =9;
    public static final int SNS_LENGTH =9;
    public static final int CC_LENGTH =8;

    //Password Length
    public static final int PWD_LENGTH =7;

    public static final List<String> YES_OR_NO = new ArrayList<>(List.of("Yes", "No"));

    //Date
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_FORMAT_AMERICAN="MM/dd/yyyy";

    public static final String DATE_FORMAT_FULL= "dd/MM/yyyy HH:mm";

    public static final String DATE_FORMAT_FULL_ALT= "MM/dd/yyyy HH:mm";

    public static final DateFormat df = new SimpleDateFormat(DATE_FORMAT);

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_FULL);

    public static final DateTimeFormatter DATE_TIME_FORMATTER_ALT= DateTimeFormatter.ofPattern(DATE_FORMAT_FULL_ALT);
    public static final String TIME_FORMAT = "HH:mm";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static final ArrayList<Employee> EMPLOYEE_ARRAY_LIST = new ArrayList<>();
    public static final LocalTime OPENING_TIME_FOR_VACCINATION_FACILITY = LocalTime.of(8,00);

    public static final LocalTime CLOSING_TIME_FOR_VACCINATION_FACILITY =LocalTime.of(20,00);

    public static final int TOTAL_MINUTES = 720;

    public static final LocalDate BIRTH_TESTER=LocalDate.of(2007,9,11);

    public static final Employee EMPLOYEE_TESTER=new Employee("Carlos Santos","Rua da Casa",911356879,15467765,"carlos@gmail.com",ROLE_NURSE);

    public static final SNSuser SN_SUSER_TESTER_FULL=new SNSuser("Joao Veiga", SexListFull[0], BIRTH_TESTER, "Rua General", "joao@gmail.com",912422195, 222077500, 17555697,"911");

    public static final SNSuser SNS_USER_TESTER_EMPTY=new SNSuser("Maria Santos", SexListFull[1], (LocalDate.of(1980,5,11)),"Rua da maria","maria@gmail.com",91233569,122077500,17555788,"mariaTest");

    public static final SNSuser SNS_USER_TESTER_ONE=new SNSuser("Pedro Cardoso", SexListFull[0], (LocalDate.of(1994,9,1)),"Rua da maria","pedro@gmail.com",91234569,122137500,17555688,"pedroTest");

    public static final TypeVaccine TYPE_VACCINE_TESTER1= new TypeVaccine("Covid-19", "COVID-19 (coronavirus disease 2019) is a disease caused by a virus named SARS-CoV-2 and was discovered in December 2019 in Wuhan, China.", "covid", "Toxoid");

    public static final TypeVaccine TYPE_VACCINE_TESTER2= new TypeVaccine("Smallpox", "Smallpox was an infectious disease caused by one of two virus variants, Variola major and Variola minor", "smallP818", "Inactivated");
    public static final String EXPORT_MESSAGE = "Export List.";
    public static final String APPLICATION_TITLE = "Vaccination Management System";

    private static final List<TypeVaccine> TYPE_VACCINE_LIST_TESTER=List.of(TYPE_VACCINE_TESTER1,TYPE_VACCINE_TESTER2);

    public static final MassVaccinationCenter VACCINATION_CENTER_TESTER=new MassVaccinationCenter("CV Porto","Rua de Paranhos",222077500,"sns24@gov.pt",222077500, "www.sns24.pt",OPENING_TIME_FOR_VACCINATION_FACILITY,CLOSING_TIME_FOR_VACCINATION_FACILITY,30,5,TYPE_VACCINE_TESTER1);

    public static final HealthCareCenter HEALTH_CARE_CENTER_TESTER=new HealthCareCenter("USF Pedrou�os","Rua da Giesta", 229774710, "sns@email.com",229774710,"www.USF.pt",OPENING_TIME_FOR_VACCINATION_FACILITY,CLOSING_TIME_FOR_VACCINATION_FACILITY,60,3,TYPE_VACCINE_LIST_TESTER);

    public static final Coordinator COORDINATOR_TESTER = new Coordinator("Jodson Santos", "Rua das Palmeiras", 936547223, 16328635, "coor@lei.sem2.pt", ROLE_COR, VACCINATION_CENTER_TESTER);
    public static final TypeVaccine TYPE_VACCINE_RECOMMENDED= new TypeVaccine("Covid-19", "COVID-19 (coronavirus disease 2019) is a disease caused by a virus named SARS-CoV-2 and was discovered in December 2019 in Wuhan, China.", "covid", "Toxoid");

    public static final List<String> VACCINE_TECHNOLOGY = List.of("Live-attenuated", "Inactivated", "Subunit", "Toxoid", "Viral vector", "Messenger RNA");

    private static final List<Integer> MIN_AGE_TESTER= List.of(8,21);

    private static final List<Integer> Max_AGE_TESTER= List.of(20,70);

    private static final List<Integer> DOSAGE_TESTER=List.of(1,3);

    private static final List<Integer> DOSE_TESTER=List.of(2,3);

    private static final ArrayList<Integer> TIME_VACCINE_TESTER1= new ArrayList<>(Arrays.asList(20));

    private static final ArrayList<Integer> TIME_VACCINE_TESTER2=  new ArrayList<>(Arrays.asList(20,30));

    private static final ArrayList<ArrayList<Integer>> TIME_VACCINE_TESTER= new ArrayList<>(Arrays.asList(TIME_VACCINE_TESTER1,TIME_VACCINE_TESTER2));

    private static final VaccineAdministration VACCINE_ADMINISTRATION_TESTER= new VaccineAdministration("Pfizer",MIN_AGE_TESTER,Max_AGE_TESTER,DOSAGE_TESTER,DOSE_TESTER,TIME_VACCINE_TESTER);

    public static final Vaccine VACCINE_TESTER=new Vaccine(VACCINE_ADMINISTRATION_TESTER,TYPE_VACCINE_TESTER1);

    public static final VaccineCard VACCINATION_RECORD_TESTER=new VaccineCard(VACCINE_TESTER,LocalDateTime.of(2022,5,30,16,30,00),1);
    public static final VaccineCard VACCINATION_RECORD_TESTER2=new VaccineCard(VACCINE_TESTER,LocalDateTime.of(2022,5,28,12,30,00),2);

    public static final VaccinationAppointment VACCINATION_SCHEDULE_TESTER=new VaccinationAppointment(SNS_USER_TESTER_ONE.getSNSnumber(),LocalDateTime.of(LocalDate.now(),LocalTime.of(15,16)),TYPE_VACCINE_TESTER1);

    public static final String BinFile_Directory = Utils.ReadProppeties("BinFiles.Directory");
    public static final String TestDocs_Directory= Utils.ReadProppeties("TestDocs.Directory");

    public static final String FILEPATH_SNSUSERS=BinFile_Directory+"/SNSusers.bin";
    public static final String FILEPATH_VACCINES=BinFile_Directory+"/Vaccines.bin";
    public static final String FILEPATH_VACCINATION_FACILITIES=BinFile_Directory+"/VaccinationFacility.bin";
    public static final String FILEPATH_TYPE_VACCINES=BinFile_Directory+"/TypeVaccines.bin";
    public static final String FILEPATH_VACCINE_ADMINISTRATIONS=BinFile_Directory+"/VaccineAdministrations.bin";
    public static final String FILEPATH_EMPLOYEES= BinFile_Directory+"/Employees.bin";

    public static final String FILEPATH_FULLY_VACCINATED_PER_DAY=BinFile_Directory+"/VaccinatedPerDay.bin";

    public static final String FILEPATH_REPORT=Utils.ReadProppeties("Auto.Report.Path");



}

