package manager;

import person.Employee;
import service.UI;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementImpl implements EmployeeManagement{
    private List<Employee> employeeList = new ArrayList<>();

    //직원 추가

    @Override
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
    @Override
    public void updateName(Employee employee,String name) {
        if(employee != null){
            employee.setName(name);
        }
    }

    //부서 수정
    @Override
    public void updateDept(Employee employee){
        if(employee != null){
            employee.setDepartment(UI.selectDepartment());
        }
    }

    //직급 수정
    @Override
    public void updatePosition(Employee employee){
        if(employee != null){
            employee.setPosition(UI.selectPosition());
        }
    }

    @Override
    public void updateEmployeeState(Employee employee) {
        if(employee != null){
            employee.setEmployeeState(UI.selectEmployeeState());
        }
    }

    //직원 퇴직
    @Override
    public void leaveEmployee(int employeeNumber) {
        boolean start = true;
        int index = 0;
        while(start){
            if(employeeList.get(index).getEmployeeNumber() == employeeNumber){
                employeeList.remove(index);
                UI.deleteEmployeeMessage();
                return;
            }
            index++;
        }
    }

    //사원번호로 직원 찾기
    @Override
    public Employee findEmployee(int employeeNumber) {
        for(Employee e : employeeList){
            if(e.getEmployeeNumber() == employeeNumber){
                return e;
            }
        }
        UI.errorEmployeeMessage();
        return null;
    }

    public List<Employee> findAll(){
        return new ArrayList<>(employeeList);
    }
}