package fr.eni.easyauction.servlets;

import java.io.IOException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import fr.eni.easyauction.bo.Enchere;
import fr.eni.easyauction.bo.Utilisateur;




/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/Accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Integer> listeCodesErreur=new ArrayList<>();
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		List<ArticleVendu> listeArticleVendu=null;
		List<ArticleVendu> listeArticleRecherche=new ArrayList<ArticleVendu>();
		List<Categorie> listeCategorie=null;
		List<ArticleVendu> listeArticleCategorie=new ArrayList<ArticleVendu>();
		List<ArticleVendu> listeArticleRechercheEtCategorie=new ArrayList<ArticleVendu>();
		int idCategorie = 0;
		try {
			listeArticleVendu = easyAuctionManager.selectionnerTousLesArticles();
			listeCategorie = easyAuctionManager.selectionnerToutesCategories();
			if(request.getParameter("recherche")!=null) {
				
			
			for(ArticleVendu article : listeArticleVendu) {
				if(article.getNomArticle().toLowerCase().contains(request.getParameter("recherche").toLowerCase().trim())) {
					listeArticleRecherche.add(article);
					
					
				}
			}
			request.setAttribute("listeArticleRecherche", listeArticleRecherche);
			
			}
			if(request.getParameter("categorie")!=null && request.getParameter("categorie")!=("- Toutes les catégories -")) {
				
				for(Categorie categorie : listeCategorie) {
					if(categorie.getLibelle().equals(request.getParameter("categorie"))) {
						
						idCategorie = categorie.getNoCategorie();
					}
				}
				
				listeArticleCategorie = easyAuctionManager.selectionnerTousLesArticlesByCategorie(idCategorie);
				
				
				request.setAttribute("listeArticleCategorie", listeArticleCategorie);
				}
			
			if(request.getParameter("categorie")!=null &&request.getParameter("recherche")!=null) {
				for(ArticleVendu article : listeArticleVendu) {
					if(article.getNomArticle().toLowerCase().contains(request.getParameter("recherche").toLowerCase())) {
						for(Categorie categorie : listeCategorie) {
							if(categorie.getLibelle().equals(request.getParameter("categorie"))) {
								
								idCategorie = categorie.getNoCategorie();
							}
						}
						
						listeArticleRechercheEtCategorie = easyAuctionManager.selectionnerTousLesArticlesByCategorie(idCategorie);
						
						
						request.setAttribute("listeArticleRechercheEtCategorie", listeArticleRechercheEtCategorie);
						
						
					}
				}
			}
			
				
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		

		
		request.setAttribute("listeCategories", listeCategorie);
		request.setAttribute("listeArticleVendu", listeArticleVendu);
		


		
		

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
		
		rd.forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
