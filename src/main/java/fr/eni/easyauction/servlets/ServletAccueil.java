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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		List<ArticleVendu> listeArticleVendu = null;
		List<ArticleVendu> listeArticleRecherche = new ArrayList<ArticleVendu>();
		List<Categorie> listeCategorie = null;
		List<ArticleVendu> listeArticleCategorie = new ArrayList<ArticleVendu>();
		List<ArticleVendu> listeArticleRechercheEtCategorie = new ArrayList<ArticleVendu>();
		String touteCat = "touteCat";
		String recherche = request.getParameter("recherche");
		String categorie = request.getParameter("categorie");
		boolean rechercheNull = (recherche == null || recherche.isEmpty());
		boolean categorieNull = (categorie == null || categorie.equals(touteCat));
		boolean rechercheNotNull = (recherche != null && !recherche.isEmpty());
		boolean categorieNotNull = (categorie != null && !touteCat.equals(categorie));
		int idCategorie = 0;
		int idArticle = 0;

		try {

			listeArticleVendu = easyAuctionManager.selectionnerTousLesArticles();
			listeCategorie = easyAuctionManager.selectionnerToutesCategories();

			//condition avec recherche sans catégorie
			if (rechercheNotNull && categorieNull) {

				for (ArticleVendu article : listeArticleVendu) {
					if (article.getNomArticle().toLowerCase()
							.contains(recherche.toLowerCase().trim())) {
						listeArticleRecherche.add(article);

					}
				}
			}
			//condition sans recherche et avec catégorie
			if (rechercheNull && categorieNotNull) {

				for (Categorie categorieLibelle : listeCategorie) {
					if (categorieLibelle.getLibelle().equals(categorie)) {

						idCategorie = categorieLibelle.getNoCategorie();
					}
				}

				listeArticleCategorie = easyAuctionManager.selectionnerTousLesArticlesByCategorie(idCategorie);

			}
			//condition avec recherche et avec catégorie
			if (rechercheNotNull && categorieNotNull) {

				for (ArticleVendu article : listeArticleVendu) {

					if (article.getNomArticle().toLowerCase()
							.contains(recherche.toLowerCase())) {

						if (article.getCategorie().getLibelle().equals(categorie)) {

							idArticle = article.getNoArticle();

							listeArticleRechercheEtCategorie.add(easyAuctionManager.selectionnerArticleById(idArticle));
						}
					}
				}
			}

		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}

		// attribut des listes
		request.setAttribute("listeArticleRecherche", listeArticleRecherche);
		request.setAttribute("listeArticleCategorie", listeArticleCategorie);
		request.setAttribute("listeCategories", listeCategorie);
		request.setAttribute("listeArticleVendu", listeArticleVendu);
		request.setAttribute("listeArticleRechercheEtCategorie", listeArticleRechercheEtCategorie);

		// supprimer le cache
		response.setDateHeader("Expires", 0);

		// redirection vers la jsp
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");

		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
