package app.ui.console;

import app.controller.ConsultWaitingRoomController;
import app.controller.RoleMenuController;
import app.domain.model.VaccinationAppointment;
import app.domain.shared.Constants;

import java.util.List;

public class ConsultWaitingRoomUI implements Runnable{

    /**
     * @author Filipe Magalhães
     * Iniciates controller
     */

    private ConsultWaitingRoomController ctrl = new ConsultWaitingRoomController();


    @Override
    public void run() {
        boolean sucess = false;


        if (sucess){

        }
        else {
            System.out.println("-----------Error! The waiting room is empty!---------------");

        }
    }

    public void printWaitingList (List<VaccinationAppointment> waitingList){
        for (int i = 0; i < waitingList.size(); i++) {
            System.out.println();

        }
    }
}
