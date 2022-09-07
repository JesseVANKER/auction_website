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
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant=null;
		String motDePasse=null;
		Utilisateur utilisateurCourant=null;
		boolean utilisateurExiste=false;
		boolean MDPcorrect = false;
		
		
		request.setCharacterEncoding("UTF-8");
		
		List<Utilisateur> lesUtilisateurs=new ArrayList<>();
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		identifiant = request.getParameter("identifiant");
		motDePasse = request.getParameter("motDePasse");
		
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		
		
		try {
			lesUtilisateurs=easyAuctionManager.selectionnerAllUtilisateur();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		for(Utilisateur utilisateur : lesUtilisateurs) {
			if(identifiant.equals(utilisateur.getPseudo())) {
				utilisateurExiste=true;
				if(motDePasse.equals(utilisateur.getMotDePasse())) {
					MDPcorrect=true;
					utilisateurCourant=utilisateur;
				}
			}
		}
		if(!utilisateurExiste)
			listeCodesErreur.add(CodesResultatServletsSignUp.UTILISATEUR_INEXISTANT);
		if(!MDPcorrect)
			listeCodesErreur.add(CodesResultatServletsSignUp.MDP_INCORRECT);
		
		if(listeCodesErreur.size()>0) {
			//Je renvoie les codes d'erreurs
			request.setAttribute("listeCodesErreur",listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			rd.forward(request, response);
		} else {
			//Si tout se passe bien, je vais vers la page de consultation:

            HttpSession session = request.getSession();
            session.setAttribute("utilisateurCourant", utilisateurCourant);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
			rd.forward(request, response);
			
		}

	}

}
