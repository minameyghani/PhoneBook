<%--
  Created by IntelliJ IDEA.
  User: Mina_Hosseyni
  Date: 11/9/2021
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Contact Form</title>
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
            <h3>Contact Form</h3>
            <c:if test="${err != null}">
                <p class="error">${err}</p>
            </c:if>
            <s:url value="/user/save_contact" var="url_csave"/>
            <f:form action="${url_csave}" modelAttribute="command">
                <table border="1">
                    <tr>
                        <td>Name</td>
                        <td><f:input path="name"/></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><f:input path="phone"/></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><f:input path="email"/></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><f:input path="address.city"/></td>
                    </tr>
                    <tr>
                        <td>Province</td>
                        <td><f:input path="address.province"/></td>
                    </tr>
                    <tr>
                        <td>Street</td>
                        <td><f:input path="address.street"/></td>
                    </tr>

                    <tr>
                        <td>Remark</td>
                        <td><f:input path="remark"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <button class="button-24">save</button>
                            <br/>
                        </td>
                    </tr>

                </table>
            </f:form>
        </td>
    </tr>

</table>
</body>
</html>

