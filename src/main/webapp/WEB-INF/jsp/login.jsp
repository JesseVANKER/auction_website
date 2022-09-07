<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
<br>

<a href="<%=request.getContextPath()%>/ServletSignUp">Sign Up</a>

<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
		%>
				<p style="color:red;">Erreur, l'utilisateur n'a pas pu être ajouté :</p>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
					<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
		<%	
				}
			}
		%>
		
<form action="<%=request.getContextPath()%>/ServletLogin" method="post">
	<div>
        <label for="identifiant">Identifiant :</label>
        <input type="text" id="identifiant" name="identifiant" value="JamesWrank">
    </div>
    
    <div>
        <label for="m">Prénom : </label>
        <input type="text" id="motDePasse" name="motDePasse" value="Pa$$w0rd">
    </div>
    
     <div>
    <input type="submit" value="Envoyer">
    <a href="<%=request.getContextPath()%>"><input type="button" value="Annuler"/></a>
  </div>
     
    
</form>
		
</body>
</html>