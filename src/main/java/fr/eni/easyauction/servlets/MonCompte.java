package fr.eni.easyauction.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.easyauction.BusinessException;
import fr.eni.easyauction.bll.EasyAuctionManager;
import fr.eni.easyauction.bo.Utilisateur;

/**
 * Servlet implementation class MonCompte
 */
@WebServlet("/MonCompte")
public class MonCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/moncompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    HttpSession session = request.getSession();
		Utilisateur utilisateurCourant = (Utilisateur) session.getAttribute("utilisateurCourant");
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreur = new ArrayList<>();
		// lecture des paramètres
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		String codePostal = request.getParameter("codePostal");
		String motDePasseActuel = request.getParameter("motDePasseActuel");
		String motDePasse = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");
		
		// Vérification des données
		if (pseudo == null || pseudo.trim().isEmpty()) {
			pseudo=utilisateurCourant.getPseudo();
		}


		if (email == null || email.trim().isEmpty()) {
			email=utilisateurCourant.getEmail();
		}
		
		if (nom == null || nom.trim().isEmpty()) {
			nom=utilisateurCourant.getNom();
		}
		if (prenom == null || prenom.trim().isEmpty()) {
			prenom=utilisateurCourant.getPrenom();
		}
		if (telephone == null || telephone.trim().isEmpty()) {
			telephone=utilisateurCourant.getTelephone();
		}
		
		if (rue == null || rue.trim().isEmpty()) {
			rue=utilisateurCourant.getRue();
		}
		if (ville == null || ville.trim().isEmpty()) {
			ville=utilisateurCourant.getVille();
		}
		if (codePostal == null || codePostal.trim().isEmpty()) {
			codePostal=utilisateurCourant.getCodePostal();
		}
		
		if (motDePasse == null || motDePasse.trim().isEmpty()) {
			motDePasse=utilisateurCourant.getMotDePasse();
		}
		
		
		if (confirmation == null || confirmation.trim().isEmpty()) {
			confirmation=utilisateurCourant.getMotDePasse();
		}
		
		if (!motDePasse.equals(confirmation)) {
			listeCodesErreur.add(CodesResultatServletsSignUp.NOUVEAU_MDP_ERREUR);
		}


		if (!motDePasseActuel.equals(utilisateurCourant.getMotDePasse())) {
			listeCodesErreur.add(CodesResultatServletsSignUp.CONFIRMATION_ERREUR);
		}
		int noUtilisateur = utilisateurCourant.getNoUtilisateur();
		// J'appelle l'instance
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();


		// Si un code d'erreur est trouvé
		if (listeCodesErreur.size() > 0) {
			// Je renvoie les codes d'erreurs
			// Et je n'enregistre pas l'utilisateur
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/moncompte.jsp");
			rd.forward(request, response);
		} else {

			try {
				//ajout de l'utilisateur dans la base de donnée
				easyAuctionManager.updateUtilisateur(noUtilisateur,pseudo, prenom, telephone, codePostal, motDePasse, confirmation, nom, email, rue, ville);
				// Si tout se passe bien, je vais vers la page de consultation:
				RequestDispatcher rd = request.getRequestDispatcher("");
				rd.forward(request, response);
			} catch (BusinessException e) {
				// Sinon je retourne à la page d'ajout pour indiquer les problèmes:
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/moncompte.jsp");
				rd.forward(request, response);
			}
		}
	}

}
