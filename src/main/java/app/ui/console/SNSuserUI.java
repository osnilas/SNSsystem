package app.ui.console;

import app.controller.SNSUserController;
import app.domain.model.VaccinationSchedule;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import app.controller.RoleMenuController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SNSuserUI implements Runnable{
    public SNSuserUI()
    {

    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Create vaccination appointment", new ScheduleVaccinationUI()));

        int option;
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