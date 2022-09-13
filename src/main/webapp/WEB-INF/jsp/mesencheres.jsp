<%@page import="fr.eni.easyauction.bll.EasyAuctionManager"%>
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
<link href="<%=request.getContextPath()%>/css/mesencheres.css" rel="stylesheet" />
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>

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
    <h1 id='title' class="header">MES ENCHÈRES</h1>
    <div class="container-fluid">
      <div class="">
        <div class="">
                <ol class="list-group">
                
               <%
                    List<Enchere> listeEnchereByUser = (List<Enchere>) request.getAttribute("listeEnchereByUser");
              		 final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM uuuu", Locale.FRANCE);
             		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
                    if(listeEnchereByUser!=null && listeEnchereByUser.size()>0)
                    {

                        for(Enchere enchere : listeEnchereByUser)
                        {
                        	String date = dtf.format(enchere.getDateEnchere());
                        	String fin = dtf.format(enchere.getArticleVendu().getDateFinEncheres());
            				enchere.getArticleVendu().setListeEnchere(easyAuctionManager.selectionnerTousLesEncheresByArticle(enchere.getArticleVendu().getNoArticle()));
                        	
               %>   
               

                  <li class="list-group-item ">
                    <div class="">
                      <div class="d-flex">
                        <img id="image-list" class="mr-3" src="./img/scooter.jpg"  alt="Generic placeholder image">

                        <div class="mr-3 enchere-body">
                          <h5 class="mt-0"><div class="fw-bold"><%=enchere.getArticleVendu().getNomArticle() %></div>
                          </h5>
                          <p>Enchère la plus élevé</p>
                          <h6><%=enchere.getArticleVendu().getListeEnchere().get(0).getMontantEnchere()%> € par <%=enchere.getArticleVendu().getUtilisateur().getPseudo()%></h6>
                          <p>Mon enchère</p>
                          <h6><%=enchere.getMontantEnchere() %>€</h6>
                        </div>
                        <div class="mr-3 enchere-body">
                          <h5 class="mt-0"><div class="fw-bold">Historique</div>
                          </h5>
                          <p>Encheri le</p>
                          <h6><%=date%></h6>
                          <p>Fin des enchères</p>
                          <h6><%=fin%></h6>
                        </div>
                      </div>
                    </div>
                  </li>
                  
                                  <%
                         }
                    }
                %>
                
                </ol> 
          </div>
        </div>
      </div>
  </div>
  
  
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>