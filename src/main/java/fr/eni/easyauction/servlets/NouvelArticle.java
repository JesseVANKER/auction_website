package fr.eni.easyauction.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import fr.eni.easyauction.bo.ArticleVendu;
import fr.eni.easyauction.bo.Categorie;
import fr.eni.easyauction.bo.Utilisateur;

/**
 * Servlet implementation class NouvelArticle
 */
@WebServlet("/NouvelArticle")
public class NouvelArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		List<Categorie> listeCategories=null;

		
		try {
			listeCategories = easyAuctionManager.selectionnerToutesCategories();

				
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		request.setAttribute("listeCategories", listeCategories);
		
		HttpSession session = request.getSession();
		
		session.getAttribute("utilisateurCourant");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/nouvelarticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		List<Categorie> listeCategories=null;
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		
		try {
			listeCategories = easyAuctionManager.selectionnerToutesCategories();

				
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		String nomArticle = null;
		String description = null;
		LocalDate dateDebut = null;
		LocalDate dateFin = null;
		int prixDepart;
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurCourant");
		Categorie categorie = new Categorie();

		
		
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreur = new ArrayList<>();
		// lecture des paramètres
		nomArticle = request.getParameter("nom");
		description = request.getParameter("description");
		dateDebut = LocalDate.parse(request.getParameter("debut_vente"), formatter);
		dateFin = LocalDate.parse(request.getParameter("fin_vente"), formatter);
		prixDepart =Integer.parseInt(request.getParameter("prix_vente"));
//		noUtilisateur = request.getParameter("confirmation");
		for(Categorie categorieTemp : listeCategories) {
			if(categorieTemp.getLibelle().equals(request.getParameter("categorie")))
				categorie=categorieTemp;
		}
		System.out.println(categorie);


		// Vérification des données
		if (nomArticle == null || nomArticle.trim().isEmpty()) {
			listeCodesErreur.add(CodesResultatServletsSignUp.NOM_ARTICLE_ERREUR);
		}
		if (description == null || description.trim().isEmpty()) {
			listeCodesErreur.add(CodesResultatServletsSignUp.DESCRIPTION_ARTICLE_ERREUR);
		}
		if (dateDebut == null || dateDebut.trim().isEmpty()) {
			listeCodesErreur.add(CodesResultatServletsSignUp.DATE_DEBUT_ERREUR);
		}
		if (dateFin == null || dateFin.trim().isEmpty()) {
			listeCodesErreur.add(CodesResultatServletsSignUp.DATE_FIN_ERREUR);
		}
		if (prixDepart == null || prixDepart.trim().isEmpty()) {
			listeCodesErreur.add(CodesResultatServletsSignUp.PRIX_DEPART_ERREUR);
		}
		

		// J'appelle l'instance
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();

		// Si un code d'erreur est trouvé
		if (listeCodesErreur.size() > 0) {
			// Je renvoie les codes d'erreurs
			// Et je n'enregistre pas l'utilisateur
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/nouvelarticle.jsp");
			rd.forward(request, response);
		} 
		else {
			
			//ArticleVendu articleNouveau = new ArticleVendu(nomArticle, description, dateDebut, dateFin, prixDepart, utilisateur, categorie)
			try {
				//ajout de l'utilisateur dans la base de donnée
				easyAuctionManager.ajouterArticle(null);
				// Si tout se passe bien, je vais vers la page de consultation:
				RequestDispatcher rd = request.getRequestDispatcher("");
				rd.forward(request, response);
			} catch (BusinessException e) {
				// Sinon je retourne à la page d'ajout pour indiquer les problèmes:
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mesventes.jsp");
				rd.forward(request, response);
			}
		}
	}

}
