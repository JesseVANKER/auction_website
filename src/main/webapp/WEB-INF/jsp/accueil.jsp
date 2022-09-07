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
<title>Insert title here</title>
</head>
<body>
<h1>Accueil</h1>
<br>
<br>
<h2>ENI-Encheres</h2>
<ul>
  <li><a href="<%=request.getContextPath()%>/ServletLogin">Login</a></li>
  <li><a href="<%=request.getContextPath()%>/ServletSignUp">Sign Up</a></li>
 
</ul>
<br>
<br>
<h2>Listes Encheres</h2>


</body>
</html>