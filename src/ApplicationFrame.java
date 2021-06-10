import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() {
        setTitle("sdf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //X누르고 응용프로그램 닫으려면 필요

        setSize(300, 300);
        setVisible(true);

        Container contentPane = getContentPane();
        JButton jb = new JButton("Click");
        contentPane.add(jb);

        JPanel p = new JPanel();
        setContentPane(p);

        //회원가입은 GRID로 만들기
        //9-3강의
    }

    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame();
    }
}
