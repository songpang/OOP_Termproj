package aplicationframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class ReservationFrame extends JFrame{
    private JLabel[] tableLabel;
    private JPanel[] tablePanel;
    private final int NUMBER_OF_TABLES = 12;
    private String[] orderInfo = new String[4];

    public ReservationFrame() {
        setTitle("Register Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();

        GridLayout grid = new GridLayout(4,3, 5, 5);
        c.setLayout(grid);

        tablePanel = new JPanel[NUMBER_OF_TABLES];
        tableLabel = new JLabel[NUMBER_OF_TABLES];

        for (int i = 0; i < NUMBER_OF_TABLES; i++) {
//            tablePanel[i] = new JPanel(new GridLayout(7, 1));
            tablePanel[i] = new JPanel(new FlowLayout(FlowLayout.CENTER,80, 5));
            tableLabel[i] = new JLabel("Table " + i);
            tableLabel[i].setHorizontalAlignment(SwingConstants.CENTER);

            tablePanel[i].setBackground(Color.pink);
            tablePanel[i].setOpaque(true);
            tablePanel[i].add(tableLabel[i]);
            c.add(tablePanel[i]);
        }
        //기록 삭제

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        c.add(jPanel);
        JButton confirmRegisterBtn = new JButton("주문");
        jPanel.add(confirmRegisterBtn);
        confirmRegisterBtn.addActionListener(e -> {
            System.out.println("클릭클릭");
            // 좌석, 인원, 음식, 시간
            orderInfo[0] = "4";
            orderInfo[1] = "3";
            orderInfo[2] = "Kimchi";
            orderInfo[3] = "1200-1400";
            reserveSeat(c, orderInfo);
        });

        String[] temp = {"테이블 번호", "124", "4530"};
        JComboBox<String> tableNumber = new JComboBox<>(temp);
        jPanel.add(tableNumber);
        for (int i = 0; i < NUMBER_OF_TABLES; i++) {

        }

//        JTextField tableNumber = new JTextField();
//        JTextField food = new JTextField();

        setSize(800, 600);
        setVisible(true);
    }

    private void tempMethod() {
        tablePanel[2].add(new JLabel("36,000원"));
//        tablePanel[2].removeAll();
        tablePanel[2].add(new JLabel("table " + 2));
    }

    // 자리를 예약하는 메서드
    private void reserveSeat(Container c,String[] orderInfo) {
        int tableNumber = Integer.parseInt(orderInfo[0]);
        int numberOfCustomers = Integer.parseInt(orderInfo[1]);

        tablePanel[tableNumber].setBackground(new Color(61, 183, 204));
        tablePanel[tableNumber].add(new JLabel(numberOfCustomers + "명"));
        tablePanel[tableNumber].add(new JLabel("36,000원"));
        tablePanel[tableNumber].add(new JLabel(orderInfo[2]));
        tablePanel[tableNumber].add(new JLabel(orderInfo[3]));
        tableLabel[tableNumber].setText("Reserved Table " + tableNumber);
    }

    public static void main(String[] args) {
        new ReservationFrame();
    }
}
