package mappers;

import app.domain.model.Employee;
import app.domain.model.User;
import mappers.dto.dtoEmployee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EmployeeMapper {
    public EmployeeMapper(){}

    public dtoEmployee toDTO(Employee employee, User user){
        return new dtoEmployee(employee.getId(), employee.getName(), employee.getAdress(), employee.getEmail(), employee.getPhone(), employee.getCc(), employee.getRoleId(), user.getPassword());
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
