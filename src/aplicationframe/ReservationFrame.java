package aplicationframe;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReservationFrame extends JFrame{
    private static final int NUMBER_OF_TABLES = 11;

    private final JLabel[] tableLabel = new JLabel[NUMBER_OF_TABLES + 1];
    private final JPanel[] tablePanel = new JPanel[NUMBER_OF_TABLES + 1];
    private final String[] orderInfo = new String[4];
    private JComboBox<String> tableNumber, partyNumber;

    private final JButton reserveBtn = new JButton("주문");
    private final JButton cleanBtn = new JButton("정산");
    private final JButton menuBtn = new JButton("메뉴판");
    private final JButton additionalOrderBtn = new JButton("추가주문");
    private final JButton checkReservationBtn = new JButton("예약자 보기");
    private final JLabel customerMileage = new JLabel();
    private final JTextField foodTextField = new JTextField();
    private final JTextField timeTextField = new JTextField();

    private final Counter counter;
    private final String[] loginUserInfo;
    private Customer customer;
    private Admin admin;

    public ReservationFrame(Counter counter, String[] userInfo) {
        loginUserInfo = userInfo;
        this.counter = counter;

        setTitle("Reservation Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();

        GridLayout grid = new GridLayout(4, 3, 5, 5);
        c.setLayout(grid);

        makeReservationSeats(c);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        makeButtons(btnPanel);
        c.add(btnPanel);

        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 5));
        makeComboBox(comboPanel);

        c.add(comboPanel);

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setLayout(new GridLayout(3, 2, 5, 5));
        labelPanel.add(new JLabel("음식"));
        labelPanel.add(foodTextField);
        labelPanel.add(new JLabel("시간"));
        labelPanel.add(timeTextField);
        c.add(labelPanel);

        if(userInfo[2].equals("Admin")) {
            customerMileage.setEnabled(false);
            for (User currentUser : LoginFrame.currentUsers) {
                if (currentUser.getId().equals(userInfo[0])) {
                    admin = new Admin(currentUser.getPosition(),
                            currentUser.getName(),
                            currentUser.getId(),
                            currentUser.getPassword(),
                            currentUser.getPhoneNumber());
                }
            }
        } else {
            for (User currentUser : LoginFrame.currentUsers) {
                if (currentUser.getId().equals(userInfo[0])) {
                    customer = new Customer(currentUser.getPosition(),
                            currentUser.getName(),
                            currentUser.getId(),
                            currentUser.getPassword(),
                            currentUser.getPhoneNumber());
                }
            }
            customerMileage.setText("회원 마일리지 : " + customer.getTotalAmount());
            cleanBtn.setEnabled(false);
            checkReservationBtn.setEnabled(false);
        }

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
        partyNumber = new JComboBox<>(new String[]{"식사 인원", "1", "2", "3", "4"});
        currentPanel.add(tableNumber);
        currentPanel.add(partyNumber);
    }

    private void makeButtons(JPanel btnPanel) {
        btnPanel.add(menuBtn);
        btnPanel.add(reserveBtn);
        btnPanel.add(cleanBtn);
        btnPanel.add(additionalOrderBtn);
        btnPanel.add(customerMileage);
        btnPanel.add(checkReservationBtn);

        reserveBtn.addActionListener(e -> {
            System.out.println("클릭클릭");

            // 좌석, 인원, 음식, 시간
            orderInfo[0] = (String) tableNumber.getSelectedItem();
            orderInfo[1] = (String) partyNumber.getSelectedItem();
            orderInfo[2] = foodTextField.getText();
            orderInfo[3] = timeTextField.getText();
            reserveSeat(orderInfo);
        });

        cleanBtn.addActionListener(e -> {
            cleanSeat((String)tableNumber.getSelectedItem());
//            customer.increaseAmount(Counter.calculatePrice(orderInfo[2]));
        });

        menuBtn.addActionListener(e -> {
            new MenuFrame(Counter.menu);
        });

        additionalOrderBtn.addActionListener(e -> {
            String additionalOrder = "삼겹살";

            orderAdditionalFood(additionalOrder);
        });

        checkReservationBtn.addActionListener(e -> {
            new UpReservationFrame(admin.checkAllReservation(counter.reservedSeats));
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
        String totalPrice = String.valueOf(Counter.reserve(tableNumber, orderInfo[1] ,orderInfo[2], orderInfo[3]));

        if(totalPrice.equals("-1")) {
            JOptionPane.showMessageDialog(null, "\"해당 자리에 예약할 수 없습니다.\"",
                    "예약 불가", JOptionPane.WARNING_MESSAGE);
        } else {
            tablePanel[tableNumber].setBackground(new Color(61, 183, 204));
            tablePanel[tableNumber].add(new JLabel(numberOfCustomers + "명"));
            //Counter에서 음식값 계산하기
            tablePanel[tableNumber].add(new JLabel(totalPrice));
            JLabel orderedFoodLabel = new JLabel(orderInfo[2]);
            tablePanel[tableNumber].add(orderedFoodLabel);
            tablePanel[tableNumber].add(new JLabel(orderInfo[3]));
            tableLabel[tableNumber].setText("Reserved Table " + tableNumber);

            DeliveryThread dt = new DeliveryThread(orderInfo, orderedFoodLabel);
            dt.start();
        }
    }

    //주문 후 음식 배달을 위한 스레드 클래스
    private class DeliveryThread extends Thread {
        String[] orderInfo;
        int seatNumber;
        JLabel hereLabel;

        public DeliveryThread(String[] orderInfo, JLabel orderedFoodLabel) {
            this.orderInfo = orderInfo;
            seatNumber = Integer.parseInt(orderInfo[0]);
            this.hereLabel = orderedFoodLabel;
        }
        //주문 후 5초가 지나면 음식이 배달된다.
        @Override
        public void run() {
            try {
                sleep(5000);
                hereLabel.setText(hereLabel.getText() + "-(O)");
                JOptionPane.showMessageDialog(null, orderInfo[0] + "번 자리에" + "\"음식이 배달되었습니다.\"",
                        "음식 완료", JOptionPane.WARNING_MESSAGE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //추가 음식을 주문하는 메서드
    private void orderAdditionalFood(String food) {
        Seat currentSeat;

        orderInfo[2] += " " + food;
        cleanSeat(orderInfo[0]);
        reserveSeat(orderInfo);
        for (Seat reservedSeat : Counter.reservedSeats) {
            if (reservedSeat.getSeatNumber() == Integer.parseInt(orderInfo[0])) {
                currentSeat = reservedSeat;
                Counter.orderAdditionalFood(currentSeat, food);
            }
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
