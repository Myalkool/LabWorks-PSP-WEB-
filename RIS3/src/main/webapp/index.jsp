<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body class="container-fluid">

    <header class="bg-dark p-5">
      <h3 class="text-center text-light"> employee management system</h3>
    </header>


    <div class="w-75 mx-auto mt-sm-4 mt-md-5">
      <form id="form" action="index" method="get" class="d-flex ">

        <select name="departmentId"
                class="form-select form-select-sm w-25 m-md-3"
                aria-label=".form-select-sm example"
        >
          <option value="">
            <c:out value="all entries" />
          </option>

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

        <button  class="btn btn-dark  m-md-3"  type="submit">select</button>
      </form>

    </div>

    <c:choose>
      <c:when test="${empty employees}">
        <h3 class=" w-75 mx-auto mt-sm-4 mt-md-5 ">Entries not found</h3>
      </c:when>
      <c:otherwise>

        <table class="table table-hover w-75 mx-auto mt-sm-4 mt-md-5">
          <tr border="1px solid black">
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Salary</th>
            <th>Department</th>
            <th></th>
          </tr>
          <c:forEach var="employee" items="${employees}">

            <tr border="1px solid black">
              <td><c:out value="${employee.getId()}" /></td>
              <td><c:out value="${employee.getFirstName()}" /></td>
              <td><c:out value="${employee.getLastName()}" /></td>
              <td><c:out value="${employee.getSalary()}" /></td>
              <td><c:out value="${employee.getDepartment().getName()}" /></td>
              <td>
                <button  class="btn btn-dark"
                         onclick="window.location.href = '/RIS_Lab3_1-1.0-SNAPSHOT/update?id=${employee.getId()}';" >
                  >
                </button>
              </td>
            </tr>

          </c:forEach>

        </table>

      </c:otherwise>
    </c:choose>


  <div class="w-75 mx-auto mt-sm-4 mt-md-5">
    <button  class="btn btn-dark" onclick="window.location.href = '/RIS_Lab3_1-1.0-SNAPSHOT/update';">
      Create
    </button>
  </div>


</body>
</html>