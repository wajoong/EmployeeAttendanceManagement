package manager;

import person.Attendance;
import person.Employee;
import java.util.ArrayList;
import java.util.List;

public class AttendanceManagement {
    private List<Attendance> attendanceList = new ArrayList<>();

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    //직원의 출퇴근 기록
    public void checkEmployee(Employee employee){
        attendanceList.add(new Attendance(employee));
    }

}
