package app.ui.console;

import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import app.controller.RoleMenuController;


import java.util.List;

import static app.domain.shared.Constants.EMPLOYEE_ARRAY_LIST;


public class RoleMenuUI implements Runnable{
    public RoleMenuUI()
    {
    }
    /**
     * @author Filipe Magalhães
     * Iniciates controller
     */
    private RoleMenuController ctrl = new RoleMenuController();


    @Override
    public void run (){
        boolean sucess = roleMenu();
    }
    /**
     * @author Filipe Magalhães
     * Menu for choosing the role to list the employees from
     * @return boolean if registration was sucessful
     */
    private boolean roleMenu (){
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
                sucess = ctrl.fillRoleArray(role);
            }
            if (sucess){
                printListEmployees(EMPLOYEE_ARRAY_LIST);
                EMPLOYEE_ARRAY_LIST.clear();
            }
            else {
                sucess = false;

            }
        if (sucess) {
            System.out.println("-----------List of Employees done successfully-----------");
        } else {
            System.out.println("-----------List of Employees failed---------------");
        }
            return sucess;
        }
    /**
     * @author Filipe Magalhães
     * Prints the list of employees from the chosen role
     */
    public static void printListEmployees(List<Employee> listOfEmployeesFromRole) {

        for (int i = 0; i < listOfEmployeesFromRole.size(); i++) {
            Employee em = listOfEmployeesFromRole.get(i);
            System.out.println(em.toString());
        }
    }

    }


