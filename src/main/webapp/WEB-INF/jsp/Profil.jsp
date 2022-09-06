<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<main class="backgroundProfil">
<section class="sectionProfile">
<h1 class="titleProfile text-center">Mon Profil</h1>
<div class="container profileContainer">
<form class="formProfile" action="profile" method="Post">
<div class="row">
<div class="col-6">
<div class="inputProfile mb-3 row">
<label for="pseudo" class="col-sm-5 col-form-label">Pseudo&nbsp:</label>
<div class="col-sm-6">
<input type="text" class="form-control" id="pseudo"
name="userName" value="${user.username}"> <input
type="hidden" name="userId" value="${user.id}">
</div>
</div>
<div class="inputProfile mb-3 row">
<label for="firstName" class="col-sm-5 col-form-label">Prénom&nbsp:</label>
<div class="col-sm-6">
<input type="text" class="form-control" id="firstName"
name="firstName" value="${user.firstName}">
</div>
</div>
<div class="inputProfile mb-3 row">
<label for="phoneNumber" class="col-sm-5 col-form-label">Téléphone&nbsp:</label>
<div class="col-sm-6">
<input type="tel" class="form-control" id="phoneNumber"
name="phoneNumber" value="${user.phoneNumber}">
</div>
</div>
<div class="inputProfile mb-3 row">
<label for="PostalCode" class="col-sm-5 col-form-label">Code
postal&nbsp:</label>
<div class="col-sm-6">
<input type="text" class="form-control" id="PostalCode"
name="PostalCode" value="${user.postalCode}">
</div>
</div>
<div class="inputProfile mb-3 row">
<label for="password" class="col-sm-5 col-form-label">Mot
de passe actuel&nbsp:</label>
<div class="col-sm-6">
<input type="password" class="form-control" id="password"
name="password" value="${user.password}">
</div>
</div>
<div class="inputProfile mb-3 row">
<label for="newMdp" class="col-sm-5 col-form-label">Nouveau
mot de passe&nbsp:</label>
<div class="col-sm-6">
<input type="password" class="form-control" id="checkMdp"
name="newMdp">
</div>
</div>
<div class="inputProfile mb-3 row">
<label for="credits" class="col-sm-2 col-form-label">Crédits</label>
<div class="col-sm-9 ms-3 credits ">
<input type="text" class="profileCredits form-control"
id="credits" name="credits" value="${user.credit }"
disabled="disabled">
</div>
</div>
</div>
<div class="col-6">
<div class="inputProfile mb-3 row">
<label for="name" class="col-sm-3 col-form-label">Nom&nbsp:</label>
<div class="col-sm-7">
<input type="text" class="form-control" id="name" name="name"
value="${user.name}">
</div>
</div>
<div class="inputProfile mb-3 row">
<label for="email" class="col-sm-3 col-form-label">Email&nbsp:</label>
<div class="col-sm-7">
<input type="email" class="form-control" id="email" name="email"
value="${user.email}">
</div>
</div>
<div class="inputProfile mb-3 row">
<label for="street" class="col-sm-3 col-form-label">Rue&nbsp:</label>
<div class="col-sm-7">
<input type="text" class="form-control" id="street"
name="street" value="${user.street}">
</div>
</div>
<div class="inputProfile mb-3 row">
<label for="city" class="col-sm-3 col-form-label">Ville&nbsp:</label>
<div class="col-sm-7">
<input type="text" class="form-control" id="city" name="city"
value="${user.city}">
</div>
</div>
<div class="inputProfile inputProfileCheck mb-3 row">
<label for="check" class="col-sm-3 col-form-label">Confirmation&nbsp:</label>
<div class="col-sm-7">
<input type="text" class="form-control" id="check" name="check">
</div>
</div>
</div>
</div>
<div class="buttonProfile">
<div class="save">
<button type="submit" class="btnSave" name="update"
value="btnUpdate">Enregistrer</button>
</div>
<div class="delete">
<button type="submit" class="btnDelete" name="delete"
value="btnDelete">Supprimer mon compte</button>
</div>
</div>
</form>
</div>
</section>
</main>

<script
src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
crossorigin="anonymous"></script>
</body>
</html>