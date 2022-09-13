package fr.eni.easyauction.servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import fr.eni.easyauction.bo.Enchere;
import fr.eni.easyauction.bo.Utilisateur;

/**
 * Servlet implementation class DetailArticle
 */
@WebServlet("/DetailArticle")
public class DetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		ArticleVendu articleCourant = new ArticleVendu();
		
        HttpSession session = request.getSession();
        Utilisateur utilisateurCourant = (Utilisateur) session.getAttribute("utilisateurCourant");
        
        int idArticle = Integer.parseInt(request.getParameter("id"));

		try {
			articleCourant = easyAuctionManager.selectionnerArticleById(idArticle);
			request.setAttribute("articleCourant", articleCourant);
			

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailarticle.jsp");
			rd.forward(request, response);
				
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailarticle.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		Enchere nouvelEnchere = null;
		ArticleVendu articleCourant = new ArticleVendu();
		List<Integer> listeCodesErreur = new ArrayList<>();
		
        HttpSession session = request.getSession();
        Utilisateur utilisateurCourant = (Utilisateur) session.getAttribute("utilisateurCourant");
        int idArticle = Integer.parseInt(request.getParameter("id"));
        int montantEnchere = Integer.parseInt(request.getParameter("ajoutenchere"));
        
		if (articleCourant.getDateDebutEncheres().isAfter(LocalDate.now())) {
			listeCodesErreur.add(CodesResultatServletsSignUp.DEBUT_VENTE_NON_COMMENCE);
		} else if (articleCourant.getDateFinEncheres().isBefore(LocalDate.now())){
			listeCodesErreur.add(CodesResultatServletsSignUp.VENTE_TERMINE);
		}
		if (listeCodesErreur.size() > 0) {
			// Je renvoie les codes d'erreurs
			// Et je n'enregistre pas l'utilisateur
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
			rd.forward(request, response);
		} else {
			try {
				articleCourant = easyAuctionManager.selectionnerArticleById(idArticle);
				nouvelEnchere=new Enchere(LocalDate.now(),montantEnchere, utilisateurCourant, articleCourant);
				easyAuctionManager.ajouterEnchere(nouvelEnchere);
				doGet(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		
	}

}
