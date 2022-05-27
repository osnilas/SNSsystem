package app.ui.console;

import app.controller.RecepcionistController;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Nogueira 1211613
 */

public class ReceptionistUI implements Runnable{

    private RecepcionistController ctrl = new RecepcionistController();
    public ReceptionistUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Check Vaccine Appointment", new CheckVaccineAppointmentUI()));
        options.add(new MenuItem("Create vaccination appointment", new ScheduleVaccinationUI()));
        int option;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
