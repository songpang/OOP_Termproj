package domain;

import aplicationframe.ApplicationFrame;
import aplicationframe.LoginFrame;
import aplicationframe.RegisterFrame;
import aplicationframe.ReservationFrame;

public class Main {
    private static LoginFrame loginFrame;
    private static RegisterFrame registerFrame;
    private static ReservationFrame reservationFrame;
    private static ApplicationFrame applicationFrame;
    private static final User[] registeredUsers = new User[1];

    private User loginUser;

    public static User createDefaultUsers(String name) {
        String position = "Admin";
        String id = "scc6920";
        String password = "1234";
        String phoneNumber = "01036926920";

        return new User(position, name, id, password, phoneNumber);
    }

    public static void main(String[] args) {
        registeredUsers[0] = createDefaultUsers("Song");
        while(true) {
            loginFrame = new LoginFrame(registeredUsers);
            if()
            loginFrame.dispose();

        }
        for (String s : loginFrame.getUserInfo()) {
            System.out.println(s);
        }

    }
}
