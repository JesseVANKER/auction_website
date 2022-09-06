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
<h1>Sign Up</h1>
<br>
<br>

<h2>Mon profil</h2>


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



<form action="<%=request.getContextPath()%>/ServletSignUp" method="post">
	<div>
        <label for="pseudo">Pseudo :</label>
        <input type="text" id="name" name="pseudo">
    </div>
    
    <div>
        <label for="prenom">Prénom : </label>
        <input type="text" id="mail" name="prenom">
    </div>
    
    <div>
        <label for="telephone">Téléphone : </label>
        <input type="tel" id="mail" name="telephone">
    </div>
    
    <div>
        <label for="codePostal">Code postal : </label>
        <input type="number" id="mail" name="codePostal">
    </div>
    
    <div>
        <label for="motDePasse">Mot de passe : </label>
        <input type="password" id="mail" name="motDePasse">
    </div>
    
    <div>
        <label for="confirmation">Confirmation : </label>
        <input type="password" id="mail" name="confirmation">
    </div>
    
    <div>
        <label for="nom">Nom : </label>
        <input type="text" id="mail" name="nom">
    </div>
    
    <div>
        <label for="email">Email : </label>
        <input type="email" id="mail" name="email">
    </div>
    
    <div>
        <label for="rue">Rue : </label>
        <input type="text" id="mail" name="rue">
    </div>
    
    <div>
        <label for="ville">Ville : </label>
        <input type="text" id="mail" name="ville">
    </div>
    
     <div>
    <input type="submit" value="Envoyer">
    <a href="<%=request.getContextPath()%>"><input type="button" value="Annuler"/></a>
  </div>
     
    
</form>

</body>
</html>