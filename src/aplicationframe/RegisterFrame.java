package aplicationframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RegisterFrame extends JFrame {
    private int isAdmin = 0;
    private String[] userInfo;

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

            userInfo = new String[4];
            userInfo[0] = idTextField.getText();
            userInfo[1] = nameTextField.getText();
            userInfo[2] = pwTextField.getText();
            userInfo[3] = phoneNumberTextField.getText();

            if (userInfo[0].trim().length() == 0 && userInfo[1].trim().length() == 0 &&
                    userInfo[2].trim().length() == 0 && userInfo[3].trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "\"전부 입력해주세요.\"",
                        "회원가입 오류", JOptionPane.WARNING_MESSAGE);
            } else {
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

//            new LoginFrame();
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
                isAdmin = 1;
            } else {
                isAdmin = 0;
            }
            System.out.println(isAdmin);
            System.out.println(nameTextField.getText());
        }
    }

    public static void main(String[] args) {
        RegisterFrame registerFrame = new RegisterFrame();
    }
}
