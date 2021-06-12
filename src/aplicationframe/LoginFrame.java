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

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        c.add(new JLabel("ID"));
        c.add(new JTextField("이곳에 입력해주세요."));
        c.add(new JLabel("PASSWORD"));
        c.add(new JTextField("이곳에 입력해주세요."));

        c.add(jPanel);
        JButton loginBtn = new JButton("로그인");
        JButton registerBtn = new JButton("회원가입");

        loginBtn.addActionListener(new LoginActionListener());
        registerBtn.addActionListener(new RegisterActionListener());
        jPanel.add(loginBtn);
        jPanel.add(registerBtn);

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
