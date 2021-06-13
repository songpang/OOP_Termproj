package aplicationframe;

import domain.Counter;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class MenuFrame extends JFrame{
    private JLabel[] menuLabels;

    public MenuFrame(Map<String, Integer> menu) {
        setTitle("Menu");

        GridLayout grid = new GridLayout(12, 1, 30, 100);
        grid.setVgap(5);
        Container c = getContentPane();

        JLabel nameOfMenu = new JLabel("*** 메뉴판 ***");
        nameOfMenu.setHorizontalAlignment(SwingConstants.CENTER);
        c.add(nameOfMenu);

        int menuCount = menu.size();
        menuLabels = new JLabel[menuCount];
        int i = 0;
        for (String s : menu.keySet()) {
            menuLabels[i] = new JLabel(s + " : " + menu.get(s));
            menuLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            c.add(menuLabels[i]);
            i++;
        }

        c.setLayout(grid);

        setSize(300, 600);
        setVisible(true);
    }
}

