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
<title>Insert title here</title>
</head>
<body>


	<c:if test="${empty utilisateurCourant}">
		  <p>Pour vendre un article vous devez vous connecter</p>
		  <div class='form-input'>
          	<button id="Login" href="<%=request.getContextPath()%>/ServletLogin">Login</button>
         </div>
	</c:if>
	
    <c:if test="${!empty utilisateurCourant}">   		

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
       		<header>
           <h1 id='title'>Nouvel Article</h1>

       		</header>
       	<form id='survey-form' method="post">
       	
           <div class='form-input'>
               <label id='name-label'>Nom</label>
               <input type='text' id='nom' name='nom' placeholder="Nom de l'article" class='form-input-size' required />
           </div>
           
           <div class='form-input'>
               <label id='email-label'>Description</label>
               <textarea id='description' name='description' placeholder='Enter la description du produit' required ></textarea>
           </div>
           

           <div class='form-input'>
               <p>Categorie</p>
               <select id='categorie' name='categorie' class='form-input-size' required>
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

         <div class='form-input'>
               <label id='photo'>Photo</label>
               <input type='file' id='photo' name='photo' placeholder='Photo'/>
          </div>
          
           <div class='form-input'>
               <label id='prix_vente'>Mise à prix</label>
               <input type='number' id='prix_vente' name='prix_vente' placeholder='100	'/>
          </div>
          
          <div class='form-input'>
               <label id='debut_vente'>Début de la vente</label>
               <input type='date' id='debut_vente' name='debut_vente' value="2022-09-16"/>
          </div>
          
           <div class='form-input'>
               <label id='fin_vente'>Fin de la vente</label>
               <input type='date' id='fin_vente' name='fin_vente' value="2022-09-24"/>
          </div>
          
           <label id='Retrait'>Retrait</label>
	            <div class='form-input'>
	               <label id='rue'>Rue</label>
	               <input type='text' id='rue' name='rue' value='${utilisateurCourant.rue}' required />
	           </div>
	           
     	       <div class='form-input'>
	               <label id='ville'>Ville</label>
	               <input type='text' id='ville' name='ville' value='${utilisateurCourant.ville}' required />
	           </div>
	           
	       	  <div class='form-input'>
	               <label id='codePostal'>Code Postal</label>
	               <input type='text' id='codePostal' name='codePostal' value='${utilisateurCourant.codePostal}' required />
	           </div>
           
           <div class='form-input'>
               <button type='submit' id='submit'>Submit</button>
           </div>
       </form>
       
       <a class="nav-link" id="retour" href="<%=request.getContextPath()%>/MesVentes">Anuller</a>
   </div>




 
	</c:if>

</body>
</html>