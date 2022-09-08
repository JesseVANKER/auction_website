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
	
	public int getNoCategorie() {
		return noCategorie;
	}
	
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public List<ArticleVendu> getListeArticleVendu() {
		return ListeArticleVendu;
	}
	
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
	
	public Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	public Categorie() {
		super();
		
	}
}
