<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результаты</title>
</head>
<body>
<h1>Результаты</h1>
<p>Среднемесячная температура: ${averageTemperature} ºC</p>
<p>Количество дней, когда температура была выше среднемесячной: ${daysAboveAverage}</p>
<p>Количество дней, когда температура опускалась ниже нуля: ${daysBelowZero}</p>
<p>Самые теплые дни: ${warmestDays}</p>
</body>
</html>