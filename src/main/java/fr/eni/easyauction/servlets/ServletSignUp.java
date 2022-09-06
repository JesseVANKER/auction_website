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

/**
 * Servlet implementation class ServletSignUp
 */
@WebServlet("/ServletSignUp")
public class ServletSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Je lis les paramètres
		String pseudo=null;
		String prenom=null;
		int telephone=0;
		int codePostal=0;
		String motDePasse=null;
		String confirmation=null;
		String nom=null;
		String email=null;
		String rue=null;
		String ville=null;
		
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreur=new ArrayList<>();
		//lecture pseudo
				pseudo = request.getParameter("pseudo");
				if(pseudo==null || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.PSEUDO_ERREUR);
				}
				if(prenom==null || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.PRENOM_ERREUR);
				}
				if(telephone==0 || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.TELEPHONE_ERREUR);
				}
				if(codePostal==0 || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.CODE_POSTAL_ERREUR);
				}
				if(motDePasse==null || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.MOT_DE_PASSE_ERREUR);
				}
				if(confirmation==null || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.CONFIRMATION_ERREUR);
				}
				if(nom==null || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.NOM_ERREUR);
				}
				if(nom==null || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.NOM_ERREUR);
				}
				if(email==null || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.EMAIL_ERREUR);
				}
				if(rue==null || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.RUE_ERREUR);
				}
				if(ville==null || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.VILLE_ERREUR);
				}
				
				if(listeCodesErreur.size()>0)
				{
					//Je renvoie les codes d'erreurs
					request.setAttribute("listeCodesErreur",listeCodesErreur);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
					rd.forward(request, response);
				}
				else
				{
//					//J'ajoute le repas
//					RepasManager repasManager = new RepasManager();
//					try {
//						repasManager.ajouterRepas(date, heure, Arrays.asList(repas.split(",")));
//						//Si tout se passe bien, je vais vers la page de consultation:
//						RequestDispatcher rd = request.getRequestDispatcher("/repas");
//						rd.forward(request, response);
//					} catch (BusinessException e) {
//						//Sinon je retourne à la page d'ajout pour indiquer les problèmes:
//						e.printStackTrace();
//						request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
//						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ajout.jsp");
//						rd.forward(request, response);
//					}
					
				}
		
		doGet(request, response);
	}

}
