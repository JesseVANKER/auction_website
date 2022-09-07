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
	private int noEnchere;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateur;
	private ArticleVendu articleVendu;
	
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	
	public int getMontantEnchere() {
		return montantEnchere;
	}
	
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	
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

	/**
	 * Constructeur.
	 * @param int1
	 * @param localDate
	 * @param int2
	 * @param int3
	 * @param int4
	 */
	public Enchere(int int1, LocalDate localDate, int int2, int int3, int int4) {
		// TODO Auto-generated constructor stub
	}
	public Enchere( LocalDate localDate, int montantUtilisateur, int no_article, int no_utilisateur) {
		// TODO Auto-generated constructor stub
	}
	
}
