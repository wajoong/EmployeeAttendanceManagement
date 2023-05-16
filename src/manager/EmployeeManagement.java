package manager;

import person.Employee;

import java.util.List;

public interface EmployeeManagement {
    void addEmployee(Employee employee);
    void updateName(Employee employee, String name);
    void updateDept(Employee employee);
    void updatePosition(Employee employee);
    void updateEmployeeState(Employee employee);
    void leaveEmployee(int employeeNumber);
    Employee findEmployee(int employeeNumber);

    List<Employee> findAll();

}
