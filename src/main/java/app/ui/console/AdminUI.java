package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{
    public AdminUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Vaccination Center ", new RegisterVaccinationCenterUI()));
        options.add(new MenuItem("Register an employee", new RegisterEmployeeUI()));
        options.add(new MenuItem("Add new SNS users from csv file",new AddUserFromCSVUI()));
        options.add(new MenuItem("Register a type of vaccine ", new RegisterTypeVaccineUI()));
        options.add(new MenuItem("Specify a new vaccine and its administration process ", new VaccineAdministrationUI()));
        options.add(new MenuItem("List of all system users",new ListingUsersUI()));
        options.add(new MenuItem("Get a list of employees with a given role ", new RoleMenuUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
