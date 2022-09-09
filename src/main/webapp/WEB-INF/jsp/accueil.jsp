<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="fr.eni.easyauction.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="fr.eni.easyauction.bo.Enchere"%>
<%@page import="fr.eni.easyauction.bo.ArticleVendu"%>
<%@page import="fr.eni.easyauction.bo.Categorie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/navbar.css" rel="stylesheet" />
<link href="css/accueil.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>





	<c:if test="${!empty utilisateurCourant}">
		<p>Bonjour ${utilisateurCourant.pseudo}</p>
	</c:if>

	<nav class="navbar navbar-expand-lg navbar-dark" id="mainNav"
		style="background-color: rgb(132, 91, 179)">

		<div class="container">
			<a class="navbar-brand" href="#page-top"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				MENU <i class="fas fa-bars ms-2"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
					<c:if test="${!empty utilisateurCourant}">


						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/MesEncheres">MES ENCHERES</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/MesVentes">MESVENTES</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/MonCompte">MON COMPTE</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/Deconnexion">DECONNEXION</a></li>

					</c:if>

					<c:if test="${empty utilisateurCourant}">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/ServletLogin">Login</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/ServletSignUp">Sign Up</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>


	<h1>Liste item enchères</h1>



	<br>

	<c:if test="${!empty listeCodesErreur}">
		<div class="alert alert-danger" role="alert">
			<strong>Erreur!</strong>
			<ul>
				<c:forEach var="code" items="${listeCodesErreur}">
					<li>${LecteurMessage.getMessageErreur(code)}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<div class="search">

		<form action="<%=request.getContextPath()%>/Accueil" method="post">
			<input placeholder="recherche" type="text" id="search"
				name="recherche">
				<div class='form-input'>
           
               <select id='dropdown' class='form-input-size' required name="categorie">
                   <option value='toutecAT'>- Toutes les catégories -</option>
                    <%
                        List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("listeCategories");
                        if(listeCategories!=null && listeCategories.size()>0)
                        {



                        for(Categorie categorie : listeCategories) {
                    %>
                         
                         <option value='<%=categorie.getLibelle()%>'><%=categorie.getLibelle()%></option>



                   <%
                          }
                        }
                    %>
               </select>
           </div>
				 <input type='submit' value="Go">

		</form>

	</div>
	<br>
	
	<br>
	<br>
<div>
	
	
	<%
	int affichageListe=0;
	String liste="";
	
	if(request.getParameter("recherche")==null && request.getParameter("categorie")==null){
		affichageListe=1;
	}
	else if(request.getParameter("recherche")!=null&& request.getParameter("categorie")==null){
		affichageListe=2;
	}
	else if(request.getParameter("recherche")!=null&& request.getParameter("categorie")!=null){
		affichageListe=3;
	}
	else if(request.getParameter("recherche")==null&& request.getParameter("categorie")!=null){
		affichageListe=4;
	}
	else{
		affichageListe=1;
	}
	
	
	
	switch(affichageListe) {
	  case 1:
	    liste ="listeArticleVendu";
	    break;
	  case 2:
		  liste ="listeArticleRecherche";
	    break;
	  case 3:
		  liste ="listeArticleRechercheEtCategorie";
		    break;
	  case 4:
		  liste ="listeCategories";
		    break;
	  default:
		  liste ="listeArticleVendu";
	}

		
		
		List<ArticleVendu> listeArticleVenduRecherche = (List<ArticleVendu>) request.getAttribute(liste);
		if (listeArticleVenduRecherche != null && listeArticleVenduRecherche.size() > 0) {
		%>

		<%
		for (ArticleVendu article : listeArticleVenduRecherche) {
		%>

		<ul id="article">
			<li><%=article.getNomArticle()%></li>
			<p>
			<li>Prix : <%=article.getMiseAPrix()%></li>
			</p>
			<p>
			<li>Fin de l'enchère : <%=article.getDateFinEncheres()%></li>
			</p>
			<p>
			<li>Vendeur : <%=article.getUtilisateur().getPseudo()%></li>
			</p>
			<br>
			<br>
		</ul>

		<%
		}
		}
		
		%>


	</div>

	













	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/scripts.js"></script>
	<script src="js/search.js"></script>

</body>
</html>