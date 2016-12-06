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
						<li>
							<a href="${s:mvcUrl('CC#itens').build()}" rel="nofollow">
								<s:message code="menu.cart" arguments="${cart.quantity}" />
							</a>
						</li>
						<li>
							<a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">
								<s:message code="menu.about"/>
							</a>
						</li>
						<li><a href="/pages/perguntas-frequentes" rel="nofollow">Frequent Questions</a></li>
						<li>
						    <a href="?locale=pt" rel="nofollow">
						        <s:message code="menu.pt"/>
						    </a>
						</li>
						
						<li>
						    <a href="?locale=en_UR" rel="nofollow">
						        <s:message code="menu.en"/>
						    </a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<nav class="categories-nav">
		<ul class="container">
			<li class="category"><a href="http://www.casadocodigo.com.br"><s:message code="nav.category.home"/></a></li>
			<li class="category"><a href="/collections/livros-de-agile"><s:message code="nav.category.agile"/> </a></li>
			<li class="category"><a href="/collections/livros-de-front-end"><s:message code="nav.category.front_end"/></a></li>
			<li class="category"><a href="/collections/livros-de-games"><s:message code="nav.category.games"/></a></li>
			<li class="category"><a href="/collections/livros-de-java"><s:message code="nav.category.java"/></a></li>
			<li class="category"><a href="/collections/livros-de-mobile"><s:message code="nav.category.mobile"/></a></li>
			<li class="category"><a href="/collections/livros-desenvolvimento-web"><s:message code="nav.category.webdevelopment"/></a></li>
			<li class="category"><a href="/collections/outros"><s:message code="nav.category.others"/></a></li>
		</ul>
	</nav>
</body>
</html>