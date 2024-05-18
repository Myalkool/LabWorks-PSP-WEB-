<%--
  Created by IntelliJ IDEA.
  User: nikak
  Date: 28.03.2024
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>

<header class="bg-dark p-5">
    <h3 class="text-center text-light"> employee management system</h3>
</header>


<div class="w-50 mx-auto mt-sm-4 mt-md-5">
    <a href="/RIS_Lab3_1-1.0-SNAPSHOT/index" class="link-dark">Back</a>
</div>

<form  class="w-50 mx-auto mt-sm-4 mt-md-5" id="form" action="update" method="post">

    <c:if test="${not empty param}">
        <div  class="form-group">
            <label>Employee ID</label>
            <input
                    type="number"
                    class="form-control"
                    name="id"
                    value="${employee.getId()}"
                    readonly
            >
        </div>
    </c:if>



    <div class="form-group">
        <label>First Name</label>
        <input type="text"
               name="first"
               class="form-control"
               placeholder="Enter name"
               value="${employee.getFirstName()}"
               required
        >
    </div>

    <div class="form-group">
        <label>Last Name</label>
        <input type="text"
               name="last"
               class="form-control"
               placeholder="Enter surname"
               value="${employee.getLastName()}"
               required
        >
    </div>

    <div class="form-group">
        <label>Salary</label>
        <input type="number"
               name="salary"
               step="0.01" min="0" value="${employee.getSalary()}" required
               class="form-control"
               placeholder="Enter salary"
        >
    </div>


    <div class="form-group">
        <label>Choose department</label>
        <select name="department"
                class="form-select form-select-sm"
                aria-label=".form-select-sm example"
                required
        >
            <c:forEach var="department" items="${departments}">
                <option value="${department.getId()}"

                        <c:if test="${not empty departmentId and department.getId() eq departmentId}">
                            selected
                        </c:if>
                >
                        <c:out value="${department.getName()}" />
                </option>
            </c:forEach>

        </select>

    </div>

    <button type="submit"  class=" mt-sm-4 mt-md-5 btn btn-dark">Save</button>

    <c:if test="${not empty param}">
        <button class="mt-sm-4 mt-md-5 btn btn-dark"
                onclick="window.location.href = '/RIS_Lab3_1-1.0-SNAPSHOT/delete?id=${employee.getId()}';">
            Delete
        </button>
    </c:if>



</form>



</body>
</html>
