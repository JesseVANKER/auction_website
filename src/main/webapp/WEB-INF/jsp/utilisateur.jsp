<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.easyauction.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/signup.css" rel="stylesheet" />
<link href="css/monprofil.css" rel="stylesheet" />
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

        
         
       <div> 
        <label>  Pseudo </label>
        <input type="texte" placeholder= " Pseudo" />
        </div>
        
        
       <div> 
        <label>  Nom </label>
        <input type="texte" placeholder= "Nom" />
        </div>
        
        
        <div> 
        <label>  Prénom </label>
        <input type="texte" placeholder= "Prénom" />
        </div>
        
        
		 <div> 
        <label>  Email </label>
        <input type="texte" placeholder= "Email" />
        </div>
       

         <div> 
        <label>  Téléphone </label>
        <input type="texte" placeholder= "Téléphone" />
        </div>

         <div> 
        <label>  Rue </label>
        <input type="texte" placeholder= "Rue" />
        </div>
        
         <div> 
        <label>  Code postal </label>
        <input type="texte" placeholder= "Code postal" />
        </div>
        

        <div> 
        <label>  Ville</label>
        <input type="texte" placeholder= "Ville" />
        </div>

    

            
		<a class="nav-link" id="retour" href="<%=request.getContextPath()%>/Accueil">Accueil</a>
</div>



</body>
</html>



  