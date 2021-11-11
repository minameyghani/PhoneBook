<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Contact List</title>
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
            <h3>Contact List</h3>
            <c:if test="${err != null}">
                <p class="error">${err}</p>
            </c:if>
            <c:if test="${param eq 'sv'}">
                <p class="success">Contact saved successfully</p>
            </c:if>
            <c:if test="${param eq 'del'}">
                <p class="success">Contact deleted successfully</p>
            </c:if>
            <c:if test="${param eq 'ed'}">
                <p class="success">Contact edited successfully</p>
            </c:if>
            <form action="<s:url value="/user/contact_search"/>" modelAttribute="command" method="post">
                <input name="name" path="name" placeholder="name" type="text"/>
                <input name="phone" path="phone" placeholder="phone" type="text"/>
                <input name="email" path="email" placeholder="email" type="text"/>
                <button class="button-24">Search</button>
            </form>
            <br/><br/>
            <div id="list">
                <table border="1">
                    <tr>
                        <th>SR</th>
                        <th>CID</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>City</th>
                        <th>Province</th>
                        <th>Street</th>
                        <th>Remark</th>
                        <th>Action</th>
                    </tr>
                    <c:if test="${empty contacts}">
                        <tr>
                            <td colspan="8" class="error" align="center">
                                No records present
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <c:forEach var="c" items="${contacts}" varStatus="st">
                        <td>${st.count}</td>
                        <td>${c.id}</td>
                        <td>${c.name}</td>
                        <td>${c.phone}</td>
                        <td>${c.email}</td>
                        <td>${c.address.city}</td>
                        <td>${c.address.province}</td>
                        <td>${c.address.street}</td>
                        <td>${c.remark}</td>
                        <s:url var="url_del" value="/user/del_contact">
                            <s:param name="cid" value="${c.id}"/>
                        </s:url>
                        <s:url value="/user/edit_contact" var="url_edit">
                            <s:param name="cid" value="${c.id}"/>
                        </s:url>
                        <td><a href="${url_edit}">EDIT</a>|<a href="${url_del}">DELETE</a></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
