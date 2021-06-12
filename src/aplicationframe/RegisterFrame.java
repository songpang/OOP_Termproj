package aplicationframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Member;

public class RegisterFrame extends JFrame {
    private int isAdmin = 0;
    private Member member;

    public RegisterFrame() {
        setTitle("Register Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(5,2);
        grid.setVgap(5);

        Container c = getContentPane();
        c.setLayout(grid);

        JPanel jPanelForRegisterBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel jPanelForAdmin = new JPanel(new FlowLayout(FlowLayout.CENTER));

        c.add(new JLabel("이름"));
        c.add(new JTextField(""));
        c.add(new JLabel("ID"));
        c.add(new JTextField(""));
        c.add(new JLabel("PASSWORD"));
        c.add(new JTextField(""));
        c.add(new JLabel("전화번호"));
        c.add(new JTextField(""));

        c.add(jPanelForAdmin);
        JCheckBox positionCheckBox = new JCheckBox("관리자");
        positionCheckBox.addItemListener(new positionCheckBoxItemListener());
        jPanelForAdmin.add(positionCheckBox);

        c.add(jPanelForRegisterBtn);
        JButton ConfirmRegisterBtn = new JButton("회원가입");
        ConfirmRegisterBtn.addActionListener(new ConfirmRegisterActionListener());
        jPanelForRegisterBtn.add(ConfirmRegisterBtn);

        setSize(600, 200);
        setVisible(true);
    }

    class ConfirmRegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("register Button Clicked");
            setVisible(false);

            LoginFrame loginFrame = new LoginFrame();
        }
    }

    class positionCheckBoxItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                isAdmin = 1;
            } else {
                isAdmin = 0;
            }

            System.out.println(isAdmin);
        }
    }

    public static void main(String[] args) {
        RegisterFrame registerFrame = new RegisterFrame();
    }
}
