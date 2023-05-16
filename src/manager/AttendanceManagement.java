package manager;

import person.Attendance;
import person.Employee;

import java.util.List;

public interface AttendanceManagement {
    void checkEmployee(Employee employee);
    List<Attendance> findAll();
}
