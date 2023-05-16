package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import person.*;

public class UI {
    public static Scanner sc = new Scanner(System.in);

    //메인화면
    public static int displayWelcomeMessage() {
            System.out.println("\n----------------메인 화면--------------");
            System.out.println("1.사원관리 / 2.출퇴근관리 / 3.직원출력 / 4.출퇴근기록 출력 / 5.나가기");
            return getInt();
    }

    //사원관리 페이지
    public static int displayEmployeeManagement() {
            System.out.println("\n----------------사원 관리--------------");
            System.out.println("1.직원추가 / 2.직원정보변경 / 3.직원삭제하기 / 4.나가기");
            return getInt();
    }

    //직원 수정 페이지
    public static int displayUpdateEmployee(){
            System.out.println("\n----------------직원 정보 변경--------------");
            System.out.println("1.이름변경 / 2.부서변경 / 3.직급변경 / 4.나가기");
            return getInt();
    }

    //부서 수정
    public static Department selectDepartment() {
        Department department;
        while(true){
            System.out.println("\n담당 부서를 정해주세요");
            System.out.println("1.인사 / 2.홍보 / 3.영업");
            switch (Integer.parseInt(sc.nextLine())){
                case 1:
                    department = Department.PERSONAL;
                    break;
                case 2:
                    department = Department.PUBLIC_RELATION;
                    break;
                case 3:
                    department = Department.SALES;
                    break;
                default:
                    errorIntMessage();
                    continue;
            }
            return department;
        }
    }

    //직급 수정
    public static Position selectPosition() {
        Position position;
        while(true){
            System.out.println("\n직급을 정해주세요");
            System.out.println("1.인턴 / 2.대리 / 3.과장");
            switch (Integer.parseInt(sc.nextLine())){
                case 1:
                    position = Position.INTERN;
                    break;
                case 2:
                    position = Position.ASSISTANT;
                    break;
                case 3:
                    position = Position.GENERAL;
                    break;
                default:
                    errorIntMessage();
                    continue;
            }
            return position;
        }
    }

    public static EmployeeState selectEmployeeState() {
        EmployeeState employeeState;
        while(true) {
            switch (displayAttendance()) {
                case 1:
                    employeeState = EmployeeState.CHECK_IN;
                    break;
                case 2:
                    employeeState = EmployeeState.CHECK_OUT;
                    break;
                case 3:
                    employeeState = EmployeeState.EARLY_LEAVE;
                    break;
                case 4:
                    employeeState = EmployeeState.VACATION;
                    break;
                default:
                    errorIntMessage();
                    break;
            }
        }
    }

    //이름 수정
    public static String selectName() {
        System.out.println("\n이름을 입력해주세요");
        return sc.nextLine();
    }

    //사번 입력
    public static int selectEmployeeNumber() {
        while(true){
            System.out.println("\n사번을 입력해주세요.");
            return getInt();
        }
    }

    //출퇴근 페이지
    public static int displayAttendance(){
            System.out.println("\n1.출근 2.퇴근 3.조퇴 4.휴가");
            return getInt();
    }

    //직원 추가 페이지
    public static Employee addEmployee(){
        Employee employee = new Employee();
        employee.setEmployeeNumber(selectEmployeeNumber());
        employee.setName(selectName());
        employee.setDepartment(selectDepartment());
        employee.setPosition(selectPosition());

        return employee;
    }

    //직원의 부서 출력
    public static String printDepartment(Department department){
        if (department == Department.PERSONAL){
            return "인사부서";
        } else if(department == Department.PUBLIC_RELATION){
            return "홍보부서";
        } else{
            return "영업부서";
        }
    }

    //직원의 직급 출력
    public static String printPosition(Position position){

        if(position == Position.INTERN){
            return "인턴";
        } else if(position == Position.ASSISTANT){
            return "대리";
        } else{
            return "과장";
        }
    }

    //직원리스트 출력
    public static void printEmployee(Employee e){
        System.out.println(toStringEmployee(e));
    }

    //출퇴근리스트 출력
    public static void printAttendance(Attendance a){
        System.out.println(toStringAttendance(a));
    }

    //정수만 입력받기
    public static int getInt(){
        int selectNumber;
        while(true){
            try {
                selectNumber = Integer.parseInt(sc.nextLine());
                return selectNumber;
            } catch (NumberFormatException e) {
                System.out.println("정수만 입력해주세요");
            }
        }
    }

    //직원 정보 문자형으로 반환
    public static String toStringEmployee(Employee e){
        return
                "담당부서 : " + printDepartment(e.getDepartment()) + " / " +
                "직급 : " + printPosition(e.getPosition()) + " / " +
                "이름 : " + e.getName() + " / " +
                "사원번호 " + e.getEmployeeNumber();
    }

    //출퇴근 시간 문자형으로 반환
    public static String getTime(Attendance a){
        if(a.getEmployeeState() == EmployeeState.VACATION){
            Calendar vacation = Calendar.getInstance();
            vacation.add(Calendar.DATE, +7);
            return  new SimpleDateFormat("yyyy년 MM월 dd일 ~ ").format(a.getCheckTime()) +
                    new java.text.SimpleDateFormat("yyyy년 MM월 dd일").format(vacation.getTime());
        }
        return new SimpleDateFormat("yyyy년 MM월 dd일").format(a.getCheckTime());
    }

    //출퇴근 리스트 문자형으로 반환
    public static String toStringAttendance(Attendance a){
        if (a.getEmployeeState() == EmployeeState.VACATION) {
            return toStringEmployee(a.getEmployee()) +
                    " / 출근상태 : 휴가" +
                    " / 휴가기간 : " + getTime(a);
        } else  if(a.getEmployeeState() == EmployeeState.CHECK_IN){
            toStringEmployee(a.getEmployee());
            return toStringEmployee(a.getEmployee()) +
                    " / 출근상태 : 출근" +
                    " / 출근시간 : " + getTime(a);
        } else if(a.getEmployeeState() == EmployeeState.CHECK_OUT){
            toStringEmployee(a.getEmployee());
            return toStringEmployee(a.getEmployee()) +
                    " / 출근상태 : 퇴근" +
                    " / 퇴근시간 : " + getTime(a);
        } else{
            toStringEmployee(a.getEmployee());
            return toStringEmployee(a.getEmployee()) +
                    " / 출근상태 : 조퇴" +
                    " / 조퇴시간 : " + getTime(a);
        }
    }

    //직원 추가 메시지
    public static void newEmployeeMessage(){
        System.out.println("직원이 추가되었습니다.");
    }

    //직원 삭제 메시지
    public static void deleteEmployeeMessage(){
        System.out.println("직원이 삭제되었습니다.");
    }

    //직급 변경 메시지
    public static void updatePositionMessage(){
        System.out.println("직급이 변경되었습니다.");
    }

    //부서 변경 메시지
    public static void updateDepartmentMessage(){
        System.out.println("부서가 변경되었습니다.");
    }

    //이름 변경 메시지
    public static void updateNameMessage(){
        System.out.println("이름이 변경되었습니다.");
    }

    //에러메시지 - 카테고리 잘못 입력
    public static void errorIntMessage(){
        System.out.println("\n올바른 값을 입력해주세요.");
    }

    //에러메시지 - 직원찾기
    public static void errorEmployeeMessage(){
        System.out.println("\n등록되어 있지 않은 사번입니다.");
    }

    //에러메시지 - 중복된 사원번호 입력
    public static void duplicateErrorMessage(){
        System.out.println("이미 등록된 번호입니다.");
    }

    //등록된 직원이 없을 때
    public static void noEmployeeMessage(){
        System.out.println("등록된 직원이 없습니다.");
    }

    //출퇴근 기록이 없을 때
    public static void noAttendanceMessage(){
        System.out.println("출퇴근 기록이 없습니다.");
    }
}