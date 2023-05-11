package person;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Attendance {
    private Employee employee;
    private Date checkTime;
    private EmployeeState employeeState;

    public Attendance(Employee employee) {
        this.employee = employee;
        this.checkTime = new Date();
        this.employeeState = employee.getEmployeeState();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public EmployeeState getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(EmployeeState employeeState) {
        this.employeeState = employeeState;
    }
}
