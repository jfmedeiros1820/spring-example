<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate>
	<article id="${product.id}">
		<header id="product-highlight" class="clearfix">
		    <div id="product-overview" class="container">
				<img width="280px" height="395px" src="http://cdn.shopify.com/s/files/1/0155/7645/products/css-eficiente-featured_large.png?v=1435245145" class="product-featured-image" />
		      	
		      	<h1 class="product-title">${product.title}</h1>
		      	<p class="product-author">
		        	<span class="product-author-link"></span>
		      	</p>
			
		    	<p class="book-description">${product.description}</p>
		    </div>
		</header>
	  
		<section class="buy-options clearfix">  
			<form:form servletRelativeAction="/cart/add" method="post" class="container">
				<input type="hidden" value="${product.id}" name="productId" />
		    	<ul id="variants" class="clearfix">
					<c:forEach items="${product.prices}" var="price">
						<li class="buy-option">
							<input type="radio" name="priceType" class="variant-radio" id="priceType" value="${price.priceType}" checked/>
						    <label class="variant-label">
							    ${price.priceType}
							</label>
						    <small class="compare-at-price">R$ 39,90</small>
						    <p class="variant-price">${price.value}</p>
						</li> 
					</c:forEach>
			    </ul>
			    <button type="submit" class="submit-image icon-basket-alt" title="Buy Now ${product.title}"></button>
		  	</form:form>
		</section>
		<div class="container">
		  <section class="summary">
		    <ul>
		      	<li><h3>And much more... <a href='/pages/sumario-java8'>see summary</a>.</h3></li>
		    </ul>
		  </section>
	
		  <section class="data product-detail">
		    <h2 class="section-title">Book Information:</h2>
		    <p>Page number: <span>${product.pages}</span></p>
		    <p></p>
		    <p>Launch date: ${product.launch}</p>
		    <p>Found a bug? <a href='/submissao-errata' target='_blank'>Submit an error</a></p>
		  </section>
		</div>
	</article>
</tags:pageTemplate>