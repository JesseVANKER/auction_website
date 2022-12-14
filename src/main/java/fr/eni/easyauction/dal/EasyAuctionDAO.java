/**
 * 
 */
package fr.eni.easyauction.dal;

import java.util.List;

import fr.eni.easyauction.BusinessException;
import fr.eni.easyauction.bo.ArticleVendu;
import fr.eni.easyauction.bo.Categorie;
import fr.eni.easyauction.bo.Enchere;
import fr.eni.easyauction.bo.Utilisateur;

/**
 * Projet : auction_web
 * Date : 5 sept. 2022
 * Auteur : jvankerrebrouck2022
 */
public interface EasyAuctionDAO {

	/**
	 * @return
	 * @throws BusinessException
	 */
	
	 /* ----------- ARTICLES --------------*/
	List<ArticleVendu> selectAllArticle() throws BusinessException;
	void insertArticle(ArticleVendu articleVendu) throws BusinessException;
	void deleteArticle(int idArticle) throws BusinessException;
	ArticleVendu selectArticleById(int idArticle, List<Enchere> listeEnchereArticle) throws BusinessException;
	List<ArticleVendu> selectAllArticleByUser(int idUtilisateur) throws BusinessException;
	
	 /* ----------- UTILISATEURS --------------*/
	void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;
	void updateUtilisateur(Utilisateur utilisateur) throws BusinessException;
	void deleteUtilisateur(int idUtilisateur) throws BusinessException;
	List<Utilisateur> selectAllUtilisateur() throws BusinessException;


	


	/* ----------- ENCHERES --------------*/
	void insertEnchere(Enchere enchere) throws BusinessException;
	List<Enchere> selectAllEnchere() throws BusinessException;
	
	List<Enchere> selectAllEncheresByUser(int idUtilisateur) throws BusinessException;
	List<Enchere> selectAllEncheresByArticle(int idUtilisateur) throws BusinessException;
	
	/* ----------- CATEGORIES --------------*/
	Categorie selectCategorieById(int idUtilisateur) throws BusinessException;

	List<Categorie> selectAllCategories() throws BusinessException;

	
	List<ArticleVendu> selectAllArticleByCategorie(int idCategorie) throws BusinessException;
	/**
	 * @param idUtilisateur
	 * @return
	 * @throws BusinessException
	 */
	/**
	 * @param idUtilisateur
	 * @return
	 * @throws BusinessException
	 */
	/**
	 * @param idArticle
	 * @param listeEnchereArticle
	 * @return
	 * @throws BusinessException
	 */


	
	/**
	 * @param idArticle
<<<<<<< HEAD
=======
	 * @return
	 * @throws BusinessException
	 */
	/**
	 * @param idArticle
	 * @return
	 * @throws BusinessException
	 */
	/**
	 * M??thode en charge de
	 * @param idUtilisateur
	 * @return
	 * @throws BusinessException
	 */



    
	/**
	 * @param idArticle
	 * @throws BusinessException
	 */
	/**
	 * @param idArticle
	 * @throws BusinessException
	 */
	/**
	 * @return
	 * @throws BusinessException
	 */
	/**
	 * @param enchere
>>>>>>> branch 'master' of https://github.com/JesseVANKER/auction_website.git
	 * @throws BusinessException
	 */
	/**
	 * @param idArticle
	 * @throws BusinessException
	 */
	/**
	 * @return
	 * @throws BusinessException
	 */
	/**
<<<<<<< HEAD

	 * M??thode en charge de
	 * @return
	 * @throws BusinessException
	 */
	/**
	 * M??thode en charge de
	 * @param idEnchere
	 * @return
	 * @throws BusinessException
	 */
	/**
	 * M??thode en charge de
	 * @param idArticle
	 * @return
	 * @throws BusinessException
	 */

	/**
	 * M??thode en charge de
	 * @param idEnchere
	 * @return
	 * @throws BusinessException
	 */
	


	




	
	/*
	public void insert(ListeCourse listeCourse) throws BusinessException;
	public void delete(int id) throws BusinessException;
	public List<ListeCourse> selectAll() throws BusinessException;
	public ListeCourse selectById(int id) throws BusinessException;
	public void deleteArticle(int idArticle) throws BusinessException;
	public void cocherArticle(int idArticle) throws BusinessException;
	public void decocherArticle(int idArticle) throws BusinessException;
	public void decocherListeCourse(int listeCourse) throws BusinessException;
	*/
	
}
