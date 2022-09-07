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

import fr.eni.easyauction.BusinessException;
import fr.eni.easyauction.bll.EasyAuctionManager;
import fr.eni.easyauction.bo.Utilisateur;

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
		String telephone=null;
		String codePostal=null;
		String motDePasse=null;
		String confirmation=null;
		String nom=null;
		String email=null;
		String rue=null;
		String ville=null;
		
		boolean verifUtilisateur=true;
		
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreur=new ArrayList<>();
		//lecture pseudo
				pseudo = request.getParameter("pseudo");
				prenom = request.getParameter("prenom");
				telephone = request.getParameter("telephone");
				codePostal = request.getParameter("codePostal");
				motDePasse = request.getParameter("motDePasse");
				confirmation = request.getParameter("confirmation");
				nom = request.getParameter("nom");
				email = request.getParameter("email");
				rue = request.getParameter("rue");
				ville = request.getParameter("ville");
				
				if(pseudo==null || pseudo.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.PSEUDO_ERREUR);
				}
				if(prenom==null || prenom.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.PRENOM_ERREUR);
				}
				if(telephone==null || telephone.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.TELEPHONE_ERREUR);
				}
				if(codePostal==null || codePostal.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.CODE_POSTAL_ERREUR);
				}
				if(motDePasse==null || motDePasse.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.MOT_DE_PASSE_ERREUR);
				}
				if(confirmation==null || confirmation.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.CONFIRMATION_ERREUR);
				}
				if(nom==null || nom.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.NOM_ERREUR);
				}
				
				if(email==null || email.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.EMAIL_ERREUR);
				}
				if(rue==null || rue.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.RUE_ERREUR);
				}
				if(ville==null || ville.trim().isEmpty())
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.VILLE_ERREUR);
				}
				
				if(!motDePasse.equals(confirmation))
				{
					listeCodesErreur.add(CodesResultatServletsSignUp.MDP_CONFIRMATION_ERREUR);
				}
				
				
				
				
				

				
				
					//J'ajoute l'utilisateur
					EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
					
					
		                List<Utilisateur> listeUtilisateur=null;
		                
		                try {
							listeUtilisateur = easyAuctionManager.selectionnerTousLesUtilisateurs();
						} catch (BusinessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		                
		                for (Utilisateur user : listeUtilisateur) {
		                	 if( user.getPseudo().equals(pseudo)) {
		                		 verifUtilisateur=false;
		                	 }
		                	 else if( user.getEmail().equals(email)) {
		                		 verifUtilisateur=false;
		                	 }
		                	 
		                	}

		                if(verifUtilisateur==false)
						{
							listeCodesErreur.add(CodesResultatServletsSignUp.COMPTE_EXISTANT_ERREUR);
						}
		                
					
		                if(listeCodesErreur.size()>0)
						{
		                	System.out.println(listeCodesErreur.size());
							//Je renvoie les codes d'erreurs
							request.setAttribute("listeCodesErreur",listeCodesErreur);
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
							rd.forward(request, response);
							
						}
		                else {
		                	
		                	try {
		                			
								 easyAuctionManager.ajouterUtilisateur(pseudo, prenom, telephone, codePostal, motDePasse,confirmation, nom, email, rue, ville);
								//Si tout se passe bien, je vais vers la page de consultation:
								RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
								rd.forward(request, response);
							} catch (BusinessException e) {
								//Sinon je retourne à la page d'ajout pour indiquer les problèmes:
								e.printStackTrace();
								request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
								RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
								rd.forward(request, response);
							}
		                }
					
					

	}
}
