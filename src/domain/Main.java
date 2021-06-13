package domain;

import aplicationframe.ApplicationFrame;
import aplicationframe.LoginFrame;
import aplicationframe.RegisterFrame;
import aplicationframe.ReservationFrame;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static LoginFrame loginFrame;
    private static RegisterFrame registerFrame;
    private static ReservationFrame reservationFrame;
    private static ApplicationFrame applicationFrame;
    private static final List<User> registeredUsers = new ArrayList<>();

    private static boolean isLogin = false;
    private User loginUser;

    public static User createDefaultUsers(String name) {
        String position = "Admin";
        String id = "1";
        String password = "1";
        String phoneNumber = "01036926920";

        return new User(position, name, id, password, phoneNumber);
    }

    public static void main(String[] args) {
        registeredUsers.add(createDefaultUsers("Song"));
        loginFrame = new LoginFrame(registeredUsers);
    }
}
