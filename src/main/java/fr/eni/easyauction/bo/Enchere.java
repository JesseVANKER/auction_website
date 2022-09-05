/**
 * 
 */
package fr.eni.easyauction.bo;

import java.time.LocalDate;

/**
 * Classe en charge de 
 * Projet : auction_website3
 * Version : auction_website3 -V1.0
 * Date : 5 sept. 2022 - 16:18:04
 * @author mrousseau2022
 *
 */
public class Enchere {
	private LocalDate dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateur;
	private ArticleVendu articleVendu;
	/**
	 * Getter pour dateEnchere
	 * 
	 * @return the dateEnchere
	 */
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	/**
	 * Setter pour dateEnchere
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	/**
	 * Getter pour montantEnchere
	 * 
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}
	/**
	 * Setter pour montantEnchere
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	/**
	 * Getter pour utilisateur
	 * 
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	/**
	 * Setter pour utilisateur
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	/**
	 * Getter pour articleVendu
	 * 
	 * @return the articleVendu
	 */
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	/**
	 * Setter pour articleVendu
	 * @param articleVendu the articleVendu to set
	 */
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", utilisateur="
				+ utilisateur + ", articleVendu=" + articleVendu + "]";
	}
	/**
	 * Constructeur.
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param utilisateur
	 * @param articleVendu
	 */
	public Enchere(LocalDate dateEnchere, int montantEnchere, Utilisateur utilisateur, ArticleVendu articleVendu) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
		this.articleVendu = articleVendu;
	}
	public Enchere() {
		super();
		
	}
	
}
