<%@page import="fr.eni.easyauction.bo.Utilisateur"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.easyauction.bo.Enchere"%>
<%@page import="fr.eni.easyauction.bo.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=request.getContextPath()%>/css/detailarticle.css" rel="stylesheet" />
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<title>Mes Ventes</title>
</head>
<body>
  <%
  ArticleVendu article = (ArticleVendu) request.getAttribute("articleCourant");
  int indexEnchere = 0;
  %>   

  <div id='back-item'>
    <h1 id='title' class="header">DETAIL DE L'ARTICLE</h1>
    <div class="container-fluid">
      <div class="row">
        <div class="col-12 mt-3">
         <p hidden id="finenchere"><%=article.getDateFinEncheres().toString().replace("-", ", ")%></p>
          <div class="card">	
            <div class="card-horizontal">
              <div class="img-square-wrapper">
                <img class="detail-img" src="./img/scooter.jpg" width="400"  alt="Card image cap">
              </div>
              <div class="card-body">
                <h4 class="card-title"><%=article.getNomArticle()%></h4>
                <p class="card-text"><%=article.getDescription()%></p>
                <a class="link" href="<%=request.getContextPath()%>/DetailArticle?id=<%=article.getNoArticle()%>">Nom Vendeur
                </a>
                <span id="enchere">Fin de l'enchère :</span>
                <h6 id="countdown"></h6>

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
                        
                      // Output the result in an element with id="countdown"
                      document.getElementById("countdown").innerHTML = days + "j " + hours + "h "
                      + minutes + "m " + seconds + "s ";
                        
                      // If the count down is over, write some text 
                      if (distance < 0) {
                        clearInterval(x);
                        document.getElementById("countdown").innerHTML = "EXPIRED";
                      }
                    }, 1000);
                  </script>
                  <%
                  Utilisateur utilisateurCourant = (Utilisateur) session.getAttribute("utilisateurCourant");
                  if(utilisateurCourant!=null){
                  %>
                 <form  action="<%=request.getContextPath()%>/DetailArticle?id=<%=article.getNoArticle()%>" method="post">
	               <div class='form-input' id='gauche'>
	                <span class="col-sm-5 col-form-label">Ajouter une enchère</span>
	                <div class="col-sm-6">
	                  <input type="text" id="ajoutenchere" placeholder='200€' class='form-input-size' name="ajoutenchere"  required>
	                </div>
	              </div>
                 </form>
                 <%} %>
                <hr> 
                <ol class="list-group">
                
               <%

                   	List<Enchere> listeEnchereArticle = article.getListeEnchere();
           			final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM uuuu", Locale.FRANCE);
             

                      for(Enchere enchere : listeEnchereArticle)
                      {
                    	if(enchere.getMontantEnchere()!=0){
                    		
	                      	String date = dtf.format(enchere.getDateEnchere());
	                      	String fin = dtf.format(enchere.getArticleVendu().getDateFinEncheres());
		               		indexEnchere++;
	                      	%>   
			                
			                  <li class="list-group-item 
			                  <%if(indexEnchere==1){ %>list-group-item-success <%}else{ %>list-group-item-secondary<%} %> d-flex justify-content-between align-items-start">
			                    <div class="ms-2 me-auto">
			                      <div class="fw-bold"><%=enchere.getMontantEnchere()%>$</div>
			                      <%=date%> par <%=enchere.getUtilisateur().getPseudo()%>
			                    </div>
			                    <span class="badge bg-secondary rounded-pill"><%=indexEnchere%></span>
			                  </li>
			                  
		               	 <%
                       	 }else{
                         %>
            				<p>Pas d'enchères</p>
            			<%
                      	 }
                      }
          	
            			%>

                




                  
                </ol>
                </div>
              </div>
            </div>  
          </div>
        </div>
      </div>
  </div>

			 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>