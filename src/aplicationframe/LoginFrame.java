package aplicationframe;

import domain.Counter;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginFrame extends JFrame {
    private final JTextField idTextField = new JTextField("1");
    private final JTextField pwTextField = new JTextField("1");
    private JRadioButton customerRadio;
    private JRadioButton adminRadio;

    public static List<User> currentUsers;
    public static List<User> currentCustomers;

    public final String[] userInfo = new String[3];
    private final Counter counter;

    public LoginFrame(Counter counter, List<User> users) {
        currentUsers = users;
        this.counter = counter;

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

    public String[] getUserInfo() {
        return userInfo;
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
        customerRadio = new JRadioButton("고객", true);
        adminRadio = new JRadioButton("관리자");

        positionGroup.add(customerRadio);
        positionGroup.add(adminRadio);

        jPanelForRadio.add(customerRadio);
        jPanelForRadio.add(adminRadio);
    }

    class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userInfo[0] = idTextField.getText();
            userInfo[1] = pwTextField.getText();

            if (userInfo[0].trim().length() == 0 && userInfo[1].trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "\"전부 입력해주세요.\"",
                        "Login Error", JOptionPane.WARNING_MESSAGE);
            } else {
                //Validation Check
                for (User currentUser : currentUsers) {
                    if(currentUser.getId().equals(userInfo[0]) &&
                            currentUser.getPassword().equals(userInfo[1])) {
                        System.out.println("로그인 성공");
                        System.out.println(currentUsers.size());
                        // 일반 사용자로 시작 시
                        if(customerRadio.isSelected()) {
                            userInfo[2] = "Customer";
                        }
                        // 관리자로 시작 시
                        else {
                            userInfo[2] = "Admin";
                        }
                        new ReservationFrame(counter, userInfo);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "\"입력하신 정보가 올바르지 않습니다..\"",
                        "Login Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("register Button Clicked");
            RegisterFrame registerFrame = new RegisterFrame();
        }
    }
}
