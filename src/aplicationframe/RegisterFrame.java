package aplicationframe;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        setTitle("Register Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(4,2);
        grid.setVgap(5);

        Container c = getContentPane();
        c.setLayout(grid);

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        c.add(new JLabel("이름"));
        c.add(new JTextField("이건?"));
        c.add(new JLabel("ID"));
        c.add(new JTextField("이건?"));
        c.add(new JLabel("PASSWORD"));
        c.add(new JTextField("이건?"));
        c.add(new JLabel("전화번호"));
        c.add(new JTextField("이건?"));

        c.add(jPanel);
        jPanel.add(new JButton("회원가입"));

        setSize(600, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        RegisterFrame registerFrame = new RegisterFrame();
    }
}
