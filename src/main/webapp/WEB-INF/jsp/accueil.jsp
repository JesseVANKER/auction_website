<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="fr.eni.easyauction.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="fr.eni.easyauction.bo.Enchere"%>
<%@page import="fr.eni.easyauction.bo.ArticleVendu"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/styles.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>


	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav" style="background-color: rgb(132, 91, 179)">
        <div class="container">
            <a class="navbar-brand" href="#page-top"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                MENU
                <i class="fas fa-bars ms-2"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
               		<c:if test="${!empty utilisateurCourant}">
		                 <li class="nav-item"><a>Bonjour${utilisateurCourant.pseudo}</a></li>
		                 <li class="nav-item"><a class="nav-link" href="#mystory">MES ENCHERES</a></li>
		                 <li class="nav-item"><a class="nav-link" href="#mystory">MESVENTES</a></li>
		                 <li class="nav-item"><a class="nav-link" href="#mystory">MON COMPTE</a></li>
        		         <li class="nav-item"><a class="nav-link" href="#mystory">DECONNEXION</a></li>
					</c:if>
					
					<c:if test="${empty utilisateurCourant}">
						  <li><a href="<%=request.getContextPath()%>/ServletLogin">Login</a></li>
				  		  <li><a href="<%=request.getContextPath()%>/ServletSignUp">Sign Up</a></li>
					</c:if>
                </ul>
            </div>
        </div>
    </nav>	

	
	
	
	

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/scripts.js"></script>

</body>
</html>