<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/signup.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
  <header>
    <h1 id='title'>INSCRIPTION</h1>
<br>
</header>


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

<div id='survey-form'>

<form  action="<%=request.getContextPath()%>/ServletSignUp" method="post">
        <div class='form-input'>
           

        <label for="pseudo">Pseudo</label>
            <input type="text" id="name" placeholder='Pseudo' class='form-input-size' name="pseudo"  required pattern="[a-zA-Z0-9]+">
        </div>

        <div class='form-input'>
          <label for="email">Email</label>
          <input type="email" id="email" name="email" placeholder='Email' class='form-input-size' required >
        </div>

        <div class='form-input'>
          <label for="nom">Nom</label>
          <input type="text" id="nom" name="nom" placeholder='Nom' class='form-input-size' required >
        </div>

        <div class='form-input'>
            <label for="prenom">Prénom</label>
            <input type="text" id="prenom" name="prenom" placeholder='Prenom' class='form-input-size' required >
        </div>

        <div class='form-input'>
            <label for="telephone">Téléphone</label>
            <input type="tel" id="mail" name="telephone" placeholder='Téléphone' class='form-input-size' required maxlength="10" 
            >
        </div>

        <div class='form-input'>
          <label for="rue">Rue</label>
          <input type="text" id="rue" name="rue" placeholder='Rue' class='form-input-size' required >
        </div>

        <div class='form-input'>
          <label for="ville">Ville</label>
          <input type="text" id="ville" name="ville" placeholder='Ville' class='form-input-size' required >
        </div>

        <div class='form-input'>
          <label for="codePostal">Code postal</label>
          <input type="text" id="codePostal" name="codePostal" placeholder='Code Postal' class='form-input-size' required >
        </div>

        <div class='form-input'>
          <label for="motDePasse">Mot de passe</label>
          <input type="text" id="motDePasse" name="motDePasse" placeholder='Mot De Passe' class='form-input-size' required >
        </div>

        <div class='form-input'>
          <label for="confirmation">Confirmation</label>
          <input type="text" id="confirmation" name="confirmation" placeholder='Confirmation' class='form-input-size' required >
        </div>

        <div class='form-input'>
          <button type='submit' id='submit'>Envoyer</button>
        </div>
        

  </form>
		<a class="nav-link" id="retour" href="<%=request.getContextPath()%>">Continuer sans créer de compte</a>
</div>



</body>
</html>