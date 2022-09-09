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
		List<Utilisateur> listeUtilisateur=null;
		try {
			listeArticleVendu = easyAuctionManager.selectionnerTousLesArticles();
			listeUtilisateur = easyAuctionManager.selectionnerTousLesUtilisateurs();
				
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		request.setAttribute("listeArticleVendu", listeArticleVendu);


		HttpSession session = request.getSession();
		
		

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
