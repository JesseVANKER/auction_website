<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="fr.eni.easyauction.bo.Enchere"%>
<%@page import="java.util.List"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/mesventes.css" rel="stylesheet" />
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>

		<div class="container">	
	      	<div class="row g-3">
               <%
                    List<Enchere> listeEnchereByUser = (List<Enchere>) request.getAttribute("listeEnchereByUser");
              		 final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM uuuu", Locale.FRANCE);
              		 
                    if(listeEnchereByUser!=null && listeEnchereByUser.size()>0)
                    {

                        for(Enchere enchere : listeEnchereByUser)
                        {
                        	String date = dtf.format(enchere.getDateEnchere());
               %>   
               

						        <div class="col-12 col-md-6 col-lg-4">
						          <div class="card" >
						            <img src="img/htmlcss2reduce.jpg" class="card-img-top" alt="...">
						            <div class="card-body">
						              <h5 class="card-title"><%=enchere.getArticleVendu().getNomArticle()%></h5>
						              <h5 class="card-text">Montant : <%=enchere.getMontantEnchere()%> €</h5>
						              <p class="card-text"><%=date%></p>
						              <p class="card-text" >vendeur : <%=enchere.getArticleVendu().getUtilisateur().getNom()%></p>
						              <a href="<%=request.getContextPath()%>/DetailArticle?id=<%=enchere.getArticleVendu().getNoArticle()%>" class="btn btn-primary">Acceder à l'article</a>
						            </div>
						          </div>
						        </div>

                <%
                         }
                    }
                %>
        	</div>
			</div>

 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>