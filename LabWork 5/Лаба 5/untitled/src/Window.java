import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window(){
        super("Пробное окно");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JButton button = new JButton("Кнопка1");
        button.setSize(100, 50);
        button.setLocation(40,20);
        button.setForeground(Color.PINK);
        panel.add(button);
        button = new JButton("Кнопка2");
        button.setSize(120, 40);
        button.setLocation(150,50);
        panel.add(button);
        setContentPane(panel);
        setSize(500, 500);
    }
}

