<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<c:url value="/" var="contextPath" />
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
	<link rel="icon" href="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/favicon.ico?11981592617154272979" type="image/ico" />
	<link href="https://plus.googlecom/108540024862647200608" rel="publisher"/>
	<title>${product.title} - House Code</title>
	<link href="${contextPath}resources/css/cssbase-min.css" rel="stylesheet" type="text/css" media="all" />
	<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'/>
	<link href="${contextPath}resources/css/fonts.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/fontello-ie7.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/fontello-embedded.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/fontello.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/layout-colors.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/responsive-style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/guia-do-programador-style.css"  rel="stylesheet" type="text/css"  media="all"  />
    <link href="${contextPath}resources/css/produtos.css" rel="stylesheet" type="text/css"  media="all"  />
	<link rel="canonical" href="http://www.casadocodigo.com.br/" />	
</head>
<body class="product">
  <header id="layout-header">
	<div class="clearfix container">
		<a href="${s:mvcUrl('HC#index').build()}" id="logo"></a>
		<div id="header-content">
			<nav id="main-nav">
				<ul class="clearfix">
					<security:authorize access="isAuthenticated()">
						<li><a href="${s:mvcUrl('PC#list').build()}" rel="nofollow">List of Products</a></li>
						<li><a href="${s:mvcUrl('PC#form').build()}" rel="nofollow">Create Products</a></li>
					</security:authorize>
					<li><a href="${s:mvcUrl('CC#itens').build()}" rel="nofollow">Cart (${cart.quantity})</a></li>
					<li><a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">About Us</a></li>
					<li><a href="/pages/perguntas-frequentes" rel="nofollow">Frequent Questions</a></li>
				</ul>
			</nav>
		</div>
	</div>
	</header>
	<nav class="categories-nav">
		<ul class="container">
			<li class="category"><a href="http://www.casadocodigo.com.br">Home</a></li>
			<li class="category"><a href="/collections/livros-de-agile">Agile </a></li>
			<li class="category"><a href="/collections/livros-de-front-end">Front End </a></li>
			<li class="category"><a href="/collections/livros-de-games">Games </a></li>
			<li class="category"><a href="/collections/livros-de-java">Java </a></li>
			<li class="category"><a href="/collections/livros-de-mobile">Mobile </a></li>
			<li class="category"><a href="/collections/livros-desenvolvimento-web"> Web </a></li>
			<li class="category"><a href="/collections/outros"> Others </a></li>
		</ul>
	</nav>

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

	<footer id="layout-footer">
		<div class="clearfix container">
			<div id="collections-footer">
				<!-- cdc-footer -->
				<p class="footer-title">Programming Collections</p>
				<ul class="footer-text-links">
					<li><a href="/collections/livros-de-java">Java</a></li>
					<li><a href="/collections/livros-desenvolvimento-web">Web Development</a></li>
					<li><a href="/collections/livros-de-mobile">Mobile</a></li>
					<li><a href="/collections/games">Games</a></li>
					<li><a href="/collections/livros-de-front-end">Front End</a></li>
				</ul>
				<p class="footer-title">Other Subjects</p>
				<ul class="footer-text-links">
					<li><a href="/collections/livros-de-agile">Agile</a></li>
					<li><a href="/collections/outros">and others...</a></li>
				</ul>
			</div>
			<div id="social-footer">
				<!-- books-footer -->
				<p class="footer-title">Links of House Code</p>
				<ul class="footer-text-links">
					<li><a href="http://livros.casadocodigo.com.br" rel="nofollow">My E-books</a></li>
					<li><a href="/pages/sobre-a-casa-do-codigo">About House Code</a></li>
					<li><a href="/pages/perguntas-frequentes">Frequent Questions</a></li>
					<li><a href="https://www.caelum.com.br">Caelum</a></li>
					<li><a href="http://www.codecrushing.com/" rel="nofollow">Code Crushing</a></li>
					<li><a href="http://www.casadocodigo.com.br/pages/politica-de-privacidade" rel="nofollow">Privacy Policy</a></li>
				</ul>
				<p class="footer-title">Social Networks</p>
				<ul>
					<li class="social-links">
						<a href="http://www.twitter.com/casadocodigo" target="_blank" id="twitter" rel="nofollow">Facebook</a>
						<a href="http://www.facebook.com/casadocodigo" target="_blank" id="facebook" rel="nofollow">Twitter</a>
					</li>
				</ul>
			</div>
			<div id="newsletter-footer">
				<!-- social-footer -->
				<p class="footer-title">Receive News and New Releases</p>
				<div id="form-newsletter">
					<form action="" method="POST" id="ss-form" class="form-newsletter">
						<ul>
							<li>
								<input type="hidden" name="pageNumber" value="0"/>
								<input type="hidden" name="backupCache" value=""/>
								<input type="email" name="entry.0.single" value="" class="ss-q-short" id="entry_0" placeholder="youremail@email.com"/>
							</li>
							<li>
								<input type="submit" name="submit" value="I wanna receive!" id="submit-newsletter"/>
							</li>
						</ul>
					</form>
				</div>
				<ul class="footer-payments">
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>
	</footer>
</body>
</html>