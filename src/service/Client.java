package service;

public class Client {
    Service service = new Service();

    public void run(){

            boolean selectRun = true;
            while(selectRun){

                switch(service.mainPage()){
                    case 1: // 사원 관리
                        service.employeeManagePage();
                        break;
                    case 2: // 출퇴근 관리
                        service.checkAttendance();
                        break;
                    case 3: // 직원 출력
                        service.printEmployee();
                        break;
                    case 4: // 출퇴근 기록 출력
                        service.printAttendance();
                        break;
                    case 5:
                        selectRun = false;
                        break;
                    default:
                        UI.errorIntMessage();
                        break;
                }
            }
    }
}
