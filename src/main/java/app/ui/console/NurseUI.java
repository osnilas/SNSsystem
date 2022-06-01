package app.ui.console;

import app.controller.NurseController;
import app.controller.RecepcionistController;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NurseUI implements Runnable{

    private NurseController ctrl = new NurseController();

    public NurseUI()
    {
    }


    @Override
    public void run() {


        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Consult waiting room", new ConsultWaitingRoomUI()));
        options.add(new MenuItem("Record a vaccine administration",new RecordVaccineAdministrationUI()));

        int option;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nNurse Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }

    }
