<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.easyauction.bo.ArticleVendu"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
		%>
				<p style="color:red;">Erreur :</p>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
					<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
		<%	
				}
			}
		%>
		
		<table align="center">
			<thead>
				<tr>
					<td>Numéro Enchere </td>
					<td>Date Enchere </td>
					<td>Montant Enchere </td>
					<td> Utilisateur</td>
				</tr>
			</thead>
				<%
					List<ArticleVendu> listeArticleVendu = (List<ArticleVendu>) request.getAttribute("listeArticleVendu");
					if(listeArticleVendu!=null && listeArticleVendu.size()>0)
					{
				%>
						<tbody>
							<%
							for(ArticleVendu article : listeArticleVendu)
							{
							%>
								<tr>
									
									<td><%=article.getNomArticle()%> </td>
									<td><%=article.getDateFinEncheres()%> </td>
									<td><%=article.getMiseAPrix()%> </td>
									<td><%=article%> </td>
									
								
								</tr>
							
						</tbody>
				<%
							}
					}
				%>
	
			
			
		</table>
</body>
</html>