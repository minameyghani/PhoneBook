<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>


<s:url value="/logout" var="url_logout"/>
<s:url value="/reg_form" var="url_reg_form"/>
<c:if test="${sessionScope.userId == null}">
    <a href="#">Home</a> | <a href="#">Login</a> | <a href="${url_reg_form}">Register</a>
</c:if>
<c:if test="${sessionScope.userId =! null && sessionScope.role == 1}">
    <%-- admin : admin menu --%>
    <s:url value="/admin/users" var="url_users"/>
    <a href="#">Home</a> | <a href="${url_users}">User list</a> | <a href="${url_logout}">Logout</a>
</c:if>
<c:if test="${sessionScope.userId =! null && sessionScope.role == 2}">
    <s:url value="/user/dashboard" var="url_uhome"/>
    <s:url value="/user/contact_form" var="url_cform"/>
    <s:url value="/user/contactList" var="url_cList"/>
    <a href="${url_uhome}">Home</a> | <a href="${url_cList}">Contact List</a>|<a href="${url_cform}">Add Contact</a> | <a
        href="${url_logout}">Logout</a>
</c:if>

