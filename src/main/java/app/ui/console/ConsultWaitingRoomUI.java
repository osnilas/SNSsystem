package app.ui.console;

import app.controller.ConsultWaitingRoomController;
import app.controller.RoleMenuController;
import app.domain.model.VaccinationAppointment;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.List;

public class ConsultWaitingRoomUI implements Runnable{

    private int index = -1;

    /**
     * @author Filipe Magalhães
     * Iniciates controller
     */

    private ConsultWaitingRoomController ctrl = new ConsultWaitingRoomController();


    @Override
    public void run() {
        boolean sucess = false;

            do {
                List<String> list = ctrl.getVaccinationFacilities();
                Utils.showList(list, "\nSelect a vaccination facility");
                index = Utils.selectsIndex(ctrl.getVaccinationFacilities());
            }while (index==-1);
            sucess = ctrl.snsUsersInWaitingRoom(index);

        if (sucess){
            System.out.println("-----------SNS Users in the waiting list---------------");
            printWaitingList(ctrl.snsUsers(index));
            System.out.println("-----------SNS Users in the waiting list---------------");

        }
        else {
            System.out.println("-----------Error! The waiting room is empty!---------------");

        }
    }

    /**
     * @author Filipe Magalhães
     * Prints the list of SNS users in the waiting room
     */

    public void printWaitingList (List<String> snsUsers){
        for (int i = 0; i < snsUsers.size(); i++) {
            System.out.println();

        }
    }
}
