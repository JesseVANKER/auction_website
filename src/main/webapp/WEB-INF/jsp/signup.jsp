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
        
      <form  action="<%=request.getContextPath()%>/ServletSignUp" method="post">
        <h1 id='title' class="header">INSCRIPTION</h1>
          <div class="row">
              <div class='form-input' id='gauche'>
                <label for="pseudo" class="col-sm-5 col-form-label">Pseudo</label>
                <div class="col-sm-6">
                  <input type="text" id="name" placeholder='Pseudo' class='form-input-size' name="pseudo"  required pattern="[a-zA-Z0-9]+">
                </div>
              </div>
              <div class='form-input' id='droite'>
                <label for="email">Email</label>
                <div class="col-sm-6">
                  <input type="text" id="email" placeholder='Email' class='form-input-size' name="email"  required >
                </div>
              </div>
          </div>
  
          <div class="row">
            <div class='form-input' id='gauche'>
              <label for="nom" class="col-sm-5 col-form-label">Nom</label>
              <div class="col-sm-6">
                <input type="text" id="nom" placeholder='Nom' class='form-input-size' name="nom"  required>
              </div>
            </div>
            <div class='form-input'>
              <label for="prenom">Prénom</label>
              <div class="col-sm-6">
                <input type="text" id="prenom" placeholder='Prénom' class='form-input-size' name="prenom"  required >
              </div>
            </div>
          </div>
  
          <div class="row">
            <div class='form-input' id='gauche'>
              <label for="telephone" class="col-sm-5 col-form-label">Téléphone</label>
              <div class="col-sm-6">
                <input type="text" id="telephone" placeholder='Téléphone' class='form-input-size' name="nom" required maxlength="10">
              </div>
            </div>
            <div class='form-input'>
              <label for="rue">Rue</label>
              <div class="col-sm-6">
                <input type="text" id="rue" placeholder='Rue' class='form-input-size' name="rue"  required >
              </div>
            </div>
          </div>
  
          <div class="row">
            <div class='form-input' id='gauche'>
              <label for="ville" class="col-sm-5 col-form-label">Ville</label>
              <div class="col-sm-6">
                <input type="text" id="ville" placeholder='Ville' class='form-input-size' name="ville" required>
              </div>
            </div>
            <div class='form-input'>
              <label for="codePostal">Code postal</label>
              <div class="col-sm-6">
                <input type="text" id="codePostal" placeholder='Code postal' class='form-input-size' name="codePostal"  required >
              </div>
            </div>
          </div>
  
          <div class="row">
            <div class='form-input' id='gauche'>
              <label for="motDePasse" class="col-sm-5 col-form-label">Mot de passe</label>
              <div class="col-sm-6">
                <input type="text" id="motDePasse" placeholder='Mot de passe' class='form-input-size' name="motDePasse" required>
              </div>
            </div>
            <div class='form-input'>
              <label for="confirmation">Confirmation</label>
              <div class="col-sm-6">
                <input type="text" id="confirmation" placeholder='Confirmation' class='form-input-size' name="confirmation"  required >
              </div>
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
                <span>Sign Up</span>
              </button>
  
              
      
        </form >
          <a class="nav-link" id="retour" href="<%=request.getContextPath()%>">Continuer sans créer de compte</a>
        </div>
      </div>
      <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>

</html>