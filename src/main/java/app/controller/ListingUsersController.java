package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.SNSuser;

import java.util.ArrayList;
import java.util.List;

public class ListingUsersController {

    private Company company;

    private App app;

    public ListingUsersController() {
        this.company = App.getInstance().getCompany();
        this.app = App.getInstance();
    }

    public ListingUsersController(Company company) {
        this.company = company;
    }

    public List<String> getSNSuserList(){
        List<SNSuser> snSuserList=this.company.getSNSuserList();
        List<String> list=new ArrayList<>();
        for(int i=0;i<snSuserList.size();i++){
            list.add(snSuserList.get(i).toString());
        }
        return list;
    }

    public List<String> getEmployeeList(){
        List<Employee> employeeList=this.company.getEmployeeList();
        List<String> list=new ArrayList<>();
        for(int i=0;i<employeeList.size();i++){
            list.add(employeeList.get(i).toString());
        }
        return list;
    }
}
