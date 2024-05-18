<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Овес лаб2</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>

<body>
    <header>
        <h2>Жд билетики</h2>
    </header>

    <div>

        <form id="priceForm" action="hello-servlet" method="get">
            <label>Фильтр по цене:</label>
            <br/>
            <input placeholder="введите цену билета" type="number"  value="${param.price}" name="price" min="0" step="0.01">
            <input type="submit" value="применить" >
        </form>

        <c:choose>
            <c:when test="${empty tickets}">
                <h3>Билеты с ценой до ${param.price} не найдены</h3>
            </c:when>
            <c:otherwise>


                <div id="checkboxContainer" class="filters">
                    <label><input type="checkbox" onchange="toggleColumn(0)" checked>Номер поезда</label>
                    <label><input type="checkbox" onchange="toggleColumn(1)" checked>Цена билета </label>
                    <label><input type="checkbox" onchange="toggleColumn(2)" checked>Тип билетаа </label>
                    <label><input type="checkbox" onchange="toggleColumn(3)" checked>Класс билета</label>
                </div>





                <table id="myTable">

                    <thead>
                    <tr>
                        <th> Номер поезда</th>
                        <th> Цена билета</th>
                        <th> Тип билета</th>
                        <th> Класс билета</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="ticket" items="${tickets}">
                        <tr>
                            <td><c:out value="${ticket.getTrainNumber()}" /></td>
                            <td><c:out value="${ticket.getPrice()}" /></td>
                            <td><c:out value="${ticket.getType()}" /></td>
                            <td><c:out value="${ticket.getTicketClass()}" /></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

            </c:otherwise>
        </c:choose>






        <form id="downloadForm" action="download" method="post">
            <label>Скачайте файл с билетами по выбранному поезду!)</label>
            <br/>
            <select name="trainNumber" required>
                <c:forEach var="train" items="${trains}">
                    <option value="${train}">Поезд ${train}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Скачать билеты">
        </form>

    </div>
    <script>
        function toggleColumn(columnIndex) {
            var table = document.getElementById("myTable");
            var rows = table.rows;

            for (var i = 0; i < rows.length; i++) {
                var cells = rows[i].cells;
                if (cells.length > columnIndex) {
                    cells[columnIndex].classList.toggle("hidden");
                }
            }
        }
    </script>



</body>
</html>
