package aplicationframe;

import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private int isAdmin = 0;

    private final JTextField idTextField = new JTextField();
    private final JTextField pwTextField = new JTextField();
    private String[] userInfo = new String[2];

    public LoginFrame(User[] users) {
        setTitle("Login Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(3, 2, 50, 50);
        grid.setVgap(5);

        Container c = getContentPane();
        c.setLayout(grid);

        JPanel jPanelForBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel jPanelForRadio = new JPanel(new FlowLayout(FlowLayout.CENTER));

        c.add(new JLabel("ID"));
        c.add(idTextField);
        c.add(new JLabel("PASSWORD"));
        c.add(pwTextField);

        createRadio(c, jPanelForRadio);
        createBtn(c, jPanelForBtn);

        setSize(600, 200);
        setVisible(true);
    }

    private void createBtn(Container c, JPanel jPanelForBtn) {
        c.add(jPanelForBtn);

        JButton loginBtn = new JButton("로그인");
        JButton registerBtn = new JButton("회원가입");

        loginBtn.addActionListener(new LoginActionListener());
        registerBtn.addActionListener(new RegisterActionListener());

        jPanelForBtn.add(loginBtn);
        jPanelForBtn.add(registerBtn);
    }

    private void createRadio(Container c, JPanel jPanelForRadio) {
        c.add(jPanelForRadio);

        ButtonGroup positionGroup = new ButtonGroup();
        JRadioButton customer = new JRadioButton("고객", true);
        JRadioButton administrator = new JRadioButton("관리자");

        positionGroup.add(customer);
        positionGroup.add(administrator);

        jPanelForRadio.add(customer);
        jPanelForRadio.add(administrator);
    }

    class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("login Button Clicked");

            userInfo[0] = idTextField.getText();
            userInfo[1] = pwTextField.getText();

            if (userInfo[0].trim().length() == 0 && userInfo[1].trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "\"전부 입력해주세요.\"",
                        "Login Error", JOptionPane.WARNING_MESSAGE);
            } else {
                System.out.println("감사합니다");
                //Validation Check
                new ReservationFrame();
            }
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
//        LoginFrame loginFrame = new LoginFrame();
    }
}
