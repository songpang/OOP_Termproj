package aplicationframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationFrame extends JFrame{
    private JLabel[] tableLabel;
    private JPanel[] tablePanel;

    public ReservationFrame() {
        setTitle("Register Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(4,3, 5, 5);

        Container c = getContentPane();
        c.setLayout(grid);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        tablePanel = new JPanel[12];
        tableLabel = new JLabel[12];
        for (int i = 0; i < 12; i++) {
            tablePanel[i] = new JPanel(new GridLayout(7, 1));
            tableLabel[i] = new JLabel("Table " + i);
            tableLabel[i].setHorizontalAlignment(SwingConstants.CENTER);

            tablePanel[i].setBackground(Color.pink);
            tablePanel[i].setOpaque(true);
            tablePanel[i].add(tableLabel[i]);
            c.add(tablePanel[i]);
        }

        tablePanel[2].add(new JLabel("36,000원"));
        tablePanel[2].removeAll();
        tablePanel[2].add(new JLabel("table " + 2));

        c.add(jPanel);
        JButton ConfirmRegisterBtn = new JButton("회원가입");
        jPanel.add(ConfirmRegisterBtn);


        setSize(800, 600);
        setVisible(true);
    }

    // 자리를 예약하는 메서드
    private void reserveSeat(int seatNumber, String[] orders, String time) {
        tablePanel[seatNumber].setBackground(Color.MAGENTA);
        tablePanel[seatNumber].add(new JLabel("36,000원"));
        tablePanel[seatNumber].add(new JLabel("삼겹살"));
        tablePanel[seatNumber].add(new JLabel("10:00-12:00"));
    }

    class ConfirmRegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("register Button Clicked");
            setVisible(false);

            LoginFrame loginFrame = new LoginFrame();
        }
    }

    public static void main(String[] args) {
        new ReservationFrame();
    }
}
