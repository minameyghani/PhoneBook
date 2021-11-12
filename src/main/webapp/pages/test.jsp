<%--
  Created by IntelliJ IDEA.
  User: Mina_Hosseyni
  Date: 11/12/2021
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <s:url var ="url_jqlib" value="/static/js/jquery-3.6.0.min.js"/>
    <script src="${url_jqlib}"></script>
    <script>
        $(document).ready(function () {
            alert('JQuery is ready');
        });
    </script>
    <title>AJAX Test Page</title>
</head>
<body>

</body>
</html>
