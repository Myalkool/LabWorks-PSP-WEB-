package First;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.Stream;
import javax.swing.JScrollPane;

public class MyApp extends JFrame {

    int k = 0;
    JRadioButton ch1, ch2, ch3;
    ButtonGroup bg;
    String[] data1 = {"one", "two", "three", "four"};
    String[] data2 = {"1", "2", "3", "4"};
    String[] data3 = {"один", "два", "три", "четыре"};
    String[] extraData = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] empty = {""};
    String s;

    JList<String> list1 = new JList<String>(data1);
    JList<String> list2 = new JList<String>(data2);
    JList<String> list3 = new JList<String>(data3);
    static JButton del, add, move;

    public MyApp(String str) {
        super(str);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        add = new JButton("Add 10 elements");
        move = new JButton("Move elements");
        del = new JButton("Clear");
        ch1 = new JRadioButton("1");
        ch2 = new JRadioButton("2");
        ch3 = new JRadioButton("3");
        bg = new ButtonGroup();
        bg.add(ch1);
        bg.add(ch2);
        bg.add(ch3);


        setLayout(null);

        move.setSize(300, 50);
        move.setLocation(200, 280);
        add.setSize(300, 50);
        add.setLocation(200, 340);
        del.setSize(300, 50);
        del.setLocation(200, 400);
        list1.setSize(100, 200);
        list1.setLocation(100, 0);
        list2.setSize(100, 200);
        list2.setLocation(300, 0);
        list3.setSize(100, 200);
        list3.setLocation(500, 0);
        ch1.setSize(100, 20);
        ch1.setLocation(130, 220);
        ch2.setSize(100, 20);
        ch2.setLocation(330, 220);
        ch3.setSize(100, 20);
        ch3.setLocation(530, 220);

        JScrollPane scrollPane = new JScrollPane(list2);
        getContentPane().add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(add);
        add(move);
        add(del);
        add(list1);
        add(list2);
        add(list3);
        add(ch1);
        add(ch2);
        add(ch3);

        del.addActionListener(new DelActionListener());
        ch1.addActionListener(new FlagActionListener());
        ch2.addActionListener(new FlagActionListener());
        ch3.addActionListener(new FlagActionListener());
        add.addActionListener(new ButtonActionListener());
        move.addActionListener(new MovingActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] newArr;
            if (k == 1) {
                data1 = Stream.concat(Arrays.stream(data1), Arrays.stream(extraData)).toArray(String[]::new);
                list1.setListData(data1);
            } else if (k == 2) {
                data2 = Stream.concat(Arrays.stream(data2), Arrays.stream(extraData)).toArray(String[]::new);
                list2.setListData(data2);
            } else if (k == 3) {
                data3 = Stream.concat(Arrays.stream(data3), Arrays.stream(extraData)).toArray(String[]::new);
                list3.setListData(data3);
            }
        }
    }

    public class MovingActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] newArr;
            Object[] selected;
            if (k == 1) {
                selected = list1.getSelectedValues();
                data2 = Stream.concat(Arrays.stream(data2), Arrays.stream(selected)).toArray(String[]::new);
                list2.setListData(data2);
            } else if (k == 2) {
                selected = list2.getSelectedValues();
                data3 = Stream.concat(Arrays.stream(data3), Arrays.stream(selected)).toArray(String[]::new);
                list3.setListData(data3);
            } else if (k == 3) {
                selected = list3.getSelectedValues();
                data1 = Stream.concat(Arrays.stream(data1), Arrays.stream(selected)).toArray(String[]::new);
                list1.setListData(data1);
            }
        }
    }

    public class FlagActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            k = 0;
            if (e.getSource() == ch1) {
                k = 1;

            } else if (e.getSource() == ch2) {
                k = 2;
            } else if (e.getSource() == ch3) {
                k = 3;
            }
        }
    }

    public class DelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (k == 1) {
                data1 = empty;
                list1.setListData(data1);
            } else if (k == 2) {
                data2 = empty;
                list2.setListData(empty);
            } else if (k == 3) {
                data3 = empty;
                list3.setListData(empty);
            }
        }
    }
}
