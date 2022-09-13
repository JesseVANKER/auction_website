<%@page import="fr.eni.easyauction.bll.EasyAuctionManager"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
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
<link href="<%=request.getContextPath()%>/css/acceuil.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<header>
  <nav class="navbar navbar-expand-lg navbar-dark" id="mainNav"
  style='background-image: url("img/nav.jpg");'>

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
            href="<%=request.getContextPath()%>/MesVentes">MES VENTES</a></li>
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
</header>

<body>






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
	
   <div id='back-item'>
	<div class="search">




      <div class="container-recherche col-md-8 col-lg-6">
        <div id='survey-form'>
        
  
          <div class="row">
            <div class='form-input' id='gauche'>
  
              <h2 id="filtrer" for="pseudo" class="col-sm-5 col-form-label">FILTRER LA RECHERCHE</h2>
  
              <form action="<%=request.getContextPath()%>/Accueil" method="post">
  
                <input class='form-input-size col-12 col-md-6 col-lg-3' placeholder="Recherche" type="text" id="search"
                  name="recherche">
  
                <div class='form-input'>
                  <select class='form-input-size col-12' id='dropdown'name="categorie">
                      <option value='touteCat'>- Toutes les catégories -</option>
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
                <button type='submit' id='submit'>
                  <div class="svg-wrapper-1">
                    <div class="svg-wrapper">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                        <path fill="none" d="M0 0h24v24H0z"></path>
                        <path fill="currentColor" d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"></path>
                      </svg>
                    </div>
                  </div>
                  <span>Filtrer</span>
                </button>
              </form>
  
  
            </div>
          </div>
        </div>
      </div>
    </div>  

	<%
	int affichageListe=0;
	String liste="";
	String touteCat="touteCat";
	String recherche=request.getParameter("recherche");
	String categorie=request.getParameter("categorie");
	boolean rechercheNull = (recherche==null || recherche.isEmpty());
	boolean categorieNull = (categorie==null || categorie.equals("touteCat"));
	boolean rechercheNotNull = (recherche!=null && !recherche.isEmpty());
	boolean categorieNotNull = (categorie!=null && !touteCat.equals(categorie));
	
	
	if(rechercheNull && categorieNull){
		affichageListe=1;
	}
	else if(rechercheNotNull && categorieNull){
		affichageListe=2;
	}
	else if(rechercheNotNull && categorieNotNull ){
		affichageListe=3;
	}
	else if(rechercheNull && categorieNotNull){
		affichageListe=4;
	}
	else{
		affichageListe=1;
	}
	
	
	//switch pour attribuer le request.getAttribute suivant la recherche
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
		  liste ="listeArticleCategorie";
		    break;
	  default:
		  liste ="listeArticleVendu";
	}
	
	%>


  <div class="container">
    <div class="row g-4">

		<%
		List<ArticleVendu> listeArticleVenduRecherche = (List<ArticleVendu>) request.getAttribute(liste);
		int index=0;
		if (listeArticleVenduRecherche != null && listeArticleVenduRecherche.size() > 0) {
			for (ArticleVendu article : listeArticleVenduRecherche) {
				String descriptionArt = article.getDescription();
				if(descriptionArt.length()>57){
					descriptionArt = descriptionArt.substring(0, 57);
					descriptionArt = descriptionArt + "...";
				}
				if(descriptionArt.length()<30){
				}
				index++;
		%>
			<p hidden id="finenchere"><%=article.getDateFinEncheres().toString().replace("-", ", ")%></p>
            <div class="col-6 col-md-4 col-lg-3 col-xxl-2">
              <div class="card" onclick="window.location='<%=request.getContextPath()%>/DetailArticle?id=<%=article.getNoArticle()%>';" >
                <img src="img/scooter.jpg" class="card-img-top" alt="..." >
                <div class="card-body">
                  <h5 class="card-title"><%=article.getNomArticle()%></h5>
                                    <%
  					if(article.getNomArticle().length()<21){
  	              %>
  	              <br>
  	              <%	
  					}
                  %>
                  <h6 class="card-subtitle mb-3 text-muted"><%=article.getMiseAPrix()%> €</h6>
                  <h6 id="demo<%=index%>"></h6>
                  <p class="card-text"><%=descriptionArt%></p>
                  <%
  					if(descriptionArt.length()<30){
  	              %>
  	              <br>
  	              <%	
  					}
                  %>
                      <a class="link" href="<%=request.getContextPath()%>/DetailArticle?id=<%=article.getNoArticle()%>"><%=article.getUtilisateur().getPseudo()%>
                      </a>
                  <script>
                  // Set the date we're counting down to
                  	var dateString =  document.getElementById("finenchere").innerHTML;
                  	console.log(dateString);
                    var countDownDate = new Date(dateString).getTime();

                    // Update the count down every 1 second
                    var x = setInterval(function() {

                      // Get today's date and time
                      var now = new Date().getTime();
                        
                      // Find the distance between now and the count down date
                      var distance = countDownDate - now;
                        
                      // Time calculations for days, hours, minutes and seconds
                      var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                      var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                      var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                      var seconds = Math.floor((distance % (1000 * 60)) / 1000);
                        
                      // Output the result in an element with id="demo1"
                      document.getElementById("demo<%=index%>").innerHTML = days + "j " + hours + "h "
                      + minutes + "m " + seconds + "s ";
                        
                      // If the count down is over, write some text 
                      if (distance < 0) {
                        clearInterval(x);
                        document.getElementById("demo<%=index%>").innerHTML = "EXPIRED";
                      }
                    }, 1000);
                  </script>
                </div>
              </div>
            </div>
                <%
                         }
                    }
                %>
    </div>
  </div>
	</div>
	<br>




	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/scripts.js"></script>
	<script src="js/search.js"></script>

</body>

</html>