package aplicationframe;

import domain.Counter;
import domain.Seat;
import domain.User;

import javax.swing.*;
import java.awt.*;

public class ReservationFrame extends JFrame {
    private static final int NUMBER_OF_TABLES = 11;

    private final JLabel[] tableLabel = new JLabel[NUMBER_OF_TABLES + 1];
    private final JPanel[] tablePanel = new JPanel[NUMBER_OF_TABLES + 1];
    private final String[] orderInfo = new String[4];
    private JComboBox<String> tableNumber;

    private final Counter counter = new Counter();
    private int focusedTableNumber;
    private final String[] loginUserInfo;

    public ReservationFrame(String[] userInfo) {
        loginUserInfo = userInfo;

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
            orderInfo[2] = "된장찌개";
            orderInfo[3] = "1200-1400";
            reserveSeat(orderInfo);
        });

        JButton cleanBtn = new JButton("정산");
        btnPanel.add(cleanBtn);

        cleanBtn.addActionListener(e -> {
            System.out.println("청소");
            cleanSeat((String)tableNumber.getSelectedItem());
        });

        JButton menuBtn = new JButton("메뉴판");
        btnPanel.add(menuBtn);

        menuBtn.addActionListener(e -> {
//            for (String s : Counter.getMenu().keySet()) {
//                System.out.println(s + " : " + Counter.getMenu().get(s));
//            }
            new MenuFrame(Counter.menu);
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
        //orderInfo 0 좌석 1 인원 2 음식 3 시간
        int tableNumber = Integer.parseInt(orderInfo[0]);
        int numberOfCustomers = Integer.parseInt(orderInfo[1]);
        String totalPrice = String.valueOf(Counter.reserve(tableNumber, orderInfo[2], orderInfo[3]));

        if(totalPrice.equals("-1")) {
            JOptionPane.showMessageDialog(null, "\"해당 자리에 예약할 수 없습니다.\"",
                    "예약 불가", JOptionPane.WARNING_MESSAGE);
        } else {
            tablePanel[tableNumber].setBackground(new Color(61, 183, 204));
            tablePanel[tableNumber].add(new JLabel(numberOfCustomers + "명"));
            //Counter에서 음식값 계산하기
            tablePanel[tableNumber].add(new JLabel(totalPrice));
            tablePanel[tableNumber].add(new JLabel(orderInfo[2]));
            tablePanel[tableNumber].add(new JLabel(orderInfo[3]));
            tableLabel[tableNumber].setText("Reserved Table " + tableNumber);
        }
    }

    // 자리를 계산하는 메서드
    // tableNumber 받아 해당하는 좌석을 정리함.
    private void cleanSeat(String tableNumber) {
        int number = Integer.parseInt(tableNumber);
        for (Seat reservedSeat : Counter.reservedSeats) {
            if (reservedSeat.getSeatNumber() == number) {
                Counter.reservedSeats.remove(reservedSeat);
                tableLabel[number] = new JLabel();

                tablePanel[number].removeAll();
                tablePanel[number].setBackground(Color.pink);
                tablePanel[number].add(tableLabel[number]);
                tableLabel[number].setText("Table " + number);

//                for (User currentUser : LoginFrame.currentUsers) {
//                    if(currentUser.getId().equals(loginUserInfo[0])) {
//                        currentUser.
//                    }
//                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "\"해당 좌석은 비어있습니다.\"",
                "에러", JOptionPane.WARNING_MESSAGE);
    }
}
