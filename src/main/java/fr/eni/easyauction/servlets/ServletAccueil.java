package fr.eni.easyauction.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.easyauction.BusinessException;
import fr.eni.easyauction.bll.EasyAuctionManager;
import fr.eni.easyauction.bo.ArticleVendu;
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
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();

		Utilisateur user = new Utilisateur();
		ArticleVendu articleVendu = new ArticleVendu("test1", "Description produit", LocalDate.now(), LocalDate.now(), 10, 15, 10, user);
		try {
			easyAuctionManager.ajouterArticle(articleVendu);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	
		
		RequestDispatcher rd = request.getRequestDispatcher("/accueil.jsp");
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
