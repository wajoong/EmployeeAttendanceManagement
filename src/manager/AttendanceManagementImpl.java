package manager;

import person.Attendance;
import person.Employee;
import java.util.ArrayList;
import java.util.List;

public class AttendanceManagementImpl implements AttendanceManagement{
    private List<Attendance> attendanceList = new ArrayList<>();

    //직원의 출퇴근 기록
    @Override
    public void checkEmployee(Employee employee){
        attendanceList.add(new Attendance(employee));
    }

    @Override
    public List<Attendance> findAll() {
        return attendanceList;
    }
}
