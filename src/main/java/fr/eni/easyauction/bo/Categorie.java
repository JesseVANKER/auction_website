/**
 * 
 */
package fr.eni.easyauction.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe en charge de 
 * Projet : auction_website3
 * Version : auction_website3 -V1.0
 * Date : 5 sept. 2022 - 16:27:23
 * @author mrousseau2022
 *
 */
public class Categorie {
	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> ListeArticleVendu = new ArrayList<ArticleVendu>();
	/**
	 * Getter pour noCategorie
	 * 
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}
	/**
	 * Setter pour noCategorie
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	/**
	 * Getter pour libelle
	 * 
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * Setter pour libelle
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * Getter pour listeArticleVendu
	 * 
	 * @return the listeArticleVendu
	 */
	public List<ArticleVendu> getListeArticleVendu() {
		return ListeArticleVendu;
	}
	/**
	 * Setter pour listeArticleVendu
	 * @param listeArticleVendu the listeArticleVendu to set
	 */
	public void setListeArticleVendu(List<ArticleVendu> listeArticleVendu) {
		ListeArticleVendu = listeArticleVendu;
	}
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", ListeArticleVendu="
				+ ListeArticleVendu + "]";
	}
	/**
	 * Constructeur.
	 * @param noCategorie
	 * @param libelle
	 * @param listeArticleVendu
	 */
	public Categorie(int noCategorie, String libelle, List<ArticleVendu> listeArticleVendu) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		ListeArticleVendu = listeArticleVendu;
	}
	
	public Categorie() {
		super();
		
	}
}
