package app.ui.console;

import app.controller.App;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

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
            option = Utils.showAndSelectIndex(options, "\n\nSNS user Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}