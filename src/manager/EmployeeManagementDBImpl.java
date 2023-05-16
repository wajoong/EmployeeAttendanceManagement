package manager;

import person.Department;
import person.Employee;
import person.EmployeeState;
import person.Position;
import service.UI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementDBImpl implements EmployeeManagement{

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
    public void addEmployee(Employee employee) throws IllegalArgumentException {
            try {
                con = DriverManager.getConnection(database, user_name, password);//계정 연결
                String sql = "insert into employee(employee_number, name, department, position, employee_state)" + "values(?,?,?,?,?)";

                psmt = con.prepareStatement(sql);
                psmt.setInt(1, employee.getEmployeeNumber());
                psmt.setString(2,employee.getName());
                psmt.setString(3, employee.getDepartment().toString());
                psmt.setString(4, employee.getPosition().toString());
                psmt.setString(5, employee.getEmployeeState().toString());
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

    @Override
    public void updateName(Employee employee, String name) {
        try {
            con = DriverManager.getConnection(database, user_name, password);
            String sql = "update employee set name = ? where employee_number = ?";
            psmt = con.prepareStatement(sql);
            psmt.setString(1,name);
            psmt.setInt(2,employee.getEmployeeNumber());
            psmt.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
    } finally {
            closeConnection();
        }
    }

    @Override
    public void updateDept(Employee employee) {
        String department = UI.selectDepartment().toString();
        try {
            con = DriverManager.getConnection(database, user_name, password);
            String sql = "update employee set department = ? where employee_number = ?";
            psmt = con.prepareStatement(sql);
            psmt.setString(1,department);
            psmt.setInt(2,employee.getEmployeeNumber());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updatePosition(Employee employee) {
        String position = UI.selectPosition().toString();
        try {
            con = DriverManager.getConnection(database, user_name, password);
            String sql = "update employee set position = ? where employee_number = ?";
            psmt = con.prepareStatement(sql);
            psmt.setString(1,position);
            psmt.setInt(2,employee.getEmployeeNumber());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updateEmployeeState(Employee employee) {
        try {
            con = DriverManager.getConnection(database, user_name, password);
            String sql = "update employee set employee_state = ? where employee_number = ?";
            psmt = con.prepareStatement(sql);
            psmt.setString(1,employee.getEmployeeState().toString());
            psmt.setInt(2,employee.getEmployeeNumber());
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void leaveEmployee(int employeeNumber) {
        try {
            con = DriverManager.getConnection(database, user_name, password);
            String sql = "select * from employee where employee_number = ?";
            psmt = con.prepareStatement(sql);
            psmt.setInt(1,employeeNumber);
            rs = psmt.executeQuery();
            if(rs.next()) {
                sql = "delete from employee where employee_number = ?";
                psmt = con.prepareStatement(sql);
                psmt.setInt(1,employeeNumber);
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public Employee findEmployee(int employeeNumber) {
        try {
            con = DriverManager.getConnection(database, user_name, password);
            String sql = "select * from employee where employee_number = ?";
            psmt = con.prepareStatement(sql);
            psmt.setInt(1,employeeNumber);
            rs = psmt.executeQuery();
            if(rs.next()) {
                return getEmployeeByResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            con = DriverManager.getConnection(database, user_name, password);
            String sql = "select * from employee";
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while(rs.next()) {
                employeeList.add(getEmployeeByResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return employeeList;
    }

    //-------------------------------

    private Employee getEmployeeByResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeNumber(resultSet.getInt("employee_number"));
        employee.setName(resultSet.getString("name"));
        employee.setDepartment(Department.valueOf(resultSet.getString("department")));
        employee.setPosition(Position.valueOf(resultSet.getString("position")));
        employee.setEmployeeState(EmployeeState.valueOf(resultSet.getString("employee_state")));
        return employee;
    }
}
