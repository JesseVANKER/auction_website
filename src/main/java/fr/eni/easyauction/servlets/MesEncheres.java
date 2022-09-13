package fr.eni.easyauction.servlets;

import java.io.IOException;
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
 * Servlet implementation class MesEncheres
 */
@WebServlet("/MesEncheres")
public class MesEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		List<Enchere> listeEnchereByUser=null;
        HttpSession session = request.getSession();
        Utilisateur utilisateurCourant = (Utilisateur) session.getAttribute("utilisateurCourant");
		try {

			listeEnchereByUser = easyAuctionManager.selectionnerTousLesEncheresByUser(utilisateurCourant.getNoUtilisateur());
			

			for(Enchere enchere : listeEnchereByUser) {
				
				enchere.getArticleVendu().setListeEnchere(easyAuctionManager.selectionnerTousLesEncheresByArticle(enchere.getArticleVendu().getNoArticle()));
			}
			request.setAttribute("listeEnchereByUser", listeEnchereByUser);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/mesencheres.jsp");
			rd.forward(request, response);
				
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
