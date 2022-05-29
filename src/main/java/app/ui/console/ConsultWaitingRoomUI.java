package app.ui.console;

import app.controller.ConsultWaitingRoomController;
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
        boolean success = false;


                List<String> list = ctrl.getVaccinationFacilities();
                Utils.showList(list, "\nSelect a vaccination facility");
                index = Utils.selectsIndex(ctrl.getVaccinationFacilities());
                ctrl.snsUsers(index).clear();

            success = ctrl.snsUsersInWaitingRoom(index);
            ctrl.snsUsers(index).clear();

        if (success){
            System.out.println("\n-----------SNS Users in the waiting room---------------");
            printWaitingList(ctrl.snsUsers(index));
            System.out.println("-----------*****************************---------------");

        }
        else {
            System.out.println("-----------The waiting room is empty!---------------");

        }
    }


    /**
     * @author Filipe Magalhães
     * Prints the list of SNS users in the waiting room
     */


    public void printWaitingList (List<String> snsUsers){
        for (int i = 0; i < snsUsers.size(); i++) {
            System.out.println(snsUsers.get(i));

        }
    }
}
