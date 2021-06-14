package domain;

import java.util.List;

public class Admin extends User{
    public Admin(String position, String name, String id, String password, String phoneNumber) {
        super(position, name, id, password, phoneNumber);
    }

    public List<Seat> checkAllReservation(List<Seat> reservation) {
        return reservation;
    }
}
