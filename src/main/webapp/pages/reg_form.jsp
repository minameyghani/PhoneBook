<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration</title>
    <s:url value="/static/css/style.css" var="url_css"/>
    <link href="${url_css}" rel="stylesheet" type="text/css" media="all"/>
    <s:url var="url_jqlib" value="/static/js/jquery-3.6.0.min.js"/>
    <script src="${url_jqlib}"></script>
    <script>
        $(document).ready(function () {
            $("#id_checkName").click(function () {

                $.ajax({
                    url:'check_available',
                    data:{username:$("#id_username").val()} ,
                    success:function (data) {
                        $("#id_result_div").html(data);
                    }
                });
            });
        });
    </script>
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
            <h3>User Registration</h3>
            <c:if test="${err != null}">
                <p class="error">${err}</p>
            </c:if>
            <s:url value="register" var="url_reg"/>
            <f:form action="${url_reg}" modelAttribute="command">
                <table border="1">
                    <tr>
                        <td>Name</td>
                        <td><f:input path="user.name"/></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><f:input path="user.phone"/></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><f:input path="user.email"/></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><f:input path="user.address.city"/></td>
                    </tr>
                    <tr>
                        <td>Province</td>
                        <td><f:input path="user.address.province"/></td>
                    </tr>
                    <tr>
                        <td>Street</td>
                        <td><f:input path="user.address.street"/></td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td><f:input id="id_username" path="user.loginName"/>
                            <button type="button" class="button-24" id="id_checkName">Check Availability</button>
                            <div id="id_result_div" class="error">

                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><f:password path="user.password"/></td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center">
                            <button class="button-24">submit</button>
                            <br/>
                            <a href="/index">login</a>
                        </td>
                    </tr>

                </table>
            </f:form>
        </td>
    </tr>

</table>
</body>
</html>
