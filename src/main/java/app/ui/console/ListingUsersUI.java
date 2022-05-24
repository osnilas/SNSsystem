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
        Utils.showList(ctlr.getSNSuserList(),"List of SNS users");
        Utils.showList(ctlr.getEmployeeList(),"List of Employees");
    }
}
