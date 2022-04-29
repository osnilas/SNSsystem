package app.ui.console;

import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import app.controller.RoleMenuController;


import java.util.ArrayList;
import java.util.List;

public class RoleMenuUI implements Runnable{
    public RoleMenuUI()
    {
    }
    private RoleMenuController ctrl = new RoleMenuController();
    private ArrayList<Employee> EmployeesRoleList = new ArrayList<>();

    @Override
    public void run() {}
    private boolean RoleMenu (){
        boolean sucess = false;

            for (int i = 0; i < Constants.RoleList.length - 2; i++) {
                System.out.println(i + 1 + "-" + Constants.RoleList[i]);
            }
            String role = null;
            int option = Utils.readIntegerFromConsole("Option:");
            switch (option) {
                case 1:
                    role = Constants.RoleList[0];
                    break;
                case 2:
                    role = Constants.RoleList[1];
                    break;
                case 3:
                    role = Constants.RoleList[2];
                    break;
                default:
                    System.out.println("ERROR");
            }
            if (role == null) {
                throw new IllegalArgumentException("Role not chosen");
            }
            else{
                sucess = ctrl.FillRoleArray(role);
            }
            if (sucess){
                ctrl.PrintListEmployeesFromRole(EmployeesRoleList);
            }
            else {
                sucess = false;

            }
            return sucess;
        }

    }


