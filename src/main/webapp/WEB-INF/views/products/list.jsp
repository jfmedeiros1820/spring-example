<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Books of Java, Android, iPhone, Ruby, PHP and much more - Code House</title>
	
	<c:url value="/resources/css" var="cssPath" />
	<link rel="stylesheet" href="${cssPath}/bootstrap.min.css">
	<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css">
</head>
<body>
	
	<nav class="navbar navbar-inverse">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="${s:mvcUrl('HC#index').build()}">Code House</a>
	    </div>
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li><a href="${s:mvcUrl('PC#list').build()}">Products List</a></li>
	        <li><a href="${s:mvcUrl('PC#form').build()}">Create Products</a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
			<li><a href="#">
		        <security:authentication property="principal" var="user"/>
		        User: ${user.username}
		    </a></li>
		  </ul>
	    </div><!-- /.navbar-collapse -->
	  </div>
	</nav>

	<div class="container">	
		<h1>Products List</h1>
		
		<div>${success}</div>
		<div>${fail}</div>
		
		<table class="table table-bordered table-striped table-hover">
	        <tr>
	            <th>Title</th>
	            <th>Description</th>
	            <th>Prices</th>
	            <th>Pages</th>
	        </tr>
	        <c:forEach items="${products}" var="product">
		        <tr>
		            <td><a href="${s:mvcUrl('PC#detail').arg(0, product.id).build()}">${product.title}</a></td>
		            <td>${product.description}</td>
		            <td>${product.prices }</td>
		            <td>${product.pages}</td>
		        </tr>
		    </c:forEach>
	    </table>
	</div>
</body>
</html>