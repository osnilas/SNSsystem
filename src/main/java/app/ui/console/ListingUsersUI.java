package app.ui.console;

import app.controller.ListingUsersController;
import app.ui.console.utils.Utils;

public class ListingUsersUI implements Runnable {

    ListingUsersController ctlr;

    public ListingUsersUI(){
        ctlr=new ListingUsersController();
    }

    @Override
    public void run() {
        Utils.printText("List of SNS users:");
        for(int i=0;i<ctlr.getSNSuserList().size();i++){
            Utils.printText(ctlr.getSNSuserList().get(i));
        }
        Utils.printText("List of Employees:");
        for(int i=0;i<ctlr.getEmployeeList().size();i++){
            Utils.printText(ctlr.getEmployeeList().get(i));
        }
    }
}
