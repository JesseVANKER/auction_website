<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


<form action="<%=request.getContextPath()%>/ServletSignUp" method="post">
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
        <label for="motDePasse">Mot de passe : </label>
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
    <a href="<%=request.getContextPath()%>"><input type="button" value="Annuler"/></a>
  </div>
     
    
</form>

</body>
</html>