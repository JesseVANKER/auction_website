/**
 * 
 */
package fr.eni.easyauction.dal;

import java.util.List;

import fr.eni.easyauction.BusinessException;
import fr.eni.easyauction.bo.ArticleVendu;
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
	List<ArticleVendu> selectAllArticle() throws BusinessException;
	void insertArticle(ArticleVendu articleVendu) throws BusinessException;
	void updatePrixArticle(int prixVente, int idArticle) throws BusinessException;
	void deleteArticle(int idArticle) throws BusinessException;
	
	void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;
	void updateUtilisateur(Utilisateur utilisateur) throws BusinessException;
	void deleteUtilisateur(int idUtilisateur) throws BusinessException;
	Utilisateur selectUtilisateurById(int idUtilisateur) throws BusinessException;
	List<Utilisateur> selectAllUtilisateur() throws BusinessException;
	List<Enchere> selectAllEnchere() throws BusinessException;
	Enchere selectEnchereById(int idEnchere) throws BusinessException;
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
	 * Méthode en charge de
	 * @return
	 * @throws BusinessException
	 */
	/**
	 * Méthode en charge de
	 * @param idEnchere
	 * @return
	 * @throws BusinessException
	 */
	/**
	 * Méthode en charge de
	 * @param idArticle
	 * @return
	 * @throws BusinessException
	 */
	ArticleVendu selectArticleById(int idArticle) throws BusinessException;
	/**
	 * Méthode en charge de
	 * @param idEnchere
	 * @return
	 * @throws BusinessException
	 */
	List<Enchere> selectAllEnchereById(int idEnchere) throws BusinessException;
	




	
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
