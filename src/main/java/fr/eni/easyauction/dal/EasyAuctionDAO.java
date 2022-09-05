/**
 * 
 */
package fr.eni.easyauction.dal;

import java.util.List;

import fr.eni.easyauction.BusinessException;

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
	List<ArticleVendu> selectAll() throws BusinessException;
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
