<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Dashboard</title>
    <s:url value="/static/css/style.css" var="url_css"/>
    <link href="${url_css}" rel="stylesheet" type="text/css" media="all"/>
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
                <h1>Admin Dashboard</h1>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
