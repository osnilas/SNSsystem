package app.domain.shared;

import app.domain.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_NURSE = "NURSE";
    public static final String ROLE_RES = "RECEPTIONIST";
    public static final String ROLE_SNS= "SNS USER";
    public static final String ROLE_COR="CENTER COORDINATOR";

    public static final String[] RoleList={ROLE_NURSE,ROLE_COR,ROLE_RES,ROLE_SNS,ROLE_ADMIN};

    public static final String[] SexList={"Male","Female","Other"};
    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";

    public static final int PHONE_NUMBER_LENGHT=9;
    public static final int SNS_LENGHT=9;
    public static final int CC_LENGHT=8;

    public static final int PWD_LENGHT=7;

    public static final List<String> YES_OR_NO = new ArrayList<>(List.of("Yes", "No"));

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static final String DATE_FORMAT_FULL= "dd/MM/yyyy HH:mm";

    public static final DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_FULL);
    public static final String TIME_FORMAT = "HH:mm";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static final ArrayList<Employee> EMPLOYEE_ARRAY_LIST = new ArrayList<>();
    public static final LocalTime OPENING_TIME_FOR_VACCINATION_FACILITY = LocalTime.of(9,00);

    public static final LocalTime CLOSING_TIME_FOR_VACCINATION_FACILITY =LocalTime.of(16,00);

    public static final LocalDate BIRTH_TESTER=LocalDate.of(2002,9,11);

    public static final Employee EMPLOYEE_TESTER=new Employee("Carlos Santos","Rua da Casa",911356879,15467765,"carlos@gmail.com",ROLE_NURSE);

    public static final SNSuser SN_SUSER_TESTER=new SNSuser("João Veiga",SexList[0],BIRTH_TESTER,"Rua General","joao@gmail.com",912422195,22207750,15467765);
    public static final TypeVaccine TYPE_VACCINE_TESTER= new TypeVaccine("Covid-19", "COVID-19 (coronavirus disease 2019) is a disease caused by a virus named SARS-CoV-2 and was discovered in December 2019 in Wuhan, China.", "covid", "Toxoid");
    public static final VaccinationCenter VACCINATION_CENTER_TESTER=new VaccinationCenter("CV Porto","Rua de Paranhos",222077500,"sns24@gov.pt",222077500, "www.sns24.pt",Constants.OPENING_TIME_FOR_VACCINATION_FACILITY, Constants.CLOSING_TIME_FOR_VACCINATION_FACILITY,30,5,TYPE_VACCINE_TESTER);
    public static final TypeVaccine TYPE_VACCINE_RECOMMENDED= new TypeVaccine("Covid-19", "COVID-19 (coronavirus disease 2019) is a disease caused by a virus named SARS-CoV-2 and was discovered in December 2019 in Wuhan, China.", "covid", "Toxoid");
    public static final List<String> VACCINE_TECHNOLOGY = List.of("Live-attenuated", "Inactivated", "Subunit", "Toxoid", "Viral vector", "Messenger RNA");

    public static final VaccinationSchedule VACCINATION_SCHEDULE_TESTER=new VaccinationSchedule(12207750,LocalDateTime.of(2022,5,25,12,00),TYPE_VACCINE_TESTER);
}
