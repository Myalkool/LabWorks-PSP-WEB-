package Second;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Library extends JFrame {
    JFrame f;
    JScrollPane scrolltxt;
    int k;
    Object boxC;
    File file = new File("C://172301//3 курс//5 сем//ПСП//Лаба 5//Registration.txt");
    static JLabel l1, l2, l3, l4, l5;
    JComboBox box_3;
    JRadioButton flag1, flag2;
    ButtonGroup bg;
    static JButton b, del;
    static JTextField author;
    static JTextField text;
    static JTextArea area;
    static String[] box3 = {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023"};

    public Library(String str) {
        super(str);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b = new JButton("Внесение в базу");
        del = new JButton("Очистить");
        text = new JTextField(9);
        author = new JTextField(9);
        area = new JTextArea(9, 9);
        l1 = new JLabel("Название книги");
        l2 = new JLabel("Описание сюжета");
        l3 = new JLabel("Дата выпуска");
        l4 = new JLabel("Есть физический вариант");
        l5 = new JLabel("Автор");

        box_3 = new JComboBox<Object>(box3);
        flag1 = new JRadioButton("да");
        flag2 = new JRadioButton("нет");
        bg = new ButtonGroup();
        bg.add(flag1);
        bg.add(flag2);

        setLayout(null);
        b.setSize(200, 30);
        b.setLocation(150, 400);
        del.setSize(100, 30);
        del.setLocation(10, 400);
        text.setSize(200, 25);
        text.setLocation(250, 20);
        author.setSize(200, 25);
        author.setLocation(250, 70);
        area.setSize(200, 150);
        area.setLocation(250, 110);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        l1.setSize(200, 25);
        l1.setLocation(30, 20);
        l2.setSize(220, 25);
        l2.setLocation(30, 110);
        l3.setSize(200, 25);
        l3.setLocation(30, 280);
        l4.setSize(200, 25);
        l4.setLocation(30, 320);
        l5.setSize(200, 25);
        l5.setLocation(30, 70);

        box_3.setSize(70, 25);
        box_3.setLocation(250, 280);
        flag1.setSize(40, 25);
        flag1.setLocation(250, 320);
        flag2.setSize(50, 25);
        flag2.setLocation(300, 320);
        add(b);
        add(del);
        add(text);
        add(author);
        add(area);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(box_3);
        add(flag1);
        add(flag2);

        b.addActionListener(new ButtonActionListener());
        flag1.addActionListener(new FlagActionListener());
        flag2.addActionListener(new FlagActionListener());
        del.addActionListener(new DelActionListener());

        box_3.addActionListener(new BoxActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter out = new FileWriter(file, true);
                try {
                    String text1 = text.getText();
                    String author1 = author.getText();
                    String area2 = area.getText();
                    out.write(text1 + " - ");
                    out.write(author1 + "\n");
                    out.write(" Название книги:" + text1 + "\n");
                    out.write(" Автор книги:" + author1 + "\n");
                    out.write(" Краткое описание:" + area2 + "\n");
                    if (k == 1)
                        out.write("Физический экземпляр имеется в наличии. " + "\n");
                    else if (k == -1)
                        out.write("Только электронный вариант. " + "\n");
                    out.write(" Дата поступления:" + boxC);
                } finally {
                    out.close();
                }
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    public class FlagActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            k = 0;
            if (e.getSource() == flag1) {
                k++;
            } else if (e.getSource() == flag2) {
                k--;
            }
        }
    }

    public class DelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == del) {
                text.setText(null);
                author.setText(null);
                area.setText(null);

            }
        }
    }

    public class BoxActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == box_3) {
                boxC = box_3.getSelectedItem();

            }
        }
    }
}
