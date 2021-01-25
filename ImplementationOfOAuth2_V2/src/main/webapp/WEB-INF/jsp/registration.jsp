<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register User</title>
</head>
<body>
<center>
	<h3 style="color: red;">Register New User</h3>

	<div id="registerEmployee">
		<form:form action="/register" method="post" modelAttribute="user">
			<p>
				<label>Username</label>
				<form:input path="username" />
			</p>
			<p>
				<label>Password</label>
				<form:input path="password" />
			</p>
			<p>
				<label>Role</label>
				<form:input path="role" />
			</p>
			<input type="SUBMIT" value="Submit" />
		</form:form>
	</div>
	</center>
</body>
</html>
