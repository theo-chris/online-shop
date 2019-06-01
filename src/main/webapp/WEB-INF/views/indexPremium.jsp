<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" media="screen" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
<html>
<head>
    <title>Premium user view</title>
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
<h1>Premium user view</h1>
</div>
<div>
<a id="Orders" href="/order/" class="btn btn-default">Orders</a>
<a id="Wishlist" href="/order/wishlist/" class="btn btn-default">Wishlist</a>
<a id="LogOffBtn" href="/logout" class="btn btn-default">Log off</a>
</div>
<hr/>
</body>
</html>
