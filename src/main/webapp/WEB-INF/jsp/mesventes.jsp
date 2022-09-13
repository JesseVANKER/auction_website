<%@page import="fr.eni.easyauction.bo.ArticleVendu"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=request.getContextPath()%>//accueil.css" rel="stylesheet" />
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<title>Mes Ventes</title>
</head>
<body>


	<header>
		<h1 id='title'>MES VENTES</h1>
	<br>
	</header>
	
	<c:if test="${empty utilisateurCourant}">
		<p>Pour voir vos ventes vous devez vous connecter</p>
		<div class='form-input'>
			<button id="Login" href="<%=request.getContextPath()%>/ServletLogin">Login</button>
		</div>
	</c:if>

	<c:if test="${!empty utilisateurCourant}">


		<form action="<%=request.getContextPath()%>/NouvelArticle">
			<input type="submit" value="Vendre un article" />
		</form>
	</c:if>

	<div>
		<form action="<%=request.getContextPath()%>/Accueil" method="get"
			target="_blank">
			<button type="submit">Accueil</button>
		</form>
	</div>

	<div class="search">

		<form action="<%=request.getContextPath()%>/MesVentes" method="post">
			<input placeholder="recherche" type="text" id="search"
				name="recherche">
			<fieldset>
				<legend>Mes Ventes</legend>

				<div>
					<input type="checkbox" id="ventesEnCours" name="ventesEnCours"
						checked value="ventesEnCours"> <label for="scales">Mes ventes en cours</label>
				</div>

				<div>
					<input type="checkbox" id="ventesNonDebutees"
						name="ventesNonDebutees" value="ventesNonDebutees"> <label for="horns">Ventes
						non d�but�es</label>
				</div>

				<div>
					<input type="checkbox" id="ventesTerminees" name="ventesTerminees" value="ventesTerminees">
					<label for="horns">Ventes termin�es</label>
				</div>
			</fieldset>
			<input type='submit' value="Go">

		</form>

	</div>

	<div class="container">
		<div class="row g-3">
			<%
			List<ArticleVendu> listeArticleVenduByUser = (List<ArticleVendu>) request.getAttribute("listeArticleVenduByUser");
			if (listeArticleVenduByUser != null && listeArticleVenduByUser.size() > 0) {

				for (ArticleVendu article : listeArticleVenduByUser) {
			%>


			<div class="col-12 col-md-6 col-lg-4">
				<div class="card">
					<img
						src="<%=request.getContextPath()%>/img/<%=article.getImage()%>"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title"><%=article.getNomArticle()%></h5>
						<p class="card-text">With supporting text below as a natural
							lead-in to additional content.</p>
						<a
							href="<%=request.getContextPath()%>/DetailArticle?id=<%=article.getNoArticle()%>"
							class="btn btn-primary">Go somewhere</a>
					</div>
				</div>
			</div>

			<%
			}
			}
			%>
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
		crossorigin="anonymous"></script>
</body>
</html>