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


        <div class='form-input'>
           

        <label for="pseudo">Pseudo</label>
            
        </div>

        <div class='form-input'>
          <label for="email">Email</label>
          <p> </p>
        </div>

        <div class='form-input'>
          <label for="nom">Nom</label>
          <p> </p>
        </div>

        <div class='form-input'>
            <label for="prenom">Prénom</label>
            <p> </p>
        </div>

        <div class='form-input'>
            <label for="telephone">Téléphone</label>
            <p> </p>
        </div>

        <div class='form-input'>
          <label for="rue">Rue</label>
          <p> </p>
        </div>

        <div class='form-input'>
          <label for="ville">Ville</label>
          <p> </p>
        </div>

        <div class='form-input'>
          <label for="codePostal">Code postal</label>
           <p> </p>
        </div>

        

            
		<a class="nav-link" id="retour" href="/accueil">Accueil</a>
</div>



</body>
</html>