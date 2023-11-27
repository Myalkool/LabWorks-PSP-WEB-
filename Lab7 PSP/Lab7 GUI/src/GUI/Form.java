package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Form extends JFrame{
    public JPanel MainPanel;
    private JLabel firstName;
    private JLabel secondName;
    private JLabel lastName;
    private JTextField SN;
    private JTextField LN;
    private JTextField FN;
    private JLabel dateOfBirth;
    private JComboBox day;
    private JComboBox month;
    private JComboBox year;
    private JTextField diagnos;
    private JLabel diagnosis;
    private JButton addPatBtn;
    private JButton showPatBtn;
    private JTextField spec;
    private JButton addDocBtn;
    private JButton showDocBtn;
    private JTextField textState;

    public Form(){
        showPatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Database to JTable");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Создание модели таблицы
                DefaultTableModel model = new DefaultTableModel();
                JTable table = new JTable(model);

                // Добавление таблицы на панель прокрутки
                JScrollPane scrollPane = new JScrollPane(table);
                frame.add(scrollPane);

                try {
                    // Подключение к базе данных
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?useUnicode=true&serverTimezone=UTC", "root", "3787");

                    // Выполнение запроса на получение данных
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM patient");

                    // Получение метаданных для определения количества столбцов и их имен
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    // Добавление имен столбцов в модель таблицы
                    for (int column = 1; column <= columnCount; column++) {
                        model.addColumn(metaData.getColumnName(column));
                    }

                    // Добавление данных в модель таблицы
                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int column = 1; column <= columnCount; column++) {
                            row[column - 1] = resultSet.getObject(column);
                        }
                        model.addRow(row);
                    }

                    // Закрытие соединения с базой данных
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Отображение фрейма с таблицей
                frame.pack();
                frame.setVisible(true);
            }
        });
        addPatBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String data1 = FN.getText();
                String data2 = SN.getText();
                String data3 = LN.getText();
                String data4 = diagnos.getText();
                String selectedItem1 = (String) day.getSelectedItem();
                String selectedItem2 = (String) month.getSelectedItem();
                String selectedItem3 = (String) year.getSelectedItem();

                // Подключение к базе данных MySQL
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?useUnicode=true&serverTimezone=UTC", "root", "3787");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                // Вставка данных в таблицу
                String query = "INSERT INTO patient (PatientID, First_name, Second_name, Last_name, Birth_date, Diagnosis) VALUES (NULL, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = connection.prepareStatement(query);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    preparedStatement.setString(1, data1);
                    preparedStatement.setString(2, data2);
                    preparedStatement.setString(3, data3);
                    preparedStatement.setString(4, selectedItem3 + '-' + selectedItem2 + '-' + selectedItem1);
                    preparedStatement.setString(5, data4);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                // Закрытие соединения с базой данных
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        addDocBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data1 = FN.getText();
                String data2 = SN.getText();
                String data3 = LN.getText();
                String data4 = textState.getText();
                String data5 = spec.getText();
                String selectedItem1 = (String) day.getSelectedItem();
                String selectedItem2 = (String) month.getSelectedItem();
                String selectedItem3 = (String) year.getSelectedItem();

                // Подключение к базе данных MySQL
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?useUnicode=true&serverTimezone=UTC", "root", "3787");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                // Вставка данных в таблицу
                String query = "INSERT INTO doctor (DoctorID, First_name, Second_name, Last_name, Birth_date, State, Specialization) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = connection.prepareStatement(query);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    preparedStatement.setString(1, data1);
                    preparedStatement.setString(2, data2);
                    preparedStatement.setString(3, data3);
                    preparedStatement.setString(4, selectedItem3 + '-' + selectedItem2 + '-' + selectedItem1);
                    preparedStatement.setString(5, data4);
                    preparedStatement.setString(6, data5);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                // Закрытие соединения с базой данных
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        showDocBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Database to JTable");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Создание модели таблицы
                DefaultTableModel model = new DefaultTableModel();
                JTable table = new JTable(model);

                // Добавление таблицы на панель прокрутки
                JScrollPane scrollPane = new JScrollPane(table);
                frame.add(scrollPane);

                try {
                    // Подключение к базе данных
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?useUnicode=true&serverTimezone=UTC", "root", "3787");

                    // Выполнение запроса на получение данных
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM doctor");

                    // Получение метаданных для определения количества столбцов и их имен
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    // Добавление имен столбцов в модель таблицы
                    for (int column = 1; column <= columnCount; column++) {
                        model.addColumn(metaData.getColumnName(column));
                    }

                    // Добавление данных в модель таблицы
                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int column = 1; column <= columnCount; column++) {
                            row[column - 1] = resultSet.getObject(column);
                        }
                        model.addRow(row);
                    }

                    // Закрытие соединения с базой данных
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Отображение фрейма с таблицей
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
