package app.domain.shared;

import javax.management.relation.RoleList;
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
    public static final String ROLE_COR="Coordinator";

    public static final String[] RoleList={ROLE_NURSE,ROLE_COR,ROLE_RES,ROLE_SNS,ROLE_ADMIN};


    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";

    public static final int PHONE_NUMBER_LENGHT=9;
    public static final int CC_LENGHT=8;

    public static final int PWD_LENGHT=7;
}
