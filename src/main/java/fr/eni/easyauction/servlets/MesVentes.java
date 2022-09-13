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
import fr.eni.easyauction.bo.ArticleVendu;
import fr.eni.easyauction.bo.Utilisateur;

/**
 * Servlet implementation class MesVentes
 */
@WebServlet("/MesVentes")
public class MesVentes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		List<ArticleVendu> listeArticleVenduByUser=null;
		List<ArticleVendu> listeArticleVenduByUserRecherche = new ArrayList<ArticleVendu>();
        HttpSession session = request.getSession();
        Utilisateur utilisateurCourant = (Utilisateur) session.getAttribute("utilisateurCourant");
        String recherche = request.getParameter("recherche");
        String ventesEncours = request.getParameter("ventesEnCours");
        String ventesNonDebutees = request.getParameter("ventesNonDebutees");
        String ventesTerminees = request.getParameter("ventesTerminees");
        boolean rechercheNull = (recherche == null || recherche.isEmpty());
        boolean ventesEncoursNull = (ventesEncours ==null);
        boolean ventesNonDebuteesNull = (ventesNonDebutees ==null);
        boolean ventesTermineesNull = (ventesTerminees ==null);
        boolean checkBoxNull = (ventesEncoursNull && ventesNonDebuteesNull && ventesTermineesNull);
        
        boolean rechercheNotNull = (recherche != null && !recherche.isEmpty());
        boolean ventesEncoursNotNull = (ventesEncours !=null);
        boolean ventesNonDebuteesNotNull = (ventesNonDebutees !=null);
        boolean ventesTermineesNotNull = (ventesTerminees !=null);
        boolean checkBoxNotNull = (ventesEncoursNotNull || ventesNonDebuteesNotNull || ventesTermineesNotNull); 
       
        
		try {
			listeArticleVenduByUser = easyAuctionManager.selectionnerTousLesArticlesByUser(utilisateurCourant.getNoUtilisateur());
			
						
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		if(rechercheNull && checkBoxNull) {
			request.setAttribute("listeArticleVenduByUser", listeArticleVenduByUser);
		}
		
		else if(rechercheNotNull && checkBoxNull) {
			
			for (ArticleVendu article : listeArticleVenduByUser) {
				if (article.getNomArticle().toLowerCase()
						.contains(recherche.toLowerCase().trim())) {
					listeArticleVenduByUserRecherche.add(article);
					

				}
			}
			request.setAttribute("listeArticleVenduByUser", listeArticleVenduByUserRecherche);
		}
		
		else if(rechercheNotNull && checkBoxNotNull) {
			 
		}
		
		else if(rechercheNull && checkBoxNotNull ) {
			
		}
		
		
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mesventes.jsp");
		rd.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
