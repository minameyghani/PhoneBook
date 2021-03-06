<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Login</title>
    <s:url value="/static/css/style.css" var="url_css"/>
    <link href="${url_css}" rel="stylesheet" type="text/css" media="all"/>

</head>
<s:url var="url_bag" value="/static/images/g4.jpg"/>

<body background="${url_bag}">
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
            <h3>Login Form</h3>
            <c:if test="${err != null}">
                <p class="error">${err}</p>
            </c:if>
            <c:if test="${param.act eq 'lo'}">
                <p class="success">Logout successfully:) Thanks for using app</p>
            </c:if>
            <c:if test="${param.act eq 'reg'}">
                <p class="success">User registered successfully.Please login</p>
            </c:if>
            <s:url value="login" var="url_login"/>
            <f:form action="${url_login}" modelAttribute="command">
                <table border="1">
                    <tr>
                        <td>username</td>
                        <td><f:input path="loginName"/></td>
                    </tr>
                    <tr>
                        <td>password</td>
                        <td><f:password path="password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <button class="button-24">Login</button>
                            <br/>
                            <a href="/reg_form">New User Registration</a>
                        </td>
                    </tr>

                </table>
            </f:form>
        </td>
    </tr>

</table>
</body>
</html>