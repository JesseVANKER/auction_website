<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=request.getContextPath()%>/css/signup.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap-grid.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
		
    <div class="container">
      <div id='survey-form'>
        
      <form  action="<%=request.getContextPath()%>/ServletLogin" method="post">
        <h1 id='title' class="header">CONNEXION</h1>
          <div class="row">
  
              <div class='form-input' id='gauche'>
                <label for="identifiant" class="col-sm-5 col-form-label">Identifiant</label>
                <div class="col-sm-6">
                  <input type="text" id="identifiant" placeholder='Identifiant' class='form-input-size' name="identifiant"  required>
                </div>
              </div>
  
              <div class='form-input'>
                <label for="motDePasse" class="col-sm-5 col-form-label">Mot de passe</label>
                <div class="col-sm-6">
                  <input type="text" id="motDePasse" placeholder='Mot de passe' class='form-input-size' name="motDePasse"  required>
                </div>
              </div>
  
            
              <button type='submit' id='submit'>
                <div class="svg-wrapper-1">
                  <div class="svg-wrapper">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                      <path fill="none" d="M0 0h24v24H0z"></path>
                      <path fill="currentColor" d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"></path>
                    </svg>
                  </div>
                </div>
                <span>log In</span>
              </button>
  
  
              <label class="toggle">
                <input class="toggle-checkbox" type="checkbox">
                <div class="toggle-switch"></div>
                <span class="toggle-label">Rester connecté</span>
              </label>
  
              
  
              
  
              
          </div>
        </form >
          <a class="nav-link" id="retour" href="<%=request.getContextPath()%>/ServletSignUp">Pas de compte ? SignUp</a>
        </div>
      </div>


		
</body>
</html>