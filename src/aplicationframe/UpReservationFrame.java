package aplicationframe;

import domain.Seat;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UpReservationFrame extends JFrame{
    private JLabel[] menuLabels;

    public UpReservationFrame(List<Seat> seats) {
        setTitle("Reservation");

        GridLayout grid = new GridLayout(12, 5);
        grid.setVgap(5);
        Container c = getContentPane();
        setLocation(790, 0);

        menuLabels = new JLabel[300];
        int i = 0;
        for (Seat seat : seats) {
            for (String s : seat.getInfo()) {
                menuLabels[i] = new JLabel(s);
                menuLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
                c.add(menuLabels[i]);
                i++;
            }
        }
        c.setLayout(grid);

        setSize(300, 600);
        setVisible(true);
    }
}
