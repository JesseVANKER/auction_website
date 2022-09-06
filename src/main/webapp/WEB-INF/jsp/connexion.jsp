<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ include file="header.jsp"%>
   <main class="backgroundConnexion">

        <div class="container cardLogin">

            <h1 class="titleConnexion my-5 text-center">Connexion</h1>

            <div class="col-md-8 borderConnexion">

                <div class="card-body card-body-connexion">

                    <form class="formConnexion" action="connexion" method="Post">

                        <div
                            class=" identifierConnexion mb-3 d-flex align-items-center text-center ">

                            <label for="identifier" class="form-label inputConnexion">Identifiant</label>

                            <input type="text" class="form-control" id="identifier" name="pseudo"

                                aria-describedby="identifier">
                                 </div>

                        <div

                            class=" passwordConnexion mb-3 d-flex align-items-center text-center">

                            <label for="password" class="form-label inputConnexion">Password</label>

                            <input type="password" class="form-control " id="password" name="password">

                        </div>

                        <p class="form-check"><span class="erreur">${ erreur}</span> </p>

                        <div class="row">

                            <div class="col-lg-4 text-center">

                                <button type="submit" class="btn  buttonConnexion">Connexion</button>

                            </div>

                            <div class="col-lg-8 checkBoxConnexion">

                                <div class="mb-3 form-check">
                               
<input type="checkbox" class="form-check-input" id="rememberMe">
                                    <label class="form-check-label" for="rememberMe" name="rememberMe">
                                        Se souvenir de moi
                                    </label>
                                    <div>
                                        <a class="linkRememberMe" href="#">Mot de passe oublié</a>
                                    </div>
                                </div>
                            </div>
                        </div>
    </form>
                    
                    <div class="buttonCompte mt-4">
                    <a class="btnCompte" href="register">Créer un compte</a>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>

                                