<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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



<form action="<%=request.getContextPath()%>/MonCompte" method="post">
	<div>
        <label for="pseudo">Pseudo (en alpha/numérique) :</label>
        <input type="text" id="name" name="pseudo" value="meeter" required pattern="[a-zA-Z0-9]+">
    </div>
    
    <div>
        <label for="prenom">Prénom : </label>
        <input type="text" id="mail" name="prenom" value="maxime">
    </div>
    
    <div>
        <label for="telephone">Téléphone : </label>
        <input type="tel" id="mail" name="telephone" value="0781726621" maxlength="10" 
        >
    </div>
    
    <div>
        <label for="codePostal">Code postal : </label>
        <input type="number" id="mail" name="codePostal" value="22440">
    </div>
    
     <div>
        <label for="motDePasseActuel">Mot de passe actuel: </label>
        <input type="password" id="mail" name="motDePasseActuel" value="azerty">
    </div>
    
    <div>
        <label for="motDePasse">Nouveau mot de passe : </label>
        <input type="password" id="mail" name="motDePasse" value="azerty">
    </div>
    
    <div>
        <label for="confirmation">Confirmation mdp : </label>
        <input type="password" id="mail" name="confirmation" value="azerty">
    </div>
    
    <div>
        <label for="nom">Nom : </label>
        <input type="text" id="mail" name="nom" value="rousseau">
    </div>
    
    <div>
        <label for="email">Email : </label>
        <input type="email" id="mail" name="email" value="maxime.rousseau99@gmail.com">
    </div>
    
    <div>
        <label for="rue">Rue : </label>
        <input type="text" id="mail" name="rue" value="25">
    </div>
    
    <div>
        <label for="ville">Ville : </label>
        <input type="text" id="mail" name="ville" value="rennes">
    </div>
    
     <div>
    <input type="submit" value="Envoyer">
    <a href="<%=request.getContextPath()%>/SupprimerCompte"><input type="button" value="Supprimer le compte"/></a>
    <a href="<%=request.getContextPath()%>"><input type="button" value="Annuler"/></a>
  </div>
     
    
</form>

</body>
</html>