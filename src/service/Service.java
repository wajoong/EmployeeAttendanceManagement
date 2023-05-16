package service;

import manager.AttendanceManagementDBImpl;
import manager.EmployeeManagementDBImpl;
import person.Attendance;
import person.Employee;
import person.EmployeeState;

public class Service {
    private final EmployeeManagementDBImpl employeeManagement = new EmployeeManagementDBImpl();
    private final AttendanceManagementDBImpl attendanceManagement = new AttendanceManagementDBImpl();
    Employee employee;


    //메인 페이지
    public int mainPage(){
        return UI.displayWelcomeMessage();
    }

    //직원 리스트 출력
    public void printEmployee() {
        if(employeeManagement.findAll().size() == 0){
            UI.noEmployeeMessage();
            return;
        }
        for (Employee e : employeeManagement.findAll()) {
            UI.printEmployee(e);
        }
    }

    //직원의 출퇴근 기록 출력
    public void printAttendance() {
        if(attendanceManagement.findAll().size() == 0){
            UI.noAttendanceMessage();
            return;
        }
        for (Attendance a : attendanceManagement.findAll()) {
            UI.printAttendance(a);
        }
    }

    //직원의 출근, 퇴근, 조퇴, 휴가 기록
    public void checkAttendance() {
        employee = employeeManagement.findEmployee(UI.selectEmployeeNumber());
        if(employee == null){
            return;
        }
        switch (UI.displayAttendance()) {
            case 1:
                employee.setEmployeeState(EmployeeState.CHECK_IN);
                attendanceManagement.checkEmployee(employee);
                break;
            case 2:
                employee.setEmployeeState(EmployeeState.CHECK_OUT);
                attendanceManagement.checkEmployee(employee);
                break;
            case 3:
                employee.setEmployeeState(EmployeeState.EARLY_LEAVE);
                attendanceManagement.checkEmployee(employee);
                break;
            case 4:
                employee.setEmployeeState(EmployeeState.VACATION);
                attendanceManagement.checkEmployee(employee);
                break;
            default:
                UI.errorIntMessage();
                break;
        }
    }

    //직원 관리 페이지
    public void employeeManagePage(){
        switch (UI.displayEmployeeManagement()){
            case 1:
                employeeManagement.addEmployee(UI.addEmployee());
                break;
            case 2:
                updateEmployeePage();
                break;
            case 3:
                employeeManagement.leaveEmployee(UI.selectEmployeeNumber());
                break;
            case 4:
                break;
            default:
                UI.errorIntMessage();
                break;
        }
    }

    //직원 정보 수정
    public void updateEmployeePage(){
        employee = employeeManagement.findEmployee(UI.selectEmployeeNumber());
        if(employee == null){
            return;
        }
        switch (UI.displayUpdateEmployee()){
            case 1:
                employeeManagement.updateName(employee, UI.selectName());
                UI.updateNameMessage();
                break;
            case 2:
                employeeManagement.updateDept(employee);
                UI.updateDepartmentMessage();
                break;
            case 3:
                employeeManagement.updatePosition(employee);
                UI.updatePositionMessage();
                break;
            case 4:
                break;
            default:
                UI.errorIntMessage();
                break;
        }
    }
}
