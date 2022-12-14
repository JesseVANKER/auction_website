package fr.eni.easyauction.bll;



import java.util.List;

import fr.eni.easyauction.BusinessException;
import fr.eni.easyauction.bo.ArticleVendu;
import fr.eni.easyauction.bo.Categorie;
import fr.eni.easyauction.bo.Enchere;
import fr.eni.easyauction.bo.Utilisateur;
import fr.eni.easyauction.dal.DAOFactory;
import fr.eni.easyauction.dal.EasyAuctionDAO;


public class EasyAuctionManager {
	private EasyAuctionDAO easyAuctionDAO;
	
	public EasyAuctionManager() {
		this.easyAuctionDAO=DAOFactory.getEasyAuctionDAO();
	}
	
	public void ajouterArticle(ArticleVendu articleVendu) throws BusinessException{
		BusinessException businessException = new BusinessException();
		//this.validerNomArticle(nomArticle, businessException);
				
		if(!businessException.hasErreurs())
		{

			this.easyAuctionDAO.insertArticle(articleVendu);
		}
		else
		{
			throw businessException;
		}
	}
	
	public void ajouterUtilisateur(String pseudo,String prenom,
			String telephone,String codePostal,String motDePasse,
			String confirmation,String nom,String email,String rue,
			String ville) throws BusinessException{
		BusinessException businessException = new BusinessException();
		//this.validerNomArticle(nomArticle, businessException);
		Utilisateur utilisateur= null;		
		
		if(!businessException.hasErreurs())
		{
			utilisateur = new Utilisateur();
			utilisateur.setPseudo(pseudo);
			utilisateur.setPrenom(prenom);
			utilisateur.setTelephone(telephone);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setMotDePasse(motDePasse);
			utilisateur.setNom(nom);
			utilisateur.setEmail(email);
			utilisateur.setRue(rue);
			utilisateur.setVille(ville);
			this.easyAuctionDAO.insertUtilisateur(utilisateur);
			
		} else {
			throw businessException;
		}
	}
	

	public List<Utilisateur> selectionnerTousLesUtilisateurs() throws BusinessException{
		return this.easyAuctionDAO.selectAllUtilisateur();
	}
	
	public List<ArticleVendu> selectionnerTousLesArticles() throws BusinessException{
		return this.easyAuctionDAO.selectAllArticle();
	}
	
	public List<ArticleVendu> selectionnerTousLesArticlesByUser(int idUser) throws BusinessException {
		return this.easyAuctionDAO.selectAllArticleByUser(idUser);
	}
	
	public List<Enchere> selectionnerToutesLesEncheres() throws BusinessException{
		return this.easyAuctionDAO.selectAllEnchere();
	}
	

	public Categorie selectionnerCategorieById(int idCategorie) throws BusinessException{
		return this.easyAuctionDAO.selectCategorieById(idCategorie);
	}

	public void supprimerUtilisateur(int idUtilisateur) throws BusinessException{
		this.easyAuctionDAO.deleteUtilisateur(idUtilisateur);
	}

	/**
	 * @param noUtilisateur 
	 * 
	 */
	public void updateUtilisateur(int noUtilisateur, String pseudo,String prenom,
			String telephone,String codePostal,String motDePasse,
			String confirmation,String nom,String email,String rue,
			String ville) throws BusinessException {
		BusinessException businessException = new BusinessException();
		//this.validerNomArticle(nomArticle, businessException);
		Utilisateur utilisateur= new Utilisateur();		
		
		if(!businessException.hasErreurs())
		{
			utilisateur.setNoUtilisateur(noUtilisateur);
			utilisateur.setPseudo(pseudo);
			utilisateur.setPrenom(prenom);
			utilisateur.setTelephone(telephone);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setMotDePasse(motDePasse);
			utilisateur.setNom(nom);
			utilisateur.setEmail(email);
			utilisateur.setRue(rue);
			utilisateur.setVille(ville);
			this.easyAuctionDAO.updateUtilisateur(utilisateur);
			
		} else {
			throw businessException;
		}
		
	}

		public List<Categorie> selectionnerToutesCategories() throws BusinessException {
			return this.easyAuctionDAO.selectAllCategories();
		}

		public List<ArticleVendu> selectionnerTousLesArticlesByCategorie(int idCategorie) throws BusinessException {
			
			return this.easyAuctionDAO.selectAllArticleByCategorie(idCategorie);
		}

		/**
		 * @param idArticle 
		 * @return
		 * @throws BusinessException 
		 */
		public ArticleVendu selectionnerArticleById(int idArticle) throws BusinessException {
			
		 	List<Enchere> listeEnchereArticle = this.easyAuctionDAO.selectAllEncheresByArticle(idArticle);
		 	
			return this.easyAuctionDAO.selectArticleById(idArticle, listeEnchereArticle);
		}

		public List<Enchere> selectionnerTousLesEncheresByUser(int noUtilisateur) throws BusinessException {

			return this.easyAuctionDAO.selectAllEncheresByUser(noUtilisateur);
		}

		/**
		 * @param nouvelEnchere
		 * @throws BusinessException 
		 */
		public void ajouterEnchere(Enchere nouvelEnchere) throws BusinessException {
			
			this.easyAuctionDAO.insertEnchere(nouvelEnchere);
		}

		/**
		 * @param noArticle
		 * @return
		 * @throws BusinessException 
		 */
		public List<Enchere> selectionnerTousLesEncheresByArticle(int noArticle) throws BusinessException {
			return this.easyAuctionDAO.selectAllEncheresByArticle(noArticle);
		}

		/**
		 * @return
		 */

	
	/*
	public List<ListeCourse> selectionnerListes() throws BusinessException
	{
		return this.listeCourseDAO.selectAll();
	}
	
	
	

	public ListeCourse selectionnerListe(int idListeCourse) throws BusinessException {
		return this.listeCourseDAO.selectById(idListeCourse);
	}


	
	public ListeCourse ajouterListeEtArticle(String nomListe, String nomArticle) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		this.validerNomListe(nomListe, businessException);
		this.validerNomArticle(nomArticle, businessException);
		
		ListeCourse listeCourse=null;
		
		if(!businessException.hasErreurs())
		{
			listeCourse = new ListeCourse();
			listeCourse.setNom(nomListe);
		
			Article article = new Article(nomArticle.trim());
			listeCourse.getArticles().add(article);
			this.listeCourseDAO.insert(listeCourse);
		}
		else
		{
			throw businessException;
		}
		return listeCourse;
	}

	public void supprimerArticle(int idArticle) throws BusinessException{
		this.listeCourseDAO.deleteArticle(idArticle);
	}
	public void supprimerListeCourse(int idListeCourse) throws BusinessException {
		this.listeCourseDAO.delete(idListeCourse);
	}
	public void cocherArticle(int idArticle) throws BusinessException
	{
		this.listeCourseDAO.cocherArticle(idArticle);
	}
	public void decocherArticle(int idArticle) throws BusinessException
	{
		this.listeCourseDAO.decocherArticle(idArticle);
	}
	public void decocherListe(int idListeCourse) throws BusinessException
	{
		this.listeCourseDAO.decocherListeCourse(idListeCourse);
	}
	
	private void validerNomListe(String nomListe, BusinessException businessException) {
		if(nomListe==null || nomListe.trim().length()>50)
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_LISTE_NOM_ERREUR);
		}
	}

	private void validerNomArticle(String nomArticle, BusinessException businessException) {
		if(nomArticle==null || nomArticle.trim().length()>50)
		{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_ERREUR);
		}
	}
	
	 */
	

	
}
