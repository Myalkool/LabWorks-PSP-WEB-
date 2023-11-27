import GUI.Form;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       Form f = new Form();
        f.setContentPane(f.MainPanel);
        f.setTitle("Hospital");
        f.setSize(1000, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}