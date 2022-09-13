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
import fr.eni.easyauction.bo.Utilisateur;

/**
 * Servlet implementation class autreUtilisateur
 */
@WebServlet("/autreUtilisateur")
public class autreUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public autreUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/autreUtilisateur.jsp");
		String pseudoUtilisateur = (String)request.getParameter("pseudo");
		String nomUtilisateur="";
		String prenomUtilisateur="";
		String emailUtilisateur="";
		String telephoneUtilisateur="";
		String rueUtilisateur="";
		String codePostalUtilisateur="";
		String villeUtilisateur="";
		List<Utilisateur> listeUtilisateur = null;
		Utilisateur autreUtilisateur = new Utilisateur();
		
		try {
			listeUtilisateur = easyAuctionManager.selectionnerTousLesUtilisateurs();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		Utilisateur utilisateurCourant = (Utilisateur) session.getAttribute("utilisateurCourant");
		if(utilisateurCourant!=null) {
			if(utilisateurCourant.getPseudo().equals(pseudoUtilisateur)) {
				request.setAttribute("autreUtilisateur", autreUtilisateur);
				rd = request.getRequestDispatcher("/MonCompte");
			}
			
		}
		
		
		for (Utilisateur utilisateur : listeUtilisateur) {
			
			if(utilisateur.getPseudo().equals(pseudoUtilisateur)) {
			
				autreUtilisateur.setPseudo(pseudoUtilisateur);
				autreUtilisateur.setNom(utilisateur.getNom());
				autreUtilisateur.setPrenom(utilisateur.getPrenom());
				autreUtilisateur.setEmail(utilisateur.getEmail());
				autreUtilisateur.setTelephone(utilisateur.getTelephone());
				autreUtilisateur.setRue(utilisateur.getRue());
				autreUtilisateur.setCodePostal(utilisateur.getCodePostal());
				autreUtilisateur.setVille(utilisateur.getVille());
				
				
			}
		}
		
		
		
		request.setAttribute("autreUtilisateur", autreUtilisateur);
	
		
	
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
