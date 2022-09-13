<%@page import="fr.eni.easyauction.messages.LecteurMessage"%>
<%@page import="fr.eni.easyauction.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/nouvelarticle.css" rel="stylesheet" />
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap-grid.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
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
        
      <form method="post">
        <h1 id='title' class="header">NOUVEL ARTICLE</h1>
  
  
          <div class="row">
              <div class='form-input' id='gauche'>
                <label for="nom" class="col-sm-5 col-form-label">Nom</label>
                <div class="col-sm-6">
                  <input type="text" id="nom" placeholder='Nom' class='form-input-size' name="nom" required>
                </div>
              </div>
  
              <div class='form-input' id='droite'>
                <label for="nom" class="col-sm-5 col-form-label">Categorie</label>
                <div class="col-sm-6">
                  <select id='categorie' name='categorie' class='form-input-size cat' required>

                    <%
                      List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("listeCategories");
                      if(listeCategories!=null && listeCategories.size()>0)
                      {
                        for(Categorie categorie : listeCategories) {
                     %>  
                    <option id='<%=categorie.getLibelle()%>' name='<%=categorie.getLibelle()%>' value='<%=categorie.getLibelle()%>'><%=categorie.getLibelle()%></option>  
                    <%
                        }
                      }
            %>
                  </select>     
                </div>
              </div>
          </div>
  
          <div class="row divdes">
            <div class='form-input mr-3 des' id='gauche'>
              <label for="description">Description</label>
              <div class="col-sm-6">
                <textarea type="text" id="description" placeholder='Description' class='form-input-size' name="description"  required ></textarea>
              </div>
            </div>
  
            <div class='form-input mr-3 droite-des' id='droite'>
              <label for="photo" class="col-sm-5 col-form-label">Photo</label>
              <div class="col-sm-6">
                <input type="button" id="photo" value="Ajouter une photo" class='form-input-size photobutton' name="photo">
  
  
              <div class="prixdiv">
                <label for="prix_vente" class="col-sm-5 col-form-label ">Mise à prix</label>
                <div class="col-sm-6">
                  <input type="number" id="prix_vente" placeholder='100' class='form-input-size' name="prix_vente"> 
                </div>
              </div>
            </div>
  
            </div>
          </div>
  
          <div class="row">
              <div class='form-input date' id='gauche'>
                <label for="debut_vente" class="col-sm-5 col-form-label">Début de la vente</label>
                <div class="col-sm-6">
                  <input type="date" id="debut_vente" value='2022-09-16' class='form-input-size date' name="debut_vente">
                </div>
              </div>
              <div class='form-input ' id=''>
                <label for="fin_vente" class="col-sm-5 col-form-label">Fin de la vente</label>
                <div class="col-sm-6">
                  <input type="date" id="fin_vente" value='2022-09-24' class='form-input-size date' name="fin_vente">
                </div>
              </div>
          </div>
  
          <div class="row">
              <div class='form-input date'>
                <h2 class="col-sm-5 col-form-label">RETRAIT</h2>
              </div>
          </div>
  
          <div class="row">
            <div class='form-input' id='gauche'>
              <label for="rue" class="col-sm-5 col-form-label">Rue</label>
              <div class="col-sm-6">
                <input type="text" id="rue" placeholder='Rue' class='form-input-size' name="rue" required>
              </div>
            </div>
            <div class='form-input' id='droite'>
              <label for="ville" class="col-sm-5 col-form-label">Ville</label>
              <div class="col-sm-6">
                <input type="text" id="ville" placeholder='Ville' class='form-input-size' name="ville" required>
              </div>
            </div>
          </div>
  
          <div class="row">
            <div class='form-input' id='gauche'>
              <label for="codePostal" class="col-sm-5 col-form-label">Code Postal</label>
              <div class="col-sm-6">
                <input type="text" id="codePostal" placeholder='Code Postal' class='form-input-size' name="codePostal" required>
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
            <span>Vendre</span>
          </button>

       </form>
       
       <a class="nav-link" id="retour" href="<%=request.getContextPath()%>/MesVentes">Anuller</a>
      </div>
    </div>

</body>
</html>