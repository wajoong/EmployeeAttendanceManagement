package manager;

import person.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceManagementDBImpl implements AttendanceManagement{
    //DBMS와 연결을 위한 Connection 객체
    Connection con = null;

    //통신하기 위한 PreparedStatement 객체, sql 실행을 위한 객체
    PreparedStatement psmt = null;
    ResultSet rs = null;

    String server = "com.mysql.jdbc.Driver";
    String database = "jdbc:mysql://localhost:3306/employee";
    String user_name = "root";
    String password = "wajoong21";

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            if (psmt != null) {
                psmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkEmployee(Employee employee) throws IllegalArgumentException{
        try {
            con = DriverManager.getConnection(database, user_name, password);//계정 연결
            EmployeeManagementDBImpl employeeManagementDB = new EmployeeManagementDBImpl();
                    employeeManagementDB.updateEmployeeState(employee);
            String sql2 = "insert into attendance(employee, check_time, employee_state)" + "values(?,CURRENT_TIMESTAMP(),?)";
            psmt = con.prepareStatement(sql2);
            psmt.setInt(1, employee.getEmployeeNumber());
            psmt.setString(2, employee.getEmployeeState().toString());
            int res = psmt.executeUpdate();
            if(res > 0) {
                System.out.println("입력 성공");
            }else {
                System.out.println("입력 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace(); //오류 내용 출력
        } finally {
            closeConnection();
        }
    }

    public List<Attendance> findAll() {
        List<Attendance> attendanceList = new ArrayList<>();
        try {
            con = DriverManager.getConnection(database, user_name, password);
            String sql = "select * from attendance";
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                attendanceList.add(getAttendanceByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return attendanceList;
    }

    //-------------------------------

    private Attendance getAttendanceByResultSet(ResultSet resultSet) throws SQLException {
        EmployeeManagementDBImpl employeeManagementDB = new EmployeeManagementDBImpl();
        Attendance attendance = new Attendance(employeeManagementDB.findEmployee(resultSet.getInt("employee")));
        attendance.setEmployee(attendance.getEmployee());
        attendance.setEmployeeState(attendance.getEmployeeState());
        return attendance;
    }
}
