<%--
  Created by IntelliJ IDEA.
  User: Mina_Hosseyni
  Date: 11/11/2021
  Time: 10:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users</title>
    <s:url value="/static/css/style.css" var="url_css"/>
    <link href="${url_css}" rel="stylesheet" type="text/css" media="all"/>
    <s:url var="url_jqlib" value="/static/js/jquery-3.6.0.min.js"/>
    <script src="${url_jqlib}"></script>
    <script>
        function changeStatus(userId, loginStatus) {
            $.ajax({
                url: 'change_status',
                data: {userId: userId, loginStatus: loginStatus},
                success: function (data) {
                    alert(data);
                }
            });
        }
    </script>
</head>
<s:url var="url_bag3" value="/static/images/g4.jpg"/>
<body background="${url_bag3}">
<div class="dashboard">
    <table border="1" width="80%" align="center">
        <tr>
            <td height="80px">
                <jsp:include page="include/header.jsp"/>
            </td>
        </tr>
        <tr>
            <td height="25px">
                <jsp:include page="include/menu.jsp"/>
            </td>
        </tr>
        <tr>
            <td height="400px" valign="top" align="center">
                <h1>User List</h1>
                <table border="1">
                    <tr>
                        <th>SR</th>
                        <th>USER ID</th>
                        <th>NAME</th>
                        <th>USERNAME</th>
                        <th>PHONE</th>
                        <th>EMAIL</th>
                        <th>CITY</th>
                        <th>PROVINCE</th>
                        <th>STREET</th>
                        <th>STATUS</th>
                    </tr>
                    <c:forEach var="u" items="${userList}" varStatus="st">
                        <tr>
                            <td>${st.count}</td>
                            <td>${u.id}</td>
                            <td>${u.name}</td>
                            <td>${u.loginName}</td>
                            <td>${u.phone}</td>
                            <td>${u.email}</td>
                            <td>${u.address.city}</td>
                            <td>${u.address.province}</td>
                            <td>${u.address.street}</td>
                            <td>
                                <select id="id_${u.id}" onchange="changeStatus(${u.id}, $(this).val())">
                                    <option class="success" value="1">Active</option>
                                    <option class="error" value="2">Block</option>
                                </select>
                                <script>
                                    $('#id_${u.id}').val(${u.loginStatus});
                                </script>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>

