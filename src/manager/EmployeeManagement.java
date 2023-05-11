package manager;

import person.Employee;
import service.UI;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagement {
    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    //직원 추가
    public void addEmployee(Employee employee) {
        for(Employee e : employeeList){
            if(e.getEmployeeNumber() == employee.getEmployeeNumber()){
                UI.duplicateErrorMessage();
                return;
            }
        }
        if(employee.getDepartment() == null){
        }
        employeeList.add(new Employee(employee));
        UI.newEmployeeMessage();
    }


    //이름 수정
    public void updateName(int employeeNumber) {
        Employee employee;
        employee = findEmployee(employeeNumber);
        if(employee != null){
            employee.setName(UI.selectName());
        }
    }

    //부서 수정
    public void updateDept(int employeeNumber){
        Employee employee;
        employee = findEmployee(employeeNumber);
        if(employee != null){
            employee.setDepartment(UI.selectDepartment());
        }
    }

    //직급 수정
    public void updatePosition(int employeeNumber){
        Employee employee;
        employee = findEmployee(employeeNumber);
        if(employee != null){
            employee.setPosition(UI.selectPosition());
        }
    }

    //직원 퇴직
    public void leaveEmployee() {
        int removeNum = UI.selectEmployeeNumber();
        if(findEmployee(removeNum) == null){
            return;
        }
        boolean start = true;
        int index = 0;
        while(start){
            if(employeeList.get(index).getEmployeeNumber() == removeNum){
                employeeList.remove(index);
                UI.deleteEmployeeMessage();
                return;
            }
            index++;
        }
    }

    //사원번호로 직원 찾기
    public Employee findEmployee(int employeeNumber) {
        for(Employee e : employeeList){
            if(e.getEmployeeNumber() == employeeNumber){
                return e;
            }
        }
        UI.errorEmployeeMessage();
        return null;
    }

}