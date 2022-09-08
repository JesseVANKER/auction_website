<%@page import="fr.eni.easyauction.bo.ArticleVendu"%>
<%@page import="java.util.List"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes Ventes</title>
</head>
<body>

	<h2>MES VENTES</h2>
	
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
	
               <%
                    List<ArticleVendu> listeArticleVenduByUser = (List<ArticleVendu>) request.getAttribute("listeArticleVenduByUser");
                    if(listeArticleVenduByUser!=null && listeArticleVenduByUser.size()>0)
                    {

                        for(ArticleVendu article : listeArticleVenduByUser)
                        {
               %>   
                           <%=article.getNomArticle()%>
                         	<%=article.getDateFinEncheres()%>
                           <%=article.getMiseAPrix()%>
                           <br>
                <%
                         }
                    }
                %>

</body>
</html>