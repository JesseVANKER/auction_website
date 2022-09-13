package fr.eni.easyauction.servlets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.easyauction.BusinessException;
import fr.eni.easyauction.bll.EasyAuctionManager;
import fr.eni.easyauction.bo.ArticleVendu;
import fr.eni.easyauction.bo.Categorie;
import fr.eni.easyauction.bo.Utilisateur;

/**
 * Servlet implementation class NouvelArticle
 */
@WebServlet("/NouvelArticle")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class NouvelArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SAVE_DIRECTORY = "img";  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		List<Categorie> listeCategories=null;

		
		try {
			listeCategories = easyAuctionManager.selectionnerToutesCategories();

				
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		request.setAttribute("listeCategories", listeCategories);
		
		HttpSession session = request.getSession();
		
		session.getAttribute("utilisateurCourant");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/nouvelarticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Categorie> listeCategories=null;
		EasyAuctionManager easyAuctionManager = new EasyAuctionManager();
		
		try {
			listeCategories = easyAuctionManager.selectionnerToutesCategories();

				
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		String nomArticle = null;
		String description = null;
		LocalDate dateDebut = null;
		LocalDate dateFin = null;
		int prixDepart;
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurCourant");
		Categorie categorie = new Categorie();

		
		
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreur = new ArrayList<>();
		// lecture des paramètres
		nomArticle = request.getParameter("nom");
		description = request.getParameter("description");
		dateDebut = LocalDate.parse(request.getParameter("debut_vente"), formatter);
		dateFin = LocalDate.parse(request.getParameter("fin_vente"), formatter);
		prixDepart =Integer.parseInt(request.getParameter("prix_vente"));
		// Gets absolute path to root directory of web app.
		String appPath = "C:\\Users\\mrousseau2022\\git\\auction_website3\\src\\main\\webapp";
		
        System.out.println(appPath);
        
        // Gets image informations
        Part part = request.getPart("picture");
        
        //Save image File and get fileName
        String fileName = saveFile(appPath, part);
		
        
        
        //noUtilisateur = request.getParameter("confirmation");
		for(Categorie categorieTemp : listeCategories) {
			if(categorieTemp.getLibelle().equals(request.getParameter("categorie")))
				categorie=categorieTemp;
		}
		ArticleVendu articleAAjouter = new ArticleVendu(nomArticle, description, dateDebut, dateFin, prixDepart,utilisateur, categorie, fileName);


		// Vérification des données
		if (nomArticle == null || nomArticle.trim().isEmpty()) {
			listeCodesErreur.add(CodesResultatServletsSignUp.NOM_ARTICLE_ERREUR);
		}
		if (description == null || description.trim().isEmpty()) {
			listeCodesErreur.add(CodesResultatServletsSignUp.DESCRIPTION_ARTICLE_ERREUR);
		}
		if (dateDebut == null || dateDebut.isBefore(LocalDate.now())) {
			listeCodesErreur.add(CodesResultatServletsSignUp.DATE_DEBUT_ERREUR);
		}
		if (dateFin == null || dateFin.isBefore(LocalDate.now())) {
			listeCodesErreur.add(CodesResultatServletsSignUp.DATE_FIN_ERREUR);
		}
		if (prixDepart < 0) {
			listeCodesErreur.add(CodesResultatServletsSignUp.PRIX_DEPART_ERREUR);
		}
		
		
		// Si un code d'erreur est trouvé
		if (listeCodesErreur.size() > 0) {
			// Je renvoie les codes d'erreurs
			// Et je n'enregistre pas l'utilisateur
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/nouvelarticle.jsp");
			rd.forward(request, response);
		} 
		else {
			
			try {
				System.out.println(articleAAjouter);
				//ajout de l'utilisateur dans la base de donnée
				easyAuctionManager.ajouterArticle(articleAAjouter);
				// Si tout se passe bien, je vais vers la page de consultation:
				RequestDispatcher rd = request.getRequestDispatcher("/MesVentes");
				rd.forward(request, response);
			} catch (BusinessException e) {
				// Sinon je retourne à la page d'ajout pour indiquer les problèmes:
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/acceuil.jsp");
				rd.forward(request, response);
			}
		}
	}

	
	
	/**
	 * Sauvegarder le fichier image
	 * @param appPath
	 * @param part
	 * @return
	 * @throws IOException
	 */
	private String saveFile(String appPath, Part part) throws IOException {
        appPath = appPath.replace('\\', '/');
 
        // The directory to save uploaded file
        String fullSavePath = null;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIRECTORY;
        } else {
            fullSavePath = appPath + "/" + SAVE_DIRECTORY;
        }
      
        
        // Creates the save directory if it does not exists
        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String filePath=null;

        String fileName = extractFileName(part);
        System.out.println(fileName);
        String[] fn = fileName.split("(\\.)");
        fileName = fn[0];
        String ext = fn[(fn.length-1)];
        if(!ext.isEmpty()) {
        	//generate a unique file name
        	UUID uuid = UUID.randomUUID();
        	fileName = fileName + "_" + uuid.toString() + "." + ext ;
        	if (fileName != null && fileName.length() > 0) {
        		filePath = fullSavePath + File.separator + fileName;
        		System.out.println("Write attachment to file: " + filePath);
        		// Write to file
        		part.write(filePath);
        	}
        }
        return fileName;
	}
	
	 private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
	                clientFileName = clientFileName.replace("\\", "/");
	                int i = clientFileName.lastIndexOf('/');
	                return clientFileName.substring(i + 1);
	            }
	        }
	        return null;
	    }
	
	
	
}
