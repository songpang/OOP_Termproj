package aplicationframe;

import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import static aplicationframe.LoginFrame.currentUsers;

public class RegisterFrame extends JFrame {
    private String[] userInfo = new String[5];

    private final JTextField nameTextField = new JTextField();
    private final JTextField idTextField = new JTextField();
    private final JTextField pwTextField = new JTextField();
    private final JTextField phoneNumberTextField = new JTextField();

    public RegisterFrame() {
        setTitle("Register Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container mainContainer = getContentPane();

        GridLayout grid = new GridLayout(6, 2, 5, 5);
        mainContainer.setLayout(grid);

        mainContainer.add(new JLabel("이름"));
        mainContainer.add(nameTextField);
        mainContainer.add(new JLabel("ID"));
        mainContainer.add(idTextField);
        mainContainer.add(new JLabel("PASSWORD"));
        mainContainer.add(pwTextField);
        mainContainer.add(new JLabel("전화번호"));
        mainContainer.add(phoneNumberTextField);

        createAdminCheckBox(mainContainer, new JPanel(new FlowLayout(FlowLayout.CENTER)));
        createRegisterBtn(mainContainer, new JPanel(new FlowLayout(FlowLayout.CENTER)));
        createBackBtn(mainContainer, new JPanel(new FlowLayout(FlowLayout.CENTER)));

        setSize(600, 400);
        setVisible(true);
    }

    private void createRegisterBtn(Container c, JPanel jPanelForRegisterBtn) {
        c.add(jPanelForRegisterBtn);
        JButton ConfirmRegisterBtn = new JButton("회원가입");
        ConfirmRegisterBtn.addActionListener(e -> {
            System.out.println("register Button Clicked");

            userInfo[1] = idTextField.getText();
            userInfo[2] = nameTextField.getText();
            userInfo[3] = pwTextField.getText();
            userInfo[4] = phoneNumberTextField.getText();

            if (userInfo[1].trim().length() == 0 && userInfo[2].trim().length() == 0 &&
                    userInfo[3].trim().length() == 0 && userInfo[4].trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "\"전부 입력해주세요.\"",
                        "회원가입 오류", JOptionPane.WARNING_MESSAGE);
            } else {
                addUser(currentUsers);
                JOptionPane.showMessageDialog(null, "\"회원가입 성공.\"",
                        "회원가입", JOptionPane.WARNING_MESSAGE);
                setVisible(false);
                System.out.println("감사합니다");
            }
        });
        jPanelForRegisterBtn.add(ConfirmRegisterBtn);
    }

    private void createBackBtn(Container c, JPanel jPanelForBackBtn) {
        c.add(jPanelForBackBtn);
        JButton backBtn = new JButton("뒤로가기");
        backBtn.addActionListener(e -> {
            System.out.println("Back Button Clicked");
            setVisible(false);
            getContentPane().getParent().setVisible(true);
        });
        jPanelForBackBtn.add(backBtn);
    }

    private void createAdminCheckBox(Container c, JPanel jPanelForAdmin) {
        c.add(jPanelForAdmin);
        JCheckBox positionCheckBox = new JCheckBox("관리자 여부 체크");
        positionCheckBox.addItemListener(new positionCheckBoxItemListener());
        jPanelForAdmin.add(positionCheckBox);
    }

    class positionCheckBoxItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                userInfo[0] = "1";
            } else {
                userInfo[0] = "0";
            }
            System.out.println(userInfo[0]);
            System.out.println(nameTextField.getText());
        }
    }

    public void addUser(List<User> users) {
        users.add(new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4]));
    }
}
