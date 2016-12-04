<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Books of Java, Android, iPhone, Ruby, PHP and much more - Code House</title>
</head>
<body>
	<form:form action="${s:mvcUrl('PC#save').build()}" method="post" commandName="product" enctype="multipart/form-data">
	    <div>
	        <label>Title</label>
	        <form:input path="title" />
	        <form:errors path="title" />
	    </div>
	    <div>
	        <label>Description</label>
	        <form:textarea rows="10" cols="20" path="description"></form:textarea>
	        <form:errors path="description" />
	    </div>
	    <div>
	        <label>Pages</label>
	        <form:input path="pages" />
	        <form:errors path="pages" />
	    </div>
	    <div>
		    <label>Launching Date</label>
		    <form:input path="launch" />
		    <form:errors path="launch" />
		</div>

	    <c:forEach items="${types}" var="priceType" varStatus="status">
	        <div>
	            <label>${priceType}</label>
	            <form:input path="prices[${status.index}].value" />
	            <form:hidden path="prices[${status.index}].priceType" value="${priceType}" />
	        </div>
	    </c:forEach>
	    
	    <div>
		    <label>Summary</label>
		    <input name="summary" type="file" />
		</div>

	    <button type="submit">Save</button>
	</form:form>
</body>
</html>