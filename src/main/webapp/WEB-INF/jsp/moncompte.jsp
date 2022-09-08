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

    
    	<div class='form-input'>
        	<label for="pseudo">Pseudo</label>
            <input type="text" id="name" placeholder='Pseudo' class='form-input-size' name="pseudo" pattern="[a-zA-Z0-9]+">
        </div>

        <div class='form-input'>
          <label for="email">Email</label>
          <input type="email" id="email" name="email" placeholder='Email' class='form-input-size' >
        </div>

        <div class='form-input'>
          <label for="nom">Nom</label>
          <input type="text" id="nom" name="nom" placeholder='Nom' class='form-input-size'>
        </div>

        <div class='form-input'>
            <label for="prenom">Prénom</label>
            <input type="text" id="prenom" name="prenom" placeholder='Prenom' class='form-input-size'>
        </div>

        <div class='form-input'>
            <label for="telephone">Téléphone</label>
            <input type="tel" id="mail" name="telephone" placeholder='Téléphone' class='form-input-size' maxlength="10" 
            >
        </div>

        <div class='form-input'>
          <label for="rue">Rue</label>
          <input type="text" id="rue" name="rue" placeholder='Rue' class='form-input-size'>
        </div>

        <div class='form-input'>
          <label for="ville">Ville</label>
          <input type="text" id="ville" name="ville" placeholder='Ville' class='form-input-size'>
        </div>

        <div class='form-input'>
          <label for="codePostal">Code postal</label>
          <input type="text" id="codePostal" name="codePostal" placeholder='Code Postal' class='form-input-size'>
        </div>

        <div class='form-input'>
          <label for="motDePasse">Nouveau Mot de passe</label>
          <input type="text" id="motDePasse" name="motDePasse" placeholder='Nouveau Mot De Passe' class='form-input-size'>
        </div>

        <div class='form-input'>
          <label for="confirmation">Confirmation Nouveau MDP</label>
          <input type="text" id="confirmation" name="confirmation" placeholder='Confirmation nouveau MDP' class='form-input-size'>
        </div>
        
          <div class='form-input'>
	        <label for="motDePasseActuel">Mot de passe actuel </label>
	        <input type="password" id="mail" name="motDePasseActuel" placeholder='Mot de passe actuel' class='form-input-size' required>
	    </div>
    
     <div>
    <input type="submit" value="Envoyer">
    <a href="<%=request.getContextPath()%>/SupprimerCompte"><input type="button" value="Supprimer le compte"/></a>
    <a href="<%=request.getContextPath()%>"><input type="button" value="Annuler"/></a>
  </div>
     
    
</form>

</body>
</html>