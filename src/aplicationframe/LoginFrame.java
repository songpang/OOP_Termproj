package aplicationframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Login Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(3,2, 50, 50);
        grid.setVgap(5);

        Container c = getContentPane();
        c.setLayout(grid);

        JPanel jPanelForBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel jPanelForRadio = new JPanel(new FlowLayout(FlowLayout.CENTER));

        c.add(new JLabel("ID"));
        c.add(new JTextField("이곳에 입력해주세요."));
        c.add(new JLabel("PASSWORD"));
        c.add(new JTextField("이곳에 입력해주세요."));

        c.add(jPanelForRadio);
        ButtonGroup positionGroup = new ButtonGroup();
        JRadioButton customer = new JRadioButton("고객", true);
        JRadioButton administrator = new JRadioButton("관리자");

        positionGroup.add(customer);
        positionGroup.add(administrator);

        jPanelForRadio.add(customer);
        jPanelForRadio.add(administrator);

        c.add(jPanelForBtn);
        JButton loginBtn = new JButton("로그인");
        JButton registerBtn = new JButton("회원가입");

        loginBtn.addActionListener(new LoginActionListener());
        registerBtn.addActionListener(new RegisterActionListener());
        jPanelForBtn.add(loginBtn);
        jPanelForBtn.add(registerBtn);

        setSize(600, 200);
        setVisible(true);
    }

    class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("login Button Clicked");
        }
    }

    class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("register Button Clicked");
            setVisible(false);

            RegisterFrame registerFrame = new RegisterFrame();
        }
    }

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
    }
}
