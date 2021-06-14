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
    private static final Counter counter = new Counter();

    public static User createDefaultUsers(String name) {
        String position = "Customer";
        String id = "1";
        String password = "1";
        String phoneNumber = "01036926920";

        return new Customer(position, name, id, password, phoneNumber);
    }

    public static void main(String[] args) {
        registeredUsers.add(createDefaultUsers("Song"));
        loginFrame = new LoginFrame(counter, registeredUsers);
    }
}
