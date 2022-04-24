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
        options.add(new MenuItem("Register Vaccination/ Healthcare Center ", new ShowTextUI("You have chosen Option 1.")));
        options.add(new MenuItem("Register an employee", new ShowTextUI("You have chosen Option 2.")));
        options.add(new MenuItem("Register an SNS User ", new ShowTextUI("You have chosen Option 3.")));
        options.add(new MenuItem("Register a type of vaccine ", new ShowTextUI("You have chosen Option 4.")));
        options.add(new MenuItem("Specify a new vaccine and its administration process ", new ShowTextUI("You have chosen Option 5.")));

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
