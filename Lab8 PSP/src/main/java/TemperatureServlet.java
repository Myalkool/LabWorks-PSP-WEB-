import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TemperatureServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Чтение списка температур из файла
        List<Double> temperatureList = readTemperatureListFromFile("C:\\172301\\3 курс\\5 сем\\ПСП\\Лаба 8\\Lab8.8\\temperatures.txt");

        // Взятие среднемесячной температуры воздуха
        double averageTemperature = calculateAverageTemperature(temperatureList);
        // Взятие количества дней, когда температура была выше среднемесячной
        int daysAboveAverage = countDaysAboveAverage(temperatureList, averageTemperature);

        // Взятие количества дней, когда температура опускалась ниже 0ºC
        int daysBelowZero = countDaysBelowZero(temperatureList);

        // Взятие трех самых теплых дней
        List<Double> warmestDays = getThreeWarmestDays(temperatureList);

        // Установка полученных значений в атрибуты запроса
        request.setAttribute("averageTemperature", averageTemperature);
        request.setAttribute("daysAboveAverage", daysAboveAverage);
        request.setAttribute("daysBelowZero", daysBelowZero);
        request.setAttribute("warmestDays", warmestDays);

        // Передача управления на JSP-страницу для отображения результатов
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    // Метод для чтения списка температур из файла
    private List<Double> readTemperatureListFromFile(String fileName) throws IOException {
        List<Double> temperatureList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            double temperature = Double.parseDouble(line);
            temperatureList.add(temperature);
        }
        reader.close();
        return temperatureList;
    }

    // Метод для расчета среднемесячной температуры
    private double calculateAverageTemperature(List<Double> temperatureList) {
        double sum = 0;
        for (double temperature : temperatureList) {
            sum += temperature;
        }
        return sum / temperatureList.size();
    }

    // Метод для подсчета количества дней, когда температура была выше среднемесячной
    private int countDaysAboveAverage(List<Double> temperatureList, double averageTemperature) {
        int count = 0;
        for (double temperature : temperatureList) {
            if (temperature > averageTemperature) {
                count++;
            }
        }
        return count;
    }

    // Метод для подсчета количества дней, когда температура опускалась ниже 0ºC
    private int countDaysBelowZero(List<Double> temperatureList) {
        int count = 0;
        for (double temperature : temperatureList) {
            if (temperature < 0) {
                count++;
            }
        }
        return count;
    }

    // Метод для получения трех самых теплых дней
    private List<Double> getThreeWarmestDays(List<Double> temperatureList) {
        List<Double> copy = new ArrayList<>(temperatureList);
        Collections.sort(copy, Collections.reverseOrder());
        return copy.subList(0, Math.min(3, copy.size()));
    }
}