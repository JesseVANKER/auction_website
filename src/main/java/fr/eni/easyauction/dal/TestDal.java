/**
 * 
 */
package fr.eni.easyauction.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.easyauction.BusinessException;
import fr.eni.easyauction.bo.ArticleVendu;
import fr.eni.easyauction.bo.Utilisateur;

/**
 * Projet : auction_web
 * Date : 6 sept. 2022
 * Auteur : jvankerrebrouck2022
 */
public class TestDal {
	Utilisateur user = new Utilisateur();
	ArticleVendu article = new ArticleVendu("styloTest", "Description du stylo", LocalDate.now(), LocalDate.now(), 5, 10, 10, user);

	
	
	public void insertArticle(ArticleVendu article) throws BusinessException{
		BusinessException businessException = new BusinessException();
				

			this.EasyAuctionDAO.insert(listeCourse);

	}
	
	public static void main(String[] args) {
		

	}

}
