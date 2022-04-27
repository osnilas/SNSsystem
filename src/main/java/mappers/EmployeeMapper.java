package mappers;

import app.domain.model.Employee;
import app.ui.console.utils.Utils;
import mappers.dto.dtoEmployee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EmployeeMapper {
    public EmployeeMapper(){}

    public void toDTO(Employee employee){
        //return new dtoEmployee(employee.getId(), employee.getName(), employee.getAdress(), employee.getEmail(), employee.getPhone(), employee.getCc(), employee.getRoleId());
    }

    public List<dtoEmployee> toDTO(List<Employee> employees){
        List<dtoEmployee> employeesDTO = new ArrayList();
        Iterator var3 = employees.iterator();

        while(var3.hasNext()) {
            Employee em = (Employee)var3.next();
            //employeesDTO.add(this.toDTO(em));
        }

        return employeesDTO;
    }

    public List<dtoEmployee> toDTO(Set<Employee> employees){
        List<dtoEmployee> dtoEmployees=new ArrayList<>();
        Iterator var3=employees.iterator();

        while (var3.hasNext()){
            Employee em= (Employee) var3.next();
            //dtoEmployees.add(this.toDTO(em));
        }
        return dtoEmployees;
    }
}
