<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" media="screen" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<html>
<head>
    <title>Login</title>
    	<style>
	.error {
		color: #ff0000;
	}
	
	.errorblock{
		color: #000;
		background-color: #ffEEEE;
		border: 3px solid #ff0000;
		padding:8px;
		margin:16px;
	}
	</style>    
</head>
<body>
<h1>Login</h1>
<form:form method="POST" commandName="userInfoLogin" action="/login">
   <table>
    <tr>
        <td><form:label path="login">Login</form:label></td>
        <td><form:input path="login" /></td>
        <td><form:errors path="login"  cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:input path="password" /></td>
        <td><form:errors path="password"  cssClass="error" /></td>
    </tr>
    <tr>
        <td colspan="1">
        		<input type="hidden"                        
				name="${_csrf.parameterName}"
				value="${_csrf.token}"/>
            <input type="submit" value="Accept" name="accept" class="btn btn-default"/>
        </td>
        <td colspan="1">
            <input type="submit" value="Cancel" name="cancel" class="btn btn-default"/>
        </td>
    </tr>
</table>  
<a href="/signup/" class="btn btn-default">Not a user yet? Sign up</a>
</form:form>
</body>
</html>
