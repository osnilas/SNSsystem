package app.domain.shared;

import app.domain.model.Employee;
import app.domain.model.TypeVaccine;
import app.domain.model.VaccinationCenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

    public static final DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static final String TIME_FORMAT = "HH:mm";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static final ArrayList<Employee> EMPLOYEE_ARRAY_LIST = new ArrayList<>();

    public static final LocalDateTime OPENING_TIME_FOR_VACCINATION_FACILITY =LocalDateTime.parse("9:00",TIME_FORMATTER);

    public static final LocalDateTime CLOSING_TIME_FOR_VACCINATION_FACILITY =LocalDateTime.parse("16:00",TIME_FORMATTER);

    public static final Employee EMPLOYEE_TESTER=new Employee("Carlos Santos","Rua da Casa",911356879,15467765,"carlos@gmail.com",ROLE_NURSE);

    public static final VaccinationCenter VACCINATION_CENTER_TESTER=new VaccinationCenter("CV Porto","Rua de Paranhos",222077500,"sns24@gov.pt",222077500, "www.sns24.pt",Constants.OPENING_TIME_FOR_VACCINATION_FACILITY, Constants.CLOSING_TIME_FOR_VACCINATION_FACILITY,30,5,"Covid");

    public static final TypeVaccine TYPE_VACCINE_TESTER= new TypeVaccine("Covid-19", "COVID-19 (coronavirus disease 2019) is a disease caused by a virus named SARS-CoV-2 and was discovered in December 2019 in Wuhan, China.");

    public static final TypeVaccine TYPE_VACCINE_RECOMMENDED= new TypeVaccine("Covid-19", "COVID-19 (coronavirus disease 2019) is a disease caused by a virus named SARS-CoV-2 and was discovered in December 2019 in Wuhan, China.");

    public static final List<String> VACCINE_TECHNOLOGY = List.of("Live-attenuated", "Inactivated", "Subunit", "Toxoid", "Viral vector", "Messenger RNA");
}
