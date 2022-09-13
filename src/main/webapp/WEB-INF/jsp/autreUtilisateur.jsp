<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.easyauction.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/signup.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
  <header>
    <h1 id='title'>Profil Utilisateur</h1>
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

<div id='survey-form' >
<%
           		Utilisateur autreUilisateur= (Utilisateur) request.getAttribute("autreUtilisateur");
           %>

        <div class='form-input'>
        <label for="pseudo">pseudo :<p>&emsp; <%=autreUilisateur.getPseudo()%> </p></label>    
        </div>
        
        <div class='form-input'>
          <label for="nom">Nom : <p>&emsp; <%=autreUilisateur.getNom()%> </p></label>
          <p> </p>
        </div>
        
         <div class='form-input'>
            <label for="prenom">Prénom : <p>&emsp; <%=autreUilisateur.getPrenom()%> </p></label>
            <p> </p>
        </div>
        

        <div class='form-input'>
          <label for="email">Email : <p>&emsp; <%=autreUilisateur.getEmail()%> </p></label>
          <p> </p>
        </div>

        <div class='form-input'>
            <label for="telephone">Téléphone : <p>&emsp; <%=autreUilisateur.getTelephone()%> </p></label>
            <p> </p>
        </div>

        <div class='form-input'>
          <label for="rue">Rue : <p>&emsp; <%=autreUilisateur.getRue()%> </p></label>
          <p> </p>
        </div>
        
        <div class='form-input'>
          <label for="codePostal">Code postal : <p>&emsp; <%=autreUilisateur.getCodePostal()%> </p></label>
           <p> </p>
        </div>
        

        <div class='form-input'>
          <label for="ville">Ville : <p>&emsp; <%=autreUilisateur.getVille()%> </p></label>
          <p> </p>
        </div>

    

        

            
		<a class="nav-link" id="retour" href="<%=request.getContextPath()%>/Accueil">Accueil</a>
</div>



</body>
</html>