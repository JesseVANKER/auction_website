/**
 * 
 */
package fr.eni.easyauction.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe en charge de 
 * Projet : auction_website3
 * Version : auction_website3 -V1.0
 * Date : 5 sept. 2022 - 16:17:22
 * @author mrousseau2022
 *
 */
public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private int etatVente;
	private Utilisateur utilisateur;
	private List<Enchere> ListeEnchere = new ArrayList<Enchere>();
	private Categorie categorie;
	private Retrait[] retrait = new Retrait[1];
	/**
	 * Getter pour noArticle
	 * 
	 * @return the noArticle
	 */
	public int getNoArticle() {
		return noArticle;
	}
	/**
	 * Setter pour noArticle
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	/**
	 * Getter pour nomArticle
	 * 
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}
	/**
	 * Setter pour nomArticle
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	/**
	 * Getter pour description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Setter pour description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Getter pour dateDebutEncheres
	 * 
	 * @return the dateDebutEncheres
	 */
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	/**
	 * Setter pour dateDebutEncheres
	 * @param dateDebutEncheres the dateDebutEncheres to set
	 */
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	/**
	 * Getter pour dateFinEncheres
	 * 
	 * @return the dateFinEncheres
	 */
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	/**
	 * Setter pour dateFinEncheres
	 * @param dateFinEncheres the dateFinEncheres to set
	 */
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	/**
	 * Getter pour miseAPrix
	 * 
	 * @return the miseAPrix
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}
	/**
	 * Setter pour miseAPrix
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	/**
	 * Getter pour prixVente
	 * 
	 * @return the prixVente
	 */
	public int getPrixVente() {
		return prixVente;
	}
	/**
	 * Setter pour prixVente
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	/**
	 * Getter pour etatVente
	 * 
	 * @return the etatVente
	 */
	public int getEtatVente() {
		return etatVente;
	}
	/**
	 * Setter pour etatVente
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
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
	 * Getter pour listeEnchere
	 * 
	 * @return the listeEnchere
	 */
	public List<Enchere> getListeEnchere() {
		return ListeEnchere;
	}
	/**
	 * Setter pour listeEnchere
	 * @param listeEnchere the listeEnchere to set
	 */
	public void setListeEnchere(List<Enchere> listeEnchere) {
		ListeEnchere = listeEnchere;
	}
	/**
	 * Getter pour categorie
	 * 
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	/**
	 * Setter pour categorie
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	/**
	 * Getter pour retrait
	 * 
	 * @return the retrait
	 */
	public Retrait[] getRetrait() {
		return retrait;
	}
	/**
	 * Setter pour retrait
	 * @param retrait the retrait to set
	 */
	public void setRetrait(Retrait[] retrait) {
		this.retrait = retrait;
	}
	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateur=" + utilisateur
				+ ", ListeEnchere=" + ListeEnchere + ", categorie=" + categorie + ", retrait="
				+ Arrays.toString(retrait) + "]";
	}
	/**
	 * Constructeur.
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param utilisateur
	 * @param listeEnchere
	 * @param categorie
	 * @param retrait
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int etatVente, Utilisateur utilisateur,
			List<Enchere> listeEnchere, Categorie categorie, Retrait[] retrait) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		ListeEnchere = listeEnchere;
		this.categorie = categorie;
		this.retrait = retrait;
	}
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int etatVente, Utilisateur utilisateur
			) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		
	}
	public ArticleVendu() {
		super();
		
		
	}
	
	public ArticleVendu( String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int etatVente, Utilisateur utilisateur
			) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		
	}
	
}
