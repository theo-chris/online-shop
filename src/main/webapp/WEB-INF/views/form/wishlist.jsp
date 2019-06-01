<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" media="screen" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<html>
<head>
    <title>Wishlist</title>
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
<div>
<h1>Wishlist</h1>
</div>
<div>
<a id="ToMainBtn" href="/system/premium/" class="btn btn-default">Premium user view</a>
<p>You must be a premium user.</p>
</div>
</body>
</html>
