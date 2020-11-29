<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Verification</title>
</head>
<body>

	<f:form action="/otpVerification" modelAttribute="otpCode">
		<f:hidden path="requestId"/>
		
		<f:input path="code" />
		
		<input type="submit" value="Verify" name="verify" />
		
	</f:form>

</body>
</html>