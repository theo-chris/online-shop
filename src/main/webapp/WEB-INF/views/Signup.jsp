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

<form:form method="POST" commandName="userInfo" action="/signup/add">
   <table>
    <tr>
        <td><form:label path="forenames">Forenames</form:label></td>
        <td><form:input path="forenames" /></td>
        <td><form:errors path="forenames"  cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="lastnames">Last names</form:label></td>
        <td><form:input path="lastnames" /></td>
        <td><form:errors path="lastnames"  cssClass="error" /></td>
    </tr>
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
        <td><form:label path="password2">Password (verification)</form:label></td>
        <td><form:input path="password2" /></td>
        <td><form:errors path="password2"  cssClass="error" /></td>
    </tr>
    <tr>
    		<td><form:label path="userType">User type</form:label></td>
    		<td>
    		<form:select path="userType">
   			<c:forEach var="userType" items="${userTypeValues}">
    				<option value="${userType}">${userType}</option>
			</c:forEach>
		</form:select>
		</td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Add" name="add" class="btn btn-default"/>
        </td>
        <td colspan="2">
            <input type="submit" value="Cancel" name="cancel" class="btn btn-default"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>
