<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<c:url value="/" var="contextPath" />
	  <meta charset="utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
		<link rel="icon" href="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/favicon.ico?11981592617154272979" type="image/ico" />
		<link href="https://plus.googlecom/108540024862647200608" rel="publisher"/>
		<title>Books of Java, Android, iPhone, Ruby, PHP and much more - Code House</title>
		<link href="${contextPath}resources/css/cssbase-min.css" rel="stylesheet" type="text/css" media="all" />
		<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'/>
		<link href="${contextPath}resources/css/fonts.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/fontello-ie7.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/fontello-embedded.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/fontello.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/layout-colors.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/responsive-style.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/guia-do-programador-style.css" rel="stylesheet" type="text/css"  media="all"  />
	    <link href="${contextPath}resources/css/produtos.css" rel="stylesheet" type="text/css"  media="all"/>
		<link rel="canonical" href="http://www.casadocodigo.com.br/" />	
		<link href="${contextPath}resources/css/book-collection.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>

  <header id="layout-header">
		<div class="clearfix container">
			<a href="${s:mvcUrl('HC#index').build()}" id="logo"></a>
			<div id="header-content">
				<nav id="main-nav">
					
					<ul class="clearfix">
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
			
	<section id="index-section" class="container middle">

		<h1 class="cdc-call">Last days with special prices. Enjoy!</h1>
		<ul class="clearfix book-collection">

			<c:forEach items="${products}" var="product">			
				<li>
					<a href="${s:mvcUrl('PC#detail').arg(0, product.id).build()}" class="block clearfix">
						<h2 class="product-title">${product.title}</h2>
						<img width="143"
							height="202"
							src="https://cdn.shopify.com/s/files/1/0155/7645/products/java8-featured_large.png?v=1411490181"
							alt="Java 8 Prático"
							title="Java 8 Prático"/>
						<small class="buy-button">Buy</small>
					</a>
				</li>
			</c:forEach>
		</ul>

		<h2 class="cdc-call">Diferenciais da Casa do Código</h2>

		<ul id="cdc-diferenciais" class="clearfix">
			<li class="col-left">
				<h3>E-books sem DRM. Leia onde quiser</h3>
				<p>
					<span class="sprite" id="sprite-drm"></span> Nossos e-books não
					possuem DRM, ou seja, você pode ler em qualquer computador, tablet
					e smartphone.
				</p>
			</li>
			<li class="col-right">
				<h3>Autores de renome na comunidade</h3>
				<p>
					<span class="sprite" id="sprite-renome"></span> Autores que
					participam ativamente na comunidade com Open Source, listas de
					discussão, grupos e mais.
				</p>
			</li>
			<li class="col-left">
				<h3>Receba atualizações dos e-books</h3>
				<p>
					<span class="sprite" id="sprite-atualizacoes"></span> Quando você
					compra um e-book, automaticamente tem direito às atualizações e
					correções dele.
				</p>
			</li>
			<li class="col-right">
				<h3>Livros com curadoria da Caelum</h3>
				<p>
					<span class="sprite" id="sprite-caelum"></span> Desenvolvedores
					experientes que avaliam e revisam os livros constantemente.
				</p>
			</li>
		</ul>



	</section>
	
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

