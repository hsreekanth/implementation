<%@page session="false"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<title>Welcome</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	 <security:authorize access="isAuthenticated()">
    <h3 style="color: red;">Hello <security:authentication property="principal.username" /> </h3>
</security:authorize>     
	
	
</body>
</html>