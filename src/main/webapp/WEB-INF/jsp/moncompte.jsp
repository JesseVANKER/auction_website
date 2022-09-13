<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=request.getContextPath()%>/css/monprofil.css" rel="stylesheet" />
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
      
    <form  action="<%=request.getContextPath()%>/MonCompte" method="post">
      <h1 id='title' class="header">MON COMPTE</h1>
        <div class="row">
            <div class='form-input' id='gauche'>
              <label for="pseudo" class="col-sm-5 col-form-label">Pseudo</label>
              <div class="col-sm-6">
                <input type="text" id="name" placeholder='Pseudo' class='form-input-size' name="pseudo"   pattern="[a-zA-Z0-9]+">
              </div>
            </div>
            <div class='form-input' id='droite'>
              <label for="email">Email</label>
              <div class="col-sm-6">
                <input type="text" id="email" placeholder='Email' class='form-input-size' name="email"   >
              </div>
            </div>
        </div>

        <div class="row">
          <div class='form-input' id='gauche'>
            <label for="nom" class="col-sm-5 col-form-label">Nom</label>
            <div class="col-sm-6">
              <input type="text" id="nom" placeholder='Nom' class='form-input-size' name="nom"  >
            </div>
          </div>
          <div class='form-input'>
            <label for="prenom">Prénom</label>
            <div class="col-sm-6">
              <input type="text" id="prenom" placeholder='Prénom' class='form-input-size' name="prenom"   >
            </div>
          </div>
        </div>

        <div class="row">
          <div class='form-input' id='gauche'>
            <label for="telephone" class="col-sm-5 col-form-label">Téléphone</label>
            <div class="col-sm-6">
              <input type="text" id="telephone" placeholder='Téléphone' class='form-input-size' name="nom"  maxlength="10">
            </div>
          </div>
          <div class='form-input'>
            <label for="rue">Rue</label>
            <div class="col-sm-6">
              <input type="text" id="rue" placeholder='Rue' class='form-input-size' name="rue"   >
            </div>
          </div>
        </div>

        <div class="row">
          <div class='form-input' id='gauche'>
            <label for="ville" class="col-sm-5 col-form-label">Ville</label>
            <div class="col-sm-6">
              <input type="text" id="ville" placeholder='Ville' class='form-input-size' name="ville" >
            </div>
          </div>
          <div class='form-input'>
            <label for="codePostal">Code postal</label>
            <div class="col-sm-6">
              <input type="text" id="codePostal" placeholder='Code postal' class='form-input-size' name="codePostal"   >
            </div>
          </div>
        </div>

        <div class="row">
          <div class='form-input' id='gauche'>
            <label for="motDePasse" class="col-sm-5 col-form-label">Nouveau mot de passe</label>
            <div class="col-sm-6">
              <input type="text" id="motDePasse" placeholder='Nouveau mot de passe' class='form-input-size' name="motDePasse" >
            </div>
          </div>
          <div class='form-input'>body
            <label for="confirmation" class="col-sm-5 col-form-label">Confirmation</label>
            <div class="col-sm-6">
              <input type="text" id="confirmation" placeholder='Confirmation Nouveau MDP' class='form-input-size' name="confirmation"   >
            </div>
          </div>
        </div>

        <div class="row">
          <div class='form-input' id='gauche'>
            <label for="motDePasseActuel" class="col-sm-5 col-form-label">Mot de passe actuel</label>
            <div class="col-sm-6">
              <input type="text" id="motDePasseActuel" placeholder='Mot de passe actuel' class='form-input-size' name="motDePasseActuel" >
            </div>
          </div>


            <button type='submit' id='submit' class="button1">
              <div class="svg-wrapper-1">
                <div class="svg-wrapper">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                    <path fill="none" d="M0 0h24v24H0z"></path>
                    <path fill="currentColor" d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"></path>
                  </svg>
                </div>
              </div>
              <span>Modifier</span>
            </button>
      </form>

            <button class="btn-2" href="<%=request.getContextPath()%>/SupprimerCompte">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"></path>
                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"></path>
              </svg>
              &nbsp;&nbsp;Delete 
            </button>


      </div>
    </div>


    <script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>