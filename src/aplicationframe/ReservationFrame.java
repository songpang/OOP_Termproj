package aplicationframe;

import javax.swing.*;
import java.awt.*;

public class ReservationFrame extends JFrame {
    private static final int NUMBER_OF_TABLES = 11;

    private final JLabel[] tableLabel = new JLabel[NUMBER_OF_TABLES + 1];
    private final JPanel[] tablePanel = new JPanel[NUMBER_OF_TABLES + 1];
    private final String[] orderInfo = new String[4];
    private JComboBox<String> tableNumber;

    private int focusedTableNumber;

    public ReservationFrame() {
        setTitle("Register Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();

        GridLayout grid = new GridLayout(4, 3, 5, 5);
        c.setLayout(grid);

        makeReservationSeats(c);

        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        makeComboBox(comboPanel);
        c.add(comboPanel);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        makeButtons(btnPanel);
        c.add(btnPanel);

//        JTextField tableNumber = new JTextField();
//        JTextField food = new JTextField();

        setSize(800, 600);
        setVisible(true);
    }

    private void makeComboBox(JPanel currentPanel) {
        String[] tableNumbers = new String[NUMBER_OF_TABLES + 1];

        tableNumbers[0] = "테이블 번호";
        for (int i = 1; i <= NUMBER_OF_TABLES; i++) {
            tableNumbers[i] = String.valueOf(i);
        }
        tableNumber = new JComboBox<>(tableNumbers);
        currentPanel.add(tableNumber);
    }

    private void makeButtons(JPanel btnPanel) {
        JButton confirmRegisterBtn = new JButton("주문");
        btnPanel.add(confirmRegisterBtn);

        confirmRegisterBtn.addActionListener(e -> {
            System.out.println("클릭클릭");

            // 좌석, 인원, 음식, 시간
            orderInfo[0] = (String) tableNumber.getSelectedItem();
            orderInfo[1] = "3";
            orderInfo[2] = "Kimchi";
            orderInfo[3] = "1200-1400";
            reserveSeat(orderInfo);
        });

        JButton cleanBtn = new JButton("계산");
        btnPanel.add(cleanBtn);

        cleanBtn.addActionListener(e -> {
            System.out.println("청소");
            cleanSeat(Integer.parseInt((String)tableNumber.getSelectedItem()));
        });
    }

    private void makeReservationSeats(Container c) {
        for (int i = 1; i <= NUMBER_OF_TABLES; i++) {
            tablePanel[i] = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 5));
            tableLabel[i] = new JLabel("Table " + i);

            tablePanel[i].setBackground(Color.pink);
            tablePanel[i].setOpaque(true);
            tablePanel[i].add(tableLabel[i]);
            c.add(tablePanel[i]);
        }
        c.add(new JPanel());
    }

    // 자리를 예약하는 메서드
    private void reserveSeat(String[] orderInfo) {
        int tableNumber = Integer.parseInt(orderInfo[0]);
        int numberOfCustomers = Integer.parseInt(orderInfo[1]);

        tablePanel[tableNumber].setBackground(new Color(61, 183, 204));
        tablePanel[tableNumber].add(new JLabel(numberOfCustomers + "명"));
        tablePanel[tableNumber].add(new JLabel("00,000원"));
        tablePanel[tableNumber].add(new JLabel(orderInfo[2]));
        tablePanel[tableNumber].add(new JLabel(orderInfo[3]));
        tableLabel[tableNumber].setText("Reserved Table " + tableNumber);
    }

    private void cleanSeat(int tableNumber) {
        tableLabel[tableNumber] = new JLabel();

        tablePanel[tableNumber].removeAll();
        tablePanel[tableNumber].setBackground(Color.pink);
        tablePanel[tableNumber].add(tableLabel[tableNumber]);
        tableLabel[tableNumber].setText("Table " + tableNumber);
    }

    public static void main(String[] args) {
        new ReservationFrame();
    }
}
