<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<body>
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
</body>
</html>