package person;

public class Employee {
    private Department department;
    private Position position;
    private String name;
    private int employeeNumber;
    private EmployeeState employeeState;

    public Employee() {
    }

    public Employee(Employee employee) {
        this.department = employee.department;
        this.position = employee.position;
        this.name = employee.name;
        this.employeeNumber = employee.employeeNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public EmployeeState getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(EmployeeState employeeState) {
        this.employeeState = employeeState;
    }
}
